package com.naifer.wihsshop.generic;

public class GenericResponse <T> {
	
	private T data;
	
	private Boolean success;

	public GenericResponse() {
		super();
		
	}

	public GenericResponse(T data, Boolean success) {
		super();
		this.data = data;
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "GenericResponse [data=" + data + ", success=" + success + "]";
	}

}
