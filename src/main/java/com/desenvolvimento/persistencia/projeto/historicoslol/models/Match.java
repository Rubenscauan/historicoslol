package com.desenvolvimento.persistencia.projeto.historicoslol.models;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;

@Entity
@Document


@Table(name = "matches")

public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    private User user;

    @ManyToOne
	private Champion champion;

    public Match(int id) {
        this.id = id;
    }

    public Match() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    @Override
    public String toString() {
        return id + ", " + user + ", " + champion;
    }  

}
