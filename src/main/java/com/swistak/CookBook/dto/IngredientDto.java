package com.swistak.CookBook.dto;

public class IngredientDto {

    private int number;
    private String name;
    private String value;

    public IngredientDto() {
    }

    public IngredientDto(int orderNumber, String name, String value) {
        this.number = orderNumber;
        this.name = name;
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
