package com.vox.voxconsumeapi.controller;

import com.vox.voxconsumeapi.dto.RequestCreateUpdateOrganizersDTO;
import com.vox.voxconsumeapi.service.OrganizersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/organizers")
public class OrganizersController {

    @Autowired
    OrganizersServices organizersServices;

    @GetMapping
    public ResponseEntity<Object> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "perPage", defaultValue = "1") int perPage,
                                       HttpSession httpSession){
        String token = (String) httpSession.getAttribute("token");
        return ResponseEntity.ok(organizersServices.
                getOrganizers(page, perPage, token));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RequestCreateUpdateOrganizersDTO requestCreateUpdateOrganizersDTO,
                                       HttpSession httpSession){
        String token = (String) httpSession.getAttribute("token");
        return ResponseEntity.ok(organizersServices.
                createOrganizers(requestCreateUpdateOrganizersDTO, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> byId(@PathVariable("id") int id,
                                       HttpSession httpSession){
        String token = (String) httpSession.getAttribute("token");
        return ResponseEntity.ok(organizersServices.
                getOrganizersById(id, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id,
                                       @RequestBody RequestCreateUpdateOrganizersDTO requestCreateUpdateOrganizersDTO,
                                       HttpSession httpSession){
        String token = (String) httpSession.getAttribute("token");
        return ResponseEntity.ok(organizersServices.
                updateOrganizers(id, requestCreateUpdateOrganizersDTO, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id,
                                         HttpSession httpSession){
        String token = (String) httpSession.getAttribute("token");
        return ResponseEntity.ok(organizersServices.
                deleteOrganizers(id, token));
    }

}
