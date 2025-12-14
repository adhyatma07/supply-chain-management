package org.jsp.SupplyChainManagement.Exception;

import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> INFE(IdNotFoundException exception)
	{
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRecordsFoundException.class)
	public ResponseEntity<ResponseStructure<String>> NRFE(NoRecordsFoundException exception)
	{
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("No record Found");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
}
