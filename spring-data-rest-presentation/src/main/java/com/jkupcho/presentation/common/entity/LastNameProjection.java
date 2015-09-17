package com.jkupcho.presentation.common.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="firstNameOnly", types=Employee.class)
public interface LastNameProjection {

	String getFirst();
	
	@Value("#{target.first eq 'Jonathan'}")
	boolean isJonathan();
	
}
