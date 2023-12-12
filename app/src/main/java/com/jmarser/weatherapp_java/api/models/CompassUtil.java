package com.jmarser.weatherapp_java.api.models;

public class CompassUtil {

    private String direction;
    private int imgRessource;

    public CompassUtil(String direction, int imgRessource) {
        this.direction = direction;
        this.imgRessource = imgRessource;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getImgRessource() {
        return imgRessource;
    }

    public void setImgRessource(int imgRessource) {
        this.imgRessource = imgRessource;
    }
}
