package co.du.pay.vyne.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Transaction not found!")
public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(){
        super();
    }
}
