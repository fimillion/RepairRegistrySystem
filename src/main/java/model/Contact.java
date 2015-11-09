package model;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @Column(name = "CONTACT_ID")
    @GeneratedValue
    private Long contactId;

    @ManyToOne
    @JoinColumn(name ="CONTACT_TYPE_ID")
    private ContactType contactType;

    @Column(name = "CONTACT_VALUE")
    String value;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
