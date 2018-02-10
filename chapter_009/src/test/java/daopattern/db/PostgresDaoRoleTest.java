package daopattern.db;

import daopattern.entity.Role;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест CRUD сущности - роль.
 */
public class PostgresDaoRoleTest {

    private PostgresDaoRole pdr = new PostgresDaoRole();

    @Before
    public void createTables() {
        PostgresConnection.INSTANCE.createTables();
    }

    @Test
    public void whenGetRolesByIdThenRoleNameIsAdmin() {
        Role role = pdr.getById(1);
        assertThat(role.getId(), is(1));
        assertThat(role.getRole(), is("ADMIN"));
    }

    @Test
    public void whenGetAllRolesThenListOfExists() {
        List<Role> roles = pdr.getAll();
        assertThat(roles.size(), is(2));
        assertThat(roles.get(0).getRole(), is("ADMIN"));
        assertThat(roles.get(1).getRole(), is("USER"));
    }

    @Test
    public void whenCreateRoleThenAddedToList() {
        Role role = new Role();
        role.setRole("MANAGER");
        pdr.create(role);
        assertThat(pdr.getById(3).getRole(), is("MANAGER"));
    }

    @Test
    public void whenUpdateRoleThenUpdatedInTheList() {
        Role role = pdr.getById(2);
        role.setRole("ASSISTANT");
        pdr.update(role);
        assertThat(pdr.getById(2).getRole(), is("ASSISTANT"));
    }

    @Test
    public void whenDeleteRoleThenNoMoreInTheList() {
        Role role = new Role();
        role.setRole("ASSISTANT");
        pdr.create(role);

        Role created = pdr.getById(3);
        assertThat(created.getRole(), is("ASSISTANT"));

        pdr.delete(created);
        List<Role> roles = pdr.getAll();
        assertThat(roles.get(0).getRole(), is("ADMIN"));
        assertThat(roles.get(1).getRole(), is("USER"));
    }

    @Test
    public void whenRequestRoleRelatedItemsThenGetAll() {
        Role role = pdr.getById(2);
        List<String[]> list = pdr.getRelatedUsers(role);

        assertThat(list.size(), is(2));
        String[] first = list.get(0);
        assertThat(first[0], is("USER"));
        assertThat(first[1], is("user"));
        assertThat(first[2], is("user"));
        assertThat(first[3], is("Samara, Lesnaya, 7"));

        String[] second = list.get(1);
        assertThat(second[0], is("USER"));
        assertThat(second[1], is("third"));
        assertThat(second[2], is("third"));
        assertThat(second[3], is("Latvia, Pendulum, 34"));
    }
}
