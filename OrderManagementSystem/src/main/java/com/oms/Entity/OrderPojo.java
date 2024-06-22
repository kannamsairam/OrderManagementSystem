package com.oms.Entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class OrderPojo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderid;
	private Date orderdate;
	private Date orderdeliverydate;
	private Integer  orderdeptId;
	private String orderempName;
	private String orderAppManager;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkorderid")
	private List<OrderProcessPojo> opp;
}