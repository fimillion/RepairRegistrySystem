package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Brand {
    @Id
    @Column(name = "BRAND_ID")
    @GeneratedValue
    private Long brandId;

    @Column(name = "BRAND_NAME")
    private String brandName;

    @Column(name = "BRAND_COUNTRY")
    private String brandCountry;

    @Column(name = "BRAND_DESCRIPTION")
    private String brandDescription;

    @Version
    long version;

    @ManyToMany
    private Set<DeviceType> deviceTypes=new HashSet<>();

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCounry() {
        return brandCountry;
    }

    public void setBrandCounry(String brandCountry) {
        this.brandCountry = brandCountry;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {

        this.version = version;
    }

    public Set<DeviceType> getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(Set<DeviceType> deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public  void addDeviceType(DeviceType deviceType){
        if(deviceType!=null){
            if(deviceTypes.add(deviceType)){
                deviceType.getBrands().add(this);
            }
        }
    }

    public void removeDeviceType(DeviceType deviceType){
        if(deviceType!=null){
            if (deviceTypes.remove(deviceType)){
                deviceType.getBrands().remove(this);
            }
        }
    }
}
