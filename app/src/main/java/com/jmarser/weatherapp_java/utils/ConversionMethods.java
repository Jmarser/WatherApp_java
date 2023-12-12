package com.jmarser.weatherapp_java.utils;

import com.jmarser.weatherapp_java.R;
import com.jmarser.weatherapp_java.api.models.CompassUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ConversionMethods {

    public static String getHumidity(float humedad){
        Double percent = (double) humedad;
        return percent.intValue() + "%";
    }

    public static String getVisibility(double visibility){
        if (visibility < 1000){
            return String.valueOf(visibility) + " m";
        }else{
            double dis = visibility / 1000;
            return String.valueOf(dis) + " Km";
        }
    }

    public static String getUvi(float uvi){
        int uv = (int) uvi;
        return String.valueOf(uv);
    }

    public static String getTemperature(double temperature){
        return Math.round((double) temperature) + "º";
    }

    public static String getWindVelocity(double windVelocity){
        return Math.round(windVelocity) + " Km/h";
    }

    public static CompassUtil getDegToCompass(double degToCompass){
        String[] cardinals = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
        int index = (int) Math.round(((double) degToCompass % 360) / 45);
        String cardinal = cardinals[index] + " " + degToCompass + "º";
        int imgRessource = 0;
        switch (index){
            case 0:
                imgRessource = R.drawable.img_brujula_n;
                break;
            case 1:
                imgRessource = R.drawable.img_brujula_ne;
                break;
            case 2:
                imgRessource = R.drawable.img_brujula_e;
                break;
            case 3:
                imgRessource = R.drawable.img_brujula_se;
                break;
            case 4:
                imgRessource = R.drawable.img_brujula_s;
                break;
            case 5:
                imgRessource = R.drawable.img_brujula_sw;
                break;
            case 6:
                imgRessource = R.drawable.img_brujula_w;
                break;
            case 7:
                imgRessource = R.drawable.img_brujula_nw;
                break;
            case 8:
                imgRessource = R.drawable.img_brujula_n;
                break;
        }

        return new CompassUtil(cardinal, imgRessource);
    }

    public static String getPressure(double pressure){
        int press = (int) pressure;
        return String.valueOf(press) + " hPa";
    }

    public static String getHora(float marca){
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Date date = new Date((long) (marca * 1000));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    public static String getIcon(String icono){
        String icon = icono.replace("-", "_");
        String url = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
        return url;
    }
}
