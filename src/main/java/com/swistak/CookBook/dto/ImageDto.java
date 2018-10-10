package com.swistak.CookBook.dto;

public class ImageDto {

    private int number;
    private String value;

    public ImageDto() {
    }

    public ImageDto(int number, String value) {
        this.number = number;
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
