package deptayto.API.untils;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiMessages {
	private String message;

	public ApiMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
