package com.example.demo.model.business;

import javax.persistence.*;

@Entity
@Table(name = "percent")
public class Percent {
    @Id
    @SequenceGenerator(name = "PercentGen", sequenceName = "PercentGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PercentGen")
    @Column(name="id", unique=true, nullable=false)
    private int id;

    @Column(name = "value", nullable = false)
    private double value;

    public Percent() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getValue() {return value;}
    public void setValue(double value) {this.value=value;}
}
