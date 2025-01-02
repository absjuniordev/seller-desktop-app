package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, String> erros = new HashMap<String, String>();

	public ValidationException(String msg) {
		super(msg);
	}

	public Map<String, String> getErros() {
		return erros;
	}

	
	public void addError(String fielName, String errorMessage) {
		erros.put(fielName, errorMessage);
	}
}
