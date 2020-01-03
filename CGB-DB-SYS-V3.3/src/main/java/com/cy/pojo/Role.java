package com.cy.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Role implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4898448069943714902L;
	
	
	private Integer id;
	
	 private String name;
	 private String note;
	 private Date createdTime;
	 private Date modifiedTime;
	 private String createdUser;
	 private String modifiedUser;

}
