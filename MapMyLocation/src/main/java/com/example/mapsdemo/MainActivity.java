package com.example.mapsdemo;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	
	  private GoogleMap map;
	  LatLng myPosition;
	  double latitude;
	    double longitude;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try
        {
        setContentView(R.layout.activity_main);
        
        
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        
    
           UpdateMap();
            
           

          
        }
        catch (Exception ex){
        	String x = ex.toString();
        	int a = 3;
        }
    }

    public void UpdateMap() {
    	 // Enabling MyLocation Layer of Google Map
        map.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);
        System.out.println("Provider "+provider);
        
        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);
        
        System.out.println("location "+location);
        
        if(location!=null){
        // Getting latitude of the current location
        latitude = location.getLatitude();

        // Getting longitude of the current location
        longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
        
        System.out.println("Lat "+latitude);
        System.out.println("long "+longitude);

         myPosition = new LatLng(latitude, longitude);
         
         // Add Makrer
         map.addMarker(new MarkerOptions().position(myPosition).title("My Location"));
        
        // Zoom into my location
         map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
