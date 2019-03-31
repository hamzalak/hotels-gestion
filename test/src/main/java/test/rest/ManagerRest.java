package test.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.dto.Manager;
import test.service.IManagerService;

@RestController
@RequestMapping("manager")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerRest implements IManagerRest{
	
@Autowired IManagerService managerService ; 	
	

 @GetMapping("/all")
 @Override
 public List<Manager> getAllManagers() {
	 
	 try {
		List<Manager> managers = managerService.selectAllManager() ;
	
		 return managers ; 

	 } catch (Exception e) {
		 e.printStackTrace();
		return null ; 

	}
 }


}
