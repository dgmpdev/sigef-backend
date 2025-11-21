package ci.dgmp.sigefbackend.admin.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler
{
    /** -----------------------------------
     *  STRUCTURE DE LA REPONSE D'ERREUR
     *  ----------------------------------- */
    private record ApiError(
            LocalDateTime timestamp,
            int status,
            String error,
            List<String> messages
    ) {}

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, List<String> messages) {
        return ResponseEntity.status(status).body(
                new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(), messages)
        );
    }

    /** -----------------------------------
     *  VALIDATION DES DTO ( @Valid )
     *  ----------------------------------- */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> {
                    String msg = err.getDefaultMessage();
                    if (msg != null && msg.contains("::"))
                        return msg.split("::")[1];
                    return msg;
                })
                .collect(Collectors.toList());

        return buildResponse(HttpStatus.BAD_REQUEST, errors);
    }

    /** -----------------------------------
     *  EXCEPTIONS MÉTIER (AppException)
     *  ----------------------------------- */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiError> handleAppException(AppException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
    }

    /** -----------------------------------
     *  AUTHENTICATION / LOGIN
     *  ----------------------------------- */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthException(AuthenticationException ex) {

        String message =
                ex instanceof DisabledException ?
                        "Votre compte a bien été créé mais n'est pas encore activé.\nPour recevoir un lien d'activation, veuillez cliquer sur le lien ci-dessous."
                        :
                        ex instanceof LockedException ?
                                "Compte bloqué."
                                :
                                ex instanceof InsufficientAuthenticationException ?
                                        ex.getMessage()
                                        :
                                        "Identifiants incorrects.";

        return buildResponse(HttpStatus.UNAUTHORIZED, List.of(message));
    }

    /** -----------------------------------
     *  JWT EXPIRE
     *  ----------------------------------- */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> handleJwtExpiration(ExpiredJwtException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, List.of("EXPIRED_TOKEN"));
    }

    /** -----------------------------------
     *  CONSTRAINTS VALIDATOR ( @NotBlank @Min ... )
     *  ----------------------------------- */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());

        return buildResponse(HttpStatus.BAD_REQUEST, errors);
    }

    /** -----------------------------------
     *  CATCH-ALL : ULTIME FILET DE SECURITÉ
     *  ----------------------------------- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex) {

        // Log serveur - indispensable
        ex.printStackTrace();

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of("Une erreur interne est survenue. Veuillez réessayer plus tard.")
        );
    }
}
