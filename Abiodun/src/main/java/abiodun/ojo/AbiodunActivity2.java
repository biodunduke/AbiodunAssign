/**
 * Abiodun Ojo
 * N01178447
 * Assignment 1
 */
package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AbiodunActivity2 extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 1
     * */
    private Button butDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun2);

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        TextView dateText = (TextView) findViewById(R.id.text_time);
        dateText.setText(date);
        try {
            Button buttonToast = (Button) findViewById(R.id.but_toast);
            buttonToast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AbiodunActivity2.this, R.string.text_toast, Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOError error) {
            System.err.println("IOError: " + error.getMessage());
        }
        try {
            Button butDial = (Button) findViewById(R.id.but_dial);
            butDial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri number = Uri.parse("tel:6477793641");
                    Intent intent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(intent);
                }
            });
        } catch (IOError error) {
            System.err.println("IOError: " + error.getMessage());
        }

        try {
            Button buttonToastLand = (Button) findViewById(R.id.but_toast2);

            buttonToastLand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AbiodunActivity2.this, R.string.text_toast_land, Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOError error) {
            System.err.println("IOError: " + error.getMessage());
        }
        try {
            Button buttonGoHome = (Button) findViewById(R.id.button3);
            buttonGoHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AbiodunActivity2.this, AbiodunActivity.class);
                    startActivity(intent);
                }
            });
        } catch (IOError error) {
            System.err.println("IOError: " + error.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menu) {
        super.onOptionsItemSelected(menu);
        switch (menu.getItemId()) {
            case R.id.menu_help:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.weblink))); //Get the resource from string.xml
                    startActivity(intent);
                } catch (ActivityNotFoundException error) {
                    System.err.println("IOError: " + error.getMessage());
                }
                break;
            case R.id.menu_info:
                Toast.makeText(this, R.string.app_info, Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_myname:
                Toast.makeText(this, R.string.text_hs_toast, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, R.string.text_url_redirect, Toast.LENGTH_LONG).show();
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.weblink))); //Get the resource from string.xml
                    startActivity(intent);
                } catch (ActivityNotFoundException error) {
                    System.err.println("IOError: " + error.getMessage());
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }
}
