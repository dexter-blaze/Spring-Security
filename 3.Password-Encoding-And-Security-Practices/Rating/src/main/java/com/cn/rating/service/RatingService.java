package com.cn.rating.service;

import com.cn.rating.dto.RatingRequest;
import com.cn.rating.entity.Rating;
import com.cn.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;


    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).get();
    }

    public void addRating(RatingRequest ratingRequest) {
        Rating rating = new Rating();
        rating.setRating(ratingRequest.getRating());
        rating.setHotelId(ratingRequest.getHotelId());
        ratingRepository.save(rating);
    }

    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    public void updateRating(RatingRequest ratingRequest) {
        Rating rating = getRatingById(ratingRequest.getHotelId());
        rating.setRating(ratingRequest.getRating());
        ratingRepository.save(rating);
    }

	public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
	}
}
