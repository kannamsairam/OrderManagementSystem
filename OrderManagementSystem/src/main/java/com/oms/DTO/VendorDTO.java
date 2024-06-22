package com.oms.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VendorDTO {
	
	private Integer vid;
	private String vname;
	private String vtan;
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Given pattern not supported : Pattern must be in * ABCDE1234F *")
	private String vpan;
	private String vloc;
    private String vdept;

}
