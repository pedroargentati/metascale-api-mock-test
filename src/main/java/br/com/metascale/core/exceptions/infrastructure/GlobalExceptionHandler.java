package br.com.metascale.core.exceptions.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.metascale.core.exceptions.BusinessException;
import br.com.metascale.core.exceptions.ErrorResponse;
import br.com.metascale.core.exceptions.RecordNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Se em qualquer controller do projeto for lançada uma Exception
	 * 'EntityNotFoundException', o método 'handleError404' será chamado.
	 * 
	 * @return Not Found exception (404).
	 **/
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundError(EntityNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("Entidade não encontrada", HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getArgs()[0], HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
