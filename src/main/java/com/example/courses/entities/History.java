package com.example.courses.entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_Currency")
    private Currency first_Currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_Currency")
    private Currency second_Currency;

    private Float f_value;
    private Float s_value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Currency getFirst_Currency() {
        return first_Currency;
    }

    public void setFirst_Currency(Currency first_Currency) {
        this.first_Currency = first_Currency;
    }

    public Currency getSecond_Currency() {
        return second_Currency;
    }

    public void setSecond_Currency(Currency second_Currency) {
        this.second_Currency = second_Currency;
    }

    public Float getF_value() {
        return f_value;
    }

    public void setF_value(Float f_value) {
        this.f_value = f_value;
    }

    public Float getS_value() {
        return s_value;
    }

    public void setS_value(Float s_value) {
        this.s_value = s_value;
    }
}
