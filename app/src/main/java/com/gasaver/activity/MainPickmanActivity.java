package com.gasaver.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gasaver.R;
import com.gasaver.Response.BaseResponse;
import com.gasaver.Response.CatResponse;
import com.gasaver.constant.AllConstant;
import com.gasaver.databinding.ActivityMainPickmanDemoOldBinding;
import com.gasaver.databinding.AddPriceItemBinding;

import com.gasaver.model.CategoryModel;
import com.gasaver.network.APIClient;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.FilePathUtils;
import com.gasaver.utils.SharedPrefs;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
//import com.google.mlkit.vision.common.InputImage;
//import com.google.mlkit.vision.text.Text;
//import com.google.mlkit.vision.text.TextRecognition;
//import com.google.mlkit.vision.text.TextRecognizer;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPickmanActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_GALLERY_PHOTO = 878;
    private ActivityMainPickmanDemoOldBinding binding;
    private Uri imageUri;
    private int REQUEST_TAKE_PHOTO = 987;
    private ArrayList<CategoryModel> subcatList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainPickmanDemoOldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ivCam.setOnClickListener(this);
        binding.ivGal.setOnClickListener(this);
        binding.searchBtn.setOnClickListener(this);


        getsubCategories();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:

                /* */
                updatepriceing();
                break;
            case R.id.iv_cam:
                checkLocation(new Location(""));
                break;
            case R.id.iv_gal:
                if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(MainPickmanActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainPickmanActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_GALLERY_PHOTO);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Choose file to upload."), REQUEST_GALLERY_PHOTO);
                }

                break;
        }
    }

    private void checkLocation(Location stationLoc) {

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    try {
                        Location currentLocation = location;
                        float distanceInMeters = location.distanceTo(stationLoc);
                        if (distanceInMeters <= 100) {
                            startCamera();
                        } else
//                            Toast.makeText(MainPickmanActivity.this, "Distance greater than 100", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainPickmanActivity.this, "With in 100 Meters Radius Camera will be Allow", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        startCamera();
                    }

                } else {
                    Toast.makeText(MainPickmanActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AllConstant.LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocation(new Location(""));
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_TAKE_PHOTO) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void startCamera() {
        if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(MainPickmanActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainPickmanActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);
        } else {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "Upload image");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your camera");
            imageUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_PHOTO && resultCode == RESULT_OK) {
            String imagePath = FilePathUtils.getRealPath(MainPickmanActivity.this, data.getData());
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            runTextRecognition(bitmap);
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = null;
                if (imageUri != null) {
                    String imagePath = FilePathUtils.getRealPath(MainPickmanActivity.this, imageUri);
                    bitmap = BitmapFactory.decodeFile(imagePath);
                    runTextRecognition(bitmap);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //to fetch text from cropped image
    private void runTextRecognition(Bitmap bitmap) {


        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        // below line is to create a variable for detector and we
        // are getting vision text detector from our firebase vision.
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance().getVisionTextDetector();

        // adding on success listener method to detect the text from image.
        detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                // calling a method to process
                // our text after extracting.
                processTxt(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // handling an error listener.
                Toast.makeText(MainPickmanActivity.this, "Fail to detect the text from image..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void processTxt(FirebaseVisionText text) {
        // below line is to create a list of vision blocks which
        // we will get from our firebase vision text.
        List<FirebaseVisionText.Line> lines = new ArrayList<>();

        // checking if the size of the
        // block is not equal to zero.
        if (text.getBlocks().size() == 0) {
            // if the size of blocks is zero then we are displaying
            // a toast message as no text detected.
            Toast.makeText(this, "No Text ", Toast.LENGTH_LONG).show();
            return;
        }
        // extracting data from each block using a for loop.
        for (FirebaseVisionText.Block block : text.getBlocks()) {

            // below line is to get text
            // from each block.
            for (FirebaseVisionText.Line txt : block.getLines()) {
                lines.add(txt);
            }

            // below line is to set our
            // string to our text view.
            Log.e("text blocks", block.getText());
        }
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
        int size = lines.size();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < size; j++) {
                if (isAtRight(lines.get(j).getBoundingBox(), lines.get(i).getBoundingBox())) {
                    ArrayList arrayList = hashMap.get(j);
                    if (arrayList == null)
                        arrayList = new ArrayList();
                    arrayList.add(lines.get(i).getText());
                    hashMap.put(j, arrayList);
                    Log.e(lines.get(i).getText(), lines.get(j).getText());
                }
            }
        }
        for (Map.Entry<Integer, ArrayList<String>> set :
                hashMap.entrySet()) {

            // Printing all elements of a Map
            Log.e("MapValues", set.getKey() + " = " + set.getValue());

            setValuesToEt(set.getValue());
        }
    }

    private void setValuesToEt(ArrayList<String> allValues) {
        for (String value : allValues) {
            int length = String.valueOf(value).length();
            try {
                double val = 0;
//                if (value.matches("[0-9.]+"))
                val = Double.parseDouble(value);
                if (length == 3 || length == 4)
                    val = val / 10;


                for (int i = 0; i < binding.llContent.getChildCount(); i++) {
                    View v = binding.llContent.getChildAt(i);
                    TextView tv_name = v.findViewById(R.id.tv_name);
                    EditText et_price = v.findViewById(R.id.et_price);
                    if (allValues.stream().anyMatch("e10"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("e10"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("unleaded"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("unleaded"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("diesel"::equalsIgnoreCase) && allValues.stream().anyMatch("prem"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("diesel"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("diesel"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("diesel"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("u95"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("u95"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("u98"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("u98"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("lpg"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("lpg"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("truck"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("truck"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("adblue"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("adblue"))
                        et_price.setText(String.valueOf(val));
                    if (allValues.stream().anyMatch("e85"::equalsIgnoreCase) && tv_name.getText().toString().toLowerCase().contains("e85"))
                        et_price.setText(String.valueOf(val));

                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean isAtRight(Rect rectA, Rect rectB) {
        /* Checks if Rect B is to the right of Rect A. */
        return rectA.top <= rectB.bottom && rectA.bottom >= rectB.top;
    }

    //getting text from recognization result
    private void updatepriceing() {
        CommonUtils.showLoading(this, "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));
        jsonObject.addProperty("station_id", getIntent().getStringExtra("station_id"));
        jsonObject.addProperty("category", getIntent().getStringExtra("category"));
        jsonObject.add("prices", getPrices());
        Call<BaseResponse> call = apiInterface.updatepriceing(jsonObject);
        Log.e(TAG, "updatepriceing: " );
        
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    CommonUtils.hideLoading();
                    Log.e(TAG, response.body().toString() );
                    Toast.makeText(MainPickmanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage() );
                CommonUtils.hideLoading();
            }
        });


    }

    private JsonObject getPrices() {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < binding.llContent.getChildCount(); i++) {
            View v = binding.llContent.getChildAt(i);
            EditText et_price = v.findViewById(R.id.et_price);
            jsonObject.addProperty(et_price.getTag().toString(), et_price.getText().toString());
        }
        return jsonObject;
    }


    private void getsubCategories() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_name", "subcategory");
        jsonObject.addProperty("reference_id", "category_id");
        jsonObject.addProperty("id", getIntent().getStringExtra("category"));
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(MainPickmanActivity.this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(MainPickmanActivity.this).getString(Constants.TOKEN));
        CommonUtils.showLoading(MainPickmanActivity.this, "Please Wait", false);

        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        Call<CatResponse> call = apiInterface.getDefaultData(jsonObject);
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                try {
                    CommonUtils.hideLoading();
                    subcatList = response.body().getData();

                    for (CategoryModel cat : subcatList) {
                        AddPriceItemBinding priceItemBinding = AddPriceItemBinding.inflate(getLayoutInflater());
                        priceItemBinding.tvName.setText(cat.getName());
                        priceItemBinding.etPrice.setTag(cat.getId());
                        binding.llContent.addView(priceItemBinding.getRoot());

                    }

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
}