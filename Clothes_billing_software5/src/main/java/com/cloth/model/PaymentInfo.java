package com.cloth.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "payment")
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    private String name;
    private String borrowedAmount;
    private String additionalMoney;

	private String paid_amount;
    private String remainingAmount;
    private String totalAmount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "money_id") // This is the name of the foreign key column
    private Money_b money;

    // Getters and setters
    
    
    
    public Long getPayment_id() {
        return payment_id;
    }

    public String getAdditionalMoney() {
		return additionalMoney;
	}

	public void setAdditionalMoney(String additionalMoney) {
		this.additionalMoney = additionalMoney;
	}

	public String getBorrowedAmount() {
		return borrowedAmount;
	}

	public void setBorrowedAmount(String borrowedAmount) {
		this.borrowedAmount = borrowedAmount;
	}

	public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Money_b getMoney() {
        return money;
    }

    public void setMoney(Money_b money) {
        this.money = money;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
	 public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
    
}