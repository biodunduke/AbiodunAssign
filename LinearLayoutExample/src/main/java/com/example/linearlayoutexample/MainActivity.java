package com.example.linearlayoutexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText text1, text2,text3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Button send =(Button)findViewById(R.id.bSend);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				text1 = (EditText)findViewById(R.id.et1);  //Get the EditText by ID and assign to an object variable
				text2 = (EditText)findViewById(R.id.et2);
				text3 = (EditText)findViewById(R.id.et3);
				//Make a Toast object
				Toast tv = Toast.makeText(MainActivity.this,(text1.getText().toString()+" "+text2.getText().toString()+" "+ text3.getText().toString()),Toast.LENGTH_LONG);
				tv.show(); //Enable the toast to show
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
