package com.cybersoft.osahaneat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "time_ship")
    private String timeShip;

    @Column(name = "price")
    private double price;

    @Column(name = "is_freeship")
    private boolean isFreeship;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @OneToMany(mappedBy = "food")
    private Set<RatingFood> listRatingFood;

    @OneToMany(mappedBy = "food")
    private Set<OrderItem> listOrderItem;

//    public Set<OrderItem> getListOrderItem() {
//        return listOrderItem;
//    }
//
//    public void setListOrderItem(Set<OrderItem> listOrderItem) {
//        this.listOrderItem = listOrderItem;
//    }
//
//    public Set<RatingFood> getListRatingFood() {
//        return listRatingFood;
//    }
//
//    public void setListRatingFood(Set<RatingFood> listRatingFood) {
//        this.listRatingFood = listRatingFood;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getTimeShip() {
//        return timeShip;
//    }
//
//    public void setTimeShip(String timeShip) {
//        this.timeShip = timeShip;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public void setFreeship(boolean isFreeship) {
//        this.isFreeship = isFreeship;
//    }
//
//    public boolean isFreeship() {
//        return isFreeship;
//    }
}
