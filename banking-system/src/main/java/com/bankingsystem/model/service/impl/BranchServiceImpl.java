package com.bankingsystem.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bankingsystem.model.dao.BranchDao;
import com.bankingsystem.model.dto.Branch;
import com.bankingsystem.model.service.BranchService;

@Transactional
public class BranchServiceImpl implements BranchService {
	@Autowired
	BranchDao branchDao;

	@Override
	public Branch update(Branch branch) {
		return branchDao.save(branch);

	}

	@Override
	public Branch save(Branch branch) {
		return branchDao.save(branch);

	}

	@Override
	public Branch find(int id) {
		return branchDao.getOne(id);
	}

	@Override
	public void delete(int id) {
		branchDao.delete(id);
	}

	@Override
	public List<Branch> getAll() {
		return branchDao.findAll();
	}

}
