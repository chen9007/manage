package com.cy.pojo;

import java.io.Serializable;

import lombok.Data;
@Data
public class JsonResult implements Serializable {
	private static final long serialVersionUID = 5955288584015909361L;
	private int state=1;
	private String message="ok";
	private Object data;
	
	public JsonResult() {
	}

	public JsonResult(String message) {
		
		this.message = message;
	}

	public JsonResult(Object data) {
	
		this.data = data;
	}

	public JsonResult(Throwable t) {
		this.state=0;
		this.message = t.getMessage();
	}
	
}
