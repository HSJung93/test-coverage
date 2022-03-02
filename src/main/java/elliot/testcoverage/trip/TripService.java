package elliot.testcoverage.trip;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.user.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

    @Autowired
    private TripDAO tripDAO;

    public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null){
            throw new UserNotLoggedInException();
        }

        return friend.isFriendsWith(loggedInUser)
            ? tripsBy(friend)
            : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    private List<Trip> tripsBy(User user) {
        return tripDAO.tripsBy(user);
    }
}
