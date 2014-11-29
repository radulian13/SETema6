package exceptions;

/**
 * Created by VoldHouse on 11/22/2014.
 */
public class InputException extends Exception {
    /**
     * The constructor for the input exception
     * @param input The input on witch the exception was obtained
     * @param errorMessage  The default exception message
     */
    public InputException(String input, String errorMessage) {
        super("Input Format exception on \"" + input + "\" : " + errorMessage);
    }
}
