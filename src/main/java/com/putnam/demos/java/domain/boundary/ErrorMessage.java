package com.putnam.demos.java.domain.boundary;

public class ErrorMessage {
	
	private int errorCode;
	private String errorSummary;

	
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorMessage(int errorCode, String errorSummary) {
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorSummary() {
		return errorSummary;
	}

	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}

	
	

}
