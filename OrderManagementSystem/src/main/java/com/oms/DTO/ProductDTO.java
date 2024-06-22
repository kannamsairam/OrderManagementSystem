package com.oms.DTO;

import java.util.List;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDTO {

	private Integer pid;
	@Column(unique = true)
	private String pname;
	private Integer pmaxq;
	private Integer pminq;
	private String pstore;
	private String pman;
	private String ptype;
	private Double pprice;
	private Double pgst;
	private List<VendorDTO> vpo;
}
