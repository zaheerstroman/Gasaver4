package com.gasaver.fragment;

//import static androidx.core.app.AppOpsManagerCompat.Api23Impl.getSystemService;

import static android.content.Context.MODE_PRIVATE;
import static androidx.fragment.app.FragmentManager.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.itextpdf.text.pdf.PdfName.NM;
import static com.itextpdf.text.pdf.PdfName.S;

import android.Manifest;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.gasaver.ApiInterface;
import com.gasaver.GooglePlaceModel;
import com.gasaver.NearLocationInterface;
import com.gasaver.PlaceModel;
import com.gasaver.R;
import com.gasaver.Response.CatResponse;
import com.gasaver.Response.DefaultDataModel;
import com.gasaver.Response.StationDataResponse;
import com.gasaver.Response.WishlistResponse;
import com.gasaver.SavedPlaceModel;
import com.gasaver.activity.DirectionActivity;
import com.gasaver.activity.MainPickmanActivity;
import com.gasaver.activity.SelectCityActivity;
import com.gasaver.activity.WishListActivity;
import com.gasaver.adapter.GooglePlaceAdapter;
import com.gasaver.adapter.InfoWindowAdapter;
import com.gasaver.constant.AllConstant;
import com.gasaver.customviews.PicassoMarker;
import com.gasaver.databinding.FragmentHomeGasaverBinding;
import com.gasaver.model.CategoryModel;
import com.gasaver.model.MyItem;
import com.gasaver.model.googleplacemodel.GoogleResponseModel;
import com.gasaver.network.APIClient;
import com.gasaver.permissions.AppPermissions;
import com.gasaver.utility.LoadingDialog;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.gasaver.view.FuelDistanceEmployeeListFragment;
import com.gasaver.webservices.RetrofitAPI;
import com.gasaver.webservices.RetrofitClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.clustering.ClusterManager;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


//FindRoutesTechMirrors
public class HomeFragmentGasaver extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, NearLocationInterface, GoogleApiClient.OnConnectionFailedListener, RoutingListener {


    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;


    //    Spinner spinner_budget;
    Spinner spinner_subcat;

    String catid = "1";

    //    private String[] budgetArrayList = {"", "E10", "Diesel", "AdBlue", "Unleaded", "PremDSL", "U95", "TruckDSL", "E85", "U98", "U91", "P95", "P98", "PDL", "B20", "EV", "DL"};
    private String[] budgetArrayList = {"", "E10", "DL", "AdBlue", "PremDSL", "U95", "TruckDSL", "E85", "U98", "U91", "P95", "P98", "PDL", "EV"};


    public static Context context;


    private FragmentHomeGasaverBinding binding;

    private GoogleMap homeMap;

    String URLString;


    //NearMe
    private GoogleMap mGoogleMap;

    FuelDistanceEmployeeListFragment addPhotoBottomDialogFragment;

    private AppPermissions appPermissions;
    private boolean isLocationPermissionOk, isTrafficEnable;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;
    private FirebaseAuth firebaseAuth;
    private Marker currentMarker;
    private LoadingDialog loadingDialog;
    private int radius = 5000;
    private RetrofitAPI retrofitAPI;
    private List<GooglePlaceModel> googlePlaceModelList;
    private PlaceModel selectedPlaceModel;
    private GooglePlaceAdapter googlePlaceAdapter;


    private GoogleMap.InfoWindowAdapter infoWindowAdapter;

    private ArrayList<String> userSavedLocationId;
    private DatabaseReference locationReference, userLocationReference;

    //Game App Studio Map Direction:----

    public static boolean asc ;
    public static boolean price_asc ;

    //   private GoogleMap map;
    private ApiInterface apiInterface;
    private List<LatLng> polylinelist;
    private PolylineOptions polylineOptions;
    //    private latlng origion, destination;
    private LatLng origion, dest;
    private GoogleMap map;


    //MapoDirectionCodeWorked:----
//    private GoogleMap mMap;
    //    private ActivityMapsBinding binding;
    private Context mContext;

    //FINDROUTESTECHMIRRORS:----
    //google map object
    private GoogleMap mMap;

    //current and destination location objects
    Location myLocation = null;
    Location destinationLocation = null;
    protected LatLng start = null;
    protected LatLng end = null;

    //to get location permissions.
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;

    //polyline object
    private List<Polyline> polylines = null;

    View view;

    private ArrayList<CategoryModel> catList = new ArrayList<>();
    private ArrayList<CategoryModel> subcatList = new ArrayList<>();
    public ArrayList<StationDataResponse.StationDataModel> stationDataList = new ArrayList<>();


    public ArrayList<WishlistResponse.WishlistModel> WishlistList = new ArrayList<>();

    public ArrayList<StationDataResponse> stationDataList1 = new ArrayList<>();

    // Declare a variable for the cluster manager.
    private ClusterManager<MyItem> clusterManager;

    public StationDataResponse stationDataResponse;

    //    private String distance = "distance";
//    private String distance = "100";
    private String distance = "30";


    private String searchCity = "Sydney";

    private double searchCityLat = -33.8688197, searchCityLang = 151.2092955;

//    List<MainData> tempList = new ArrayList<>();

    public static HomeFragmentGasaver context1;

    private static final int NOTIFICATION_ID = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Fragment
        binding = FragmentHomeGasaverBinding.inflate(inflater, container, false);


        appPermissions = new AppPermissions();
        firebaseAuth = FirebaseAuth.getInstance();
        loadingDialog = new LoadingDialog(requireActivity());
        retrofitAPI = RetrofitClient.getRetrofitClient().create(RetrofitAPI.class);
        googlePlaceModelList = new ArrayList<>();
        userSavedLocationId = new ArrayList<>();
        locationReference = FirebaseDatabase.getInstance().getReference("Places");

        context1 = HomeFragmentGasaver.this;

        //-----------------------------

//        Push Notification using FireBase:----------



        context = requireContext();

        boolean pushNotificationEnabled = isPushNotificationEnabled();
        if (pushNotificationEnabled) {

            // Create a notification manager
            NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);

            // Create a notification builder
            NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "channel_id")
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")

                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("GaSaver")
                    .setContentText("Welcome To Gasaver \uD83C\uDF89")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);



            // Create a notification channel (required for Android Oreo and above)
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            // Show the notification
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }




        //---------------------------------------------------------

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.homeMap);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://maps.googleapis.com/").build();
        apiInterface = retrofit.create(ApiInterface.class);
        //request location permission.
        requestPermision();
        binding.btnMapType.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.map_type_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {

                    case R.id.btnMenu:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                        mGoogleMap.setOnInfoWindowCloseListener(Color.RED);

                        break;

                    case R.id.btnNormal:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;

                    case R.id.btnSatellite:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;

                    case R.id.btnTerrain:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;

                    case R.id.btnHybrid:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

//               case R.id.btnGraph:
//                  mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                  break;

                    case R.id.btnSearchPlus:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnReward:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnSetting:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

//                    case R.id.btnGalary:
//                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                        break;
//
//                    case R.id.btnPlus:
//                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                        break;
//
//                    case R.id.btnMaines:
//                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                        break;


                }
                return true;
            });

            popupMenu.show();
        });


        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });


        binding.enableTraffic.setOnClickListener(view -> {

            if (isTrafficEnable) {
                if (mGoogleMap != null) {
                    mGoogleMap.setTrafficEnabled(false);
                    isTrafficEnable = false;
                }
            } else {
                if (mGoogleMap != null) {
                    mGoogleMap.setTrafficEnabled(true);
                    isTrafficEnable = true;
                }
            }

        });

        //Vinni
        binding.currentLocation.setOnClickListener(currentLocation -> getCurrentLocation());
        //Ali
        binding.currentLocation.setOnClickListener(v -> getMyLocationNow());


//        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showBottomSheet();
//            }
//        });

        binding.btnSearchPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });


        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.homeMap)).getMap();
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.homeMap);

//                Button zoomInButton = (Button) findViewById(R.id.zoom_in_button);
                Button zoomInButton = (Button) view.findViewById(R.id.btnPlus);

                zoomInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get the current camera position and zoom level
                        CameraPosition cameraPosition = map.getCameraPosition();
                        float zoomLevel = cameraPosition.zoom;

                        // Increment the zoom level and animate the camera to the new position
                        map.animateCamera(CameraUpdateFactory.zoomTo(zoomLevel + 1));
                    }
                });
//                showBottomSheet();
            }
        });


        // Get a reference to the Google Map object

        binding.edtPlaceName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getPlaces(s.toString());
            }
        });
        try {

            if (!Places.isInitialized()) {
                Places.initialize(getActivity(), getResources().getString(R.string.API_KEY));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) binding.getRoot().findViewById(R.id.edit_search);
        new FetchCityNamesTask(requireContext(), autoCompleteTextView).execute();

        // Define an array of city names to use as suggestions
//        String[] cityNames = new String[]{"New York", "Los Angeles", "Chicago", "Houston", "Philadelphia", "Phoenix", "San Antonio", "San Diego", "Dallas", "San Jose"};
        String[] cityNames = new String[]{"Sydney", "Melbourne", "Canberra", "Brisbane", "Adelaide", "Perth", "Darwin", "Hobart", "Newcastle", "Cairns",
                "Townsville", "Geelong", "Wollongong", "Gold Coast", "Ballarat", "Bundaberg", "Bendigo", "Bathurst", "Albany", "Bunbury", "Mackay", "Dubbo", "Orange"};

// Create an ArrayAdapter to provide the suggestions
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, cityNames);

// Get the AutoCompleteTextView and set the adapter

        autoCompleteTextView.setAdapter(adapter);

// Set the minimum number of characters required to show suggestions
        autoCompleteTextView.setThreshold(1);

// Set a listener to handle the user's selection of a suggestion


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = (String) parent.getItemAtPosition(position);
                // Handle the selected city here
                EditText editText = binding.getRoot().findViewById(R.id.edit_search);
                editText.setText(selectedCity);
                performSearch();


            }
        });

        AutoCompleteTextView editText = (AutoCompleteTextView) binding.getRoot().findViewById(R.id.edit_search);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Handle search action here
                    performSearch();
                    return true;

                }
                performSearch();
                return false;
            }
        });

//        edit_search


        binding.spinnerSubcat.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int oldIndex, @Nullable String oldItem, int newIndex, String newItem) {
                getStationsData();

            }
        });
        binding.placesGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                binding.spinnerSubcat.selectItemByIndex(binding.placesGroup.getCheckedChipId() + 1);
            }
        });

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this::getStationsData,7000);
        return binding.getRoot();
    }


    public boolean isPushNotificationEnabled() {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_preferences", MODE_PRIVATE);
//        SharedPrefs preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
//        return preferences.getBoolean("push_notification_enabled", true);
        return preferences.getBoolean("push_notification_enabled", true);
    }

//    public void setPushNotificationEnabled(boolean enabled) {
//        SharedPrefs preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
//        SharedPrefs.Editor editor = preferences.edit();
//        editor.putBoolean("push_notification_enabled", enabled);
//        editor.apply();
//    }

    private void performSearch() {
        AutoCompleteTextView searchEditText = (AutoCompleteTextView) binding.getRoot().findViewById(R.id.edit_search);
        String searchString = searchEditText.getText().toString();
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(searchString, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                double lat = address.getLatitude();
                double lng = address.getLongitude();
                LatLng latLng = new LatLng(lat, lng);

//                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                String city = address.getLocality();
                String country = address.getCountryName();
                searchCity = city + ", " + country;
                searchCityLat = lat;
                searchCityLang = lng;
                getStationsData();


//                getStationsList();
            } else {
                // No results found
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setUpClusterer() {
        // Position the map.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        clusterManager = new ClusterManager<MyItem>(context, map);

        map.setOnCameraIdleListener(clusterManager);
        map.setOnMarkerClickListener(clusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 51.5145160;
        double lng = -0.1270060;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng, "Title " + i, "Snippet " + i);
            clusterManager.addItem(offsetItem);
        }
    }
//    });


    private void getCategories() {

        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);


        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_name", "category");
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));


        Call<CatResponse> call = apiInterface.getDefaultData(jsonObject);
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                try {


                    CommonUtils.hideLoading();
                    catList = response.body().getData();

                    ArrayList<String> spinnerItems = new ArrayList<>();

                    for (CategoryModel c : response.body().getData()) {
                        spinnerItems.add(c.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerCaseText1.setAdapter(adapter);
                    binding.spinnerCaseText1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        //                    binding.spinnerSubcat.setAdapter(adapter);
//                    binding.spinnerSubcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.addProperty("table_name", "subcategory");
                            jsonObject.addProperty("reference_id", "category_id");
                            jsonObject.addProperty("id", catList.get(binding.spinnerCaseText1.getSelectedItemPosition()).getId());


                            jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
                            jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));

                            getsubCategories(jsonObject);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

//        }


    }


    private void getsubCategories(JsonObject jsonObject) {
        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        Call<CatResponse> call = apiInterface.getDefaultData(jsonObject);
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                try {
                    com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);

                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

                    CommonUtils.hideLoading();
                    subcatList = response.body().getData();
                    ArrayList<String> spinnerItems = new ArrayList<>();
                    spinnerItems.add("Select");
                    for (CategoryModel c : response.body().getData()) {
                        spinnerItems.add(c.getName());
                        Chip chip = new Chip(requireContext());
                        chip.setText(c.getName());
                        chip.setGravity(Gravity.CENTER_HORIZONTAL);
                        chip.setId(response.body().getData().indexOf(c));
//                        chip.setPadding(8, 8, 8, 8);
//                        chip.setTextColor(getResources().getColor(white));
                        chip.setTextColor(getResources().getColor(R.color.chip_textclr));
                        chip.setChipStrokeColorResource(R.color.chip_textclr);
                        chip.setChipBackgroundColorResource(R.color.chip_bg);
                        chip.setCheckable(true);
                        chip.setCheckedIconVisible(false);

//                        builder.addFormDataPart("fcm_token", SharedPrefs.getInstance(this).getString(Constants.FCM_TOKEN))
                        builder.addFormDataPart("token", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.TOKEN))
//                                .addFormDataPart("city", binding.spinnerCity.getSelectedItem().toString())
                                .addFormDataPart("id", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.ID)).addFormDataPart("user_id", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.USER_ID))
//                                .addFormDataPart("city", binding.spinnerSubcat.getSelectedItem().toString())
//                                .addFormDataPart("subcategory", binding.spinnerSubcat.getSelectedItem().toString())
//                                .addFormDataPart("subcategory", binding.placesGroup.getSelectedItem().toString())

//                        jsonObject.addProperty("reference_id", "category_id");
                                .addFormDataPart("reference_id", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.REFERENCE_ID)).build();


                        binding.placesGroup.addView(chip);
//                        binding.spinnerSubcat.addView(chip);

                    }


                    //Vinni
//                    binding.spinnerSubcat.setAdapter(adapter);
//                    binding.spinnerSubcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                            binding.placesGroup.check(position);
//                            if (binding.spinnerSubcat.getSelectedItemPosition() != 0)
//                                binding.placesGroup.check(position - 1);
//                            else
//
//                                //05-04-2023
//                                binding.placesGroup.clearCheck();
//
//
//                            builder.addFormDataPart("token", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.TOKEN)).addFormDataPart("id", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.ID)).addFormDataPart("user_id", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.USER_ID))
////                                    .addFormDataPart("subcategory", binding.spinnerSubcat.getSelectedItem().toString())
////                                    .addFormDataPart("city", binding.spinnerCity.getSelectedItem().toString())
//
//                                    .addFormDataPart("reference_id", SharedPrefs.getInstance(getApplicationContext()).getString(Constants.REFERENCE_ID)).build();
//
//
//                            //05-04-2023
////                            if (binding.spinnerSubcat.getSelectedItemPosition() != 0)
//                            getStationsData();
////                            else {
//
//                            //05-04-2023
//                            mGoogleMap.clear();
////                            }
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });


                    //Ali
                    binding.spinnerSubcat.setItems(spinnerItems);

                    //By Default Selected not E10 every time Previous O/p
                    binding.spinnerSubcat.selectItemByIndex(0);


                    binding.spinnerSubcat.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
                        @Override
                        public void onItemSelected(int oldIndex, @Nullable String oldItem, int newIndex, String newItem) {
                            binding.spinnerSubcat.getSelectedIndex();
                            if (!newItem.equals("Select")) {
                                catid = response.body().getData().get(binding.spinnerSubcat.getSelectedIndex() - 1).getId();
                                getStationsData();
                            } else {
                                getStationsData();
                            }
                        }
                    });
                    binding.placesGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
                        @Override
                        public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                            binding.spinnerSubcat.selectItemByIndex(binding.placesGroup.getCheckedChipId() + 1);
                            if (!binding.spinnerSubcat.getText().toString().equals("Select")) {
                                catid = response.body().getData().get(binding.spinnerSubcat.getSelectedIndex() - 1).getId();
                                getStationsData();

                                //03-05-2023 Changes
//                                if (stationDataList == null || stationDataList.isEmpty())
//                                    Toast.makeText(getActivity(), "No stations found", Toast.LENGTH_SHORT).show();


                            } else {
                                getStationsData();
                            }
                            getStationsData();
                        }
                    });


                    //05-04-2023
//                    binding.placesGroup.check(0);
//                    binding.spinnerSubcat.check(0);

//                    binding.spinnerSubcat.setItems(spinnerItems);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });
    }


    private void getStationsData() {

        //05-04-2023
        if (mGoogleMap != null)
            mGoogleMap.clear();


        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("latitude", searchCityLat);
        jsonObject.addProperty("longitude", searchCityLang);

        //23-03-2023
        jsonObject.addProperty("distance", "30");

//        jsonObject.addProperty("city", "Sydney");
        jsonObject.addProperty("city", searchCity);


        jsonObject.addProperty("sort", "distance");

        //Ascending or Decending both want not single asc
        jsonObject.addProperty("order", "asc");

        jsonObject.addProperty("category", catList.get(binding.spinnerCaseText1.getSelectedItemPosition()).getId());

        //Vinni
        //if (binding.spinnerSubcat.getSelectedItemPosition() != 0)
        //            jsonObject.addProperty("subcategory", subcatList.get(binding.spinnerSubcat.getSelectedItemPosition() - 1).getId());

        //Ali
        if (!binding.spinnerSubcat.getText().equals("Select"))
            jsonObject.addProperty("subcategory", catid);

        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));

        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);

        Call<StationDataResponse> call = apiInterface.getStationsData(jsonObject);

        call.enqueue(new Callback<StationDataResponse>() {


            @Override
            public void onResponse(Call<StationDataResponse> call, Response<StationDataResponse> response) {
                try {


                    CommonUtils.hideLoading();
                    stationDataList = response.body().getData();

                    // Extract default data from the response
                    StationDataResponse.StationDataModel dataResponse = response.body().getData().get(1);

                    // Create a list of default data models
                    ArrayList<DefaultDataModel> defaultDataModels = new ArrayList<>();

                    for (DefaultDataModel defaultData : dataResponse.getDefault_data()) {
                        // Create a new DefaultDataModel object
                        DefaultDataModel defaultDataModel = new DefaultDataModel();

                        // Set the type and amount values for the current default data object
                        defaultDataModel.setType(defaultData.getType());
                        defaultDataModel.setAmount(defaultData.getAmount());

                        // Add the current default data object to the list
                        defaultDataModels.add(defaultDataModel);
                    }


                    mGoogleMap.setInfoWindowAdapter(new InfoWindowAdapter(requireContext(), defaultDataModels));

//                    DefaultDataModel
                    for (StationDataResponse.StationDataModel stationDataModel : stationDataList) {
                        Log.e(TAG, "called: ");
                        addMarker(stationDataModel);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "No stations found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StationDataResponse> call, Throwable t) {
//            public void onFailure(Call<StationDataResponse.StationDataModel> call, Throwable t) {

                CommonUtils.hideLoading();

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getStationsList(String sortby, String order) {

//    public void getStationsList(String sortby, String order) {

        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("latitude", searchCityLat);
//        jsonObject.addProperty("longitude", searchCityLang);

        //23-03-2023
//        jsonObject.addProperty("distance", "100");
        jsonObject.addProperty("distance", distance);

        jsonObject.addProperty("city", searchCity);

        jsonObject.addProperty("sort", sortby);

        //Assending & Dessending Both we want
//        jsonObject.addProperty("order", order);
        jsonObject.addProperty("order", order);
//        jsonObject.addProperty("order", "desc");


        jsonObject.addProperty("category", catList.get(binding.spinnerCaseText1.getSelectedItemPosition()).getId());

//        Vinni
//        if (binding.spinnerSubcat.getSelectedItemPosition() != 0)
//            jsonObject.addProperty("subcategory", subcatList.get(binding.spinnerSubcat.getSelectedItemPosition() - 1).getId());

//        Ali
        if (!binding.spinnerSubcat.getText().equals("Select"))
            jsonObject.addProperty("subcategory", catid);

        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));


        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);


        Call<StationDataResponse> call = apiInterface.getStationsData(jsonObject);

        call.enqueue(new Callback<StationDataResponse>() {
            @Override
            public void onResponse(Call<StationDataResponse> call, Response<StationDataResponse> response) {

                try {

                    Log.e(TAG, response.body().getData().get(1).toString());

                    CommonUtils.hideLoading();

                    stationDataList = response.body().getData();

                    //Vinni:----

                    // Extract default data from the response
                    StationDataResponse.StationDataModel dataResponse = response.body().getData().get(1);
                    // Create a list of default data models
                    ArrayList<DefaultDataModel> defaultDataModels = new ArrayList<>();

                    for (DefaultDataModel defaultData : dataResponse.getDefault_data()) {
                        // Create a new DefaultDataModel object
                        DefaultDataModel defaultDataModel = new DefaultDataModel();

                        // Set the type and amount values for the current default data object
                        defaultDataModel.setType(defaultData.getType());
                        defaultDataModel.setAmount(defaultData.getAmount());

                        // Add the current default data object to the list
                        defaultDataModels.add(defaultDataModel);
                    }

                    if (stationDataList.get(1).getDefault_data() == null || stationDataList.get(1).getDefault_data().isEmpty())
                        Toast.makeText(getActivity(), "No stations found", Toast.LENGTH_SHORT).show();

//                    if (stationDataList == null || stationDataList.isEmpty())
//                        Toast.makeText(getActivity(), "No stations found", Toast.LENGTH_SHORT).show();

                    mGoogleMap.setInfoWindowAdapter(new InfoWindowAdapter(requireContext(), defaultDataModels));

//                    DefaultDataModel
                    for (StationDataResponse.StationDataModel stationDataModel : stationDataList) {
                        addMarker(stationDataModel);
                    }


//                    Ali:----
                    addPhotoBottomDialogFragment.dismiss();

                    showBottomSheet();

                    CommonUtils.hideLoading();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<StationDataResponse> call, Throwable t) {

                CommonUtils.hideLoading();

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void requestPermision() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            locationPermission = true;
        }
    }

    //-----------------


    //to get user location
    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return;
        }

        //homeMap
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setOnMyLocationChangeListener(location -> {

            myLocation = location;

            //Currect, Your & My Location will show only, Google Map Fuel Logo Marker also show then get back Live Location

//            LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ltlng, 16f);
//                mGoogleMap.animateCamera(cameraUpdate);

        });


    }

    private void getMyLocationNow() {
        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            // Get the last known location
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            // Zoom the camera to the current location
                            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15)); // 15 is the zoom level
                        } else {
                            Toast.makeText(requireContext(), "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {

            ActivityCompat.requestPermissions(HomeFragmentGasaver.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }


//    ------------------------------------------


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView();
//      getUserSavedLocations();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        Log.d(TAG, "onMapReady() called with");
//        MapsInitializer.initialize(this);
        MapsInitializer.initialize(getApplicationContext());

//        addCustomMarker();

        getCategories();
        mGoogleMap = googleMap;

//        -----------------------

        //FindRoutesTechMirrors

        //homeMap
        mMap = googleMap;

        if (locationPermission) {
            getMyLocation();
        }
//        mGoogleMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()


//                        .target(new LatLng(17.393522,78.472950))
                .target(new LatLng(17.43539564025845, 78.44543771341806))

                .zoom(10).bearing(0).tilt(45).build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);


        //GoogleMapMyApplication1
        LatLng globical = new LatLng(17.435392154622992, 78.44546021228582);

//        googleMap.addMarker(new MarkerOptions().position(globical).title("Globicle E-commerce,Malakpet"));
//        googleMap.addMarker(new MarkerOptions().position(globical).title("Aakruti Software Solutions, Ameerpet"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(globical));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        //-----

//        Splash to Google Map Finding Best Routes to Karimnagar Rama Krishna Colony

//        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    MY_PERMISSIONS_REQUEST_LOCATION);
//        } else {
//            // Permission has already been granted
//            // Open Google Maps and redirect to the desired location
//            Uri gmmIntentUri = Uri.parse("google.navigation:q=latitude,longitude");
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");
//            startActivity(mapIntent);
//        }
//
//        Uri gmmIntentUri = Uri.parse("google.navigation:q=latitude,longitude");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);


//      ---------------------------


        if (appPermissions.isLocationOk(requireContext())) {
            isLocationPermissionOk = true;

            setUpGoogleMap();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(requireContext()).setTitle("Location Permission").setMessage("Near me required location permission to show you near by places").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestLocation();
                }
            }).create().show();
        } else {
            requestLocation();
        }

    }


    // function to find Routes.
    public void Findroutes(LatLng Start, LatLng End) {
        if (Start == null || End == null) {
            Toast.makeText(getApplicationContext(), "Unable to get location", Toast.LENGTH_LONG).show();
//            Toast.makeText(this,"Unable to get location", Toast.LENGTH_LONG).show();
        } else {

            Routing routing = new Routing.Builder().travelMode(AbstractRouting.TravelMode.DRIVING).withListener(this).alternativeRoutes(true).waypoints(Start, End)

                    //zaheerstroman@gmail.com
//                    .key("AIzaSyBrBCjR96NhwdK1RM5S00y-8edeAuMuA0g")  //also define your api key here.

                    //android@aakrutisolutions.com
                    .key("AIzaSyA1B-lkYW4V5rR8PP3Zr9gUbBWZoR3hOkg")  //also define your api key here.

                    .build();
            routing.execute();
        }
    }

    //Routing call back functions.
    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {
//        Toast.makeText(this,"Finding Route...",Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Finding Route...", Toast.LENGTH_LONG).show();

    }

    //If Route finding success..
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        if (polylines != null) {
            polylines.clear();
        }
        PolylineOptions polyOptions = new PolylineOptions();
        LatLng polylineStartLatLng = null;
        LatLng polylineEndLatLng = null;


        polylines = new ArrayList<>();
        //add route(s) to the map using polyline
        for (int i = 0; i < route.size(); i++) {

            if (i == shortestRouteIndex) {
                polyOptions.color(getResources().getColor(R.color.purple_500));
                polyOptions.width(7);
                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());

                //homeMap
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylineStartLatLng = polyline.getPoints().get(0);
                int k = polyline.getPoints().size();
                polylineEndLatLng = polyline.getPoints().get(k - 1);
                polylines.add(polyline);

            } else {

            }

        }

        //Add Marker on route starting position
        MarkerOptions startMarker = new MarkerOptions();
        startMarker.position(polylineStartLatLng);
        startMarker.title("My Location");

        //homeMap
        mMap.addMarker(startMarker);

        //Add Marker on route ending position
        MarkerOptions endMarker = new MarkerOptions();
        endMarker.position(polylineEndLatLng);
        endMarker.title("Destination");

        //homeMap
        mMap.addMarker(endMarker);
    }


    @Override
    public void onRoutingCancelled() {
        Findroutes(start, end);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Findroutes(start, end);

    }


//    -----------------------------------------------------------------------------------------------------


    private void requestLocation() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION}, AllConstant.LOCATION_REQUEST_CODE);
    }


    private void setUpGoogleMap() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(true);
        mGoogleMap.setOnMarkerClickListener(this::onMarkerClick);

        setUpLocationUpdate();
    }

    private void setUpLocationUpdate() {

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    for (Location location : locationResult.getLocations()) {
                        Log.d("TAG", "onLocationResult: " + location.getLongitude() + " " + location.getLatitude());
                    }
                }
                super.onLocationResult(locationResult);
            }
        };

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        startLocationUpdates();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Code to be executed after 5 seconds
                mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                    private float currentZoom = -1;

                    @Override
                    public void onCameraChange(CameraPosition pos) {
                        if (pos.zoom != currentZoom) {
                            currentZoom = pos.zoom;

                            // Get the camera position
                            LatLng cameraPosition = pos.target;

                            // Create a geocoder instance
                            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

                            try {
                                // Get the address from the camera position
                                List<Address> addresses = geocoder.getFromLocation(cameraPosition.latitude, cameraPosition.longitude, 1);

                                if (addresses.size() > 0) {
                                    // Get the city name from the address
                                    String cityName = addresses.get(0).getLocality();

                                    // Get the radius in kilometers from the current zoom level
                                    double radiusInKm = 156543.03392 * Math.cos(cameraPosition.latitude * Math.PI / 180) / Math.pow(2, currentZoom);

                                    Log.e(TAG, "City name: " + cityName + ", Radius: " + radiusInKm + " km");

                                    try {
                                        if (cityName != null) {
                                            searchCityLat = cameraPosition.latitude;
                                            searchCityLang = cameraPosition.longitude;
                                            int radiusInKmInt = (int) radiusInKm;
                                            distance = String.valueOf(radiusInKmInt);
                                            getStationsData();
                                        }
                                    } catch (Exception e) {
                                        Log.e(TAG, e.toString());
                                    }


                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }, 5000); // 5000 milliseconds = 5 seconds


    }


    private void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getCurrentLocation();

                if (task.isSuccessful()) {
//                            Toast.makeText(requireContext(), "Location updated started", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getCurrentLocation() {

        // Construct a PlacesClient
        Places.initialize(getApplicationContext(), getString(R.string.maps_key));
//        placesClient = Places.createClient(getApplicationContext());

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation = location;
                Log.e(TAG, "onSuccess: " + location);

                try {
//                    LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
//                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13f));


                    //Ali
//                    LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void stopLocationUpdate() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        Log.d("TAG", "stopLocationUpdate: Location Update stop");
    }

    @Override
    public void onPause() {
        super.onPause();

        if (fusedLocationProviderClient != null) stopLocationUpdate();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.spinnerSubcat.dismiss();
        if (fusedLocationProviderClient != null) {

            startLocationUpdates();
        }
    }


    //    private void initMarker(ArrayList<StationDataResponse.StationDataModel> stationDataList) {
    private void addMarker(StationDataResponse.StationDataModel stationDataModel) {

        double smalVal = Double.parseDouble(stationDataModel.getPrices().get(0).getAmount());
        for (StationDataResponse.PriceModel priceModel : stationDataModel.getPrices()) {
            Double am = Double.valueOf(priceModel.getAmount());
            if (smalVal > am)
                smalVal = am;
        }
        LatLng latLng = new LatLng(Double.parseDouble(stationDataModel.getLatitude()), Double.parseDouble(stationDataModel.getLongitude()));

        //Red /Blue
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(stationDataModel.getBrand() + " Price:" + smalVal).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        Marker mapmarker = mGoogleMap.addMarker(markerOptions);
        mapmarker.setSnippet(new Gson().toJson(stationDataModel)); // 10 15 5
        mapmarker.setTag(stationDataModel.getStationid()); // 10 15 5
        loadMarkerIcon(mapmarker, stationDataModel.getBrandIcon());

        //Direct Go with out Select
//        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

//        Search under Spinner Select will Go Gasaver Fuel Markers

//        if (binding.spinnerSubcat.getText().toString().equals("Select")){
//                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//        }

        if (!binding.spinnerSubcat.getText().toString().equals("Select") && !binding.editSearch.getText().toString().isEmpty()) {
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }

        if (stationDataList == null || stationDataList.isEmpty())
            Toast.makeText(getActivity(), "No stations found", Toast.LENGTH_SHORT).show();


        mapmarker.showInfoWindow();

    }

    private void loadMarkerIcon(final Marker marker, String BrandIcon) {
        try {
//            All Same Logos Are coming:------
//        Picasso.get().load("https:\\/\\/houseofspiritshyd.in\\/gasaver\\/admin\\/assets\\/portal\\/icons\\/Ampol.png").into(new com.squareup.picasso.Target() {
//            Picasso.get().load(BrandIcon).into(new com.squareup.picasso.Target() {
            Log.e(TAG, "pre execute: ");

            Glide.with(requireContext()).load(BrandIcon).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                    int height = 100;
                    int width = 100;
                    Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                    Bitmap customSizeMarker = Bitmap.createScaledBitmap(bitmap, width, height, false);
                    try {
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(customSizeMarker));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Log.e(TAG, "post execute: ");
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "onBitmapLoaded: " + e);
        }
    }

    //Water Mark image
    private Bitmap addWaterMark(Bitmap src) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);

        Bitmap waterMark = BitmapFactory.decodeResource(getResources(), R.drawable.zaheer);
        //  canvas.drawBitmap(waterMark, 0, 0, null);
        int startX = (canvas.getWidth() - waterMark.getWidth()) / 2;//for horisontal position
        int startY = (canvas.getHeight() - waterMark.getHeight()) / 2;//for vertical position
        canvas.drawBitmap(waterMark, startX, startY, null);

        return result;
    }


    private BitmapDescriptor getCustomIcon() {


        Drawable background = ContextCompat.getDrawable(requireContext(), R.drawable.location03);


        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());


        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(bitmap);


        background.draw(canvas);


        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }

    //GoogleMapMyApplication1

    //Search Scroll RecyclerView
    private void setUpRecyclerView() {

        binding.placesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.placesRecyclerView.setHasFixedSize(false);
        googlePlaceAdapter = new GooglePlaceAdapter(this);
        binding.placesRecyclerView.setAdapter(googlePlaceAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(binding.placesRecyclerView);

        binding.placesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (position > -1) {
                    GooglePlaceModel googlePlaceModel = googlePlaceModelList.get(position);
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlePlaceModel.getGeometry().getLocation().getLat(), googlePlaceModel.getGeometry().getLocation().getLng()), 20));
                }
            }
        });

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        String markerTag = (String) marker.getTag();
        Log.d("TAG", "onMarkerClick: " + markerTag);

        for (StationDataResponse.StationDataModel stationDataModel : stationDataList) {
//        for (WishlistResponse.WishlistModel wishlistModel : WishlistList) {

            if (stationDataModel.getStationid().equals(markerTag)) {
//            if (wishlistModel.getStationid().equals(markerTag)) {

                binding.stationLayout.cvStation.setVisibility(View.VISIBLE);

                binding.stationLayout.ivWishlist.setImageResource(stationDataModel.getWishlist() != null && stationDataModel.getWishlist().equalsIgnoreCase("yes") ? R.drawable.wishlist_added : R.drawable.like_icon);

                binding.stationLayout.ivShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String sAux = "\nJust clicked & install this application:\n\n";
//
//                    sAux = sAux + "https://play.google.com/store/apps/details?id=org.halalscan.jss\n\n";
                            sAux = sAux + " https://play.google.com/store/apps/details?id=com.pineconesoft.petrolspy&pli=1\n\n";
                            i.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(i, "Choose One"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                binding.stationLayout.txtPlaceName.setText(stationDataModel.getName());

                Glide.with(getActivity()).load(stationDataModel.getBrandIcon()).into(binding.stationLayout.ivProjImg);

//                Glide.with(getActivity()).load(stationDataModel.getBrandIcon()).into(binding.stationLayout.imgBrand);

                binding.stationLayout.txtPlaceAddress.setText(stationDataModel.getAddress());


                String latesttDTE = null;

                try {
                    binding.stationLayout.txtPrices.setText("");

                    for (StationDataResponse.PriceModel priceModel : stationDataModel.getPrices()) {

//                        binding.stationLayout.txtPrices.append(priceModel.getType() + ": $ " + priceModel.getAmount() + "\n");

                        String priceString = priceModel.getType() + ": $ " + priceModel.getAmount() + "\n";

                        // Generate a random color
                        Random random = new Random();
                        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                        // Create a SpannableString to set the color for the priceModel.getType() section
                        SpannableString spannableString = new SpannableString(priceString);
                        int start = priceString.indexOf(priceModel.getType());
//                        int end = start + priceModel.getType().length();
                        int end = priceString.length();
                        spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        // Set the modified SpannableString on the TextView
//                        holder.txtPrices.append(spannableString);
                        binding.stationLayout.txtPrices.append(spannableString);

                        if (latesttDTE == null)
                            latesttDTE = priceModel.getLastupdated();
                        else if (CommonUtils.getDate(latesttDTE).getTime() >= (CommonUtils.getDate(priceModel.getLastupdated()).getTime())) {
                            latesttDTE = priceModel.getLastupdated();
                        }
                    }
                    binding.stationLayout.txtLastUpdated.setText("Last Updated: ");
                    binding.stationLayout.txtLastUpdated.append(latesttDTE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Log.e(TAG, "onSuccess: " + location );


                try {
                    double distance = SphericalUtil.computeDistanceBetween(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), marker.getPosition());

                    if (distance > 1000) {
                        double kilometers = distance / 1000;
                        binding.stationLayout.txtDis.setText(CommonUtils.roundUpDecimal(distance, 2) + " KM");

                        Log.e(TAG, "kilometers: " + kilometers);

                    } else {
                        binding.stationLayout.txtDis.setText(CommonUtils.roundUpDecimal(distance, 2) + " Meters");

                    }

                    float speed = currentLocation.getSpeed();

                    if (speed > 0) {
                        double time = distance / speed;
                        binding.stationLayout.txtTime.setText(CommonUtils.roundUpDecimal(time, 2) + " sec");

                        Log.e(TAG, "time: " + time);
                    } else {
                        binding.stationLayout.txtTime.setText("N/A");

                    }
//                    binding.stationLayout.txtDis.setText(String.valueOf(stationDataModel.getDistance()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                binding.stationLayout.btnNavigate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {

                            String uri = "http://maps.google.com/maps?saddr=" + searchCity + "," + searchCity + "&daddr=" + searchCityLat + "," + searchCityLang;
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            startActivity(intent);

                            intent.putExtra("lat", marker.getPosition().latitude);
                            intent.putExtra("lng", marker.getPosition().longitude);

                            startActivity(intent);

                        }
                    }
                });
                binding.stationLayout.btnSubmitPrices.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), MainPickmanActivity.class);
                        intent.putExtra("station_id", stationDataModel.getStationid());
//                        intent.putExtra("station_id", wishlistModel.getStationid());

                        intent.putExtra("category", catList.get(binding.spinnerCaseText1.getSelectedItemPosition()).getId());
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);

                    }
                });
                binding.stationLayout.ivWishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveWishlist(stationDataModel, stationDataModel.getId(), stationDataModel.getWishlist() != null && stationDataModel.getWishlist().equalsIgnoreCase("Yes") ? "No" : "Yes");

//                        saveWishlist(wishlistModel, wishlistModel.getId(), stationDataModel.getWishlist() != null && stationDataModel.getWishlist().equalsIgnoreCase("Yes") ? "No" : "Yes");
//                        saveWishlist(wishlistModel, wishlistModel.getId(), wishlistModel.getWishlist() != null && wishlistModel.getWishlist().equalsIgnoreCase("Yes") ? "No" : "Yes");

                    }
                });
                binding.stationLayout.ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.stationLayout.cvStation.setVisibility(View.GONE);
                    }
                });


//                Glide.with(getApplicationContext()).load(Constants.LOGO_IMG_URL + stationDataList.get(position).getBrandIcon()).into(holder.iv_proj_img);

//                Glide.with(getApplicationContext()).load(Constants.LOGO_IMG_URL + stationLayout.get(position).getBrandIcon()).into(holder.iv_proj_img);


            }
        }

//        binding.placesRecyclerView.scrollToPosition(markerTag);
        return false;
    }

    private void saveWishlist(StationDataResponse.StationDataModel stationDataModel, Integer stationid, String iswishlist) {
//private void saveWishlist(WishlistResponse.WishlistModel wishlistModel, Integer stationid, String iswishlist) {

//        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));
        jsonObject.addProperty("vendor_id", stationid);
        jsonObject.addProperty("wishlist", iswishlist);

        Call<StationDataResponse> call = apiInterface.saveWishlist(jsonObject);
//    Call<WishlistResponse> call = apiInterface.saveWishlist(jsonObject);

        call.enqueue(new Callback<StationDataResponse>() {
//    call.enqueue(new Callback<WishlistResponse>() {

            @Override
            public void onResponse(Call<StationDataResponse> call, Response<StationDataResponse> response) {
//        public void onResponse(Call<WishlistResponse> call, Response<WishlistResponse> response) {

                try {
                    CommonUtils.hideLoading();
                    if (response.body().isStatus_code()) {

                        stationDataModel.setWishlist(iswishlist);
//                        wishlistModel.setWishlist(iswishlist);

                        binding.stationLayout.ivWishlist.setImageResource(iswishlist.equalsIgnoreCase("yes") ? R.drawable.wishlist_added : R.drawable.like_icon);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<StationDataResponse> call, Throwable t) {
//            public void onFailure(Call<WishlistResponse> call, Throwable t) {

                CommonUtils.hideLoading();
            }
        });


    }

    @Override
    public void onSaveClick(GooglePlaceModel googlePlaceModel) {

        if (userSavedLocationId.contains(googlePlaceModel.getPlaceId())) {
            new AlertDialog.Builder(requireContext()).setTitle("Remove Place").setMessage("Are you sure to remove this place?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    removePlace(googlePlaceModel);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        } else {
            loadingDialog.startLoading();

            locationReference.child(googlePlaceModel.getPlaceId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {

                        SavedPlaceModel savedPlaceModel = new SavedPlaceModel(googlePlaceModel.getName(), googlePlaceModel.getVicinity(), googlePlaceModel.getPlaceId(), googlePlaceModel.getRating(), googlePlaceModel.getUserRatingsTotal(), googlePlaceModel.getGeometry().getLocation().getLat(), googlePlaceModel.getGeometry().getLocation().getLng());

                        saveLocation(savedPlaceModel);
                    }

                    saveUserLocation(googlePlaceModel.getPlaceId());

                    int index = googlePlaceModelList.indexOf(googlePlaceModel);
                    googlePlaceModelList.get(index).setSaved(true);
                    googlePlaceAdapter.notifyDataSetChanged();
                    loadingDialog.stopLoading();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    private void removePlace(GooglePlaceModel googlePlaceModel) {

        userSavedLocationId.remove(googlePlaceModel.getPlaceId());
        int index = googlePlaceModelList.indexOf(googlePlaceModel);
        googlePlaceModelList.get(index).setSaved(false);
        googlePlaceAdapter.notifyDataSetChanged();

        Snackbar.make(binding.getRoot(), "Place removed", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSavedLocationId.add(googlePlaceModel.getPlaceId());
                googlePlaceModelList.get(index).setSaved(true);
                googlePlaceAdapter.notifyDataSetChanged();

            }
        }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);

                userLocationReference.setValue(userSavedLocationId);
            }
        }).show();

    }

    private void saveUserLocation(String placeId) {

        userSavedLocationId.add(placeId);
        userLocationReference.setValue(userSavedLocationId);
        Snackbar.make(binding.getRoot(), "Place Saved", Snackbar.LENGTH_LONG).show();
    }

    private void saveLocation(SavedPlaceModel savedPlaceModel) {
        locationReference.child(savedPlaceModel.getPlaceId()).setValue(savedPlaceModel);
    }

    @Override
    public void onDirectionClick(GooglePlaceModel googlePlaceModel) {

        String placeId = googlePlaceModel.getPlaceId();
        Double lat = googlePlaceModel.getGeometry().getLocation().getLat();
        Double lng = googlePlaceModel.getGeometry().getLocation().getLng();

        Intent intent = new Intent(requireContext(), DirectionActivity.class);
        intent.putExtra("placeId", placeId);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);

        startActivity(intent);

    }

    //---------------------------------

    public void showBottomSheet() {
        addPhotoBottomDialogFragment = new FuelDistanceEmployeeListFragment(stationDataList, currentLocation, this.requireContext());

        addPhotoBottomDialogFragment.show(getParentFragmentManager(), "");
    }

    private void getPlaces(String placeName) {

        if (isLocationPermissionOk) {

            loadingDialog.startLoading();
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + currentLocation.getLatitude() + "," + currentLocation.getLongitude() + "&radius=" + radius + "&type=" + placeName + "&key=" + getResources().getString(R.string.API_KEY);

            if (currentLocation != null) {


                retrofitAPI.getNearByPlaces(url).enqueue(new Callback<GoogleResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<GoogleResponseModel> call, @NonNull Response<GoogleResponseModel> response) {
                        Gson gson = new Gson();
                        String res = gson.toJson(response.body());
                        Log.d("TAG", "onResponse: " + res);
                        if (response.errorBody() == null) {
                            if (response.body() != null) {
                                if (response.body().getGooglePlaceModelList() != null && response.body().getGooglePlaceModelList().size() > 0) {

                                    googlePlaceModelList.clear();
                                    mGoogleMap.clear();
                                    ArrayList<String> placenames = new ArrayList<>();
                                    for (int i = 0; i < response.body().getGooglePlaceModelList().size(); i++) {

//                                        if (userSavedLocationId.contains(response.body().getGooglePlaceModelList().get(i).getPlaceId())) {
//                                            response.body().getGooglePlaceModelList().get(i).setSaved(true);
//                                        }
                                        GooglePlaceModel place = response.body().getGooglePlaceModelList().get(i);
                                        googlePlaceModelList.add(place);
                                        placenames.add(place.getName());

                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, placenames);
                                    binding.edtPlaceName.setAdapter(adapter);
//                                    googlePlaceAdapter.setGooglePlaceModels(googlePlaceModelList);

                                }
                            }

                        } else {
                            Log.d("TAG", "onResponse: " + response.errorBody());
                            Toast.makeText(requireContext(), "Error : " + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }


                        loadingDialog.stopLoading();

                    }

                    @Override
                    public void onFailure(Call<GoogleResponseModel> call, Throwable t) {

                        Log.d("TAG", "onFailure: " + t);
                        loadingDialog.stopLoading();

                    }
                });
            }
        }

    }


    private class MarkerCallBack implements com.squareup.picasso.Callback {

        Marker marker = null;

        public MarkerCallBack(Marker marker) {
            this.marker = marker;
        }

        @Override
        public void onSuccess() {

            if (marker != null && marker.isInfoWindowShown()) {

                marker.hideInfoWindow();
                marker.showInfoWindow();

            }
        }

        @Override
        public void onError(Exception e) {

            Log.e(getClass().getSimpleName(), "Error Loading Thumbnail");
        }
    }


    private void buildAlertMessageNoGps() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
