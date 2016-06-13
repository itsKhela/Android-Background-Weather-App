package jester.bgweather.com;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    FrameLayout content;

    GoogleApiClient mGoogleApiClient;

    MainFragment mainFragment;
    OptionsFragment optionsFragment;

    static final int REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        content = (FrameLayout) findViewById(R.id.content_fragment);

         mainFragment = new MainFragment();
        optionsFragment = new OptionsFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_fragment, optionsFragment).commit();

        Intent intent = new Intent("CHANGE_WALLPAPER");
        PendingIntent startPIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating( AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10, 60000, startPIntent);

        sendBroadcast(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, mainFragment).commit();
            case R.id.nav_gallery:
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, mainFragment).commit();
            case R.id.nav_manage:
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, optionsFragment).commit();
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_fragment, mainFragment).commit();
        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, mainFragment).commit();
        } else if (id == R.id.nav_main) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_fragment, mainFragment).commit();
        } else if (id == R.id.nav_share) {
// Create the text message with a string
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_string));
            sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
