package test.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController ;

import test.dto.Room;
import test.service.IRoomService;
import test.service.IhotelService;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomRest implements IRoomRest {

	private static Logger LOGG = LogManager.getLogger(RoomRest.class);


@Autowired
IRoomService roomService ; 
@Autowired
IhotelService hotelService ; 


@GetMapping("/all")
@Override
//TODO
public List<Room> getAllRooms(){
	
	List<Room> rooms;
	
	try {
		rooms = roomService.selectAllRooms();
	} catch (Exception e) {
		rooms = null ; 
		e.printStackTrace();
		
	}
	
	return rooms ; 
}

@GetMapping("rooms/{id}")
@Override
//TODO
public List<Room> getRoomsOfHotel(@PathVariable Integer id){
  
	 try {
		List<Room>  rooms = roomService.selectRoomsOfHotel(id) ;
		return rooms ; 
	} catch (Exception e) {
		e.printStackTrace();
		return null ; 

	} 
}

//TODO 
@DeleteMapping("/{id}")
@Override
public void deleteRoom(@PathVariable Integer id ) {
	
	  try {
		roomService.deleteRoom(id);
	} catch (Exception e) {
		
		e.printStackTrace();
	}  
   	
}
//TODO
@PostMapping("/{id}")
@Override
public void createRoom(@RequestBody Room room , @PathVariable Integer id) {
	  
	 Room r = new Room() ; 
	try {
		r.setDateDispo(room.getDateDispo()) ;
		LOGG.info("\n\n\n\n\n"+room);
		r.setPrix(room.getPrix()) ; 
		r.setHotel(hotelService.selectHotel(id) ) ; 
		roomService.addRoom(r);
	} catch (Exception e) {
		e.printStackTrace();
	}  
	
}
//TODO
@PutMapping("/{id}")
@Override
public void updateRoom(@RequestBody Room room , @PathVariable Integer id) {
	  
	try {
		Room r = roomService.selectRoom(id) ;
		r.setDateDispo(room.getDateDispo());
		r.setPrix(room.getPrix());
		roomService.saveOrUpdate(r);
		
	} catch (Exception e) {
		e.printStackTrace();
	}  
	
}

	
}
