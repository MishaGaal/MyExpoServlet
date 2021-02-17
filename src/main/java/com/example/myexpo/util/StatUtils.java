package com.example.myexpo.util;


public class StatUtils {
    private Integer id;
    private String title;
    private Long count;

    public static Builder builder() {
        return new StatUtils().new Builder();
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Integer id) {
            StatUtils.this.id = id;
            return this;
        }

        public Builder title(String title) {
            StatUtils.this.title = title;
            return this;
        }

        public Builder count(Long count) {
            StatUtils.this.count = count;
            return this;
        }

        public StatUtils build() {
            return StatUtils.this;
        }
    }

}
