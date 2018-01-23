package com.bankapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.dto.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Integer> {

}
