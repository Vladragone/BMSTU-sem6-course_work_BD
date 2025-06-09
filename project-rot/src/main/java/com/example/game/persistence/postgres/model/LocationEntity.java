package com.example.game.persistence.postgres.model;

import jakarta.persistence.*;

@Entity
@Table(name = "game_locations")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double lat;

    @Column(name = "`lng`")
    private Double lng;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }

    public Double getLng() { return lng; }
    public void setLng(Double lng) { this.lng = lng; }
}
