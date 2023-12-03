package com.desenvolvimento.persistencia.projeto.historicoslol.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "championPorName", query = "SELECT c FROM Champion c WHERE c.name = :name")
})
@Entity
@Document
@Table(name = "champions")
public class Champion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String range;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String resource;

    @Column(nullable = false)
    private String creationDate;

    public Champion(String name, String position, String range, String region, String resource) {
        this.name = name;
        this.position = position;
        this.range = range;
        this.region = region;
        this.resource = resource;
        this.creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    }

    public Champion() {
    }

    @Override
    public String toString() {
        return name + ", " + position + ", " + range + ", " + region
                + ", " + resource + ", Criado no dia " + creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Champion champion = (Champion) o;
        return Objects.equals(name, champion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
