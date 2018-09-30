/**
 * Abiodun Ojo
 * N01178447
 * Assignment 1
 */
package abiodun.ojo;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AbiodunActivity2 extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 1
     */
    private Button butDial;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun2);
        final int permission = ContextCompat.checkSelfPermission(AbiodunActivity2.this, Manifest.permission.CALL_PHONE);

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
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AbiodunActivity2.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                        if (ActivityCompat.shouldShowRequestPermissionRationale(AbiodunActivity2.this, Manifest.permission.CALL_PHONE)) {
                            Toast.makeText(AbiodunActivity2.this, R.string.toast_permission_needed, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Uri number = Uri.parse("tel:6477793641");
                        Intent intent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(intent);
                    }
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
            case R.id.homeAsUp: //Takes you to previous parent activity
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }

    //Checking the result of Permission Request
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: { //If permission is allowed
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Uri number = Uri.parse("tel:6477793641");
                    Intent intent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(intent);
                } else { //If permission is not allowed
                    Toast.makeText(AbiodunActivity2.this, R.string.toast_permission_not_granted, Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }
    }
}
