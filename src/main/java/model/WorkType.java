package model;

import javax.persistence.*;

@Entity
public class WorkType {

    @Id
    @Column(name = "WORK_TYPE_ID")
    @GeneratedValue
    private Long workTypeId;

    @Column(name = "WORK_TYPE_NAME")
    private String workTypeName;

    @Column(name = "WORK_TYPE_PRICE")
    private Double workTypePrice;

    @Version
    private long version;

    public Long getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Long workTypeId) {

        this.workTypeId = workTypeId;
    }

    public String getWorkTypeName() {

        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {

        this.workTypeName = workTypeName;
    }

    public Double getWorkTypePrice() {

        return workTypePrice;
    }

    public void setWorkTypePrice(Double workTypePrice) {

        this.workTypePrice = workTypePrice;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
