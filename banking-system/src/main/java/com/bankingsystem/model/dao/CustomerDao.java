package com.bankingsystem.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingsystem.model.dto.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
