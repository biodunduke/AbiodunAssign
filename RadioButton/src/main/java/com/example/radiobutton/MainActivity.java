package com.example.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butDisplay = (Button)findViewById(R.id.but_display);
        butDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = new String();
                RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup01);
                int selected = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selected);
                str=("Option clicked is " + rb.getText());
                Toast tv = Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG);
                tv.show();
            }
        });
        RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup01);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("chk","id"+checkedId);
                if(checkedId ==R.id.rad_01){
                    Toast.makeText(MainActivity.this,getString(R.string.rad_1)+ " "+getString(R.string.checked),Toast.LENGTH_LONG).show();
                }
                else if(checkedId==R.id.rad_02){
                    Toast.makeText(MainActivity.this,getString(R.string.rad_2)+  " "+getString(R.string.checked),Toast.LENGTH_LONG).show();
                }
                else if(checkedId==R.id.rad_03){
                    Toast.makeText(MainActivity.this,getString(R.string.rad_3)+  " "+getString(R.string.checked),Toast.LENGTH_LONG).show();
                }
                else if(checkedId==R.id.rad_04){
                    Toast.makeText(MainActivity.this,getString(R.string.rad_4)+ " "+getString(R.string.checked),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
