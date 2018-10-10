package com.swistak.CookBook.dto;

public class StepDto {

    private int number;
    private String value;

    public StepDto() {
    }

    public StepDto(int number, String value) {
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
