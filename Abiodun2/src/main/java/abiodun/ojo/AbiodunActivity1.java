package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import static android.Manifest.permission.CAMERA;

public class AbiodunActivity1 extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 2
     */
    ImageButton imgPizzaHut,imgPizzaNova,imgPizzaPizza,selection;
    ImageView imgView;
    Integer imgID=-1;
    Menu menu;  //Global menu declaration to access menu item
    String url="";
    private static final int PERMISSION_REQUEST_CODE = 100;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun1);
        imgView  = findViewById(R.id.abiodun_pizza);
        Button but_next = (Button)findViewById(R.id.abiodun_but_next);
        imgPizzaHut = (ImageButton)findViewById(R.id.abiodun_pizzahut);
        imgPizzaNova = (ImageButton)findViewById(R.id.abiodun_pizzanova);
        imgPizzaPizza = (ImageButton)findViewById(R.id.abiodun_pizzapizza);

        imgPizzaHut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=1;
                selection=imgPizzaHut;
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.text_pizzahut),Toast.LENGTH_SHORT).show();
                url = getResources().getString(R.string.pizza_hut_url);
                menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzahut);
        }
        });
        imgPizzaNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=2;
                selection=imgPizzaNova;
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.text_pizzanova),Toast.LENGTH_SHORT).show();
                url = getResources().getString(R.string.pizzanova_url);
                menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzanova);
            }
        });
        imgPizzaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=3;
                selection=imgPizzaPizza;
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.text_pizzapizza),Toast.LENGTH_SHORT).show();
                url = getResources().getString(R.string.pizzapizza_url);
                menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzapizza);
            }
        });
        but_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(imgID<0) {
                   Toast.makeText(AbiodunActivity1.this, R.string.error_select_store, Toast.LENGTH_LONG).show();
               }
                else{
                    Intent intent = new Intent(AbiodunActivity1.this, AbiodunActivity2.class);
                    intent.putExtra("id", imgID);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            }
        });

    }

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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
}
