package com.partycipate.Partycipate.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.*;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.DatabaseReader.*;

import javax.xml.crypto.Data;


public class GetLocationExample {




    public void getLocation(String ipAddress) {

        try {
            File file = new File(
                    "C:\\Users\\Besitzer\\IdeaProjects\\Partycipate\\src\\main\\resources\\GeoLite2-Country_20210427GeoLite2-Country.mmdb");
           DatabaseReader databaseReader = new DatabaseReader.Builder(file).build();
           CountryResponse country = databaseReader.country(InetAddress.getByName("5.56.225.199"));
           System.out.println(country.getCountry().getIsoCode());

        } catch (IOException | GeoIp2Exception e) {
            System.err.println(e.getMessage());
        }



    }
}