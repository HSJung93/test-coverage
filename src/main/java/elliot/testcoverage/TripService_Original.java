package elliot.testcoverage;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.trip.Trip;
import elliot.testcoverage.trip.TripDAO;
import elliot.testcoverage.user.User;
import elliot.testcoverage.user.UserSession;
import java.util.ArrayList;
import java.util.List;

public class TripService_Original {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = UserSession.getInstance().getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = TripDAO.findTripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }
}
