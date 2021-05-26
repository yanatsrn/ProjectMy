package com.example.project.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Player {
    private String player;
    private Double rate;
    private LocalDate date;
    private String resultOfMatch;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResultOfMatch() {
        return resultOfMatch;
    }

    public void setResultOfMatch(String resultOfMatch) {
        this.resultOfMatch = resultOfMatch;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player1 = (Player) o;
        return Objects.equals(player, player1.player) && Objects.equals(date, player1.date) && Objects.equals(resultOfMatch, player1.resultOfMatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, date, resultOfMatch);
    }
}
