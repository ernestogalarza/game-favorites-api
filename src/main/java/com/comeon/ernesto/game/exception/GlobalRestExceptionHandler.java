package com.comeon.ernesto.game.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ApiError apiError = new ApiError(
                errorCode.getCode(),
                ex.getMessage(),
                errorCode.getHttpStatus().value()
        );
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ApiError apiError = new ApiError(
                ErrorCode.INTERNAL_ERROR.getCode(),
                ex.getMessage(),
                ErrorCode.INTERNAL_ERROR.getHttpStatus().value()
        );
        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.getHttpStatus()).body(apiError);
    }
}
