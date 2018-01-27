package daopattern.db;

import daopattern.entity.Music;
import daopattern.entity.Role;
import daopattern.entity.Address;
import daopattern.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест CRUD сущности - пользователь.
 */
public class PostgresDaoUserTest {

    private PostgresDaoUser pdu = new PostgresDaoUser();

    @Before
    public void createTables() {
        PostgresConnection.INSTANCE.createTables();
    }

    @Test
    public void whenGetUserByIdThenAllDataAvailable() {
        User user = pdu.getById(1);
        int id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        Role role = user.getRole();
        Address address = user.getAddress();
        List<Music> list = user.getMusic();

        assertThat(id, is(1));
        assertThat(login, is("root"));
        assertThat(password, is("root"));
        assertThat(role.getRole(), is("ADMIN"));
        assertThat(address.getAddress(), is("Moscow, Mira, 3"));
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getMusic(), is("ROCK"));
    }

    @Test
    public void whenGetAllUsersThenListOfExists() {
        List<User> users = pdu.getAll();
        assertThat(users.size(), is(3));
        assertThat(users.get(0).getLogin(), is("root"));
        assertThat(users.get(1).getLogin(), is("user"));
        assertThat(users.get(2).getLogin(), is("third"));
    }

    @Test
    public void whenCreateUserThenAddedToList() {
        Role role = new Role();
        role.setId(1);

        Address address = new Address();
        address.setAddress("ADDRESS");

        Music music1 = new Music();
        music1.setId(2);
        Music music2 = new Music();
        music2.setId(3);

        List<Music> list = new ArrayList<>(Arrays.asList(music1, music2));

        User user = new User();
        user.setLogin("LOGIN");
        user.setPassword("PASSWORD");
        user.setRole(role);
        user.setAddress(address);
        user.setMusic(list);

        pdu.create(user);

        User added = pdu.getById(4);
        int idAdded = added.getId();
        String loginAdded = added.getLogin();
        String passwordAdded = added.getPassword();
        Role roleAdded = added.getRole();
        Address addressAdded = added.getAddress();
        List<Music> listAdded = added.getMusic();

        assertThat(idAdded, is(4));
        assertThat(loginAdded, is("LOGIN"));
        assertThat(passwordAdded, is("PASSWORD"));
        assertThat(roleAdded.getRole(), is("ADMIN"));
        assertThat(addressAdded.getAddress(), is("ADDRESS"));
        assertThat(listAdded.size(), is(2));
        assertThat(listAdded.get(0).getMusic(), is("RAP"));
        assertThat(listAdded.get(1).getMusic(), is("POP"));
    }

    @Test
    public void whenUpdateUserThenUpdatedInTheList() {
        User user = pdu.getById(2);

        Role role = new Role();
        role.setId(1);

        Address address = new Address();
        int addressId = user.getAddress().getId();
        address.setId(addressId);
        address.setAddress("ADDRESS");

        Music music = new Music();
        music.setId(1);

        List<Music> list = new ArrayList<>(Arrays.asList(music));

        user.setId(2);
        user.setLogin("LOGIN");
        user.setPassword("PASSWORD");
        user.setRole(role);
        user.setAddress(address);
        user.setMusic(list);

        pdu.update(user);

        User updated = pdu.getById(2);
        String loginUpdated = updated.getLogin();
        String passwordUpdated = updated.getPassword();
        Role roleUpdated = updated.getRole();
        Address addressUpdated = updated.getAddress();
        List<Music> listUpdated = updated.getMusic();

        assertThat(loginUpdated, is("LOGIN"));
        assertThat(passwordUpdated, is("PASSWORD"));
        assertThat(roleUpdated.getRole(), is("ADMIN"));
        assertThat(addressUpdated.getAddress(), is("ADDRESS"));
        assertThat(listUpdated.size(), is(1));
        assertThat(listUpdated.get(0).getMusic(), is("ROCK"));
    }

    @Test
    public void whenDeleteUserThenNoMoreInTheList() {
        User user = pdu.getById(1);

        pdu.delete(user);

        List<User> users = pdu.getAll();
        assertThat(users.size(), is(2));
        assertThat(users.get(0).getId(), is(2));
        assertThat(users.get(1).getId(), is(3));
    }

    @Test
    public void whenSearchByAddressThenFind() {
        String address = "ra";
        List<User> users = pdu.getUsersByAddress(address);
        assertThat(users.size(), is(2));
        assertThat(users.get(0).getLogin(), is("root"));
        assertThat(users.get(1).getLogin(), is("user"));
    }

    @Test
    public void whenSearchByRolesThenFind() {
        PostgresDaoRole pdr = new PostgresDaoRole();
        Role admin = pdr.getById(1);
        Role user = pdr.getById(2);

        List<User> admins = pdu.getUsersByRole(admin);
        assertThat(admins.size(), is(1));
        assertThat(admins.get(0).getLogin(), is("root"));

        List<User> users = pdu.getUsersByRole(user);
        assertThat(users.size(), is(2));
        assertThat(users.get(0).getLogin(), is("user"));
        assertThat(users.get(1).getLogin(), is("third"));
    }
}
