package com.example.courses.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    private  Long id;
    private  String Name;
    private  String Char;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getChar() {
        return Char;
    }

    public void setChar(String aChar) {
        Char = aChar;
    }
}