package com.cy.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Page<T> implements Serializable {
		private static final long serialVersionUID = 6780580291247550747L;//类泛型
	    /**当前页的页码值*/
		private Integer pageCurrent=1;
	    /**页面大小*/
	    private Integer pageSize=3;
	    /**总记录(通过查询获得)*/
	    private Integer rowCount=0;
	    /**总页数(通过计算获得)*/
	    private Integer pageCount=1;
	    /**当前页记录*/
	    private List<T> records;

}
