package com.vox.voxconsumeapi.service;

import com.vox.voxconsumeapi.dto.RequestCreateUpdateOrganizersDTO;
import com.vox.voxconsumeapi.util.RestApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class OrganizersServices {
    @Autowired
    private RestApiUtil restApiUtil;

    @Value("${integration.organizers}")
    private String urlOrganizers;

    public Map<String, Object> getOrganizers(int page, int perPage, String token){
        String url = urlOrganizers + "?page="+page+"&"+"?perPage="+perPage;
        return restApiUtil.getExtObjectWithToken(url, Map.class, token);
    }

    public Map<String, Object> createOrganizers(RequestCreateUpdateOrganizersDTO requestCreateUpdateOrganizersDTO, String token){
        return restApiUtil.postExtObjectWithToken
                (urlOrganizers, Map.class, token, requestCreateUpdateOrganizersDTO);
    }

    public Map<String, Object> getOrganizersById(int id, String token){
        String url = urlOrganizers + "/"+ id;
        return restApiUtil.getExtObjectWithToken(url, Map.class, token);
    }

    public Map<String, Object> updateOrganizers(int id,
                                                RequestCreateUpdateOrganizersDTO requestCreateUpdateOrganizersDTO,
                                                String token){
        String url = urlOrganizers + "/"+ id;
        return restApiUtil.putExtObjectWithToken
                (url, Map.class, token, requestCreateUpdateOrganizersDTO);
    }

    public Map<String, Object> deleteOrganizers(int id,String token){
        String url = urlOrganizers + "/"+ id;
        return restApiUtil.deleteExtObjectWithToken
                (url, Map.class, token);
    }

}
