package com.vox.voxconsumeapi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vox.voxconsumeapi.dto.RequestCreateUpdateOrganizersDTO;
import com.vox.voxconsumeapi.dto.RequestLoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
public class OrganizerTest {

    @Autowired
    private MockMvc mvc;

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


    @Test
    public void organizers() throws Exception {
        String uri = "/api/v1/users/login";
        RequestLoginDTO requestLoginDTO = new RequestLoginDTO();
        requestLoginDTO.setEmail("dicky@gmail.com");
        requestLoginDTO.setPassword("D!cky123");
        String inputJson = mapToJson(requestLoginDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, Object> resultLogin = mapFromJson(content, Map.class);
        String resultToken = resultLogin.get("token").toString();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("token", resultToken);

        int statusLogin = mvcResult.getResponse().getStatus();
        assertEquals(200, statusLogin);

        RequestCreateUpdateOrganizersDTO requestCreateUpdateOrganizersDTO = new RequestCreateUpdateOrganizersDTO();
        requestCreateUpdateOrganizersDTO.setOrganizerName("sichibukai");
        requestCreateUpdateOrganizersDTO.setImageLocation("sichibukai.jpg");

        String jsonCreate = mapToJson(requestCreateUpdateOrganizersDTO);
        String uriCreateOrganize = "/api/v1/organizers";
        MvcResult mvcResultOrganizeCreate = mvc.perform(MockMvcRequestBuilders.post(uriCreateOrganize)
                .session(session)
                        .content(jsonCreate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusCreateOrganize = mvcResultOrganizeCreate.getResponse().getStatus();
        assertEquals(200, statusCreateOrganize);
        String resultCreateOrganize = mvcResultOrganizeCreate.getResponse().getContentAsString();
        Map<String, Object> mapOrganize = mapFromJson(resultCreateOrganize, Map.class);

        String uriOrganizeById = "/api/v1/organizers/"+Integer.parseInt(mapOrganize.get("id").toString());
        MvcResult mvcResultOrganizeById = mvc.perform(MockMvcRequestBuilders.get(uriOrganizeById)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusGetOrganizeById = mvcResultOrganizeById.getResponse().getStatus();
        assertEquals(200, statusGetOrganizeById);

        String uriOrganizeList = "/api/v1/organizers";
        MvcResult mvcResultOrganizeList = mvc.perform(MockMvcRequestBuilders.get(uriOrganizeList)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusGetOrganizeBList = mvcResultOrganizeList.getResponse().getStatus();
        assertEquals(200, statusGetOrganizeBList);

        RequestCreateUpdateOrganizersDTO requestUpdateOrganizersDTO = new RequestCreateUpdateOrganizersDTO();
        requestUpdateOrganizersDTO.setOrganizerName("yonkou");
        requestUpdateOrganizersDTO.setImageLocation("yonkou.jpg");

        String jsonUpdate = mapToJson(requestUpdateOrganizersDTO);
        String uriUpdateOrganize = "/api/v1/organizers/"+Integer.parseInt(mapOrganize.get("id").toString());;
        MvcResult mvcResultOrganizeUpdate = mvc.perform(MockMvcRequestBuilders.put(uriUpdateOrganize)
                .session(session)
                .content(jsonUpdate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusUpdateOrganize = mvcResultOrganizeUpdate.getResponse().getStatus();
        assertEquals(200, statusUpdateOrganize);

        String uriDeleteOrganize = "/api/v1/organizers/"+Integer.parseInt(mapOrganize.get("id").toString());;
        MvcResult mvcResultOrganizeDelete = mvc.perform(MockMvcRequestBuilders.delete(uriDeleteOrganize)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusDeleteOrganize = mvcResultOrganizeDelete.getResponse().getStatus();
        assertEquals(200, statusDeleteOrganize);
    }
}
