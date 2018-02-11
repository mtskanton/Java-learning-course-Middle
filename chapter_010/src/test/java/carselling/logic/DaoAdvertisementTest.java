package carselling.logic;

import carselling.models.Advertisement;
import carselling.models.Brand;
import carselling.models.Carcase;
import carselling.models.User;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertTrue;

public class DaoAdvertisementTest {

    private final DaoAdvertisement ads = DaoAdvertisement.getInstance();
    private final DaoBrand brands = DaoBrand.getInstance();
    private final DaoCarcase carcases = DaoCarcase.getInstance();
    private final DaoUser users = DaoUser.getInstance();

    @Test
    public void testGetById() {
        Advertisement ad = new Advertisement();
        this.ads.create(ad);
        Advertisement result = this.ads.getById(ad.getId());
        assertThat(ad, is(result));
    }

    @Test
    public void testGetAll() {
        Advertisement ad = new Advertisement();
        this.ads.create(ad);
        assertTrue(this.ads.getAll().contains(ad));
    }

    @Test
    public void createTest() {
        Advertisement ad = new Advertisement();
        Brand brand = new Brand();
        Carcase carcase = new Carcase();
        User user = new User();
        this.brands.create(brand);
        this.carcases.create(carcase);
        this.users.create(user);
        ad.setBrand(brand);
        ad.setModel("Focus");
        ad.setDescription("your best choice");
        ad.setYear(2018);
        ad.setCarcase(carcase);
        ad.setPrice(1000);
        ad.setDate(new Timestamp(System.currentTimeMillis()));
        ad.setSold(false);
        ad.setUser(user);
        this.ads.create(ad);
        Advertisement result = this.ads.getById(ad.getId());
        assertThat(ad, is(result));
    }

    @Test
    public void updateTest() {
        Advertisement ad = new Advertisement();
        Brand brand = new Brand();
        Carcase carcase = new Carcase();
        User user = new User();
        this.brands.create(brand);
        this.carcases.create(carcase);
        this.users.create(user);
        ad.setBrand(brand);
        ad.setModel("Focus");
        ad.setDescription("your best choice");
        ad.setYear(2018);
        ad.setCarcase(carcase);
        ad.setPrice(1000);
        ad.setDate(new Timestamp(System.currentTimeMillis()));
        ad.setSold(false);
        ad.setUser(user);
        this.ads.create(ad);
        assertThat(ad, is(this.ads.getById(ad.getId())));

        Brand brand2 = new Brand();
        Carcase carcase2 = new Carcase();
        User user2 = new User();
        this.brands.create(brand2);
        this.carcases.create(carcase2);
        this.users.create(user2);
        ad.setBrand(brand2);
        ad.setModel("Fiesta");
        ad.setDescription("updated description");
        ad.setYear(2017);
        ad.setCarcase(carcase2);
        ad.setPrice(20000);
        ad.setDate(new Timestamp(System.currentTimeMillis()));
        ad.setSold(true);
        ad.setUser(user2);
        assertThat(ad, not(this.ads.getById(ad.getId())));
        this.ads.update(ad);
        assertThat(ad, is(this.ads.getById(ad.getId())));
    }

    @Test
    public void testDelete() {
        Advertisement ad = new Advertisement();
        this.ads.create(ad);
        assertTrue(this.ads.getAll().contains(ad));
        this.ads.delete(ad);
        assertFalse(this.ads.getAll().contains(ad));
    }

    @Test
    public void testGetAllFilteredByBrand() {
        Advertisement ad = new Advertisement();
        Brand brand = new Brand();
        this.brands.create(brand);
        ad.setBrand(brand);
        this.ads.create(ad);
        String brandId = String.valueOf(brand.getId());
        boolean result = this.ads.getAllFiltered(null, brandId).contains(ad);
        assertTrue(result);
    }

    @Test
    public void testGetAllFilteredForToday() {
        Advertisement ad = new Advertisement();
        ad.setDate(new Timestamp(System.currentTimeMillis()));
        this.ads.create(ad);
        boolean result = this.ads.getAllFiltered("on", null).contains(ad);
        assertTrue(result);
    }
}
