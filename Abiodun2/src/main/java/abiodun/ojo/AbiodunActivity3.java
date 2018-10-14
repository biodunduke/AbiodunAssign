
package abiodun.ojo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class AbiodunActivity3 extends AppCompatActivity {
    /**
     * Abiodun Ojo
     * N01178447
     * Assignment 2
     */
    EditText customerName, customerAddress,creditCardNo,creditCardExpiry;
    Menu menu;  //Global menu declaration to access menu item
    String url="";
    int imgID;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        imgID=bundle.getInt("id");
        if(imgID==1) {
            setTheme(R.style.PizzaHut);
        }
        if(imgID==2) setTheme(R.style.PizzaNova);
        if(imgID==3) setTheme(R.style.PizzaPizza);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun3);
        final int selectionID=bundle.getInt("selection"); //Retrieve the items passed
      final String size = bundle.getString("size");
       final String type = bundle.getString("type");
      final   String toppings = bundle.getString("toppings");
        imgID=bundle.getInt("id");
        url = bundle.getString("url");

        TextView tv = findViewById(R.id.abiodun_order_detail);
        tv.setTextSize(18);
        tv.setText(getString(R.string.text_order_details)+"\n"+getString(R.string.text_size)+": "+ size+"\n"+getString(R.string.text_type)+": "+type+"\n"+getString(R.string.text_toppings)+": "+toppings+".\n");

        ImageView img = findViewById(R.id.abiodun_shop_image);
        img.setImageResource(selectionID);
        Button butOrder = findViewById(R.id.abiodun_but_order);

        customerName = findViewById(R.id.abiodun_edit_text_customer_name);
        customerAddress = findViewById(R.id.abiodun_edit_text_customer_address);
        creditCardNo = findViewById(R.id.abiodun_edit_text_credit_card_no);
        creditCardExpiry = findViewById(R.id.abiodun_edit_text_credit_card_expiry);


        // Validations
        customerName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String name = customerName.getText().toString().trim();
                if(name.isEmpty())
                    customerName.setError(getString(R.string.error_empty_edittext));
                else if(name.length()<3)
                    customerName.setError(getString(R.string.error_name_length));
                    if (!isAlpha(name)) //Call the function to check if a number is present
                        customerName.setError(getString(R.string.error_name_char));
            }
        });
        customerAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String address = customerAddress.getText().toString().trim();
                if(address.isEmpty())
                    customerAddress.setError(getString(R.string.error_empty_edittext));
            }
        });
        creditCardNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cardNo = creditCardNo.getText().toString().trim();
                if(cardNo.isEmpty())
                    creditCardNo.setError(getString(R.string.error_empty_edittext));
                else if (cardNo.length()!=16)
                    creditCardNo.setError(getString(R.string.error_creditCard_length));
            }
        });

        creditCardExpiry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cardExpiry = creditCardExpiry.getText().toString().trim();
                if(cardExpiry.isEmpty())
                    creditCardExpiry.setError(getString(R.string.error_empty_edittext));
             else if(cardExpiry.length()!=4)
                creditCardExpiry.setError(getString(R.string.error_creditCard_expiry));
            }
        });

        butOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgID == 1) menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzahut);
                if (imgID == 2) menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzanova);
                if (imgID == 3) menu.findItem(R.id.abiodun_pizza).setIcon(R.drawable.logo_pizzapizza);
                //Submit Validations  //Empty
                if (customerName.getText().toString().trim().isEmpty()){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_empty_edittext), Toast.LENGTH_SHORT).show();
                    customerName.requestFocus();
                    }
                else if (customerAddress.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_empty_edittext), Toast.LENGTH_SHORT).show();
                    customerAddress.requestFocus();
                }
                else if (creditCardNo.getText().toString().trim().isEmpty()){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_empty_edittext), Toast.LENGTH_SHORT).show();
                    creditCardNo.requestFocus();
                }
                else if (creditCardExpiry.getText().toString().trim().isEmpty()){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_empty_edittext), Toast.LENGTH_SHORT).show();
                    creditCardExpiry.requestFocus();
                }
                //Short Customer Name length
                else if(customerName.getText().toString().trim().length()<3){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_name_length), Toast.LENGTH_SHORT).show();
                    customerName.requestFocus();
                }
                //Customer name must not contain letter
                else if(!isAlpha(customerName.getText().toString())){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_name_char), Toast.LENGTH_SHORT).show();
                    customerName.requestFocus();
                }
                else if(creditCardNo.getText().toString().trim().length()!=16){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_creditCard_length), Toast.LENGTH_SHORT).show();
                    creditCardNo.requestFocus();
                }
                else if(creditCardExpiry.getText().toString().trim().length()!=4){
                    Toast.makeText(AbiodunActivity3.this,getString(R.string.error_creditCard_expiry),Toast.LENGTH_SHORT).show();
                    creditCardExpiry.requestFocus();
                }
                else {
                    Intent intent = new Intent(AbiodunActivity3.this,AbiodunActivity4.class); //This is to be modified
                    intent.putExtra("size",size);//Pizza Size,
                    intent.putExtra("type",type);//Pizza Type,
                    intent.putExtra("toppings",toppings);//Pizza Toppings
                    intent.putExtra("name",customerName.getText().toString().trim());//Customer name
                    intent.putExtra("address",customerAddress.getText().toString().trim());//Customer address
                    intent.putExtra("url",url);
                    intent.putExtra("id",imgID);
                    startActivity(intent);
                }
            }
        });

    }

    //Function to check for numbers in a string
    public boolean isAlpha(String name){
        char[] chars = name.toCharArray();
        for(char c : chars){
            if(Character.isSpaceChar(c)) continue;//Skip the white spaces
            if(!Character.isLetter(c))
                return false;
        }
        return true;
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
