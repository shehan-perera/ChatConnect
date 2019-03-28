package com.example.chatconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
//import com.example.gettopicinput.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public LoginButton loginButton;
    private ImageView circleImageView;
    private TextView txtName, txtEmail;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        txtName = (TextView) findViewById(R.id.profile_name);
        txtEmail = (TextView) findViewById(R.id.profile_email);
        circleImageView =(ImageView) findViewById(R.id.profile_pic);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        checkLoginStatus();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Toast.makeText(getApplicationContext(), "You're Logged in", Toast.LENGTH_LONG).show();
                // Move to the next page from here
                startActivity(new Intent(MainActivity .this, getInterests.class));


            }
            @Override
            public void onCancel() {
                //Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException error) {
                //Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){ // meaning the person has logged out
                txtName.setText("");
                txtEmail.setText("");
                circleImageView.setImageResource(0);

                Toast.makeText(MainActivity.this, "User Logged Out", Toast.LENGTH_LONG).show();
            } else {
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    // Move to Next Page From Here.
                    String first_name = object.getString("first name");
                    String last_name = object.getString("last name");
                    String email = object.getString("email");
                    String id = object.getString("id");

                    ArrayList<String> FacebookUserData = new ArrayList<String>();
                    FacebookUserData.add(first_name);
                    FacebookUserData.add(id);

                    Intent myIntent = new Intent(MainActivity.this, getInterests.class); // send the facebook info there
                    myIntent.putStringArrayListExtra("Data", FacebookUserData);

                    String image_url = "https://graph.facebook.com/" + id + "/pictures?type=normal";

                    // Set the things we got to our activity view
                    txtEmail.setText(email);
                    txtName.setText(first_name);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(MainActivity.this).load(image_url).into(circleImageView);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //Toast.makeText(getApplicationContext(), "YYYYYYY", Toast.LENGTH_LONG).show();
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            loadUserProfile(AccessToken.getCurrentAccessToken());

        }
    }
}
