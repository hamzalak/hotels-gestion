package test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.dto.Hotel;
import test.dto.Room;

//CREATION DES TABLES DES CLASSE DE LA DTO == MAPPING

public interface RoomRepository  extends JpaRepository<Room,Integer> {
	  



@Query(value = "SELECT * FROM  room  r  where r.hotel_id = :id", 
nativeQuery=true)
public List<Room> getRoomsOfHotel(Integer id ) ; 


//@Query(value = "SELECT u FROM Hotel u")
//public List<Hotel> getMyHotels() ; 
}