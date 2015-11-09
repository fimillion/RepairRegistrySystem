package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Device {

    @Id
    @Column(name = "DEVICE_ID")
    @GeneratedValue
    private Long deviceId;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "MODEL_ID")
    private Model model;

    @OneToMany
    Set<RepairPart> repairParts = new HashSet<>();

    @OneToMany
    Set<Work> works = new HashSet<>();

    @Column(name = "STATE_DIRECTORY")
    private String stateDirectory;

    @Column(name = "MALFUNCTION")
    private String malfunction;

    @Column(name = "COMPLETENESS")
    private String completeness;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Set<RepairPart> getRepairParts() {
        return repairParts;
    }

    public void setRepairParts(Set<RepairPart> repairParts) {
        this.repairParts = repairParts;
    }

    public String getStateDirectory() {
        return stateDirectory;
    }

    public void setStateDirectory(String stateDirectory) {
        this.stateDirectory = stateDirectory;
    }

    public String getMalfunction() {
        return malfunction;
    }

    public void setMalfunction(String malfunction) {
        this.malfunction = malfunction;
    }

    public String getCompleteness() {
        return completeness;
    }

    public void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }
}
