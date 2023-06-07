package com.fels.fels_oauth2_server.services;

import com.fels.fels_oauth2_server.entities.*;
import com.fels.fels_oauth2_server.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomClientService implements RegisteredClientRepository {
    private final ClientRepository clientRepository;
    private final ClientDetailRepository clientDetailRepository;
    private final AuthMethodRepository authMethodRepository;
    private final ScopeRepository scopeRepository;
    private final UriRepository uriRepository;
    private final GrantTypeRepository grandTypeRepository;

    public CustomClientService(ClientRepository clientRepository, ClientDetailRepository clientDetailRepository, AuthMethodRepository authMethodRepository, ScopeRepository scopeRepository, UriRepository uriRepository, GrantTypeRepository grandTypeRepository) {
        this.clientRepository = clientRepository;
        this.clientDetailRepository = clientDetailRepository;
        this.authMethodRepository = authMethodRepository;
        this.scopeRepository = scopeRepository;
        this.uriRepository = uriRepository;
        this.grandTypeRepository = grandTypeRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        AuthMethod authMethodByName = findAuthMethodByName(
                registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .findAny()
                        .get()
                        .getValue()
        );
        Scope scopeByName = findScopeByName(
                registeredClient.getScopes()
                        .stream()
                        .findAny()
                        .get()
        );
        Uri uriByName = findUriByName(
                registeredClient.getRedirectUris()
                        .stream()
                        .findAny()
                        .get()
        );
        GrantType grantTypeByName = findGrantTypeByName(
                registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .findAny()
                        .get()
                        .getValue()
        );

        clientDetailRepository.save(new ClientDetail(
                new Client(registeredClient.getClientId(), registeredClient.getClientSecret()),
                authMethodByName,
                scopeByName,
                uriByName,
                grantTypeByName
        ));
    }

    @Override
    public RegisteredClient findById(String id) {
        var client =
                clientRepository.findById(Long.valueOf(id))
                        .orElseThrow();
        return from(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client =
                clientRepository.findByClientId(clientId)
                        .orElseThrow();
        return from(client);
    }

    public AuthMethod findAuthMethodByName(String authMethod) {
        return authMethodRepository.findByName(authMethod).orElseThrow();
    }

    public Scope findScopeByName(String scope) {
        return scopeRepository.findByName(scope).orElseThrow();
    }

    public Uri findUriByName(String redirectUri) {
        return uriRepository.findByName(redirectUri).orElseThrow();
    }

    public GrantType findGrantTypeByName(String grantType) {
        return grandTypeRepository.findByName(grantType).orElseThrow();
    }

    public static RegisteredClient from(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())

                //Authentication methods
                .clientAuthenticationMethod(
                        client.getClientDetails().stream()
                                .map(a -> new ClientAuthenticationMethod(a.getAuthMethod().getName()))
                                .findFirst()
                                .orElseThrow(null)
                )
                //Scopes
                .scopes(scope -> {
                    Set<String> clientScopes = client.getClientDetails().stream()
                            .map(s -> s.getScope().getName())
                            .collect(Collectors.toSet());
                    scope.addAll(clientScopes);
                })
                //Redirect Uris
                .redirectUris(redirectUris -> {
                    Set<String> uris = client.getClientDetails()
                            .stream()
                            .map(uri -> uri.getUri().getName())
                            .collect(Collectors.toSet());
                    redirectUris.addAll(uris);
                })
                //Grant types
                .authorizationGrantTypes(authorizationGrantTypes -> {
                    Set<AuthorizationGrantType> grantTypes = client.getClientDetails().stream()
                            .map(ag -> new AuthorizationGrantType(ag.getGrantType().getName()))
                            .collect(Collectors.toSet());
                    authorizationGrantTypes.addAll(grantTypes);
                })
                //Token settings
                .tokenSettings(
                        TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofSeconds(client.getAccessTokenTimeToLive()))
                                .build()
                )
                .build();
    }

}
