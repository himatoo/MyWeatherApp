package com.example.like2.myweatherapp;

public class WeatherDataModel {
    private String city;
    private String country;
    private Long date;
    private Double tempday;
    private Double tempmax;
    private Double tempmin;
    private Double tempmor;
    private Double tempnight;
    private Double pressure;
    private Double humidity;
    private Double speed;
    private Double cloud;
    private String weather;

    public WeatherDataModel(String city, String country, Long date, Double tempday, Double tempmax, Double tempmin, Double tempmor, Double tempnight, Double pressure, Double humidity, Double speed, Double cloud, String weather) {
        this.city = city;
        this.country = country;
        this.date = date;
        this.tempday = tempday;
        this.tempmax = tempmax;
        this.tempmin = tempmin;
        this.tempmor = tempmor;
        this.tempnight = tempnight;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
        this.cloud = cloud;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getTempday() {
        return tempday;
    }

    public void setTempday(Double tempday) {
        this.tempday = tempday;
    }

    public Double getTempmax() {
        return tempmax;
    }

    public void setTempmax(Double tempmax) {
        this.tempmax = tempmax;
    }

    public Double getTempmin() {
        return tempmin;
    }

    public void setTempmin(Double tempmin) {
        this.tempmin = tempmin;
    }

    public Double getTempmor() {
        return tempmor;
    }

    public void setTempmor(Double tempmor) {
        this.tempmor = tempmor;
    }

    public Double getTempnight() {
        return tempnight;
    }

    public void setTempnight(Double tempnight) {
        this.tempnight = tempnight;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getCloud() {
        return cloud;
    }

    public void setCloud(Double cloud) {
        this.cloud = cloud;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
