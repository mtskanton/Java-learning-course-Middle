package ru.job4j;

import org.junit.Test;
import ru.job4j.User;
import ru.job4j.UserConvert;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {
    /**
     * Test of converting ArrayList into HashMap
     */
    @Test
    public void whenConvertListToMapThenTheSameItemsWithId() {
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User(1, "name1", "city1");
        User user2 = new User(2, "name2", "city2");
        User user3 = new User(3, "name3", "city3");
        userList.addAll(Arrays.asList(user1, user2, user3));

        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> userListStoredInHashMap = userConvert.process(userList);
        User expected = userListStoredInHashMap.get(3);
        assertThat(expected, is(user3));
    }
}
