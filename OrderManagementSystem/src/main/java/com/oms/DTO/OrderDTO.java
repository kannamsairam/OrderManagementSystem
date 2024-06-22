package com.oms.DTO;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class OrderDTO {
	
	private Integer orderid;
	@PastOrPresent
	private Date orderdate;
	@FutureOrPresent
	private Date orderdeliverydate;
	private Integer  orderdeptId;
	private String orderempName;
	private String orderAppManager;
	private List<OrderProcessDTO> opp;
}