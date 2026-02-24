package com.cn.homeControlSystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

/**
 * Add lombok annotations for auto generating constructors, getters and setters.
 * Add proper annotations to make this class as entity.
 **/
@Entity
@Data //required equal hashcode
@NoArgsConstructor
@AllArgsConstructor
public class SmartDevice {

    //add proper annotations for mapping a property as id.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String status;
    private Integer roomId;
}
