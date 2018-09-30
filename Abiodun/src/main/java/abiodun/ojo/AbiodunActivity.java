/**
 * Abiodun Ojo
 * N01178447
 * Assignment 1
 */
package abiodun.ojo;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
     */
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun);
        final int permission = ContextCompat.checkSelfPermission(AbiodunActivity.this, Manifest.permission.CAMERA);

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
                public void onClick(View v) { //Requesting runtime permmission
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AbiodunActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);

                        if (ActivityCompat.shouldShowRequestPermissionRationale(AbiodunActivity.this, Manifest.permission.CAMERA)) {
                            Toast.makeText(AbiodunActivity.this, R.string.toast_permission_needed, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);
                    }
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

    //Checking the result of Permission Request
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: { //If permission is allowed
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                } else { //If permission is not allowed
                    Toast.makeText(AbiodunActivity.this, R.string.toast_permission_not_granted, Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }
    }
}
