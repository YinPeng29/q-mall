package com.bays.service.token.impl;

import com.bays.service.token.TokenService;
import com.bays.utils.SysUtil;

public class TokenServiceImpl implements TokenService {
    public String getToken(String signKey) {
        String token = SysUtil.strUUID(signKey);
        return token;
    }
}
