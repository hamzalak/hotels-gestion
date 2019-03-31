package test.service;

import java.util.List;


import test.dto.* ;

public interface IRoomService  {
	
    public void addRoom(Room room) throws Exception  ; 
	public void deleteRoom(Integer id )throws Exception ;
	public void saveOrUpdate(Room room)throws Exception ;
	public List<Room> selectAllRooms() throws Exception ;
	public Room selectRoom (Integer id) throws Exception;
 	List<Room> selectRoomsOfHotel(Integer id) throws Exception; 
	
}
