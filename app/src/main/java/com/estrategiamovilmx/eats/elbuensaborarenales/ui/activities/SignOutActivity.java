package com.estrategiamovilmx.eats.elbuensaborarenales.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.estrategiamovilmx.eats.elbuensaborarenales.R;
import com.estrategiamovilmx.eats.elbuensaborarenales.enums.LoginType;
import com.estrategiamovilmx.eats.elbuensaborarenales.items.UserItem;
import com.estrategiamovilmx.eats.elbuensaborarenales.requests.UserOperationRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.UserResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.retrofit.RestServiceWrapper;
import com.estrategiamovilmx.eats.elbuensaborarenales.tools.ApplicationPreferences;
import com.estrategiamovilmx.eats.elbuensaborarenales.tools.Constants;
import com.estrategiamovilmx.eats.elbuensaborarenales.tools.FireBaseOperations;
import com.estrategiamovilmx.eats.elbuensaborarenales.tools.GeneralFunctions;
import com.estrategiamovilmx.eats.elbuensaborarenales.tools.ShowConfirmations;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;

public class SignOutActivity extends AppCompatActivity {
    private static final String TAG = SignOutActivity.class.getSimpleName();
    private static final String operation_signout = "operation_signout";
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);
        logout();
    }

    private void logout(){
        UserItem user = GeneralFunctions.getCurrentUser(getApplicationContext());
        if (user!=null){//elimina token a este usuario
            signout(user);
        }else{
            ApplicationPreferences.saveLocalPreference(getApplicationContext(), Constants.user_object, null);
            startMainActivity();
        }
    }
    private void signout(UserItem user){
        UserOperationRequest request = new UserOperationRequest();
        request.setUser(user);
        request.setOperation(operation_signout);
        RestServiceWrapper.userOperation(request, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                Log.d(TAG, "Respuesta: " + response);
                if (response != null && response.isSuccessful()) {
                    UserResponse login_response = response.body();

                    if (login_response != null && login_response.getStatus().equals(Constants.success)) {
                        ApplicationPreferences.saveLocalPreference(getApplicationContext(), Constants.user_object, null);
                        ApplicationPreferences.saveLocalPreference(getApplicationContext(), Constants.days_to_show_orders, Constants.days_to_show_orders_default);
                        FireBaseOperations.unSubscribe(getApplicationContext(), Constants.profile_client);
                        FireBaseOperations.unSubscribe(getApplicationContext(), Constants.profile_admin);
                        FireBaseOperations.unSubscribe(getApplicationContext(), Constants.profile_deliver_man);
                        //close google or facebook session
                        String type_login = ApplicationPreferences.getLocalStringPreference(SignOutActivity.this,LoginActivity.TYPE_LOGIN);
                        Log.d(TAG,"type_login:"+type_login);
                        if (!type_login.equals(String.valueOf(LoginType.NATIVE))) {

                            if (type_login.equals(String.valueOf(LoginType.FACEBOOK))) {
                                revokeAccessFacebook();
                            } else if (type_login.equals(String.valueOf(LoginType.GOOGLE))) {
                                signOut();
                            }
                        }else {
                            startMainActivity();
                        }
                    } else if (login_response != null && login_response.getStatus().equals(Constants.no_data)) {
                        String response_error = login_response.getMessage();
                        Log.d(TAG, "Mensage:" + response_error);
                        onError(getString(R.string.error_invalid_login, response_error));
                    } else {

                        String response_error = login_response.getMessage();
                        Log.d(TAG, "Error:" + response_error);
                        onError(getString(R.string.error_invalid_login, response_error));
                    }


                } else {

                    onError(getString(R.string.error_invalid_login, getString(R.string.error_generic)));
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "ERROR: " + t.getStackTrace().toString() + " --->" + t.getCause() + "  -->" + t.getMessage() + " --->");
                onError(getString(R.string.error_invalid_login, t.getMessage()));
            }
        });
    }
    private void onError(String response_error){
        onBackPressed();
        ShowConfirmations.showConfirmationMessage(getString(R.string.error_invalid_login, response_error), SignOutActivity.this);
    }

    private void startMainActivity(){
        Context context = SignOutActivity.this;
        Intent intentMain =  new Intent(context, MainActivity.class);
        context.startActivity(intentMain);
        finish();
    }

    public void revokeAccessFacebook() {
        Log.d(TAG, "revokeAccessFacebook");
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();
                startMainActivity();
            }
        }).executeAsync();
    }

    private void revokeGoogleAccess() {
        Log.d(TAG,"revokeGoogleAccess");
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        startMainActivity();
                    }
                });
    }
    private void signOut() {
        Log.d(TAG,"signOut google");
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        startMainActivity();
                    }
                });
    }
    @Override
    protected void onStart() {
        String type_login = ApplicationPreferences.getLocalStringPreference(SignOutActivity.this,LoginActivity.TYPE_LOGIN);
        if (type_login.equals(String.valueOf(LoginType.GOOGLE))) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
            mGoogleApiClient.connect();
        }
        super.onStart();
    }
}
