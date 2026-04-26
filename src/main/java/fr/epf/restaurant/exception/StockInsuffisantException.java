package fr.epf.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class StockInsuffisantException extends RuntimeException {
      public StockInsuffisantException(String message) {
        super(message);
    }
}
