package com.bankingsystem.model.service;

import java.util.List;

import com.bankingsystem.model.dto.Branch;

public interface BranchService {

	public Branch update(Branch branch);

	public Branch save(Branch branch);

	public Branch find(int id);

	public void delete(int id);

	public List<Branch> getAll();
}
