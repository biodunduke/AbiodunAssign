package abiodun.ojo.style_testing;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button simpleBtn, bigBtn;
    private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.customText);
        tv2 = (TextView) findViewById(R.id.bigText);

        simpleBtn = (Button) findViewById(R.id.simpleTextButton);
        simpleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                tv1.setTextAppearance(getApplicationContext(), R.style.SimpleStyle);
            }
        });

        bigBtn = (Button) findViewById(R.id.bigTextButton);
        bigBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                tv2.setTextAppearance(getApplicationContext(), R.style.SimpleStyle_BigPurple);
            }
        });
    }

}

