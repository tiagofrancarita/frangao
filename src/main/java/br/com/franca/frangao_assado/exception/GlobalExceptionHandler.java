package br.com.franca.frangao_assado.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.ObjectError;
import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleClienteNotFoundException(ClienteNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Data Integrity Violation",
                ex.getMessage(),
                request.getDescription(false).substring(4) // Remove "uri=" do início
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /*
        Captura exceções de argumentos inválidos.

        Exemplo: um cliente envia um argumento inválido.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
        Captura exceções de validação.

        Exemplo: um cliente envia um corpo de requisição inválido.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "Validation Failed",
                errorMessage,
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    /*
        Captura exceções quando um elemento necessário não é encontrado.

        Exemplo: um cliente tenta acessar um recurso que não existe.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomErrorResponse> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), "Not Found",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /*
        Captura exceções quando há violação de integridade de dados.

        Exemplo: um cliente tenta inserir um registro duplicado.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {

        String errorMessage = "Ocorreu um erro de integridade de dados. Verifique se todos os dados são válidos e se não há duplicatas.";

        // Optional: Verificar a mensagem da exceção para determinar o tipo específico de violação
        if (ex.getMessage().contains("Unique index or primary key violation")) {
            errorMessage = "Já existe um registro com este email.";
        }

        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(), "Data Integrity Violation",
                errorMessage,
                request.getDescription(false) // Obtendo o caminho do URI
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }



}
