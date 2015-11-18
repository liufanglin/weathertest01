package com.shangpai.bean;

public class Weather {
	private String date;
	private String weather;
	private String temperature;
	private String wind;
	public Weather(){};
	public Weather(String weather, String temperature, String wind,String date) {
		this.weather = weather;
		this.temperature = temperature;
		this.wind = wind;
		this.date=date;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	@Override
	public String toString() {
		return "日期"+date+"天气"+weather+"温度"+temperature+"风力"+wind;
	}
	

}
