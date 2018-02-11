package carselling.logic;

import carselling.models.Carcase;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class DaoCarcaseTest {

    private final DaoCarcase carcases = DaoCarcase.getInstance();

    @Test
    public void testCreateAndGetById() {
        Carcase carcase = new Carcase();
        carcase.setType("test");
        this.carcases.create(carcase);
        Carcase result = this.carcases.getById(carcase.getId());
        assertThat(carcase, is(result));
    }

    @Test
    public void testGetAll() {
        Carcase carcase = new Carcase();
        carcase.setType("test");
        this.carcases.create(carcase);
        assertTrue(this.carcases.getAll().contains(carcase));
    }

}
