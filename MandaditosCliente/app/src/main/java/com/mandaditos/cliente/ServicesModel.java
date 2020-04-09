package com.mandaditos.cliente;

public class ServicesModel {

    private String ServiceName;
    private String ServiceDescription;
    private int ServiceImage;

    public ServicesModel(String ServiceName, String ServiceDescription, int ServiceImage) {
        this.ServiceName = ServiceName;
        this.ServiceDescription = ServiceDescription;
        this.ServiceImage = ServiceImage;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public String getServiceDescription() {
        return ServiceDescription;
    }

    public int getServiceImage() {
        return ServiceImage;
    }
}
