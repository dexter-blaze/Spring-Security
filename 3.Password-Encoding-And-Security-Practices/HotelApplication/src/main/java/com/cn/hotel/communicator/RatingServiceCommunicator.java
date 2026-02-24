package com.cn.hotel.communicator;

import com.cn.hotel.dto.RatingResponse;
import com.cn.hotel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RatingServiceCommunicator {

    public final RestTemplate restTemplate;

    public RatingServiceCommunicator(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void addRating(Map<String,Long> ratingsMap) {
        String url;

    }

    public RatingResponse getRating (String id, String jwtToken) {
        String url = "http://localhost:8081/rating/id/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<RatingResponse> ratingResponse =
                restTemplate.exchange(url+id, HttpMethod.GET,requestEntity, RatingResponse.class);
        return ratingResponse.getBody();
    }

    public void updateRating(Map<String,Long> ratingsMap) {

        String url;
    }

    public void deleteRating(String id){
        String url;
    }
}
