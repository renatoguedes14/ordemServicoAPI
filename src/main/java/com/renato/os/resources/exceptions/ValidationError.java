package com.renato.os.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errosList = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErrosList() {
		return errosList;
	}

	public void addErro(String fieldName, String message) {
		this.errosList.add(new FieldMessage(fieldName, message));
	}
	
}
