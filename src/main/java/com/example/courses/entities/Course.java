package com.example.courses.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private     Long id;
    private     LocalDate date;
    private     Integer Nominal;
    private     Float Value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Currency_id")
    private Currency currency;


    public Course(){}
    public Course(LocalDate date, Integer Nominal, Float Value, Currency Currency_id){
        this.date     = date;
        this.Nominal   = Nominal;
        this.Value   = Value;
        this.currency  = Currency_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNominal() {
        return Nominal;
    }

    public void setNominal(Integer nominal) {
        Nominal = nominal;
    }

    public Float getValue() {
        return Value;
    }

    public void setValue(Float value) {
        Value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
