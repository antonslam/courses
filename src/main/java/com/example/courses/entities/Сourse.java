package com.example.courses.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Сourse")
public class Сourse {

    @Id
    private Date date;
    private  Integer Nominal;
    private  Float Value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Currency_id")
    private Currency currency;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
