package com.partycipate.Partycipate.dto;

public class RegionUser {

    private String id;
    private Long v;

    public RegionUser(String id, Long v) {
        this.id = id;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getV() {
        return v;
    }

    public void setV(Long v) {
        this.v = v;
    }
}