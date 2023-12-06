package com.audsat.insurances.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.audsat.insurances.exception.ApiErrors;
import com.audsat.insurances.exception.InsuranceNotFoundException;
import com.audsat.insurances.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegrasNegocioException(RegraNegocioException ex) {
		String messageError = ex.getMessage();
		return new ApiErrors(messageError);
	}

	@ExceptionHandler(InsuranceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException(InsuranceNotFoundException ex) {
		String messageError = ex.getMessage();
		return new ApiErrors(messageError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getAllErrors().stream()
			.map(erro -> erro.getDefaultMessage())
			.collect(Collectors.toList());
		
		return new ApiErrors(erros);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String messageError = ex.getMessage();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErrors apiErrors = new ApiErrors(messageError);
		
		return new ResponseEntity(apiErrors, codigoStatus);
		
	}

}
