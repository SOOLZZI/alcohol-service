package com.haruhanjan.alcoholservice.entity;

public enum AlcoholType {
    BEER("맥주"),
    WINE("와인"),
    SPIRIT("증류주"),
    COCKTAIL("칵테일"),
    SAKE("사케"),
    LIQUOR("고량주"),
    ;
    private final String valueKor;

    private AlcoholType(String valueKor) {
        this.valueKor = valueKor;
    }

    public String getValueKor() {
        return valueKor;
    }
}
