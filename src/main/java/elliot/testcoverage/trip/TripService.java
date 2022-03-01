package elliot.testcoverage.trip;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.user.User;
import elliot.testcoverage.user.UserSession;
import java.util.ArrayList;
import java.util.List;

public abstract class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();

        // scene -> division of 2 classes
        User loggedUser = getLoggedInUser();
        boolean isFriend = false;
        if(loggedUser != null){
            for(User friend:user.getFriends()){
                if(friend.equals(loggedUser)){
                    isFriend = true;
                    break;
                }
            }
            if (isFriend){
                tripList = tripsBy(user);
            }
            return tripList;
        }else{
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
