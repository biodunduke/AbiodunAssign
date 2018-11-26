package abiodun.ojo;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends Activity implements OnClickListener  {
    TextView tv;
    TextView readView;
    Button btnSave;
    Button btnRead;
    String filename = "TestFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String destPath = "/data/data/" + getPackageName() + "/files"; //Crate a path for DB
        FileOutputStream fOut;

        {
            try {
                fOut = new FileOutputStream(destPath + "/TestFile.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                btnRead.setEnabled(false);
            }
        }

        tv = (TextView) this.findViewById(R.id.tv_message);
        readView = (TextView) this.findViewById(R.id.tv_read);

        btnSave = (Button) findViewById(R.id.button_send);
        btnSave.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    int i=1;
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.button_send:
                // TODO Auto-generated method stub
                StringBuilder strFileContents = new StringBuilder("");
                String content = tv.getText().toString();
                strFileContents.append(content);
                strFileContents.append("\n\t");
                strFileContents.append("Adding line " +i + " of text\n");
                content = strFileContents.toString();
                i++;
                //String strFileContents = "abc";
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(filename, MODE_PRIVATE|MODE_APPEND);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                }
                try {

                    fos.write(content.getBytes());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                //finish();
                break;
            case R.id.btnRead:
                                    // InputStream inStream = null;
break;
        }

    }

}
