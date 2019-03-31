package test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//CREATION DES TABLES DES CLASSE DE LA DTO == MAPPING
public interface ManagerRepository extends JpaRepository<test.dto.Manager,Integer> {

}
