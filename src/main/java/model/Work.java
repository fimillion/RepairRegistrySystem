package model;

import javax.persistence.*;

@Entity
public class Work {

    @Id
    @Column(name = "WORK_ID")
    @GeneratedValue
    private Long workId;

    @ManyToOne
    @JoinColumn(name = "WORKTYPE_ID")
    private WorkType workType;

    @Column(name = "WORK_PRICE")
    private Double workPrice;

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public Double getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(Double workPrice) {
        this.workPrice = workPrice;
    }
}
