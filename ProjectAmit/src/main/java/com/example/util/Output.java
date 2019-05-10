package com.example.util;

import java.util.List;

public class Output {

	private String resMsg;
	private String userId;
	private List<Object> valErrors;
	
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Object> getValErrors() {
		return valErrors;
	}
	public void setValErrors(List<Object> valErrors) {
		this.valErrors = valErrors;
	}
		
}

