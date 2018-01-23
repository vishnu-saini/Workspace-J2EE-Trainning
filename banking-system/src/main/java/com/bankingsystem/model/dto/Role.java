package com.bankingsystem.model.dto;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Entity
@Table(name="roles")
public class Role {
 
    public Role(Integer id, String role, List<User> userList) {
		super();
		this.id = id;
		this.role = role;
		this.userList = userList;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", userList=" + userList + "]";
	}
	@Id
    @GeneratedValue
    private Integer id;
     
    private String role;
     
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<User> userList;
     
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
     
}