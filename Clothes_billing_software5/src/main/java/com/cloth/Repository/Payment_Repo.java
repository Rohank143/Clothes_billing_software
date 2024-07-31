package com.cloth.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloth.model.Money_b;
import com.cloth.model.PaymentInfo;

@Repository
public interface Payment_Repo extends JpaRepository<PaymentInfo, Long> {
	List<PaymentInfo> findByMoney(Money_b money);
}
