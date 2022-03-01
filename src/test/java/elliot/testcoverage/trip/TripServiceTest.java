package elliot.testcoverage.trip;

import static elliot.testcoverage.user.UserBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.user.User;
import elliot.testcoverage.user.UserBuilder;
import java.util.List;
import org.junit.Before;
import org.junit.internal.runners.statements.FailOnTimeout.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    public static final User ANOTHER_USER = new User();
    private static final Trip TO_BRAZIL = new Trip();
    private static final Trip TO_LONDON = new Trip();
    private User loggedInUser;
    private TripService tripService;

    @BeforeEach
    public void initialise(){
        loggedInUser = REGISTERED_USER;
        tripService = new TestableTripService();
    }

    @Test
    public void should_throw_an_exception_when_user_is_not_logged_in(){

        //given
        loggedInUser = GUEST;

        //when/then
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(UNUSED_USER);
        } );

    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends(){

        User friend = aUser()
            .friendsWith(ANOTHER_USER)
            .withTrips(TO_BRAZIL)
            .build();

        // given
//        User friend = new User();
//        friend.addFriend(ANOTHER_USER);
//        friend.addTrip(TO_BRAZIL);

        //when/then
        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertThat(friendTrips.size()).isEqualTo(0);
    }

    @Test
    public void should_return_friend_trips_when_users_are_friends(){

        // builder pattern
        User friend = aUser().friendsWith(ANOTHER_USER, loggedInUser)
            .withTrips(TO_BRAZIL, TO_LONDON)
            .build();

        // given
//        User friend = new User();
//        friend.addFriend(ANOTHER_USER);
//        friend.addFriend(loggedInUser);
//        friend.addTrip(TO_BRAZIL);
//        friend.addTrip(TO_LONDON);

        //when/then
        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertThat(friendTrips.size()).isEqualTo(2);
    }

    private class TestableTripService extends TripService{
        @Override
        protected User getLoggedInUser(){
            return loggedInUser;
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }

}