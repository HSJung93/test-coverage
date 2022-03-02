package elliot.testcoverage.user;

import elliot.testcoverage.exception.DependedClassCallDuringUnitTestException;

public class UserSession {

    public static final UserSession userSession = new UserSession();

    private UserSession(){

    }

    public static UserSession getInstance(){
        return userSession;
    }

    public User getLoggedUser(){
        throw new DependedClassCallDuringUnitTestException(
            "UserSession.getLoggedUser() should not be called in an unit test"
        );
    }

}
