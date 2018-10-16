package com.example.androidalertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	        Button alert2 = (Button) findViewById(R.id.alert2);
	        alert2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	               // showDialog(ALERT_DIALOG2);
	                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	            	builder.setTitle("Dilog 2"); 
	            	builder.show();
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
