package com.cooksy.model.api;

public class SpCuParameters {

    private final String start;
    private final String ingredients;
    private final String equipments;
    private final String types;

    public SpCuParameters(String start, String ingredients, String equipments, String types) {
        this.start = start.replaceAll("'", "");
        this.ingredients = ingredients.replaceAll("'", "");
        this.equipments = equipments.replaceAll("'", "");
        this.types = types.replaceAll("'", "");
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
}
