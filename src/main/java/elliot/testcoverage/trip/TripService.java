package elliot.testcoverage.trip;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.user.User;
import elliot.testcoverage.user.UserSession;
import java.util.ArrayList;
import java.util.List;

public abstract class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (getLoggedInUser() == null){
            throw new UserNotLoggedInException();
        }

        return user.isFriendsWith(getLoggedInUser())
            ? tripsBy(user)
            : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
