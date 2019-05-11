package webController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import util.ValidationUtil;
import util.exception.*;

import javax.servlet.http.HttpServletRequest;

import static util.exception.ErrorType.*;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    public static final String EXCEPTION_DUPLICATE_LOGIN = "exception.user.duplicateLogin";
    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleError(HttpServletRequest req, Exception e) {
        ErrorInfo errorInfo = logAndGetErrorInfo(req, e, true, APP_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }

    @ExceptionHandler(TooLateToVote.class)
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest req, TooLateToVote e) {
        ErrorInfo errorInfo = logAndGetErrorInfo(req, e, false, TOO_LATE_TO_VOTE);
        return ResponseEntity.status(HttpStatus.LOCKED).body(errorInfo);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> notFoundExceptionError(HttpServletRequest req, NotFoundException e) {
        ErrorInfo errorInfo = logAndGetErrorInfo(req, e, false, DATA_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorInfo> illegalArgumentExceptionHandler(HttpServletRequest req, IllegalRequestDataException e) {
        ErrorInfo errorInfo = logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorInfo);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> dataIntegrityViolationHandler(HttpServletRequest req, DataIntegrityViolationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
        if (rootMsg != null) {
            String lowerCaseMsg = rootMsg.toLowerCase();
            if (lowerCaseMsg.contains("users_unique_login_idx")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, EXCEPTION_DUPLICATE_LOGIN));
            } else if (lowerCaseMsg.contains("users_unique_email_idx")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, EXCEPTION_DUPLICATE_EMAIL));
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(logAndGetErrorInfo(req, e, true, DATA_ERROR));
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(LOG, req, e, logException, errorType);
        return new ErrorInfo(req.getRequestURL(), errorType, errorType.getErrorCode(),
                details.length != 0 ? details : new String[]{ValidationUtil.getMessage(rootCause)});
    }
}
