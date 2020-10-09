package com.gk.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gk.dto.Customer;

public class CustomerDaoImpl implements CustomerDao {

	String status = "";
	String query = "";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public String add(Customer customer) {
		try {
			Customer cust = search(customer.getCid());
			if (cust == null) {
				query = "insert into CUSTOMER values(:CID, :CNAME, :CADDR)";
				SqlParameterSource param_source=new MapSqlParameterSource()
						.addValue("CID", customer.getCid())
						.addValue("CNAME", customer.getCname())
						.addValue("CADDR", customer.getCaddr());
				int rowCount = namedParameterJdbcTemplate.update(query,param_source);
				if (rowCount == 1) {
					status = "Customer Added Successfully";
				} else {
					status = "Customer Addition Failed";
				}
			} else {
				status = "Customer Existed Already";
			}
		} catch (Exception e) {
			status = "Customer Addition Failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Customer search(int cId) {
		Customer customer = null;
		try {
			query = "select * from CUSTOMER where CID= :CID";
			SqlParameterSource param_source=new MapSqlParameterSource().addValue("CID", cId);
			List<Customer> custList = namedParameterJdbcTemplate.query(query, param_source, (rs, rowCount) -> {
				Customer cust = new Customer();
				cust.setCid(rs.getInt("CID"));
				cust.setCname(rs.getString("CNAME"));
				cust.setCaddr(rs.getString("CADDR"));
				return cust;
			});
			boolean b = custList.isEmpty();
			if (b == true) {
				customer = null;
			} else {
				customer = custList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public String update(Customer customer) {
		try {
			query = "update CUSTOMER set CNAME=:CNAME, CADDR=:CADDR where CID=:CID";
			SqlParameterSource param_source=new MapSqlParameterSource()
					.addValue("CNAME", customer.getCname())
					.addValue("CADDR", customer.getCaddr())
					.addValue("CID", customer.getCid());
			int rowCount = namedParameterJdbcTemplate.update(query, param_source);
			if (rowCount == 1) {
				status = "Customer Successfully Upadted";
			} else {
				status = "Customer Updation Failed";
			}
		} catch (Exception e) {
			status = "Customer Updation Failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(int cId) {
		try {
			Customer customer = search(cId);
			if (customer == null) {
				status = "Customer Not Existed";
			} else {
				query = "delete from CUSTOMER where CID=:CID";
				SqlParameterSource param_source=new MapSqlParameterSource()
						.addValue("CID", cId);
				int rowCount = namedParameterJdbcTemplate.update(query, param_source);
				if (rowCount == 1) {
					status = "Customer Deleted Successfully";
				} else {
					status = "Customer Deletion Failed";
				}
			}
		} catch (Exception e) {
			status = "Customer Deletion Failed";
			e.printStackTrace();
		}
		return status;
	}

}
