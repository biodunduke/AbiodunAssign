package abiodun.ojo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static abiodun.ojo.R.id.abiodun_content_frame;


public class MainActivity extends AppCompatActivity implements OjoSet.OnFragmentInteractionListener {
    private static final int PERMISSION_REQUEST_CODE = 5;
    public static List<String> list; //To be used in AbHome Fragment
     AbHome abHome; //Dependencies must be added
     AbiDown abDown;
     OjoSet ojoSet;
     OjSrv ojSrv;
    MyPagerAdapter myPagerAdapter;
    ViewPager viewPager;
    FileOutputStream fos = null;
    Spinner spinner;
    Menu menu;  //Global menu declaration to access menu item
    LocationManager locationManager;
    String provider;
    Criteria criteria;
    Location location;
    float lat,lng;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = getString(R.string.fileName);
        String courseName = getString(R.string.courseName);
        // Creating an empty criteria object
        criteria = new Criteria();
        //Writing file
        StringBuilder fileContent = new StringBuilder("");
        list = new ArrayList<String>(); //For the spinner
        list.add(0, getString(R.string.firstName)); //Adding my name as title for the spinner
        for (int i = 0; i < 5; i++) { //Appending abiodun
            fileContent.append(courseName);
            fileContent.append("\n");
            list.add(courseName); //For the spinner
        }
        courseName = fileContent.toString();//Final string to write to file
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.toString());
        }
        try {
            fos.write(courseName.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //TODO: French translation
        //TODO: WIre the buttons (Nav Drawer)
        //TODO: Font Size
        //TODO: WebServices

        //Toolbar
        Toolbar toolbar = findViewById(R.id.abiodun_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //Showing the Fragments
        abHome = new AbHome();
        abDown = new AbiDown();
        ojSrv = new OjSrv();
        ojoSet = new OjoSet();

        viewPager = findViewById(R.id.abiodunviewPager);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        mDrawerLayout = findViewById(R.id.abiodun_drawer_layout);
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        //Toast.makeText(getApplicationContext(),"Hey Slide!",Toast.LENGTH_LONG).show(); // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        //  Toast.makeText(getApplicationContext(),"Hey Open!",Toast.LENGTH_LONG).show();// Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        //     Toast.makeText(getApplicationContext(),"Hey Closed!",Toast.LENGTH_LONG).show();// Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.abiodun_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                String itemSelected = (String) menuItem.getTitle();
                switch (itemSelected) {
                    case "Abiodun":
                        abDown = new AbiDown();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.abiodunDownLayout, abDown).commit();
                        // Toast.makeText(getApplicationContext(),getString(R.string.firstName),Toast.LENGTH_LONG).show();
                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }

    @Override
    public void onFragmentInteraction(String name, String desc) {
        //TODO: do firstname frag here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //Create Menu and Inflate it
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //New edit
        super.onOptionsItemSelected(item); //May cause issue
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.abiodunMenuHelp:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.github_url)));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), R.string.invalid_url, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.abiodunMenuSms:
                String phoneNo = getString(R.string.phoneNo);
                String message = getResources().getString(R.string.courseName);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.SEND_SMS
                    }, PERMISSION_REQUEST_CODE);
                    //Check again and send the sms
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                        sendSMS(phoneNo, message);
                } else
                    sendSMS(phoneNo, message);
                break;
            case R.id.abiodunMenuLocation:
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
                    }, PERMISSION_REQUEST_CODE);
                }
                else {
                    provider = locationManager.getBestProvider(criteria, false);
                    location = locationManager.getLastKnownLocation(provider);
                     lat = (float) (location.getLatitude());
                     lng = (float) (location.getLongitude());
                     String position = "Longitude: "+String.valueOf(lng)+
                             ", Latitude: "+String.valueOf(lat);
                    Snackbar.make(findViewById(abiodun_content_frame), position,Snackbar.LENGTH_LONG).show();
                }
                //TODO Location
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), R.string.request_granted, Toast.LENGTH_SHORT).show();
                } else { //If user declines, show a toast saying so
                    Toast.makeText(getApplicationContext(), R.string.request_declined, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected void sendSMS(String phoneNo, String message) {
        // TODO Auto-generated method stub
        String SENT = getString(R.string.smsSent);
        String DELIVERED = getString(R.string.smsDelivered);

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), getString(R.string.genericFailure),
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), getString(R.string.noService),
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), getString(R.string.nullPDU),
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), getString(R.string.radioOff),
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), getString(R.string.deliveredSMS),
                                Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), getString(R.string.smsUndelivered),
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, sentPI, deliveredPI);
    }

    public  class MyPagerAdapter extends FragmentPagerAdapter implements AbHome.OnFragmentInteractionListener {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return abHome;
                case 1:
                    return abDown;
                case 2:
                    return ojSrv;
                case 3:
                    return ojoSet;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4; //Four fragments
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public void onFragmentInteraction(String name, String desc) {
            ojoSet.onFragmentInteraction(name, desc);
        }
    }
    //Handling the back button pressed
@Override
    public void onBackPressed(){
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle(getResources().getString(R.string.fullName));
    builder.setMessage(getString(R.string.confirmExit))
            .setCancelable(true)
            .setPositiveButton(getString(R.string.yesDialog), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            })
            .setNegativeButton(getString(R.string.noDialog), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onResume();
                }
            });
        builder.show();

}
}
