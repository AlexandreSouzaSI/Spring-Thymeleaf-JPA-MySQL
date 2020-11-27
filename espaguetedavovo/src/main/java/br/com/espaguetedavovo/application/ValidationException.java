package br.com.espaguetedavovo.application;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	public ValidationException(String message) {
		super(message);
	}
}
