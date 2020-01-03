package com.cy.pojo;

import java.io.Serializable;

import org.apache.ibatis.annotations.Mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Mapper
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 2031967811425337153L;
	private Integer id;
	private String name;
	     

}
