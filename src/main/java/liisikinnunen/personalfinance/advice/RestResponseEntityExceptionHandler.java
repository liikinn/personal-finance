package liisikinnunen.personalfinance.advice;

import liisikinnunen.personalfinance.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.data.rest.webmvc.ControllerUtils;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public RestResponseEntityExceptionHandler(ApplicationContext applicationContext) {
        this.messageSourceAccessor = new MessageSourceAccessor(applicationContext);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, new JsonResponse("Access denied"), headers, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ResourceSupport> handleNotFoundException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ControllerUtils.toEmptyResponse(HttpStatus.NOT_FOUND, headers);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RepositoryConstraintViolationException exception = new RepositoryConstraintViolationException((ex.getBindingResult()));
        RepositoryConstraintViolationExceptionMessage message = new RepositoryConstraintViolationExceptionMessage(exception, messageSourceAccessor);
        return new ResponseEntity<>(message, headers, status);
    }
}
