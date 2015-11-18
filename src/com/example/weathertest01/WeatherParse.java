package com.example.weathertest01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
public class WeatherParse {
	
	private static StringBuilder sb,weathersb;

	public static StringBuilder WeatherXmlparse(InputStream is){
		sb=new StringBuilder();
		weathersb=new StringBuilder();
		BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		String line=null;
		JSONObject rootObject;
		try {
			while (((line=reader.readLine()))!=null) {
				sb.append(line);
				System.out.println(sb.toString());
			}
			reader.close();
			System.out.println(sb+"==================");
			String json=sb.toString();
			
			JSONTokener token=new JSONTokener(json);
			rootObject = (JSONObject) token.nextValue();
		//	{"weatherinfo":{"city":"西安","cityid":"101110101",
		//		"temp":"9","WD":"东北风","WS":"2级","SD":"78%","WSE":"2","time":"10:00",
		//		"isRadar":"1","Radar":"JC_RADAR_AZ9290_JB","njd":"5100","qy":"971"}}	
			JSONObject result=rootObject.getJSONObject("weatherinfo");
			weathersb.append(result.getString("city"));
			weathersb.append(result.getString("cityid"));
			weathersb.append(result.getString("WD"));
			weathersb.append(result.getString("WS"));
			weathersb.append(result.getString("SD"));
			weathersb.append(result.getInt("WSE"));
			weathersb.append(result.getString("time"));
			weathersb.append(result.getString("isRadar"));
			weathersb.append(result.getString("Radar"));
			weathersb.append(result.getString("njd"));
			weathersb.append(result.getString("qy"));
			System.out.println(weathersb+"___________________________________");
			
			
			/*weathersb.append("error："+rootObject.getInt("error"));
			System.out.println("---------------------------------------"+weathersb.toString());
			weathersb.append("status："+rootObject.getInt("status"));
			weathersb.append("日期"+rootObject.getBoolean("date"));
			//拿到里面的数组
			JSONArray array=rootObject.getJSONArray("results");
			for (int i = 0; i < array.length(); i++) {
				if (i==0) {
					JSONObject result=array.getJSONObject(i);
					weathersb.append(result.getString("currentCity"));
					weathersb.append(result.getString("pm25"));
					JSONArray arrayIndex=result.getJSONArray("index");
					for (int j = 0; j < arrayIndex.length(); j++) {
						JSONObject result1=arrayIndex.getJSONObject(i);
						weathersb.append(result1.get("title"));
						weathersb.append(result1.get("zs"));
						weathersb.append(result1.get("tipt"));
						weathersb.append(result1.get("des"));
					}
					JSONArray arrayData=result.getJSONArray("weather_data");
					for (int j = 0; j < arrayData.length(); j++) {
						JSONObject result1=arrayIndex.getJSONObject(i);
						weathersb.append(result1.getString("date"));
						weathersb.append(result1.getString("dayPictureUrl"));
						weathersb.append(result1.getString("nightPictureUrl"));
						weathersb.append(result1.getString("weather"));
						weathersb.append(result1.getString("weather"));
						weathersb.append(result1.getString("wind"));
						weathersb.append(result1.getString("temperature"));
					}
				}
			}*/
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		return weathersb;
		
		
	}
}
