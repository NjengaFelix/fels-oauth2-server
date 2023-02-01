package com.fels.fels_oauth2_server.services;

import com.fels.fels_oauth2_server.entities.AuthMethod;
import com.fels.fels_oauth2_server.entities.Client;
import com.fels.fels_oauth2_server.entities.ClientDetail;
import com.fels.fels_oauth2_server.repositories.ClientDetailRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomClientService implements RegisteredClientRepository {
    private final ClientDetailRepository clientDetailRepository;

    public CustomClientService(ClientDetailRepository clientDetailRepository) {
        this.clientDetailRepository = clientDetailRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }

    /*public static ClientDetail from(RegisteredClient registeredClient) {
        ClientDetail clientDetail = new ClientDetail();
        clientDetail.setClient();
        clientDetail.setAuthMethod();
        clientDetail.setScope();
        clientDetail.setUri();
        clientDetail.setGrantType();
    }*/
}
