package com.cy.pojo;

import java.io.Serializable;

import lombok.Data;
@Data
public class Node implements Serializable{
	private static final long serialVersionUID = -6577397050669133046L;
	private Integer id;
	private String name;
	private Integer parentId;
}
