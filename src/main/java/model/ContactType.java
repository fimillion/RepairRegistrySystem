package model;

import javax.persistence.*;

@Entity
public class ContactType {

    @Id
    @Column(name = "CONTACT_TYPE_ID")
    @GeneratedValue
    private Long contactTypeId;

    @Column(name = "CONTACT_TYPE_NAME")
    private String contactTypeName;

    @Column(name = "CONTACT_TYPE_MASK")
    private String contactTypeMask;

    @Version
    long version;

    public Long getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(Long contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public String getContactTypeMask() {
        return contactTypeMask;
    }

    public void setContactTypeMask(String contactTypeMask) {
        this.contactTypeMask = contactTypeMask;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
