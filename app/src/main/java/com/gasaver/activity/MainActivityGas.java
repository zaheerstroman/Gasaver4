package com.gasaver.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.gasaver.R;
import com.gasaver.Response.GraphReportsResponse;
import com.gasaver.databinding.ActivityMainGasBinding;
import com.gasaver.databinding.NavDrawerLayoutBinding;
import com.gasaver.databinding.ToolbarLayoutBinding;
import com.gasaver.fragment.HomeFragmentGasaver;
import com.gasaver.fragment.ProfileFragment;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.skydoves.powerspinner.PowerSpinnerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import io.sentry.Sentry;

public class MainActivityGas extends AppCompatActivity {

    Spinner spinner_caseText1, registertype_spinner;

    PowerSpinnerView spinner_subcat;

    private String[] budgetArrayList = {"", "E10", "Diesel", "AdBlue", "Unleaded", "PremDSL", "U95", "TruckDSL", "E85", "U98", "U91", "P95", "P98", "PDL", "B20", "EV", "DL"};



    public NavDrawerLayoutBinding navDrawerLayoutGasBinding;

    public ActivityMainGasBinding activityMainGasBinding;

    public ToolbarLayoutBinding toolbarLayoutGasBinding;


    public FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        navDrawerLayoutGasBinding = NavDrawerLayoutBinding.inflate(getLayoutInflater());

        setContentView(navDrawerLayoutGasBinding.getRoot());

        spinner_caseText1 = (Spinner) findViewById(R.id.spinner_caseText1);
        spinner_subcat = (PowerSpinnerView) findViewById(R.id.spinner_subcat);

//        spinner_subcat.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_units, getResources().getStringArray(R.array.budgets)));


        activityMainGasBinding = navDrawerLayoutGasBinding.mainActivityGas;


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen




        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.btnMenu);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
                Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);

                if (fragment instanceof HomeFragmentGasaver)
                    ((HomeFragmentGasaver) fragment).showBottomSheet();

            }
        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.btnHybrid);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowGraph();
            }
        });

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.btnReward);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityGas.this, RewardActivity.class));

            }
        });


        FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.btnSetting);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityGas.this, SettingsActivity.class));


            }
        });


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_geeks);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:

                        break;

                    case R.id.dashboard:
                        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
                        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);

                        if (fragment instanceof HomeFragmentGasaver)
                            ((HomeFragmentGasaver) fragment).showBottomSheet();

                        return true;


                    case R.id.nav_search:

                        Intent intent = new Intent(getApplicationContext(), AdvancedBannerSlidSearchActivity.class);
                        startActivity(intent);
                        return true;


                    case R.id.about:

                         intent = new Intent(getApplicationContext(), WishListActivity.class);

                        navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
                        fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
                        if (fragment instanceof HomeFragmentGasaver) {
                            intent.putExtra("data", new Gson().toJson(((HomeFragmentGasaver) fragment).stationDataList));
                            startActivity(intent);
                        }

                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.profile:
                        ProfileFragment addPhotoBottomDialogFragment =
                                new ProfileFragment();
                        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "");


                        return true;

                }
                return false;
            }
        });


//        -----------------------------------------------------------------------------------------------


        NavController navController = Navigation.findNavController(this, R.id.fragmentContainer);
        NavigationUI.setupWithNavController(

                navDrawerLayoutGasBinding.navigationView,
                navController
        );

    }

    private void ShowGraph() {
        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("user_id", "119");
        jsonObject.addProperty("token", "24631cdd0323cea063e6cb4e5b2b0f6606540a5ae48428ed41e412131efb3c5a");

        Call<GraphReportsResponse> call = apiInterface.fetchGraphReports(jsonObject);

        call.enqueue(new Callback<GraphReportsResponse>() {

            @Override
            public void onResponse(Call<GraphReportsResponse> call, Response<GraphReportsResponse> response) {

                GraphReportsResponse graphReportsResponse = response.body();

                if (graphReportsResponse != null && !graphReportsResponse.getGraphReport().isEmpty()) {

                    if (graphReportsResponse.getMessage() != null && !graphReportsResponse.getMessage().isEmpty()) {

                        // Extract the graph image URL from the response
                        String graphImageUrl = graphReportsResponse.getGraphReport();
                        final ImagePopup imagePopup = new ImagePopup(MainActivityGas.this);
                        imagePopup.setBackgroundColor(Color.TRANSPARENT);
                        imagePopup.setFullScreen(false);
                        imagePopup.setHideCloseIcon(true);
                        imagePopup.setImageOnClickClose(true);
                        imagePopup.setHideCloseIcon(true);
                        imagePopup.setMaxWidth(60);
                        imagePopup.setMaxHeight(200);
                        imagePopup.initiatePopupWithPicasso(graphImageUrl);
                        imagePopup.viewPopup();

                    }
                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<GraphReportsResponse> call, Throwable t) {

                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }


    @Override
    public void onBackPressed() {

        if (navDrawerLayoutGasBinding.navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawerLayoutGasBinding.navDrawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


}