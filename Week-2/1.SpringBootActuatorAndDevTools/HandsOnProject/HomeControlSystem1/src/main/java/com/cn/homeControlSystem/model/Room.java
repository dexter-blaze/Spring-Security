package com.cn.homeControlSystem.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Add lombok annotations for auto generating constructors, getters and setters.
 * Add proper annotations to make this class as entity.
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    //add proper annotations for mapping a property as id.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
