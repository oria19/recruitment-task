package com.szymonz.recruitmenttask.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class DbNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal number;

    public DbNumber() {
    }

    public DbNumber(BigDecimal number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }
}
