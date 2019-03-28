package com.example.chatconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class chooseLocation extends AppCompatActivity {

    protected String locationsString; // Might wanna remove protected if you are passing this to somewhere else.
    protected Button Enter;
    protected Button SeeLocations;
    protected Button Next;
    protected TextView ViewLocations;
    protected EditText SetLocations;
    protected String locationsToast;

    // String To Store The Interest String
    private static final String LIST = "interestString";

    // Call the onSaveInstanceState To Save The User Input
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(LIST, locationsString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        // Get String Information From Previous State.
        if (savedInstanceState != null){
            locationsString = savedInstanceState.getString(LIST);
        }

        //************************** Button 1 - Enter Button ********************************//
        /**
         * Button On Screen: "Enter"
         * Purpose: Get user input from the EditText field on locations they want to talk at.
         *        : Converts the user input and stores it into a string for later use.
         */

        Enter = (Button) findViewById(R.id.enter2);
        SetLocations = (EditText) findViewById(R.id.setLocations);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationsString = SetLocations.getText().toString();
            }
        });

        //************************* Button 2 - View Locations *******************************//
        /**
         * Button On Screen: "See Locations"
         * Purpose: Shows the user the Locations they have entered
         *        : FUTURE UPDATES - This button can show all Locations so the user
         *          doesn't have to enter in something they already have entered at an earlier date.
         */

        ViewLocations = (TextView) findViewById(R.id.view_locations);
        SeeLocations = (Button) findViewById(R.id.see_locations);

        SeeLocations.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                ViewLocations.setText(locationsString);
            }
        });

        //************************* Button 3 - Next Button *********************************//
        /**
         * Button On Screen: "NEXT"
         * Purpose: Opens/Goes to the next activity -> UNSPECIFIED SO FAR.
         *        : Checks if the user entered a string before going to next activity.
         *        : If the user has no string, show a toast.
         */

        Next = (Button) findViewById(R.id.Next2);

        locationsToast = "Please Enter Locations";
        Next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                if(locationsString != null && !locationsString.isEmpty()) {
                    Intent myIntent = new Intent(ChooseLocation.this, CHANGE_TO_NEXT_ACTIVITY_NAME.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(getApplicationContext(), locationsToast, Toast.LENGTH_LONG).show();
                }
                 */
            }
        });
    }
}

/*

 END ACTIVITY

 */