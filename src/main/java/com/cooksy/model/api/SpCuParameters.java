package com.cooksy.model.api;

public class SpCuParameters {

    private final String start;
    private final String ingredients;
    private final String equipments;
    private final String types;

    public SpCuParameters(String start, String ingredients, String equipments, String types) {
        this.start = validationString(start);
        this.ingredients = validationString(ingredients);
        this.equipments = validationString(equipments);
        this.types = validationString(types);
    }

    public String getStart() {
        return start;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getEquipments() {
        return equipments;
    }

    public String getTypes() {
        return types;
    }

    private String validationString(String str) {
        return (str != null) ? str.replaceAll("'", "") : null;
    }
}
