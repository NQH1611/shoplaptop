package vn.edu.stu.shop_laptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import vn.edu.stu.shop_laptop.response.ImageMessage;

@ControllerAdvice
public class ImageUploadExceptionAdvice extends ResponseEntityExceptionHandler{
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ImageMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage("File too large!"));
    }
}
