package test.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import test.dto.Hotel;

public interface IHotelRest {
	
	public List<Hotel> listDesHotels() ;

	//ResponseEntity<String> deleteHotel(Integer id);

	Hotel getHotelById(Integer id);

	void deleteHotel(Integer id);

	public ResponseEntity<Integer> createHotel(Hotel hotel);

	void updateHotel(Hotel hotel, Integer id);


}
