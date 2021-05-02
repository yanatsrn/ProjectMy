package com.example.project.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Match {
    private Long sportId;
    private String name;
    private String player1;
    private String player2;
    private double rate1;
    private double rate0;
    private double rate2;
    private LocalDate date;

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public double getRate1() {
        return rate1;
    }

    public void setRate1(double rate1) {
        this.rate1 = rate1;
    }

    public double getRate0() {
        return rate0;
    }

    public void setRate0(double rate0) {
        this.rate0 = rate0;
    }

    public double getRate2() {
        return rate2;
    }

    public void setRate2(double rate2) {
        this.rate2 = rate2;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Double.compare(match.rate1, rate1) == 0 && Double.compare(match.rate0, rate0) == 0 && Double.compare(match.rate2, rate2) == 0 && Objects.equals(sportId, match.sportId) && Objects.equals(name, match.name) && Objects.equals(player1, match.player1) && Objects.equals(player2, match.player2) && Objects.equals(date, match.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportId, name, player1, player2, rate1, rate0, rate2, date);
    }

    @Override
    public String toString() {
        return "Match{" +
                "sportId=" + sportId +
                ", name='" + name + '\'' +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", rate1=" + rate1 +
                ", rate0=" + rate0 +
                ", rate2=" + rate2 +
                ", date=" + date +
                '}';
    }
}


