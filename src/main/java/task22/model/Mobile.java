package task22.model;

import java.util.Objects;

public class Mobile {
    private Integer id;
    private String  model;
    private Integer price;
    private String  manufacturer;


    public Mobile() {
    }

    public Mobile(int id, String model, Integer price, String manufacturer) {
        this.id           = id;
        this.model        = model;
        this.price        = price;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Mobile{" +
               "id=" + id +
               ", model='" + model + '\'' +
               ", price=" + price +
               ", manufacturer=" + manufacturer +
               '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mobile mobile = (Mobile) o;
        return getId()==(mobile.getId()) &&
                getModel().equals(mobile.getModel()) &&
                getPrice().equals(mobile.getPrice()) &&
                getManufacturer().equals(mobile.getManufacturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getPrice(), getManufacturer());
    }
}
