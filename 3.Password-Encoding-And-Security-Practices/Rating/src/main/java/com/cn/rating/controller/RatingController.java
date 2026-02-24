package com.cn.rating.controller;

import com.cn.rating.dto.RatingRequest;
import com.cn.rating.entity.Rating;
import com.cn.rating.service.RatingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    //use to add new rating
    @PostMapping(path = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void addRating(@RequestBody RatingRequest ratingRequest) {
        ratingService.addRating(ratingRequest);
    }

    //use to update a existing rating
    @PutMapping (path = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateRating(@RequestBody RatingRequest ratingRequest) {
        ratingService.updateRating(ratingRequest);
    }

    //get rating bt id
    @GetMapping(path = "/id/{id}")
    @PreAuthorize("hasRole('NORMAL')")
    public Rating getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id);
    }

    //get all the ratings
    @GetMapping(path = "/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Rating> getAllRating() {
        return ratingService.getAllRating();
    }

    //delete rating by id
    @DeleteMapping(path = "/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}
