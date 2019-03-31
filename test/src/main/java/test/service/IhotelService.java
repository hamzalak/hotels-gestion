package test.service;

import java.util.List;
import java.util.Map;

import test.dto.* ;

public interface IhotelService  {
	
    public void addHotel(Hotel hotel) throws Exception  ; 
    
	public void deleteHotel(Integer id )throws Exception ;
	
	public void saveOrUpdate(Hotel hotel)throws Exception ;
	
	public List<Hotel> selectAllHotels() throws Exception ;
	
	public Hotel selectHotel (Integer id) throws Exception;

	Map<Integer, List<Hotel>> hotelByStar(Integer id); 
	
}
