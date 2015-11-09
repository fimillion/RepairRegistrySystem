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
}
