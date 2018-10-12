package abiodun.ojo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Random;

public class AbiodunActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun4);
        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String type = bundle.getString("type");
        final String size = bundle.getString("size");
        final String toppings = bundle.getString("toppings");
        final String address = bundle.getString("address");

        TextView textOrder = findViewById(R.id.abiodun_order_details);
        textOrder.setText(size+", "+type+" " +getString(R.string.pizza)+ " "+getString(R.string.topping_details)+toppings+"\n");
        TextView textCustomer = findViewById(R.id.abiodun_order_customer);
        textCustomer.setText(name+"\n"+address+"\n");

        Button butCheckOut = findViewById(R.id.abiodun_button_checkout);
        butCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int MIN = 5555;
                final int MAX = 10000;
                Random random = new Random();
                Calendar now = Calendar.getInstance();
                now.add(Calendar.HOUR,1);
                int pickupHour = now.get(Calendar.HOUR);
                int pickupMinute = now.get(Calendar.MINUTE);
                int confirmationNumber = random.nextInt((MAX-MIN)+1)+MIN;
                String pickupTime = pickupHour+":"+pickupMinute;
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AbiodunActivity4.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_checkout,null);
               // Button btnOk = dialogView.findViewById(R.id.abiodun_butDialogOk);
                Button btnCancel = mView.findViewById(R.id.abiodun_butDialogCancel);
                TextView confirmation = mView.findViewById(R.id.abiodun_confirmationView);
                confirmation.setText(getString(R.string.orderNo)+" "+confirmationNumber+getString(R.string.orderReadyTime)+" "+pickupTime);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(AbiodunActivity4.this,AbiodunActivity1.class);
                            startActivity(intent);
                        }
                    });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


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
                NavUtils.navigateUpFromSameTask(AbiodunActivity4.this);
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
