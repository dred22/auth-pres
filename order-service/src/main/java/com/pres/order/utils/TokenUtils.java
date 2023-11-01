package com.pres.order.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.util.UUID;

@UtilityClass
public class TokenUtils {
    public UUID getUserId(Jwt jwt){
        String sub = (String) jwt.getClaims().get("sub");
        Assert.notNull(sub, "Subject can not be null");
        return UUID.fromString(sub);
    }
}
