package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionV2Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@ControllerAdvice + @ResponseBody
//대상 컨트롤러를 지정하지 않을시 모든 컨트롤러에 적용됨
// @RestControllerAdvice(basePackages = "hello.exception.api")
@RestControllerAdvice(assignableTypes = ApiExceptionV2Controller.class)
public class ExControllerAdvice {

    //ExceptionHandler사용시 Status는 정상응답(200)으로 응답하지만 변경가능
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("@ExceptionHandler 처리 ::: ", e);

        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("@ExceptionHandler 처리", e);

        ErrorResult errorResult = new ErrorResult("USER-EXCEPTION", e.getMessage());

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("@ExceptionHandler 처리", e);

        return new ErrorResult("EX", "내부오류");
    }
}
