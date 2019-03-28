package com.example.chatconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;


public class getInterests extends AppCompatActivity {

    protected EditText setInterests;
    protected String interestString;
    protected Button Enter;
    protected Button seeInterests;
    protected TextView viewInterests;
    protected String interestsToast;
    protected Button NEXT;

    // String To Store The Interest String
    private static final String LIST = "interestString";

    // String For LogCat Messages
    private static final String TAG = "LogCat_Messages";

    // APPLICATION STATES - LogCat Messages
    @Override
    protected void onStart() {super.onStart(); Log.i(TAG, "onStart");}

    @Override
    protected void onResume(){super.onResume(); Log.i(TAG, "onResume");}

    @Override
    protected void onDestroy(){super.onDestroy(); Log.i(TAG, "onDestroy");}

    @Override
    protected void onPause(){super.onPause(); Log.i(TAG, "onPause");}

    @Override
    protected void onStop(){super.onStop(); Log.i(TAG, "onStop");}

    @Override
    protected void onRestart(){super.onRestart(); Log.i(TAG, "onRestart");}

    // Call the onSaveInstanceState To Save The User Input
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(LIST, interestString);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_interests);
        Log.i(TAG, "onCreate");

        // Get String Information From Previous State.
        if (savedInstanceState != null){
            interestString = savedInstanceState.getString(LIST);
        }

        //layOutToAdd = (LinearLayout) findViewById(R.layout.activity_main);

        // *********************** Button 1 - Enter Button. *******************************//
        /***
         * Button On Screen: "Enter"
         * Purpose: Get user input from the EditText field on interests they want to talk about
         *        : Converts the user input and stores it into a string for later use.
         */

        setInterests = (EditText) findViewById(R.id.setInterests);
        Enter = (Button) findViewById(R.id.enter1);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interestString = setInterests.getText().toString();
            }
        });

        //************************ Button 2 ************************************************//
        /***
         * Button On Screen: "See Interests"
         * Purpose: Shows the user the interests they have entered
         *        : Future updates - This button can show all interests so the user
         *          doesn't have to enter in something they already have entered at an earlier date.
         */
        seeInterests = (Button) findViewById(R.id.see_interests);
        viewInterests = (TextView) findViewById(R.id.view_interests);

        seeInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewInterests.setText(interestString);
            }
        });

        //************************ Button 3 - Next Page Button ******************************//
        /***
         * Button On Screen: "NEXT"
         * Purpose: Opens/Goes to the next activity -> Choosing Locations
         *        : Checks if the user entered a string before going to next activity.
         *        : If the user has no string, show a toast.
         */
        NEXT = (Button) findViewById(R.id.Next1);

        interestsToast = "Please Enter Interests!";
        NEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {

                if(interestString != null && !interestString.isEmpty()) {
                    Intent myIntent = new Intent(getInterests.this, chooseLocation.class);
                    //myIntent.putExtra("Interests", interestString);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(getApplicationContext(), interestsToast, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

/*

 END ACTIVITY

 */