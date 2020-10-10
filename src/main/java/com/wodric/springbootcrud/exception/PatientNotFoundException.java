package com.wodric.springbootcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Patient Not Found")
public class PatientNotFoundException extends Exception {
}
