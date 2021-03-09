package com.capg.movie.capg.movie.booking.servicesImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Admin;
import com.capg.movie.capg.movie.booking.repository.AdminRepository;
import com.capg.movie.capg.movie.booking.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService {
	@Autowired
	AdminRepository iar;
	
	Optional<Admin> admins = null;
	
	//Adding a Admin
	public void addAdmin(Admin a)   {
		admins = iar.findById(a.getadminId());
		if(admins.isEmpty()) {
			iar.save(a);
		}
//		else {
//			 
//			 new AdminAlreadyExistsException("Admin with id:"+a.getadminId()+" already exists");
//		}
	}
	
	//Viewing all admins
	public List<Admin> viewAllAdmin() {
		List<Admin> admins = iar.findAll();
		return admins;
	}

	//Viewing customer by id
	public Admin viewAdminById(int id) {
		
		Optional<Admin> admin = iar.findById(id);
		Admin a = null;
		if(admin.isPresent()) {
			a = admin.get();
		}
		
		return a;
	}
	
	//Deleting a customer
	public void deleteAdmin(int id){
		Optional<Admin> admin = iar.findById(id);
		Admin a = null;
		if(admin.isPresent()) {
			a = admin.get();
			iar.delete(a);
		}
//		else {
//			throw new CustomerDoesNotExistException("Customer with id:"+c.getCust_id()+" does not exist");
//		}
	}

	//Update
	@Transactional
	public Admin updateAdmin(Admin admin) {
		Optional<Admin> getAdmin = iar.findById(admin.getadminId());
		Admin updateadmin = null;
		if(getAdmin.isPresent()) {
			updateadmin = getAdmin.get();
			if(admin.getadminName()!=null) {
				updateadmin.setadminName(admin.getadminName());
			}
			if(admin.getadminContact()!=null) {
				updateadmin.setadminContact(admin.getadminContact());
			}
			
		}
		return updateadmin;
	}
}