package com.zehra.loginbackend.model;

import jakarta.persistence.*;

@Entity
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;      // Kartı seçen oyuncu
    private String cardValue;     // Seçilen kart (örneğin: "5", "?", "☕️")
    private String explanation;   // Açıklama (isteğe bağlı)

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
