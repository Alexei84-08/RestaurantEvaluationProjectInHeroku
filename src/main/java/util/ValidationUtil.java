package util;

import model.parents.AbstractBaseEntity;
import org.slf4j.Logger;
import util.exception.ErrorType;
import util.exception.IllegalRequestDataException;
import util.exception.NotFoundException;
import util.exception.TooLateToVote;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

import static util.TimeUtil.getVoteFinishTime;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static <T extends AbstractBaseEntity> T isNotNew(T entity) {
        if (!entity.isNew()) {
            throw new IllegalRequestDataException("Entity is not new, id=" + entity.getId());
        }
        return entity;
    }

    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return rootCause;
    }

    public static void isItTooLateToVote() {
        if (!LocalTime.now().isBefore(getVoteFinishTime())) {
            throw new TooLateToVote();
        }
    }
}
