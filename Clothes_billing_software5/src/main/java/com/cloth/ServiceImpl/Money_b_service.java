package com.cloth.ServiceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloth.Repository.Money_Repository;
import com.cloth.Repository.Payment_Repo;
import com.cloth.model.Money_b;
import com.cloth.model.PaymentInfo;

@Service
public class Money_b_service {
	@Autowired
	private Money_Repository money_repo;
	
	     @Autowired
	    private Payment_Repo paymentRepo;
	
	
	public void moneySave(com.cloth.model.Money_b money) {
		money_repo.save(money);
	}
	public List<com.cloth.model.Money_b> getAllMoney() {
		
		List<com.cloth.model.Money_b> list = money_repo.findAll();
		return list;
	}
	
	
	public List<Money_b> getAllMoney1() {
		
		List<Money_b> list = money_repo.findAll();
		return list;
	}
//	public Money_b getMoney_bById(Long id) {
//	    // Example repository call
//	    return money_repo.findById(id).orElse(null);
//	}
	public void updateMoney_b(Money_b updatedMoney) {
		// TODO Auto-generated method stub
		
	}
//	public void updateMoney(Money_b updatedMoney) {
//		// TODO Auto-generated method stub
//		
//	}


	    public Money_b getMoney_bById(Long id) {
	        return money_repo.findById(id).orElse(null);
	    }

	    public void updateMoney(Money_b updatedMoney) {
	        Money_b existingMoney = money_repo.findById(updatedMoney.getId()).orElse(null);
	        if (existingMoney != null) {
	            // Update existing Money_b data
	           // existingMoney.setPaid_amount(updatedMoney.getPaidAmount());
	        	existingMoney.setPaidAmount(updatedMoney.getPaidAmount());
	            existingMoney.setRemainingAmount(updatedMoney.getRemainingAmount());
	            existingMoney.setTotalAmount(updatedMoney.getTotalAmount());
	            existingMoney.setDate(updatedMoney.getDate());
	            existingMoney.setName(updatedMoney.getName());
	            existingMoney.setBorrowedAmount(updatedMoney.getBorrowedAmount());
	            
	            // Save updated Money_b
	            money_repo.save(existingMoney);

	            // Create or update PaymentInfo records
	           // PaymentInfo payment = new PaymentInfo();
	            PaymentInfo payment=new PaymentInfo();
	            payment.setMoney(existingMoney);
	            payment.setPaid_amount(updatedMoney.getPaidAmount());
	            payment.setRemainingAmount(updatedMoney.getRemainingAmount());
	            payment.setTotalAmount(updatedMoney.getTotalAmount());
	            payment.setDate(updatedMoney.getDate());
	            payment.setName(updatedMoney.getName());
	            payment.setBorrowedAmount(updatedMoney.getBorrowedAmount());
	            paymentRepo.save(payment);
	        }
	    }
	
}