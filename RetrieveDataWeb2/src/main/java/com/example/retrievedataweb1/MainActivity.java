package com.example.retrievedataweb1;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
TextView tv2, tv1;
Button btnRetrieve;
String stringText = "";

//final String textSource = "http://sites.google.com/site/androidersite/text.txt"; //302
//final String textSource = "http://api.flickr.com/services/feeds/photos_public.gne?id=26648248@N04&lang=en-us&format=atom"; //404
final String textSource = "https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		
		btnRetrieve = (Button) findViewById(R.id.btn_retrieve);
		
        
		
		btnRetrieve.setOnClickListener(new View.OnClickListener() {
			public void onClick (View v) {

				try
				{
					 tv1.setText("....In Progress.. Please wait");
					// To DO
					 new Thread() { //This moves the network call to another thread (Process)
				            @Override
				            public void run() {
				                
				            	URL url = null;
								HttpURLConnection urlConnection;

				                try {
				                   
				                	url = new URL(textSource);

									urlConnection = (HttpURLConnection) url.openConnection();

									int responseCode = urlConnection.getResponseCode();

									if(responseCode == HttpURLConnection.HTTP_OK){ //Check if server can be reached
										BufferedReader bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
										String StringBuffer;

										while ((StringBuffer = bufferReader.readLine()) != null) {
											stringText += StringBuffer; //Keep concatenating the buffer line by line
										}
										bufferReader.close(); //Close the stream
									}


									   
				                    runOnUiThread(new Runnable() {
				                        @Override
				                        public void run() {
				                        	tv2.setText(stringText);
				                        	tv1.setText("Finished...!!");
				                        	Toast.makeText(getBaseContext(), "Data Retrieved Successufully", Toast.LENGTH_LONG).show();
				                            try {
				                                
				                            } catch (Exception e) {
				                                e.printStackTrace();
				                            }
				                        }
				                    });

				                } catch (Exception e) {
				                    e.printStackTrace();
				                } 

							}
				        }.start();
				}
				catch (Exception e){
					Toast.makeText(getBaseContext(), "Error Occurred " +e.toString(), Toast.LENGTH_LONG).show();
				}
			}

			// No used
			private void ReadData() {
				// TODO Auto-generated method stub
				try
				{
					
					 URL textUrl;
					  try {
					   textUrl = new URL(textSource);
					   BufferedReader bufferReader = new BufferedReader(new InputStreamReader(textUrl.openStream()));
					   String StringBuffer;
					         String stringText = "";
					   while ((StringBuffer = bufferReader.readLine()) != null) {
					    stringText += StringBuffer;
					   }
					         bufferReader.close();
					         tv2.setText(stringText);
					  } catch (MalformedURLException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
					   tv2.setText(e.toString());
					  } catch (IOException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
					   tv2.setText(e.toString());
					  }
					  
					  tv1.setText("Finished!");
							        

				
				Toast.makeText(getBaseContext(), "Data Retrieved Successufully", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex){
					tv2.setText(ex.toString());
					Toast.makeText(getBaseContext(), "Error Occurred " +ex.toString(), Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
