package com.cn.hotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
	private Long id;

    @Column
	private String name;
    @Column
	private long rating;
    @Column
	private String city;

    public Hotel() {

    }
	
	public Hotel(Long id, String name, long rating, String city) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", rating=" + rating + ", city=" + city + "]";
	}

	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
