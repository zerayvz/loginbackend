package com.zehra.loginbackend.model;

public class Player {
    private String name;
    private String initial;
    private String selectedCard;

    public Player() {}

    public Player(String name, String initial, String selectedCard) {
        this.name = name;
        this.initial = initial;
        this.selectedCard = selectedCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(String selectedCard) {
        this.selectedCard = selectedCard;
    }
}
