package com.example.myexpo.entity;


import java.time.LocalDate;
import java.util.Set;

public class Expo {


    private Integer id;
    private String title;
    private String titleUa;
    private String description;
    private String descriptionUa;
    private LocalDate startDate;
    private LocalDate endDate;

    private int amount;

    private String imgName;
    private double ticketPrice;
    private boolean exhibited;


    private Set<Holle> holles;

    public static Builder builder() {
        return new Expo().new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUa() {
        return titleUa;
    }

    public void setTitleUa(String titleUa) {
        this.titleUa = titleUa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isExhibited() {
        return exhibited;
    }

    public void setExhibited(boolean exhibited) {
        this.exhibited = exhibited;
    }

    public Set<Holle> getHolles() {
        return holles;
    }

    public void setHolles(Set<Holle> holles) {
        this.holles = holles;
    }

    @Override
    public String toString() {
        return "Expo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", ticketPrice=" + ticketPrice +
                ", holles=" + holles +
                '}';
    }

    public enum Holle {
        RED, GREEN, BLUE;

    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Integer id) {
            Expo.this.id = id;
            return this;
        }

        public Builder title(String title) {
            Expo.this.title = title;
            return this;
        }

        public Builder titleUa(String titleUa) {
            Expo.this.titleUa = titleUa;
            return this;
        }

        public Builder description(String description) {
            Expo.this.description = description;
            return this;
        }

        public Builder descriptionUa(String description) {
            Expo.this.descriptionUa = description;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            Expo.this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            Expo.this.endDate = endDate;
            return this;
        }

        public Builder amount(int amount) {
            Expo.this.amount = amount;
            return this;
        }

        public Builder imgName(String imgName) {
            Expo.this.imgName = imgName;
            return this;
        }

        public Builder ticketPrice(double price) {
            Expo.this.ticketPrice = price;
            return this;
        }

        public Builder exhibited(boolean exhibited) {
            Expo.this.exhibited = exhibited;
            return this;
        }

        public Builder holles(Set<Holle> holles) {
            Expo.this.holles = holles;
            return this;
        }

        public Expo build() {
            return Expo.this;
        }

    }
}
