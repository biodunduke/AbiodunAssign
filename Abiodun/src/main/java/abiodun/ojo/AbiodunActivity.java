/**
 * Abiodun Ojo
 * N01178447
 * Assignment 1
 * */
package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOError;

public class AbiodunActivity extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 1
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun);

        Button but_activity = (Button) findViewById(R.id.but_activity);
        try {
            but_activity.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AbiodunActivity.this, AbiodunActivity2.class);
                    startActivity(intent);
                }
            });
        } catch (IOError error) {
            System.err.println("IOError: " + error.getMessage());
        }
        try {
            Button but_camera = (Button) findViewById(R.id.but_camera);
            but_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
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
}
