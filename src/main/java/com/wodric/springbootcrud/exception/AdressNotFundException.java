package com.wodric.springbootcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Adress Not Found")
public class AdressNotFundException extends Exception{
}
