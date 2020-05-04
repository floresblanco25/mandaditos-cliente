package com.mandaditos.cliente;




//para la lista de servicios 
public class mHomeModel {

    private String ServiceName;
    private String ServiceDescription;
    private int ServiceImage;

    public mHomeModel(String ServiceName, String ServiceDescription, int ServiceImage) {
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
