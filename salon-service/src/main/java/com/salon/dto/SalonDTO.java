package com.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonDTO {

    private Long id;
    private String name;
    private List<String> images;
    private String address;
    private String phoneNumber;
    private String city;
    private Long ownerId;
    private LocalTime openTime;
    private LocalTime closeTime;
}
