package com.bankingsystem.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingsystem.model.dto.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Integer> {

}
