package elliot.testcoverage.trip;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.user.User;
import java.util.ArrayList;
import java.util.List;

public abstract class TripService {

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null){
            throw new UserNotLoggedInException();
        }

        return user.isFriendsWith(loggedInUser)
            ? tripsBy(user)
            : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
