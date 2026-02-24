package com.codingNinjas.taxEase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxRecordDto {
    private String taxYear;
    private int income;
    private int deductions;
}
