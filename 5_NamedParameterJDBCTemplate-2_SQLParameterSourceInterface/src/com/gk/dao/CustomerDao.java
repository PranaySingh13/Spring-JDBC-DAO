package com.gk.dao;

import com.gk.dto.Customer;

public interface CustomerDao {

	public String add(Customer customer);

	public Customer search(int cId);

	public String update(Customer customer);

	public String delete(int cId);
}
