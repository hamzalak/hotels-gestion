package test.service;

import java.util.List;


import test.dto.* ;

public interface IManagerService  {
	
    public void addManager(Manager manager) throws Exception  ; 
	public void deleteManager(Integer id )throws Exception ;
	public void saveOrUpdate(Manager manager)throws Exception ;
	public List<Manager> selectAllManager() throws Exception ;
	public Manager selectManager (Integer id) throws Exception; 
	
}
