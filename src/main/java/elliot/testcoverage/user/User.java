package elliot.testcoverage.user;

import elliot.testcoverage.trip.Trip;
import java.util.List;
import java.util.ArrayList;

public class User {

    private List<Trip> trips = new ArrayList<Trip>();
    private List<User> friends = new ArrayList<User>();

    public List<User> getFriends(){
        return friends;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public void addTrip(Trip trip){
        trips.add(trip);
    }

    public List<Trip> trips(){
        return trips;
    }

    public boolean isFriendsWith(User anotherUser) {

        return friends.contains(anotherUser);
    }
}
