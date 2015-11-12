package model;

import javax.persistence.*;

@Entity
public class Model {

    @Id
    @Column(name = "MODEL_ID")
    @GeneratedValue
    private Long modelId;

    @Column(name = "MODEL_NAME")
    private String modelName;

    @Version
    long version;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private DeviceType deviceType;

    private Long price;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
