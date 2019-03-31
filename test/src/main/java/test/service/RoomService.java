package test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.dao.RoomRepository;
import test.dto.Room;

@Service
public class RoomService implements IRoomService {

	@Autowired
	public RoomRepository RoomRepo ; 
	 
	@Override
	public void addRoom(Room room) throws Exception {
		RoomRepo.save(room) ; 
	}

	@Override
	public void deleteRoom(Integer id) throws Exception {
		RoomRepo.deleteById(id);
	}

	@Override
	public void saveOrUpdate(Room room) throws Exception {
		 
		RoomRepo.save(room) ; 
		
	}

	@Override
	public List<Room> selectAllRooms() {
		
		List<Room>  rooms = RoomRepo.findAll() ; 
		
		return rooms ;
	}

	@Override
	public Room selectRoom(Integer id) {
		
		Room room = RoomRepo.getOne(id) ;
		
		return room ;
	}

	@Override
	public List<Room> selectRoomsOfHotel(Integer id){
		
		
	List<Room> rooms = RoomRepo.getRoomsOfHotel(id) ; 
	
//	   List<Room> rooms = RoomRepo.findAll().stream()
//			                       .filter(obj -> obj.getHotel().getId() == id)
//			                        .collect(Collectors.toList()) ; 
		
	  return rooms ; 
	}
//	
}

