package com.vox.voxconsumeapi.controller;

import com.vox.voxconsumeapi.dto.RequestCreateUserDTO;
import com.vox.voxconsumeapi.dto.RequestLoginDTO;
import com.vox.voxconsumeapi.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLoginDTO requestLoginDTO, HttpSession httpSession){
        Map<String, Object> result = userServices.login(requestLoginDTO);
        httpSession.setAttribute("token", result.get("token"));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RequestCreateUserDTO requestCreateUserDTO){
        return ResponseEntity.ok(userServices.create(requestCreateUserDTO));
    }

}
