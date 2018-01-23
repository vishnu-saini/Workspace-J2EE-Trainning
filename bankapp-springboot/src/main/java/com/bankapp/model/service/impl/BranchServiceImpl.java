package com.bankapp.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.model.dao.BranchDao;
import com.bankapp.model.dto.Branch;
import com.bankapp.model.service.BranchService;

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
