package com.example.myexpo.dto;

public class ExpoDto {

    private String imgName = "60.jpg";
    private String title;
    private String titleUa;
    private String description;
    private String descriptionUa;
    private String startDate;
    private String endDate;
    private String amount;
    private String ticketPrice;


    public static Builder builder() {
        return new ExpoDto().new Builder();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public class Builder {

        private Builder() {
        }

        public Builder title(String title) {
            ExpoDto.this.title = title;
            return this;
        }

        public Builder titleUa(String titleUa) {
            ExpoDto.this.titleUa = titleUa;
            return this;
        }

        public Builder description(String description) {
            ExpoDto.this.description = description;
            return this;
        }

        public Builder descriptionUa(String description) {
            ExpoDto.this.descriptionUa = description;
            return this;
        }

        public Builder startDate(String startDate) {
            ExpoDto.this.startDate = startDate;
            return this;
        }

        public Builder endDate(String endDate) {
            ExpoDto.this.endDate = endDate;
            return this;
        }

        public Builder amount(String amount) {
            ExpoDto.this.amount = amount;
            return this;
        }

        public Builder imgName(String imgName) {
            ExpoDto.this.imgName = imgName;
            return this;
        }

        public Builder ticketPrice(String price) {
            ExpoDto.this.ticketPrice = price;
            return this;
        }


        public ExpoDto build() {
            return ExpoDto.this;
        }
    }
}
