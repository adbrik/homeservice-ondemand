package com.seg2015.group.homeserviceondemand;

public class FullService {

    String serviceName;
    String serviceRate;
    String provider;
    String serviceRating;


    public FullService(String provider, String serviceName, String serviceRate, String serviceRating){
        this.provider = provider;
        this.serviceName = serviceName;
        this.serviceRate = serviceRate;
        this.serviceRating = serviceRating;
    }

    public FullService(){

    }

    public String getProvider() {
        return provider;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceRate() {
        return serviceRate;
    }

    public String getServiceRating() {
        return serviceRating;
    }
}
