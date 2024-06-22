package com.oms.DTO;

import java.sql.Date;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class OrderProcessDTO {

	private Integer productid;
	private String productName;
	@Range(max = 10)
	private Integer productquan;
	private String productdeptname;
	private Date productdeliverydate;
	private String producttype;
	private String productAppmanager;
}
