package com.example.bookmyshow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter



public class Theator extends BaseModel{

    private String name;
    private String address;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "theator")
    private List<Auditorium> auditoriumList;
}
