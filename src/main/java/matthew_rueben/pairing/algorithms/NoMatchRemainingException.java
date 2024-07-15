package matthew_rueben.pairing.algorithms;

public class NoMatchRemainingException extends RuntimeException
{
    // Passthroughs for all 5 of Exception's constructors.
    public NoMatchRemainingException() { super(); }
    public NoMatchRemainingException(String message) { super(message); }
    public NoMatchRemainingException(String message, Throwable cause) { super(message, cause); }
    protected NoMatchRemainingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { super(message, cause, enableSuppression, writableStackTrace); }
    public NoMatchRemainingException(Throwable cause) { super(cause); }
}