package elliot.testcoverage.exception;

public class DependedClassCallDuringUnitTestException extends RuntimeException {

    private static final long serialVersionUID = -4584041339906109902L;

    public DependedClassCallDuringUnitTestException(){
        super();
    }

    public DependedClassCallDuringUnitTestException(String message, Throwable cause){
        super(message, cause);
    }

    public DependedClassCallDuringUnitTestException(String message){
        super(message);
    }

    public DependedClassCallDuringUnitTestException(Throwable cause){
        super(cause);
    }

}
