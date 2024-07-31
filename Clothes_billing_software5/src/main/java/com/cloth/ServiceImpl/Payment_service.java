package com.cloth.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloth.Repository.Payment_Repo;
import com.cloth.model.PaymentInfo;

@Service
public class Payment_service 
{
	
	@Autowired
	private Payment_Repo p_repo;
	

	    public void PaymentSave(PaymentInfo Payment_b) 
	    {
	    	p_repo.save(Payment_b);
	    }

	  
	    public List<PaymentInfo> getAllPayment1() {
	        return p_repo.findAll();
	        
	    }

	   
	    public PaymentInfo getPayment_bById(Long id) {
	        return p_repo.findById(id).orElse(null);
	    }
	

}
