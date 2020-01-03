package com.cy.pojo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lombok.Data;

@Mapper
@Data
public class RoleMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/**角色名称*/
	private String name;
    /**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
}
