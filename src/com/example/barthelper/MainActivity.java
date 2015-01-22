package com.example.barthelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	String station = null;
	/* set up the url and key=MW9S-E7SL-26DU-VV8V that apply from Bart api and this key is for test ,you can apply by youself .
	 * when we get the url and with the selected bart station  by user we can parser then get the info . 
	*/
	String server_url = "http://api.bart.gov/api/etd.aspx?cmd=etd&orig="
			+ station + "&key=MW9S-E7SL-26DU-VV8V";

	private Spinner spinner_station = null;
	private ImageButton btn_Search = null;
	private TextView tv_bartName;
	private TextView tv_color;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		init();
		initListners();
	}

	private void initListners() {
		// TODO Auto-generated method stub
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();

	}

	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		spinner_station
				.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		String station_color = spinner_station.getSelectedItem().toString();
		colorMath(station_color);
	}

	private void colorMath(String station_color) {
		// TODO Auto-generated method stub
		if (station_color == "12th") {
			// test.setBackgroundResource(R.color.holo_green_light);
			tv_color.setBackgroundResource(R.color.orange);
		}
	}

	// this method implement the click on button event and also get the parsered
	// xml then set and display on text view .
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		btn_Search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String url_station = spinner_station.getSelectedItem()
						.toString();

				String new_url = "http://api.bart.gov/api/etd.aspx?cmd=etd&orig="
						+ url_station + "&key=MW9S-E7SL-26DU-VV8V";
				getXmlFromUrl(new_url);
				
				List<BartItem> bart_list = null;
				XMLPullParserHandler parser = new XMLPullParserHandler();
				// get the XML with URL
				InputStream is = getInputStream(new_url);
				Log.v("i", new_url);
				bart_list = parser.parse(is);
				Log.v("get parse", bart_list.toString());
				// use StringBuffer hold the vaule after parser xml .
				StringBuffer strBuf = new StringBuffer();
				for (int i = 0; i < bart_list.size(); i++) {
					BartItem mBartItem = bart_list.get(i);
					strBuf.append("\n");
					strBuf.append("-this the " + i + "th bart --info：\n");

					strBuf.append("Bart Destination :  "
							+ mBartItem.getDestination() + "\n");
					strBuf.append("Bart Abbreviation:  "
							+ mBartItem.getAbbreviation() + "\n");
					strBuf.append("Bart Platfrom:  " + mBartItem.getPlatform()
							+ "\n");
					strBuf.append("Bart Direction:  "
							+ mBartItem.getDirection() + "\n");
					strBuf.append("Time coming :  " + mBartItem.getMinutes()
							+ "mins \n");
					strBuf.append("Bart Color :  " + mBartItem.getColor()
							+ " \n");
				
				}
				
				tv_bartName.setText(strBuf.toString());
				
			}
		});

	}

	protected void init_bartIteminfo(String[] bart_items) {
		// TODO Auto-generated method stub
		String arr[] = bart_items;
		String str_bart = arr.toString();
		str_bart.substring(9, arr.length - 1);

		Log.v("========", bart_items.toString());
		Log.v("**", str_bart.toString());

	}

	// the method is implement get inputstream for URI
	public static InputStream getInputStream(String path) {
		InputStream inputStream = null;
		HttpURLConnection httpURLConnection = null;

		try {
			
			// accord the URL address init a object which  use for HttpURLConnection object.
			URL url = new URL(path);

			if (url != null) {
				
				// open Connection get the current link
				httpURLConnection = (HttpURLConnection) url.openConnection();

				// setting 3 sec respone time
				httpURLConnection.setConnectTimeout(3000);

				// setting allowed the input
				httpURLConnection.setDoInput(true);

				// set the get method
				httpURLConnection.setRequestMethod("GET");
				
				// get the connnect code , 200 means succeed , if not 200 means
				// failed.
				int responseCode = httpURLConnection.getResponseCode();
				if (responseCode == 200) {
					
					//getInputStream get the server data.
					inputStream = httpURLConnection.getInputStream();
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	public String getXmlFromUrl(String url) {
		String xml = null;

		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return xml;
	}

	private void init() {
		// this method only for init the each view like spinner ,btn , and
		// textview .
		spinner_station = (Spinner) findViewById(R.id.spinner_stations);
		btn_Search = (ImageButton) findViewById(R.id.btn_search);
		tv_bartName = (TextView) findViewById(R.id.textView_bartdetails);
		tv_bartName.setMovementMethod(ScrollingMovementMethod.getInstance());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

}
