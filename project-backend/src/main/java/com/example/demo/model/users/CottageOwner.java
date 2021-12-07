package com.example.demo.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cottageOwner")
@DiscriminatorValue("CO")
public class CottageOwner extends User{

}
