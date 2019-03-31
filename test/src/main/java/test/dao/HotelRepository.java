package test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.dto.Hotel;
//CREATION DES TABLES DES CLASSE DE LA DTO == MAPPING
public interface HotelRepository extends JpaRepository<test.dto.Hotel,Integer> {
	
}
