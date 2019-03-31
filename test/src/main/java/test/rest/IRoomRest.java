package test.rest;

import java.util.List;

import test.dto.Room;

public interface IRoomRest {


	List<Room> getAllRooms();

	List<Room> getRoomsOfHotel(Integer id);

	void deleteRoom(Integer id);

	void createRoom(Room room, Integer id);

	void updateRoom(Room room, Integer id);

 

}
