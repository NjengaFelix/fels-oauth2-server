/*
 * Copyright 2020-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fels.fels_oauth2_server.federation;

// tag::imports[]

import com.fels.fels_oauth2_server.repositories.UserDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.function.Consumer;
// end::imports[]

/**
 * Example {@link Consumer} to perform JIT provisioning of an {@link OAuth2User}.
 *
 * @author Steve Riesenberg
 * @since 1.1
 */
// tag::class[]
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryOAuth2UserHandler.class);

	private final UserDetailRepository userDetailRepository;

	public UserRepositoryOAuth2UserHandler(UserDetailRepository userDetailRepository) {
		this.userDetailRepository = userDetailRepository;
	}

	@Override
	public void accept(OAuth2User user) {
		LOGGER.info("Passed here");
		// Capture user in a local data store on first authentication
		/*if (this.userRepository.findByUsername(user.getName()) == null) {
			this.userRepository.save(user);
		}*/
			LOGGER.info("Saving first-time user: name=" + user.getName() + ", claims=" + user.getAttributes() + ", authorities=" + user.getAuthorities());
	}

//	static class UserRepository {
//
//		private final Map<String, OAuth2User> userCache = new ConcurrentHashMap<>();
//
//		public OAuth2User findByName(String name) {
//			return this.userCache.get(name);
//		}
//
//		public void save(OAuth2User oauth2User) {
//			this.userCache.put(oauth2User.getName(), oauth2User);
//		}
//
//	}

}
// end::class[]
