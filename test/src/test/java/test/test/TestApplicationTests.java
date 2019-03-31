package test.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.test.context.junit4.SpringRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import test.dto.DateUtils;
import test.dto.Hotel;
import test.dto.Manager;
import test.dto.Room;
import test.service.IManagerService;
import test.service.IRoomService;
import test.service.IhotelService;

@RunWith(SpringRunner.class)
@SpringBootTest
/**** important le lien entre la classe Main et la classe Test existe(d'aillieur le nom de la classe test est TestApplicationTests) 
 * il faut comprendre ce****/
/** Lorsqu'on fait build (clean install) Maven créer les tables ***/
public class TestApplicationTests {
	
	private static Logger LOGG = LogManager.getLogger(TestApplicationTests.class);
	
	public List<Hotel> hotels = new ArrayList<>(); 
	
 	@Autowired
	private IManagerService managerService;
	@Autowired
 	private IhotelService hotelService;
	@Autowired 
	private IRoomService roomService ; 
	
   //	@Before //Before s'execute Pour chaque @test 
	// Commentaire par rapport à l'absence de transactional (ici fermeture et ouverture de la session)
	//@Test
	@Ignore
	public void insertionId() {
    	for (int i = 0; i < 10; i++) {

            Hotel hotel = new Hotel();
            hotel.setNom("Ibis__" + i);
            hotel.setAdresse(i + " rue d'exemple");
            hotel.setStars(5 % (i + 1));

            try {
                hotelService.saveOrUpdate(hotel);
                hotels.add(hotel);
                LOGG.info("hotel has been inserted");
                Assert.assertNotNull(hotel.getId());
            } catch (Exception e) {
                LOGG.error("can'nt insert hotel to the database", e);
            }
        }
    	
    }
	    
		 //@Test
		 @Ignore
	    public void deleteTest() {
	        try {
	            hotels = hotelService.selectAllHotels();
	            for (Hotel hot : hotels) {
	                if (hot.getStars() < 5) {
	                    hotelService.deleteHotel(hot.getId());
	                }
	            }

	            System.out.println("----------------HOTELS-----------------------" + hotels);

	            hotels = hotelService.selectAllHotels();
	            for (Hotel hotel : hotels) {
	                Assert.assertEquals(hotel.getStars(), Integer.valueOf(5));
	            }

	        } catch (Exception ex) {
	            LOGG.error("select all failed", ex);
	        }

	    }
		@Ignore
	    public void updateTest() {
	        try {
	            hotels = hotelService.selectAllHotels();
	            if (!hotels.isEmpty()) {
	                Hotel h = hotels.get(0);
	                h.setNom("budjet");
	                hotelService.saveOrUpdate(h);
	            }
	        } catch (Exception e) {
	            LOGG.error("update hotel failed", e);

	        }

	}

		
	
	//TODO insertion des rooms	
	@Ignore
	public void insertRooms() throws ParseException {	 
	 
	 
	 try {
	 
	 List<Hotel> hotels = hotelService.selectAllHotels() ; 
	 
	 
	 int j = 0  ; 
	 
	 for(int i = 1 ; i < 26 ; i+=5) {
	      
		 Room room = new Room() ; 
		 room.setPrix(15.15d + i);
		 room.setDateDispo(DateUtils.parseDate(i+"/"+"01/2018"));
		  
		 try { 
			 
		   
           room.setHotel(hotels.get(j));
		  
		   j++ ;
		  roomService.saveOrUpdate(room); 	       	 
		  Assert.assertNotNull("iL EST PAS NULL", roomService.selectRoom(room.getId()));	 
		}
		 catch(Exception e) {
			LOGG.error("\n\nErreur d'insertion", e);
		  
		 }	  
		}
	}catch(Exception e) {
		
	   e.printStackTrace();
	}
	 }
	
	// TODO insertion des managers
	@Ignore
	public void insertManagers() throws ParseException {	 

		
	 int j = 0 ; 
	 char lettre = 'a' ;  int i = 1 ;
	 
	  while(   i < 26 && lettre < 'f' ) {
		  Manager manager = new Manager() ; 
		  manager.setAge(25+i);
		  manager.setFirstName(lettre+"karl"+lettre);
          manager.setLastName(lettre+"max"+lettre);
           i+=5 ; 
           lettre++ ; 
		  try { 
		 List<Hotel> hotels = hotelService.selectAllHotels() ; 	  
		 managerService.saveOrUpdate(manager); 	       	 			 
         hotels.get(j).setManager(manager);
         hotelService.saveOrUpdate(hotels.get(j));
		 j++ ;
		  Assert.assertNotNull("iL EST PAS NULL", roomService.selectRoom(manager.getId()));	 
		}
		 catch(Exception e) {
			LOGG.error("\n\nErreur d'insertion", e);
		  
		 }	  
		}
	
	 }
	 
	 @Ignore
	 public void TestInsertionList() {
		
		List<Room> listRoom = new ArrayList<>() ;  
	    Hotel hotel1 = new Hotel() ; 
	    hotel1.setNom("hotel des amis ");
	    Room room1 = new Room() ;
	    room1.setPrix(700.02d);
	    Room room2 = new Room() ; 
	    room2.setPrix(701.05d);
	    listRoom.add(room1) ; listRoom.add(room2) ;
        
           	
	    try {
		     
		      hotelService.saveOrUpdate(hotel1);
		      
		    }catch(Exception e){
		    	
		    	e.getStackTrace() ; 
		    	
		    }
	      room1.setHotel(hotel1);
	      room2.setHotel(hotel1); 
	      try {
			roomService.saveOrUpdate(room1);
			roomService.saveOrUpdate(room2) ;
		} catch (Exception e) {
		
			e.printStackTrace();
		}	
	     
	      
	  }
	 
	 @Ignore 
	 @Transactional  // Commentaire si on fait pas transactionnel il va pas arriver à récuperer les données 
	 // de la table chambre (pour récuperer les chambres on aura besoin d'une requête, donc il faudera 
	 // laisser la session ouverte pour faire une autre requête , donc on fait transactionnel 
	 //)
	 public void manegerEtHotelMoins36() {
		 
		 
		 try {
		 
		 
		 List<Manager> managers = managerService.selectAllManager() ; 
		 
		 List<Hotel> hotels = hotelService.selectAllHotels() ; 
		 
		 
			 
			 for(int j = 0 ; j < managers.size(); j++) {
				 
				 if(managers.get(j).getAge() < 36) {
					 
					 LOGG.info("\n\n " +managers.get(j).getHotel().getRooms());
				 }
			 
			 
		 }
		 }catch(Exception e) {
			 
            e.printStackTrace();
		 }
		 
	 }
	//TODO 
	 @Ignore
	 public void dleteHotelByIdTestCascade() {
		 
		 try {
			hotelService.deleteHotel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
	 }
	
	 // TODO Auto-generated catch block
	
	 // @Test
	 public void AjoutDeQulquesRommsAvecHotel() {
		 
		  Hotel hotel1 = new Hotel("Barcelo","Baralona",4) ;
		  Manager man = new Manager("moino","mauricio",50) ;
		  Room r1 = new Room(58.5d) ; 
		  Room r2 = new Room(40.5d) ; 
		  Room r3 = new Room(40.5d) ; 
		  Hotel hotel2 = new Hotel("miranda","Madrid",3) ;
		  Manager man1 = new Manager("conatrio","salim",40) ;

		  Room rr1 = new Room(70.02d) ; 
		  Room rr2 = new Room(101.5d) ; 
		  Room rr3 = new Room(70.5d) ; 

		  try {
			r1.setDateDispo(DateUtils.parseDate("05/01/2018"));  
			r2.setDateDispo(DateUtils.parseDate("05/05/2018"));  
			r3.setDateDispo(DateUtils.parseDate("05/06/2018"));
			rr1.setDateDispo(DateUtils.parseDate("05/01/2018"));  
			rr2.setDateDispo(DateUtils.parseDate("05/05/2018"));  
			rr3.setDateDispo(DateUtils.parseDate("05/06/2018"));
			managerService.saveOrUpdate(man);
			managerService.saveOrUpdate(man1);
			hotel1.setManager(man);
			hotel2.setManager(man1);
			hotelService.saveOrUpdate(hotel1);
			hotelService.saveOrUpdate(hotel2);
			r1.setHotel(hotel1);
			r2.setHotel(hotel1);
			r3.setHotel(hotel1);
			rr1.setHotel(hotel2);
			rr2.setHotel(hotel2);
			rr3.setHotel(hotel2);
			roomService.saveOrUpdate(r1);
			roomService.saveOrUpdate(r2);
			roomService.saveOrUpdate(r3);
			roomService.saveOrUpdate(rr1);
			roomService.saveOrUpdate(rr2);
			roomService.saveOrUpdate(rr3);

		} catch (Exception e) {
			e.printStackTrace();
		}
		          
		  
	 }
	//@Test
		 public void Enrichissement() {
			 
			  Hotel hotel1 = new Hotel("Mariposa","Sevilla",5) ;
			  Manager man = new Manager("Antonio","sierra",60) ;
			  Room r1 = new Room(40.5d) ; 
			  Room r2 = new Room(35.5d) ; 
			  Room r3 = new Room(30.5d) ; 
			  Hotel hotel2 = new Hotel("Nariz","Malaga",3) ;
			  Manager man1 = new Manager("fabiano","fonce",40) ;

			  Room rr1 = new Room(40.02d) ; 
			  Room rr2 = new Room(32.5d) ; 
			  Room rr3 = new Room(36.5d) ; 

			  try {
				r1.setDateDispo(DateUtils.parseDate("05/01/2018"));  
				r2.setDateDispo(DateUtils.parseDate("05/05/2018"));  
				r3.setDateDispo(DateUtils.parseDate("05/06/2018"));
				rr1.setDateDispo(DateUtils.parseDate("05/01/2018"));  
				rr2.setDateDispo(DateUtils.parseDate("05/05/2018"));  
				rr3.setDateDispo(DateUtils.parseDate("05/06/2018"));
				managerService.saveOrUpdate(man);
				managerService.saveOrUpdate(man1);
				hotel1.setManager(man);
				hotel2.setManager(man1);
				hotelService.saveOrUpdate(hotel1);
				hotelService.saveOrUpdate(hotel2);
				r1.setHotel(hotel1);
				roomService.saveOrUpdate(r1);
				r2.setHotel(hotel1);
				r3.setHotel(hotel1);
				rr1.setHotel(hotel2);
				rr2.setHotel(hotel2);
				rr3.setHotel(hotel2);
				roomService.saveOrUpdate(r2);
				roomService.saveOrUpdate(r3);
				roomService.saveOrUpdate(rr1);
				roomService.saveOrUpdate(rr2);
				roomService.saveOrUpdate(rr3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			          
			  
		 }
	  @Test
		 public void Enrichissement1() {
			 
			  Hotel hotel1 = new Hotel("el Grande Perro","Andorra",3) ;
			  Manager man = new Manager("Mariano","alkacer",60) ;
			  Room r1 = new Room(60.5d) ; 
			  Room r2 = new Room(50.5d) ; 
			  Room r3 = new Room(24.5d) ; 
			  Hotel hotel2 = new Hotel("Roje Blanco","Maderra",4) ;
			  Manager man1 = new Manager("Manuela","anissa",35) ;

			  Room rr1 = new Room(37.02d) ; 
			  Room rr2 = new Room(40.5d) ; 
			  Room rr3 = new Room(42.5d) ; 

			  try {
				r1.setDateDispo(DateUtils.parseDate("20/06/2018"));  
				r2.setDateDispo(DateUtils.parseDate("10/06/2018"));  
				r3.setDateDispo(DateUtils.parseDate("04/07/2018"));
				rr1.setDateDispo(DateUtils.parseDate("09/10/2018"));  
				rr2.setDateDispo(DateUtils.parseDate("11/08/2018"));  
				rr3.setDateDispo(DateUtils.parseDate("22/05/2018"));
				managerService.saveOrUpdate(man);
				managerService.saveOrUpdate(man1);
				hotel1.setManager(man);
				hotel2.setManager(man1);
				hotelService.saveOrUpdate(hotel1);
				hotelService.saveOrUpdate(hotel2);
				r1.setHotel(hotel1);
				roomService.saveOrUpdate(r1);
				r2.setHotel(hotel1);
				r3.setHotel(hotel1);
				rr1.setHotel(hotel2);
				rr2.setHotel(hotel2);
				rr3.setHotel(hotel2);
				roomService.saveOrUpdate(r2);
				roomService.saveOrUpdate(r3);
				roomService.saveOrUpdate(rr1);
				roomService.saveOrUpdate(rr2);
				roomService.saveOrUpdate(rr3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			          
			  
		 }
//TODO	
	  @Ignore
	  public void deleteHotelTestOfCascade() {

		    try {
				hotelService.deleteHotel(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
	  }
}
	





