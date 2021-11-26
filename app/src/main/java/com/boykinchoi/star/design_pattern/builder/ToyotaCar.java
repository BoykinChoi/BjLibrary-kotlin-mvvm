package com.boykinchoi.star.design_pattern.builder;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:41
 */
public class ToyotaCar {
    private String engine;
    private String wheel;
    private String style;
    private String color;
    private String gearbox;
    private String grade;
    private String price;

    private ToyotaCar(Builder builder) {
        this.engine = builder.engine;
        this.wheel = builder.wheel;
        this.style = builder.style;
        this.color = builder.color;
        this.gearbox = builder.gearbox;
        this.grade = builder.grade;
        this.price = builder.price;
    }

    public static class Builder {
        private String engine;
        private String wheel;
        private String style;
        private String color;
        private String gearbox;
        private String grade;
        private String price;

        public Builder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder setWheel(String wheel) {
            this.wheel = wheel;
            return this;
        }

        public Builder setStyle(String style) {
            this.style = style;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setGearbox(String gearbox) {
            this.gearbox = gearbox;
            return this;
        }

        public Builder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public ToyotaCar build() {
            return new ToyotaCar(this);
        }
    }

    public String getEngine() {
        return engine;
    }

    public String getWheel() {
        return wheel;
    }

    public String getStyle() {
        return style;
    }

    public String getColor() {
        return color;
    }

    public String getGearbox() {
        return gearbox;
    }

    public String getGrade() {
        return grade;
    }

    public String getPrice() {
        return price;
    }
}
