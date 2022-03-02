package elliot.testcoverage.trip;

import static elliot.testcoverage.user.UserBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import elliot.testcoverage.exception.UserNotLoggedInException;
import elliot.testcoverage.user.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    public static final User ANOTHER_USER = new User();
    private static final Trip TO_BRAZIL = new Trip();
    private static final Trip TO_LONDON = new Trip();

    @Mock
    private TripDAO tripDAO;

    @InjectMocks
    @Spy
    private TripService tripService = new TripService();

    @Test
    public void should_throw_an_exception_when_user_is_not_logged_in(){
        //given

        //when/then
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getFriendTrips(UNUSED_USER, GUEST);
        } );

    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends(){

        // given
        User friend = aUser()
            .friendsWith(ANOTHER_USER)
            .withTrips(TO_BRAZIL)
            .build();

//        User friend = new User();
//        friend.addFriend(ANOTHER_USER);
//        friend.addTrip(TO_BRAZIL);

        //when/then
        List<Trip> friendTrips = tripService.getFriendTrips(friend, REGISTERED_USER);
        assertThat(friendTrips.size()).isEqualTo(0);
    }

    @Test
    public void should_return_friend_trips_when_users_are_friends(){

        // given
        // builder pattern
        User friend = aUser().friendsWith(ANOTHER_USER, REGISTERED_USER)
            .withTrips(TO_BRAZIL, TO_LONDON)
            .build();

        //configure my mock to return trips
        given(tripDAO.tripsBy(friend)).willReturn(friend.trips());

//        User friend = new User();
//        friend.addFriend(ANOTHER_USER);
//        friend.addFriend(loggedInUser);
//        friend.addTrip(TO_BRAZIL);
//        friend.addTrip(TO_LONDON);

        //when/then
        List<Trip> friendTrips = tripService.getFriendTrips(friend, REGISTERED_USER);

        assertThat(friendTrips.size()).isEqualTo(2);
    }

}