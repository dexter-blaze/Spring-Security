package com.cn.homeControlSystem.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Add the lombok annotations a for auto generating constructors, getters and setters.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private String name;
    private String type;
    private String status;
    private Integer roomId;
}
