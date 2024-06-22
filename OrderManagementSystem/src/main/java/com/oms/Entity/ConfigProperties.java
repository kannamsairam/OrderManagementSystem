package com.oms.Entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("opv.student")
public class ConfigProperties {

	private Integer sage;
	private String sname, squal;
	private Double sfee;
}
