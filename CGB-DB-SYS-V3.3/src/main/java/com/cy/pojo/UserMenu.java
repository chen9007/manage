package com.cy.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class UserMenu implements Serializable{

	
	private static final long serialVersionUID = 5013351249606937680L;
	private Integer id;
	
	private String name;
	
	private String url;
	List<Menu> childMenus;

}
