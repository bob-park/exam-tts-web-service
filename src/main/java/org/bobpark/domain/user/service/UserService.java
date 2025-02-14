package org.bobpark.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import org.bobpark.common.auth.AuthUtils;
import org.bobpark.domain.user.feign.UserFeignClient;
import org.bobpark.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFeignClient userClient;

    public UserResponse me(OidcUser user) {

        String uniqueId = AuthUtils.getUniqueId(user);

        UserResponse result = userClient.getById(uniqueId);

        return result.toBuilder()
            .build();
    }

}
