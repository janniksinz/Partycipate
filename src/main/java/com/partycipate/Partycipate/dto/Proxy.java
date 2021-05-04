package com.partycipate.Partycipate.dto;

public class Proxy{
    private Boolean proxy;
    private Boolean  vpn;
    private Boolean  tor;

    public Boolean getProxy() {
        return proxy;
    }

    public void setProxy(Boolean proxy) {
        this.proxy = proxy;
    }

    public Boolean getVpn() {
        return vpn;
    }

    public void setVpn(Boolean vpn) {
        this.vpn = vpn;
    }

    public Boolean getTor() {
        return tor;
    }

    public void setTor(Boolean tor) {
        this.tor = tor;
    }
}
