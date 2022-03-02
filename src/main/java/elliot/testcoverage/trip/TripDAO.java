package elliot.testcoverage.trip;

import elliot.testcoverage.exception.DependedClassCallDuringUnitTestException;
import elliot.testcoverage.user.User;
import java.util.List;

public class TripDAO {

    public static List<Trip> findTripsByUser(User user){
        throw new DependedClassCallDuringUnitTestException("TripDAO should not be invoked on an unit test.");
    }

    public List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
