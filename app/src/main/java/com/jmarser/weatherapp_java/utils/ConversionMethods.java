package com.jmarser.weatherapp_java.utils;

import android.content.Context;
import android.util.Log;

import com.jmarser.weatherapp_java.R;
import com.jmarser.weatherapp_java.api.models.CompassUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String getCurrentDayAndTime(Context context){
        Calendar calendar = Calendar.getInstance();
        String dayOfWeek = context.getString(getDayName(calendar.get(Calendar.DAY_OF_WEEK)));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String hour = sdf.format(calendar.getTime());
        return dayOfWeek + " " + hour;
    }

    public static String formatedDate(Calendar calendar, Context context){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return day + " " + context.getString(getMonthName(month)) + " " + year;
    }

    /**
     * Método con el que obtenemos el día de la semana partiendo de un dateTime
     * @param marca, float
     * @return int, obtenemos un int que es el identificador del string
     * */
    public static int getDayName(long marca){
        Date date = new Date(marca * 1000);
        switch(date.toString().substring(0,3)){
            case "Mon": return R.string.monday;
            case "Tue": return R.string.tuesday;
            case "Wed": return R.string.wednesday;
            case "Thu": return R.string.thursday;
            case "Fri": return R.string.friday;
            case "Sat": return R.string.saturday;
            case "Sun": return R.string.sunday;
            default: return R.string.undefined;
        }
    }

    public static int getDayName(int day){
        switch (day) {
            case 1: return R.string.sunday;
            case 2: return R.string.monday;
            case 3: return R.string.tuesday;
            case 4: return R.string.wednesday;
            case 5: return R.string.thursday;
            case 6: return R.string.friday;
            case 7: return R.string.saturday;
            default: return R.string.undefined;
        }
    }

    public static int getMonthName(int month){
        switch (month) {
            case 0: return R.string.january;
            case 1: return R.string.february;
            case 2: return R.string.march;
            case 3: return R.string.april;
            case 4: return R.string.may;
            case 5: return R.string.june;
            case 6: return R.string.july;
            case 7: return R.string.august;
            case 8: return R.string.september;
            case 9: return R.string.october;
            case 10: return R.string.november;
            case 11: return R.string.december;
            default: return R.string.undefined;
        }
    }

    public static String getIcon(String icono){
        String icon = icono.replace("-", "_");
        String url = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
        return url;
    }
}
