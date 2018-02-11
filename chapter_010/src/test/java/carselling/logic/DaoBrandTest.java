package carselling.logic;

import carselling.models.Brand;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DaoBrandTest {

    private final DaoBrand brands = DaoBrand.getInstance();

    @Test
    public void testCreateAndGetById() {
        Brand brand = new Brand();
        brand.setName("test");
        this.brands.create(brand);
        Brand result = this.brands.getById(brand.getId());
        assertThat(brand, is(result));
    }

    @Test
    public void testGetAll() {
        Brand brand = new Brand();
        brand.setName("test");
        this.brands.create(brand);
        assertTrue(this.brands.getAll().contains(brand));
    }
}
