package im.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonsExceptionHandler {

	@ExceptionHandler({Throwable.class})
	public String handlerException(Exception e) {
	   return "error/error.html" ; 
    }
}
