package com.example.myexpo.entity;

public class Ticket {


    private Integer id;

    private Expo expo;

    private User user;

    public Ticket() {
    }

    public Ticket(Expo expo, User user) throws Exception {
        if (expo.getAmount() == 0 || !expo.isExhibited()) {
            throw new Exception("No more tickets left");
        }
        expo.setAmount(expo.getAmount() - 1);
        this.expo = expo;
        this.user = user;
    }

    public static Builder builder() {
        return new Ticket().new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Expo getExpo() {
        return expo;
    }

    public void setExpo(Expo expo) {
        this.expo = expo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return id.toString() + " : " + user.getUsername() + " : " + expo.getTitle();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Integer id) {
            Ticket.this.id = id;
            return this;
        }

        public Builder user(User user) {
            Ticket.this.user = user;
            return this;
        }

        public Builder expo(Expo expo) {
            Ticket.this.expo = expo;
            return this;
        }

        public Ticket build() {
            return Ticket.this;
        }

    }
}
