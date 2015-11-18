package com.example.weathertest01;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tv_show;
	private StringBuilder sb;
	protected static final int FAILTURE = 0;	
	protected static final int ALTER=1;
	protected static final int ERROR=2;
	protected static final int POST=3;
	private String address="%E5%8C%97%E4%BA%AC";
	private String key="E4805d16520de693a3fe707cdc962045";
	private Handler handler =new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what==FAILTURE) {
				Toast.makeText(MainActivity.this, "请求失败", 0).show();
			}
			if (msg.what==ERROR) {
				Toast.makeText(MainActivity.this, "请求错误", 0).show();
			}
			if (msg.what==ALTER) {
				Toast.makeText(MainActivity.this, "get请求成功", 0).show();
				System.out.println(sb+"222222222222222222");
				tv_show.setText(sb.toString());
			}
			if (msg.what==POST) {
				Toast.makeText(MainActivity.this, "post请求成功", 0).show();
				
				tv_show.setText(msg.obj.toString());
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_show=(TextView) findViewById(R.id.tv_show);
		
	}
	public void myStart(View view){
		
		final String address="%E5%8C%97%E4%BA%AC";
		final String key="5uAGF6wmZhTo2GLkHhmFZqvs";
		
			new Thread() {
				public void run() {
					try {
						//String path="http://api.map.baidu.com/telematics/v3/weather?location="+URLEncoder.encode(address, "UTF-8")+"&output=json&ak="+URLEncoder.encode(key, "UTF-8");
						//URL url = new URL(path);
						//URL url=new URL("http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak="+key+"&mcode=11");
						
						URL url=new URL("http://www.weather.com.cn/adat/sk/101110101.html");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
		
						conn.setConnectTimeout(3000);
					//	conn.connect();
						int code = conn.getResponseCode();
						if (200 == code) {
							InputStream is = conn.getInputStream();
							sb= WeatherParse.WeatherXmlparse(is);
							Message msg=Message.obtain();
							msg.what=ALTER;
							handler.sendMessage(msg);
						} else {
							Message msg = Message.obtain();
							msg.what = FAILTURE;
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
					
						Message msg = Message.obtain();
						msg.what = ERROR;
						handler.sendMessage(msg);
						e.printStackTrace();
					}
				};
			}.start();
		//	post();
	
			
			
			
	}
	public void post(){
		final String path = "http://www.weather.com.cn/adat/sk/101110101.html";
		new Thread() {
			public void run() {
				try {
					String data = "&location="+URLEncoder.encode(address,"UTF-8")+"&output=json&ak="+URLEncoder.encode(key,"UTF-8");
					  //location="+URLEncoder.encode(address, "UTF-8")+"&output=json&ak="+URLEncoder.encode(key, "UTF-8")            
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setConnectTimeout(3000);
					
					//设置Content-Type、Content-Length
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", data.length()+"");
					//设置是否可以写入数据
					conn.setDoOutput(true);
					//像服务器里写数据
					conn.getOutputStream().write(data.getBytes());
					conn.connect();
					int code = conn.getResponseCode();
					if (200 == code) {
						// 获得服务器端的响应数据
						InputStream is = conn.getInputStream();
						
						String result = WeatherParse.WeatherXmlparse(is).toString();
						Message msg = Message.obtain();
						msg.what=POST;
						msg.obj = result;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
		
	
}
