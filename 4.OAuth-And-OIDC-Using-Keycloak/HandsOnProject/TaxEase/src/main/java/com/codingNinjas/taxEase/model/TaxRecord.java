package com.codingNinjas.taxEase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaxRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // Many TaxRecords can belong to one User
    @JoinColumn(name = "user_id") // Foreign key column in TaxRecord table
    @JsonIgnoreProperties("taxRecords")
    private User user;
    private String taxYear;
    private int income;
    private int deductions;
    private boolean isFilingApproved;
}
