package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MALFUNC")
public class Malfunctions extends Directory {
}
