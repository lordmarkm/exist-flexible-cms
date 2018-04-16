package com.mynt.core.web.handler;

import com.mynt.core.dto.OperationResult;
import com.mynt.core.web.FormValidationMessage;
import com.mynt.core.web.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    @Qualifier("messages")
    private MessageSource messageSource;

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<OperationResult<String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.CONFLICT)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistIllegalArgumentException.class)
    public ResponseEntity<OperationResult<String>> handleMyntIllegalArgumentException(ExistIllegalArgumentException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistExistingDataException.class)
    public ResponseEntity<OperationResult<String>> handleMyntDataAlreadyExistException(ExistExistingDataException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.CONFLICT)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistNotFoundException.class)
    public ResponseEntity<OperationResult<String>> handleMyntDataNotExistException(ExistNotFoundException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.NOT_FOUND)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<OperationResult<String>> handleAllMyntExceptions(AbstractException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OperationResult<String>> handleUncaughtExceptions(Exception ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<OperationResult<String>> handleRuntimeExpcetions(RuntimeException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OperationResult> parseFormValidationResponse(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FormValidationMessage validationMessage = new FormValidationMessage();
        result.getFieldErrors().forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, null);
            validationMessage.setValidationMessage(fieldError.getField(), message);
        });
        OperationResult operationResult = new OperationResult(validationMessage)
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new ResponseEntity<OperationResult>(operationResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<OperationResult> parseIOException(HttpMessageNotReadableException ex) {
        logError(ex);
        return new ResponseEntity<>(new OperationResult<>(ex.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getClass().getSimpleName()), HttpStatus.BAD_REQUEST);
    }

    private void logError(Exception e) {
        LOGGER.error("Non-mynt exception!", e);
    }
}
