package com.desenvolvimento.persistencia.projeto.historicoslol.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@NamedQueries({
        @NamedQuery(name = "userPorName", query = "SELECT u FROM User u WHERE u.name = :name")
})
@Entity
@Document


@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Match> historic;

    @Override
    public String toString() {
        return "Name = " + name;
    }

    public String toStringComHistorico() {
        StringBuilder result = new StringBuilder("Name = " + name + "\nHistorico:\n");

        for (Match match : historic) {
            result.append(match.toString()).append("\n");
        }

        return result.toString();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.historic = new ArrayList<>();
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Match> getHistoric() {
        return historic;
    }

    public void setHistoric(List<Match> historic) {
        this.historic = historic;
    }

    public void addNoHistoric(Match match) {
        historic.add(match);
    }

}
