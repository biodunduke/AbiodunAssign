package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Random;

import static android.Manifest.permission.CAMERA;

public class AbiodunActivity4 extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 2
     */
    Menu menu; //Global menu declaration to access menu item
    String url="";
    int imgID;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun4);

        //Get values passed from the previous screen
        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String type = bundle.getString("type");
        final String size = bundle.getString("size");
        final String toppings = bundle.getString("toppings");
        final String address = bundle.getString("address");
        imgID=bundle.getInt("id");
        url = bundle.getString("url");

        TextView textOrder = findViewById(R.id.abiodun_order_details);
        textOrder.setText(size+", "+type+" " +getString(R.string.pizza)+ " "+getString(R.string.topping_details)+toppings+"\n");
        TextView textCustomer = findViewById(R.id.abiodun_order_customer);
        textCustomer.setText(name+"\n"+address+"\n");

        Button butCheckOut = findViewById(R.id.abiodun_button_checkout);
        butCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Generate a random value as order number
                final int MIN = 10000;
                final int MAX = 99999;
                Random random = new Random();
                int confirmationNumber = random.nextInt((MAX-MIN)+1)+MIN;
                //Get the time of the day
                Calendar now = Calendar.getInstance();
                now.add(Calendar.HOUR,1); //One hour for Pizza to be ready for pickup
                int pickupHour = now.get(Calendar.HOUR);
                int pickupMinute = now.get(Calendar.MINUTE);
                String AMPM;
                if(now.get(Calendar.AM_PM)==0) //Find the time of the day in AM or PM
                    AMPM = "AM";
                else
                    AMPM = "PM";

                //Make the pickup time in string
                String pickupTime = pickupHour+":"+pickupMinute+" "+AMPM;

                //Make AlertDialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AbiodunActivity4.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_checkout,null); //Inflate the dialog.xml as the new view

                Button btnCancel = mView.findViewById(R.id.abiodun_butDialogCancel);
                TextView confirmation = mView.findViewById(R.id.abiodun_confirmationView);
                confirmation.setText(getString(R.string.orderNo)+" "+confirmationNumber+"\n"+getString(R.string.orderReadyTime)+" "+pickupTime+"\n");

                //When the cancel button is clicked
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(AbiodunActivity4.this,AbiodunActivity1.class);
                            startActivity(intent);
                        }
                    });

                mBuilder.setView(mView); //Set the view to the AlertDialog
                AlertDialog dialog = mBuilder.create(); //Create the Alert Dialog
                dialog.show(); //Show the Alert Dialog
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        super.onOptionsItemSelected(menu);

        switch(menu.getItemId()){
            case R.id.abiodun_help:
                if(url.isEmpty())  //User must select a store
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.error_select_store),Toast.LENGTH_SHORT).show();
                else {
                    try{
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.github_url)));
                        startActivity(intent);
                    }catch (ActivityNotFoundException e){
                        Toast.makeText(getApplicationContext(),R.string.invalid_url,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.abiodun_pizza:
                if(imgID==-1)  //User must select a store
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.error_select_store),Toast.LENGTH_SHORT).show();
                else {
                    try{
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }catch (ActivityNotFoundException e){
                        Toast.makeText(getApplicationContext(),R.string.invalid_url,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.abiodun_abiodun:
                if(checkPermission())
                {
                    Toast.makeText(getApplicationContext(),R.string.access_granted,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.access_notGranted,Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                break;
        }
        return super.onOptionsItemSelected(menu);
    }
    //Function to check permission
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(getApplicationContext(),R.string.request_granted,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                    } else {
                        Toast.makeText(getApplicationContext(),R.string.request_declined,Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }
}
