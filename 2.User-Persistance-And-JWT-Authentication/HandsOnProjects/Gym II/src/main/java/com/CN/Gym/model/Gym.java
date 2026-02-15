package com.CN.Gym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*
    This is the entity class, complete this class by doing the following:
    a. Add the required annotations for making this class an entity.
    b. Add the required lombok annotations for getter, setter and constructors
 */
@Entity
@Table(name = "gym")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Long contactNo;
    private String membershipPlans;
    private String facilities;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JsonManagedReference
    List<User> members;
}
