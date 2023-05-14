package com.vox.voxconsumeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUpdateOrganizersDTO {
    private String organizerName;
    private String imageLocation;
}
