
package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CAMERA;

public class AbiodunActivity2 extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 2
     */
    String selectedSize="";
    String selectedType="";
    String selectedTopping="";
    int i,numOfToppings =0;
    int selection=0;
    String []topping = new String[numOfToppings];
    List<String> list = new ArrayList<>();
    RadioGroup rdgSize, rdgType;
    RadioButton thick,thin, regular,large,medium,small;
    CheckBox mushroom,bacon,lettuce,pepperoni,ham,beef,chicken;
    Menu menu;  //Global menu declaration to access menu item
    String url="";
    private static final int PERMISSION_REQUEST_CODE = 100;
    int imgID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        imgID=bundle.getInt("id");
        if(imgID==1) setTheme(R.style.PizzaHut);
        if(imgID==2) setTheme(R.style.PizzaNova);
        if(imgID==3) setTheme(R.style.PizzaPizza);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun2);


        url = bundle.getString("url");

        Button but_shop_next = findViewById(R.id.abiodun_shop_but_next); //Next button
        ImageView img = findViewById(R.id.abiodun_shop_image); //To show the store image
        //Setting the right image according to user's selection
        if(imgID==1) img.setImageResource(R.drawable.pizzahut);
        if(imgID==2)img.setImageResource(R.drawable.pizzanova);
        if(imgID==3)img.setImageResource(R.drawable.pizzapizza);
        //Radio Selection
        rdgSize = findViewById(R.id.abiodun_rg_size);
        rdgType = findViewById(R.id.abiodun_rg_type);
        //Radio button selection
        large = findViewById(R.id.abiodun_rad_size_large);
        medium = findViewById(R.id.abiodun_rad_size_medium);
        small = findViewById(R.id.abiodun_rad_size_small);
        thick = findViewById(R.id.abiodun_rad_type_thick);
        thin = findViewById(R.id.abiodun_rad_type_thin);
        regular = findViewById(R.id.abiodun_rad_type_regular);

        rdgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.abiodun_rad_size_large:
                        selectedSize=large.getText().toString();
                        break;
                    case R.id.abiodun_rad_size_medium:
                        selectedSize=medium.getText().toString();
                        break;
                    case R.id.abiodun_rad_size_small:
                        selectedSize=small.getText().toString();
                        break;
                }
            }
        });

        rdgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.abiodun_rad_type_thick:
                        selectedType=thick.getText().toString();
                        break;
                    case R.id.abiodun_rad_type_thin:
                        selectedType=thin.getText().toString();
                        break;
                    case R.id.abiodun_rad_type_regular:
                        selectedType=regular.getText().toString();
                        break;
                }
            }
        });
        //Checkboxes
        mushroom = findViewById(R.id.abiodun_chkBox_mushroom);
        beef = findViewById(R.id.abiodun_chkBox_beef);
        bacon = findViewById(R.id.abiodun_chkBox_bacon);
        pepperoni = findViewById(R.id.abiodun_chkBox_pepperoni);
        lettuce= findViewById(R.id.abiodun_chkBox_lettuce);
        ham = findViewById(R.id.abiodun_chkBox_ham);
        chicken = findViewById(R.id.abiodun_chkBox_chicken);

            but_shop_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imgID == 1) {
                    selection = R.drawable.logo_pizzahut;
                    menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzahut);
                }
                if (imgID == 2) {
                    selection = R.drawable.logo_pizzanova;
                    menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzanova);
                }
                if (imgID == 3) {
                    selection = R.drawable.logo_pizzapizza;
                    menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzapizza);
                }
                Intent intent = new Intent(AbiodunActivity2.this, AbiodunActivity3.class);
                if (selectedSize.isEmpty())
                    Toast.makeText(AbiodunActivity2.this,R.string.error_select_size,Toast.LENGTH_LONG).show();
                else if (selectedType.isEmpty())
                    Toast.makeText(AbiodunActivity2.this,R.string.error_select_type,Toast.LENGTH_LONG).show();
                else if(numChecked()<5)
                    Toast.makeText(AbiodunActivity2.this,getString(R.string.select_toppings),Toast.LENGTH_LONG).show();
                else{
                    intent.putExtra("size", selectedSize);
                    intent.putExtra("type", selectedType);
                    intent.putExtra("selection",selection);
                    intent.putExtra("id",imgID);

                    if(bacon.isChecked()) selectedTopping=selectedTopping +" "+bacon.getText().toString();
                    if(mushroom.isChecked()) selectedTopping=selectedTopping +" "+mushroom.getText().toString();
                    if(pepperoni.isChecked()) selectedTopping=selectedTopping +" "+pepperoni.getText().toString();
                    if(beef.isChecked()) selectedTopping=selectedTopping +" "+beef.getText().toString();
                    if(chicken.isChecked()) selectedTopping=selectedTopping +" "+chicken.getText().toString();
                    if(lettuce.isChecked()) selectedTopping=selectedTopping +" "+lettuce.getText().toString();
                    if(ham.isChecked()) selectedTopping=selectedTopping +" "+ham.getText().toString();

                    intent.putExtra("toppings", selectedTopping);
                    intent.putExtra("url",url);
                    startActivity(intent);
            }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //Create Menu and Inflate it
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        super.onOptionsItemSelected(menu);

        switch(menu.getItemId()){
            case R.id.abiodun_help:
                if(this.url.isEmpty())  //User must select a store
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
                       // Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show(); // new edit
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }catch (ActivityNotFoundException e){
                        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }
    public int numChecked(){ //To count the number of checked items
        int num =0;
        if(bacon.isChecked())
            num++;
        if(mushroom.isChecked())
            num++;
        if(pepperoni.isChecked())
            num++;
        if(beef.isChecked())
            num++;
        if(chicken.isChecked())
            num++;
        if(lettuce.isChecked())
            num++;
        if(ham.isChecked())
            num++;
        return num;
    }
}
