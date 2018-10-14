package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
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
    Integer imgID=-1;
    Menu menu;  //Global menu declaration to access menu item
    String url="";
    private static final int PERMISSION_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun1);
        Button but_next = findViewById(R.id.abiodun_but_next);
        //Getting variables for the clickable images
        imgPizzaHut = findViewById(R.id.abiodun_pizzahut);
        imgPizzaNova = findViewById(R.id.abiodun_pizzanova);
        imgPizzaPizza = findViewById(R.id.abiodun_pizzapizza);

        //Setting the  clock listener
        imgPizzaHut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=1; //Assign a value so I can pass it to next activity
                selection=imgPizzaHut; //Save the selected image
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.text_pizzahut),Toast.LENGTH_SHORT).show(); //Show the name of the pizza store selected
                url = getResources().getString(R.string.pizza_hut_url);  //Save the url of the pizza store selected
                menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzahut); //Assign the saved url to the menu item "Pizza"
                v=findViewById(R.id.biodun_constraint_layout); //
                v.setBackgroundResource(R.drawable.bg_pizzahut); //Change the view background to respective bg of the selected pizza store
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
                v=findViewById(R.id.biodun_constraint_layout);
                v.setBackgroundResource(R.drawable.bg_pizzanova);
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
                v=findViewById(R.id.biodun_constraint_layout);
                v.setBackgroundResource(R.drawable.bg_pizzapizza);
            }
        });
        but_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(imgID<0) { //Nothing is selected yet
                   Toast.makeText(AbiodunActivity1.this, R.string.error_select_store, Toast.LENGTH_LONG).show(); //Show the error message
               }
                else{ //A store has been selected
                    Intent intent = new Intent(AbiodunActivity1.this, AbiodunActivity2.class); //To go to the next activity
                    intent.putExtra("id", imgID); //Pass the number assigned to the image
                    intent.putExtra("url",url); //Pass the url of the selected image
                    startActivity(intent); //Go to Activity2
                }
            }
        });

    }

    //Creating Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return true;
    }

    //When a menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        super.onOptionsItemSelected(menu);

        switch(menu.getItemId()){
            case R.id.abiodun_help:
                if(url.isEmpty())  //User must select a store
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.error_select_store),Toast.LENGTH_SHORT).show();
                else {
                    try{
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.github_url))); //Go to my Github page
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
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); //Go to the pizza store website
                        startActivity(intent);
                    }catch (ActivityNotFoundException e){
                        Toast.makeText(getApplicationContext(),R.string.invalid_url,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.abiodun_abiodun:  //Starts the camera
                if(checkPermission()) //If the permission is already granted
                {   //Start the camera after showing a toast that the permission had earlier been granted
                    Toast.makeText(getApplicationContext(),R.string.access_granted,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.access_notGranted,Toast.LENGTH_SHORT).show();
                    requestPermission(); //If no permission yet, request permission
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
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_REQUEST_CODE); //The permission for Camera is requested
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) { //if user grants permission, show a toast then start the camera
                        Toast.makeText(getApplicationContext(),R.string.request_granted,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                    } else { //If user declines, show a toast saying so
                        Toast.makeText(getApplicationContext(),R.string.request_declined,Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
        }
    }
}
