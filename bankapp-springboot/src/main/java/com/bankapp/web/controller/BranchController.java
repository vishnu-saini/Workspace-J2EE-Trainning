package com.bankapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankapp.model.dto.Branch;
import com.bankapp.model.service.BranchService;

public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@RequestMapping(value = "/api/branch/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> getBranchById(@PathVariable("id") Integer id) {

		Branch branch = branchService.find(id);
		if (branch == null) {
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<Branch>(branch, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/branch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
		Branch savedBranch = branchService.save(branch);
		return new ResponseEntity<Branch>(savedBranch, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/branch/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch) {
		Branch updatedBranch = branchService.update(branch);
		return new ResponseEntity<Branch>(updatedBranch, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/branch/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Branch> deleteBranch(@PathVariable("id") Integer id)throws Exception {
		branchService.delete(id);
		return new ResponseEntity<Branch>(HttpStatus.NO_CONTENT);
	}
}
