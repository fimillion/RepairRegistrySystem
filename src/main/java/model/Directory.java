package model;


import javax.persistence.*;

@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "directory_type")
abstract class Directory {

    @Id
    @GeneratedValue
    private Long directoryId;

    @Column(name = "DIRECTORY_NAME")
    private String directoryName;

    @Version
    long version;

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
