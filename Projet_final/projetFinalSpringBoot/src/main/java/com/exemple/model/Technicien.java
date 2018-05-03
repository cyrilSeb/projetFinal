package com.exemple.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technicien")
public class Technicien extends User {

}
