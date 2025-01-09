package com.bih.nic.attendenceapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendenceRequestDto {
	private String staffId;
	private String locId;
    private String image;
    private String latitude;
    private String longitude;
    private String inOut;
}
