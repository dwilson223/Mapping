package com.example.a1wilsd12.mapping;

import android.app.Activity;
import org.osmdroid.config.Configuration;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class HelloMap extends Activity implements View.OnClickListener
{

    MapView mv;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        Button submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(this);

        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);

    }

    public void onClick(View view)
    {
        EditText latitudeEditText = (EditText)findViewById(R.id.enterLatitude);
        EditText longitudeEditText = (EditText)findViewById(R.id.enterLongitude);

        double latitude = Double.parseDouble(latitudeEditText.getText().toString());
        double longitude = Double.parseDouble(longitudeEditText.getText().toString());

        mv.getController().setCenter(new GeoPoint(latitude, longitude));

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // opens choose activity
        if(item.getItemId() == R.id.choosemap)
        {
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivity(intent);
                 startActivityForResult(intent,0);
                //react to the menu item being selected...
            return true;
        }

        // opens set lcoation
        else if(item.getItemId() == R.id.setLocation)
        {
            Intent intent = new Intent(this,SetLocationActivity.class);
            startActivity(intent);
            startActivityForResult(intent,1);
            //react to the menu item being selected...
            return true;
        }

        // opens preferences
        else if(item.getItemId() == R.id.prefs)
        {
            Intent intent = new Intent(this,MyPrefsActivity.class);
            startActivity(intent);
            startActivityForResult(intent,2);
            //react to the menu item being selected...
            return true;
        }

        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );

        mv.getController().setCenter(new GeoPoint(lat, lon));



    }
}

