package abiodun.ojo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements AbHome.OnFragmentInteractionListener {
    static AbHome abHome; //Dependencies must be added
    static AbiDown abDown;
    static OjoSet ojoSet;
    static OjSrv ojSrv;
    MyPagerAdapter myPagerAdapter;
    ViewPager viewPager;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Handle Permission
        //Todo Design all fragments
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
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter implements AbHome.OnFragmentInteractionListener {

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
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public void onFragmentInteraction(String name, String desc) {
            abDown.onFragmentInteraction(name, desc);
        }
    }

    @Override
    public void onFragmentInteraction(String name, String desc) {
        //TODO: do firstname frag here
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
