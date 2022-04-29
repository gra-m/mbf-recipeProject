package fun.madeby.mbfrecipeproject.exceptions;

/**
 * Created by Gra_m on 2022 04 29
 */

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
