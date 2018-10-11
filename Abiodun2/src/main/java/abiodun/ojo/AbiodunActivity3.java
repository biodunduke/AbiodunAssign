/**
 * Abiodun Ojo
 * N01178447
 * Assignment 2
 */
package abiodun.ojo;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AbiodunActivity3 extends AppCompatActivity {
    EditText customerName, customerAddress,creditCardNo,creditCardExpiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun3);
        Bundle bundle = getIntent().getExtras();
        final int selectionID=bundle.getInt("selection"); //Retrieve the items passed
      final String size = bundle.getString("size");
       final String type = bundle.getString("type");
      final   String toppings = bundle.getString("toppings");

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
                    creditCardNo.setError(getString(R.string.error_creditcard_length));
            }
        });

        creditCardExpiry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cardExpiry = creditCardExpiry.getText().toString().trim();
                if(cardExpiry.isEmpty())
                    creditCardExpiry.setError(getString(R.string.error_empty_edittext));
             else if(cardExpiry.length()!=4)
                creditCardExpiry.setError(getString(R.string.error_creditcard_expiry));
            }
        });

        butOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Submit Validations
                //Empty
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
                else if(!isAlpha(customerName.getText().toString().trim())){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_name_char), Toast.LENGTH_SHORT).show();
                    customerName.requestFocus();
                }
                else if(creditCardNo.getText().toString().trim().length()!=16){
                    Toast.makeText(AbiodunActivity3.this, getString(R.string.error_creditcard_length), Toast.LENGTH_SHORT).show();
                    creditCardNo.requestFocus();
                }
                else if(creditCardExpiry.getText().toString().trim().length()!=4){
                    Toast.makeText(AbiodunActivity3.this,getString(R.string.error_creditcard_expiry),Toast.LENGTH_SHORT).show();
                    creditCardExpiry.requestFocus();
                }
                else {
                    Intent intent = new Intent(AbiodunActivity3.this,AbiodunActivity4.class); //This is to be modified
                    intent.putExtra("size",size);//Pizza Size,
                    intent.putExtra("type",type);//Pizza Type,
                    intent.putExtra("toppings",toppings);//Pizza Toppings
                    intent.putExtra("name",customerName.getText().toString().trim());//Customer name
                    intent.putExtra("address",customerAddress.getText().toString().trim());//Customer address
                    startActivity(intent);
                }
            }
        });

    }

    //Function to check for numbers in a string
    public boolean isAlpha(String name){
        char[] chars = name.toCharArray();
        for(char c : chars){
            if(!Character.isLetter(c))
                return false;
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        super.onOptionsItemSelected(menu);
        switch(menu.getItemId()){
            case R.id.abiodun_help:
                break;
            case R.id.abiodun_pizza:
                break;
            case R.id.abiodun_abiodun:
                break;
            case R.id.homeAsUp: //Takes you to previous parent activity
                NavUtils.navigateUpFromSameTask(AbiodunActivity3.this);
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
}
