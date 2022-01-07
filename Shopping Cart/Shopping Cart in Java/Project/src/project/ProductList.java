
package project;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ProductList {
    private String brand;
    private String model;
    private int price;
    private int qty;
    private String description;
    private String mimage;

    public ProductList(String brand, String model, int price, int qty, String description, String image) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.mimage = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimage() {
         return mimage;
    }

    public void setMimage(String mimage) {
        this.mimage = mimage;
    }
    
    
    
}
