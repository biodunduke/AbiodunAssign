/**
 * Abiodun Ojo
 * N01178447
 * Assignment 2
 */
package abiodun.ojo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AbiodunActivity1 extends AppCompatActivity {
    ImageButton imgPizzaHut,imgPizzaNova,imgPizzaPizza,selection;
    Integer imgID=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiodun1);
        Button but_next = (Button)findViewById(R.id.abiodun_but_next);
        imgPizzaHut = (ImageButton)findViewById(R.id.abiodun_pizzahut);
        imgPizzaNova = (ImageButton)findViewById(R.id.abiodun_pizzanova);
        imgPizzaPizza = (ImageButton)findViewById(R.id.abiodun_pizzapizza);

        imgPizzaHut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=1;
                selection=imgPizzaHut;
            }
        });
        imgPizzaNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=2;
                selection=imgPizzaNova;
            }
        });
        imgPizzaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgID=3;
                selection=imgPizzaPizza;
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
                    startActivity(intent);
                }
            }
        });

    }

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
        }
        return super.onOptionsItemSelected(menu);
    }
}
