package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue
    private Long personId;

    @Column(name = "PERSON_FIRST_NAME")
    private String personFirstName;

    @Column(name = "PERSON_SECOND_NAME")
    private String personSecondName;

    @OneToMany
    Set<Contact> contacts = new HashSet<>();

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonSecondName() {
        return personSecondName;
    }

    public void setPersonSecondName(String personSecondName) {
        this.personSecondName = personSecondName;
    }

    public void addContact(Contact contact){
        if(contact!= null){
            contacts.add(contact);
        }
    }

    public void removeContact(Contact contact){
        if(contact!= null){
            contacts.remove(contact);
        }
    }
}
