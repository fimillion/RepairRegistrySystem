package model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
public class DeviceType {

    @Id
    @Column(name = "DEVICE_TYPE_ID")
    @GeneratedValue
    private Long deviceTypeId;

    @Column(name = "DEVICE_TYPE_NAME")
    private String deviceTypeName;

    @Column(name = "DEVICE_TYPE_DESCRIPTION")
    private String deviceTypeDescription;

    @ManyToMany(mappedBy = "deviceTypes")
    private Set<Brand> brands=new HashSet<>();

    @Version
    private long version;

    public Long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeDescription() {
        return deviceTypeDescription;
    }

    public void setDeviceTypeDescription(String deviceTypeDescription) {
        this.deviceTypeDescription = deviceTypeDescription;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Set<Brand> getBrands() {

        return Collections.unmodifiableSet(brands);
    }


    public void addBrand(Brand brand){
        if(brand!=null){
            if(brands.add(brand)){
             brand.getDeviceTypes().add(this);
            }

        }
    }

    public void removeBrand(Brand brand){
        if(brand!=null){
            if(brands.remove(brand)){
                brand.getDeviceTypes().remove(this);
            }

        }
    }
}