package carselling.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Модель - объявление.
 */
public class Advertisement implements Comparable<Advertisement> {

    private int id;
    private Brand brand;
    private String model;
    private String description;
    private int year;
    private Carcase carcase;
    private int price;
    private Timestamp date;
    private boolean sold;
    private User user;

    public Advertisement() { }

    public Advertisement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Carcase getCarcase() {
        return carcase;
    }

    public void setCarcase(Carcase carcase) {
        this.carcase = carcase;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(Advertisement o) {
        return o.getDate().compareTo(this.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id == that.id
                && year == that.year
                && price == that.price
                && sold == that.sold
                && Objects.equals(brand, that.brand)
                && Objects.equals(model, that.model)
                && Objects.equals(description, that.description)
                && Objects.equals(carcase, that.carcase)
                && Objects.equals(date, that.date)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, description, year, carcase, price, date, sold, user);
    }
}

