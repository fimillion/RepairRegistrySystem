package model;

import javax.persistence.*;

@Entity
public class RepairPart {

    @Id
    @Column(name = "REPAIR_PART_ID")
    @GeneratedValue
    private Long repairPartId;

    @ManyToOne
    @JoinColumn(name ="MODEL_ID")
    private Model model;

    @Column(name = "REPAIR_PART_PRICE")
    private Double repairPartPrice;

    @Version
    private long version;

    public Long getRepairPartId() {
        return repairPartId;
    }

    public void setRepairPartId(Long repairPartId) {
        this.repairPartId = repairPartId;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Double getRepairPartPrice() {
        return repairPartPrice;
    }

    public void setRepairPartPrice(Double repairPartPrice) {
        this.repairPartPrice = repairPartPrice;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
