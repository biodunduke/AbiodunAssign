package com.example.mapsdemo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity implements LocationListener{
	
	  private GoogleMap map;
	  LatLng myPosition;
	  double latitude;
      double longitude;
      String provider;
      LocationManager locationManager;
      MarkerOptions markerOptions;
      Location location =null;
      TextView longLat;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try
        {
           setContentView(R.layout.activity_main);

           map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
           longLat = (TextView) findViewById(R.id.textView2);

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
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        

        // Getting the name of the best provider
        // if true, only provider currently enabled will be returned!
        provider = locationManager.getBestProvider(criteria, true);
        System.out.println("Provider "+provider);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Getting Current Location
            location = locationManager.getLastKnownLocation(provider);
        }
        System.out.println("location "+location);
        
        if(location!=null){
        // Getting latitude of the current location
        latitude = location.getLatitude();

        // Getting longitude of the current location
        longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
        
        System.out.println("Lat " + latitude);
        System.out.println("long " + longitude);

        longLat.setText("Long= " +longitude +" Lat= "+latitude+ " "+"Provider: "+provider );

        myPosition = new LatLng(latitude, longitude);

        map.clear(); //Clear the previous marker


         // Add Makrer
         map.addMarker(new MarkerOptions().position(myPosition).title("My Location 1").snippet("My location 2"));
        
        // Zoom into my location
         map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
         
         locationManager.requestLocationUpdates(provider, 20000, 3000, this);
        }
        else {
            longLat.setText("Location not found "+provider);
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
    
    /* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    // notify my location only when over 400 meters compared to the previous.
	   // 1 Milliseconds
          if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                  || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                  locationManager.requestLocationUpdates(provider, 20000, 3000, this);
              UpdateMap();
              }
              else {
              //Request for permission here
          }

	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
          if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                  || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

              locationManager.removeUpdates(this);
          }
	  }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		UpdateMap();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		// Called when the provider status changes. This method is called when a provider
		// is unable to fetch a location or if the provider has recently become available after a period of unavailability.
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
