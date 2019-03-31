package test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.dao.ManagerRepository;
import test.dto.Manager;

@Service
public class ManagerService implements IManagerService {

	@Autowired
	public ManagerRepository managerRepo ; 
	 
	@Override
	public void addManager(Manager manager) throws Exception {
		managerRepo.save(manager) ; 
	}

	@Override
	public void deleteManager(Integer id) throws Exception {
		managerRepo.deleteById(id);
	}

	@Override
	public void saveOrUpdate(Manager manager) throws Exception {
		 
		managerRepo.save(manager) ; 
		
	}

	@Override
	public List<Manager> selectAllManager() {
		
		List<Manager>  managers = managerRepo.findAll() ; 
		
		return managers ;
	}

	@Override
	public Manager selectManager(Integer id) {
		
		Manager manager = managerRepo.getOne(id) ;
		
		return manager ;
	}

	
}

