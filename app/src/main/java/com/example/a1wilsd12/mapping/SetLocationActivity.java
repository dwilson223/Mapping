package com.example.a1wilsd12.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;

/**
 * Created by 1wilsd12 on 06/03/2017.
 */


// this is a comment
public class SetLocationActivity extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlocation);

        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        Button submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(this);
}
    public void onClick(View view)
    {
        EditText latitudeEditText = (EditText)findViewById(R.id.enterLatitude);
        EditText longitudeEditText = (EditText)findViewById(R.id.enterLongitude);

        double latitude = Double.parseDouble(latitudeEditText.getText().toString());
        double longitude = Double.parseDouble(longitudeEditText.getText().toString());

        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        bundle.putDouble("com.example.latitude",latitude);
        bundle.putDouble("com.example.latitude",longitude);

        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        dummy();
        finish();



    }

    public void dummy() {
        System.out.println("this isnt doing anything");
    }
}
