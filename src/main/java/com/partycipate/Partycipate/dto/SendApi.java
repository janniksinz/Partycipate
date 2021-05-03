package com.partycipate.Partycipate.dto;

import java.util.Set;

public class SendApi {
    private String ip;
    private Location location;
    private Set<String> domains;
    private String isp;
    private Proxy proxy;
    private AS as;

    public AS getAs() {
        return as;
    }

    public void setAs(AS as) {
        this.as = as;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<String> getDomains() {
        return domains;
    }

    public void setDomains(Set<String> domains) {
        this.domains = domains;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
