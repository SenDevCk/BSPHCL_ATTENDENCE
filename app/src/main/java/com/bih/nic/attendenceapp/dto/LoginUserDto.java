package com.bih.nic.attendenceapp.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Chandan Singh on 1/8/2025.
 */
@Data
@Builder
public class LoginUserDto {
    private String userId;
    private String password;
}
