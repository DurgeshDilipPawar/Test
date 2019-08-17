package choicetechlab.demo;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Durgesh on 17/08/19.
 */

public class GoogleService extends Service implements LocationListener {

    public static String str_receiver = "servicetutorial.service.receiver";
    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude, longitude;
    LocationManager locationManager;
    Location location;
    long notify_interval = 1000;
    Intent intent;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;

    public GoogleService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        mTimer.schedule(new TimerTaskToGetLocation(), 5, notify_interval);
        intent = new Intent(str_receiver);
//        fn_getlocation();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * Location Manager
     * This class provides access to the system location services.  These
     * services allow applications to obtain periodic updates of the
     * device's geographical location, or to fire an application-specified
     * when the device enters the proximity of a given
     * geographical location.
     */
    private void fn_getlocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnable && !isNetworkEnable) {
        } else {

            /**
             * This provider determines location based on
             * availability of cell tower and WiFi access points. Results are retrieved
             * by means of a network lookup.
             */
            if (isNetworkEnable) {
                location = null;
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                    }
                }

            }

            /**
             *      This GPS provider determines location using
             *      satellites. Depending on conditions, this provider may take a while to return
             *      a location fix. Requires the permission
             */
            if (isGPSEnable) {
                location = null;
                assert locationManager != null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                    }
                }
            }


        }

    }

    /**
     * Broadcast the given intent to all interested BroadcastReceivers.
     *
     * @param location
     */
    private void fn_update(Location location) {

        intent.putExtra("latutide", location.getLatitude() + "");
        intent.putExtra("longitude", location.getLongitude() + "");
        sendBroadcast(intent);
    }

    /**
     * A task that can be scheduled for one-time or repeated execution by a Timer.
     */
    private class TimerTaskToGetLocation extends TimerTask {
        @Override
        public void run() {
/*
  When an object implementing interface Runnable  is used
   to create a thread, starting the thread causes the object's
   run method to be called in that separately executing
   thread.
 */
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fn_getlocation();
                }
            });

        }
    }
}
