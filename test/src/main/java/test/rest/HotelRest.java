package test.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.dto.Hotel;
import test.service.HotelService;
import test.service.IManagerService;
import test.service.IRoomService;
import test.service.IhotelService;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hotel")
@RestController
public class HotelRest implements IHotelRest  {

	private static Logger LOGG = LogManager.getLogger(RoomRest.class);

@Autowired
	  IRoomService roomService ;
@Autowired
      IManagerService managerService ; 
	
@Autowired
   IhotelService hotelService ;  
   
//TODO 
@GetMapping("/all")
@Override
	public List<Hotel> listDesHotels(){
		
	List<Hotel> hotels ; 
		try { 
	     
		hotels = hotelService.selectAllHotels() ; 
				 
	   return hotels ; 
	    		
	   
		}catch(Exception e) {
			e.printStackTrace();
			return null ; 
		}
	}
/**
 * Selection d'un hotel {@link test.service.IhotelService}
 */
//TODO 
@GetMapping("/{id}")
@Override 
public Hotel getHotelById(@PathVariable("id") Integer id ) {

	
	
	try {

		Hotel hotel = hotelService.selectHotel(id) ; 		 

		return hotel ;
		 
	} catch (Exception e1) {
		e1.printStackTrace();
		
		return null ; 
	} 
	   
	 
 }
//TODO 
@Override 
@PostMapping("/createHotel")
 public ResponseEntity<Integer> createHotel(@RequestBody Hotel hotel) {
	
	///Je suis arrivé ici //
	Integer id  ; 
   try {
//	roomService.addRoom(hotel.getRooms().get(hotel.getRooms().size()-1));   
	managerService.addManager(hotel.getManager()); 
	hotelService.saveOrUpdate(hotel);
	id = hotel.getId() ; 
	return new ResponseEntity<>(id,HttpStatus.OK) ; 
} catch (Exception e) {
	e.printStackTrace();
	return null ; 
} 
   
   }
// TODO 
@Override
@DeleteMapping("/{id}")
public void deleteHotel(@PathVariable("id") Integer id) {
	
	 System.out.println("\n\n\n\n\n\n"+ "fofo") ; 
	 System.out.println("\n\n\n\n\n\n"+id) ; 
	
 	  
	try {
		hotelService.deleteHotel(id);
	} catch (Exception e) {
		e.printStackTrace();
	}
     
}

//TODO 
@GetMapping("/etoile/{id}")
public Map<Integer,List<Hotel>>   hotelEtoile(@PathVariable Integer id){
	
	
	
	return hotelService.hotelByStar(id) ;  

}

//TODO
@PutMapping("/{id}")
@Override
public void updateHotel(@RequestBody Hotel hotel, @PathVariable Integer id ) {
	
        Hotel hot;
		try {
			hot = hotelService.selectHotel(id);
			 hot.setAdresse(hotel.getAdresse());
		     hot.setNom(hotel.getNom());
		     hot.setStars(hotel.getStars());
		     hotelService.saveOrUpdate(hot);
		} catch (Exception e) {
			e.printStackTrace();
		} 
       
	
}

}
    

