package com.oms.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrderProcessPojo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	private String productName;
	private Integer productquan;
	private String productdeptname;
	private Date productdeliverydate;
	private String producttype;
	private String productAppmanager;
}
