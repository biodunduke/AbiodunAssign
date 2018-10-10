/**
 * Abiodun Ojo
 * N01178447
 * Assignment 2
 */
package abiodun.ojo;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
        String size = bundle.getString("size");
        String type = bundle.getString("type");
        String [] array = bundle.getStringArray("toppings");
        String toppings=""; //Making a string to hold the toppings selected
        for (int i=0; i<array.length;i++) {
            if(array[i]!=null){
                if(i==array.length-1)
                    toppings = toppings + array[i] + ".";
                else
                    toppings = toppings + array[i] + ", ";
            }
        }

        TextView tv = (TextView)findViewById(R.id.abiodun_order_detail);
        tv.setTextSize(18);
        tv.setText(getString(R.string.text_order_details)+"\n"+getString(R.string.text_size)+": "+ size+"\n"+getString(R.string.text_type)+": "+type+"\n"+getString(R.string.text_toppings)+": "+toppings);

        ImageView img = (ImageView)findViewById(R.id.abiodun_shop_image);
        img.setImageResource(selectionID);
        Button butOrder = (Button)findViewById(R.id.abiodun_but_order);

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
