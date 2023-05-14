package com.vox.voxconsumeapi.service;

import com.vox.voxconsumeapi.dto.RequestCreateUserDTO;
import com.vox.voxconsumeapi.util.RestApiUtil;
import com.vox.voxconsumeapi.dto.RequestLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServices {

    @Autowired
    private RestApiUtil restApiUtil;

    @Value("${integration.user.login}")
    private String urlLogin;

    @Value("${integration.user.create}")
    private String urlCreate;


    public Map<String, Object> login(RequestLoginDTO requestLoginDTO){
        Map<String, Object> result = restApiUtil.postExtObject(urlLogin, Map.class, requestLoginDTO);
        log.debug("result login{} ", result);
        return result;
    }

    public Map<String, Object> create(RequestCreateUserDTO requestCreateUserDTO){
        Map<String, Object> result = restApiUtil.postExtObject(urlCreate, Map.class, requestCreateUserDTO);
        log.debug("result create{} ", result);
        return result;
    }

}
