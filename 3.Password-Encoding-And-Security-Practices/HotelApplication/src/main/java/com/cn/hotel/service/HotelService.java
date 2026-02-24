package com.cn.hotel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.hotel.communicator.RatingServiceCommunicator;
import com.cn.hotel.dal.HotelRepository;
import com.cn.hotel.dto.HotelRequest;
import com.cn.hotel.dto.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.hotel.model.Hotel;

@Service
public class HotelService {

    public final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Autowired
    RatingServiceCommunicator communicator;

	
	public void createHotel(HotelRequest hotelRequest) {
		
		Hotel hotel = new Hotel();
        hotel.setName(hotelRequest.getName());
        hotel.setCity(hotelRequest.getCity());
        hotel.setRating(hotelRequest.getRating());

        hotelRepository.save(hotel);
	}


	public Hotel getHotelById(String  authorizationHeader, Long id) {

        String jwtToken = authorizationHeader.split(" ")[1];
		Hotel hotel =  hotelRepository.findById(id).get();
        RatingResponse ratingResponse = communicator.getRating(id.toString(), jwtToken);
        hotel.setRating(ratingResponse.getRating());
        return hotel;
	}


	public List<Hotel> getAllHotels() {
		
		return hotelRepository.findAll();
	}


	public void deleteHotelById(Long id) {
		hotelRepository.deleteById(id);
	}


	public void updateHotel(Hotel updatedHotel) {
		//1. Get the previous data of the hotel
		//2. remove this old data from list
		//3. Add the updated data to the list.
		
//		Hotel existingHotel= getHotelById(updatedHotel.getId());

		
		
		
		//4. update the previous data with new data.
		//5. Update the map with new data.
		
		Map<String,Long> updatedRating = new HashMap<>();
//		updatedRating.put(updatedHotel.getId(), updatedHotel.getRating());
		
	}

	
	
}
