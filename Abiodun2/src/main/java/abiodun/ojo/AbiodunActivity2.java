/**
 * Abiodun Ojo
 * N01178447
 * Assignment 2
 */
package abiodun.ojo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AbiodunActivity2 extends AppCompatActivity {
    String selectedSize="";
    String selectedType="";
    RadioGroup rdgSize, rdgType;
    RadioButton thick,thin, regular,large,medium,small;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun2);
        Bundle bundle = getIntent().getExtras();
       final int resID=bundle.getInt("id");

        Button but_shop_next = (Button)findViewById(R.id.abiodun_shop_but_next);
        ImageView img = (ImageView)findViewById(R.id.abiodun_shop_image);

        //Setting the right image according to user's selection
        if(resID==1)
        img.setImageResource(R.drawable.pizzahut);
        if(resID==2)
            img.setImageResource(R.drawable.pizzanova);
        if(resID==3)
            img.setImageResource(R.drawable.pizzapizza);
        //Radio Selection
        rdgSize = (RadioGroup)findViewById(R.id.abiodun_rg_size);
        rdgType = (RadioGroup)findViewById(R.id.abiodun_rg_type);
        //Radio button selection
        large = (RadioButton)findViewById(R.id.abiodun_rad_size_large);
        medium = (RadioButton)findViewById(R.id.abiodun_rad_size_medium);
        small = (RadioButton)findViewById(R.id.abiodun_rad_size_small);
        thick = (RadioButton)findViewById(R.id.abiodun_rad_type_thick);
        thin = (RadioButton)findViewById(R.id.abiodun_rad_type_thin);
        regular = (RadioButton)findViewById(R.id.abiodun_rad_type_regular);

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
        but_shop_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbiodunActivity2.this, AbiodunActivity3.class);
                if (selectedSize =="")
                    Toast.makeText(AbiodunActivity2.this,R.string.error_select_size,Toast.LENGTH_LONG).show();
                else if (selectedType=="")
                    Toast.makeText(AbiodunActivity2.this,R.string.error_select_type,Toast.LENGTH_LONG).show();
                else{
                if (resID == 1)
                    intent.putExtra("selection", R.drawable.pizzahut);
                if (resID == 2)
                    intent.putExtra("selection", R.drawable.pizzanova);
                if (resID == 3)
                    intent.putExtra("selection", R.drawable.pizzapizza);
                intent.putExtra("size", selectedSize);
                intent.putExtra("type", selectedType);
                startActivity(intent);
            }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
                NavUtils.navigateUpFromSameTask(AbiodunActivity2.this);
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
