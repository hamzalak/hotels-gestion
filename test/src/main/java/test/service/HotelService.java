package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.dao.HotelRepository;
import test.dto.Hotel;

@Service
public class HotelService implements IhotelService {

	@Autowired
	public HotelRepository hotelRepo ; 
	 
	@Override
	public void addHotel(Hotel hotel) throws Exception {
		hotelRepo.save(hotel) ;
	}

	@Override
	public void deleteHotel(Integer id) throws Exception {
		hotelRepo.deleteById(id);
	}

	@Override
	public void saveOrUpdate(Hotel hotel) throws Exception {
		 
		hotelRepo.save(hotel) ; 
		
	}

	@Override
	public List<Hotel> selectAllHotels() {
		
		List<Hotel>  hotels = hotelRepo.findAll() ; 
		
		return hotels ;
	}

	@Override
	public Hotel selectHotel(Integer id) {
		
		Hotel hotel = hotelRepo.getOne(id);
		
		return hotel ;
	}
	
	@Override 
	public Map<Integer,List<Hotel>> hotelByStar(Integer id){
		
		Map map1  = new HashMap<Integer,Hotel>() ;
		
        List<Hotel> list = hotelRepo.findAll().stream()
        		                                      .filter(item-> item.getStars() == id )
        		                                      .collect(Collectors.toList()) ; 
        map1.put(id, list)  ; 
        
		return  map1 ; 
	}

	
}

