package com.oms.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class VendorPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vid;
	private String vname;
	private String vtan;
	private String vpan;
	private String vloc;
    private String vdept;
}
