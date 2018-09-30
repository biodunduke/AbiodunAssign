/*
Name: Abiodun Ojo
Student ID: N01178447
 */
package abiodun.ojo.lab;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;


public class AbiodunActivity1 extends AppCompatActivity {
    private Button buttonLoc;
    public static final int REQUEST_CODE = 1;
    int permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun1);
        permission = ContextCompat.checkSelfPermission(AbiodunActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION);

        try {
            Button buttonLoc = (Button) findViewById(R.id.button);

            buttonLoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    permission = ContextCompat.checkSelfPermission(AbiodunActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION);
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AbiodunActivity1.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                        if (ActivityCompat.shouldShowRequestPermissionRationale(AbiodunActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                            Toast.makeText(AbiodunActivity1.this, "Permission is required", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AbiodunActivity1.this, "Permission granted", Toast.LENGTH_SHORT).show();
                        setContentView(R.layout.activity_abiodun1);
                        TextView tv1 = (TextView) findViewById(R.id.textView2);
                        tv1.setText("ALLOW " + getResources().getString(R.string.app_name));
                        Toast.makeText(AbiodunActivity1.this, "Permission already granted " + getResources().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } catch (IOError error) {
            System.err.println("IOError: " + error.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: { //If permission is allowed
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(AbiodunActivity1.this, "Permission granted", Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_abiodun1);
                    TextView tv1 = (TextView) findViewById(R.id.textView2);
                    tv1.setText("ALLOW " + getResources().getString(R.string.app_name));

                } else { //If permission is not allowed
                    Toast.makeText(AbiodunActivity1.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_abiodun1);
                    TextView tv1 = (TextView) findViewById(R.id.textView2);
                    tv1.setText("DENY " + getResources().getString(R.string.app_name));
                }
            }
            return;
        }
    }

}
