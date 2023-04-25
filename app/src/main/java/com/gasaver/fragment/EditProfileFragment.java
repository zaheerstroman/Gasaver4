package com.gasaver.fragment;

import static com.gasaver.utils.Constants.REQUEST_GALLERY_PHOTO;
import static com.gasaver.utils.Constants.REQUEST_TAKE_PHOTO;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BaseResponseGasaverTProperty;
import com.gasaver.Response.LocationTableResponse;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.databinding.ActivityEditProfileFragmentBinding;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.FilePathUtils;
import com.gasaver.utils.SharedPrefs;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends AppCompatActivity {

    EditText et_email, et_first_name;
    ImageView iv_close;
    ShapeableImageView iv_profile;
    Button btn_save;

    ProfileUserDataResponseGasaverT.Data data;


    private Uri imageUri;
    private String selectedPath = " ";
    ActivityEditProfileFragmentBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileUserDataResponseGasaverT.Data.class);
        init();

        fetchCities();
        fetchCountry();
        fetchStates();

    }

    private void init() {
        iv_profile = findViewById(R.id.iv_profile);
        iv_close = findViewById(R.id.iv_close);
        btn_save = findViewById(R.id.btn_save);
        et_email = findViewById(R.id.et_email);
        et_first_name = findViewById(R.id.et_first_name);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsValidated())
                    updateProfileDetails();
            }
        });
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        setdata();

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cv_station.setVisibility(View.GONE);
            }
        });

    }

    private void setdata() {

        try {
            et_email.setText((CharSequence) data.getEmail());

            et_first_name.setText((CharSequence) data.getName());
            binding.etDob.setText((CharSequence) data.getDob());
            if (data.getGender().toString().toLowerCase().equals("male"))
                binding.rbMale.setChecked(true);
            if (data.getGender().toString().toLowerCase().equals("female"))
                binding.rbFemale.setChecked(true);

            Glide.with(EditProfileFragment.this).load(getIntent().getStringExtra("basepath") + data.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProfileDetails() {
        CommonUtils.showLoading(EditProfileFragment.this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);


        if (!selectedPath.trim().isEmpty()) {
            File file = new File(selectedPath);
            RequestBody fbody = RequestBody.create(MediaType.parse("image/*"),
                    file);
            builder.addFormDataPart("file", file.getName(), fbody);
        }
        String city = "";
        String state = "";
        String country = "";
        try {
            city=binding.spinnerCity.getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }  try {
            state=binding.spinnerState.getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }  try {
            country=binding.spinnerCountry.getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String gender = "";
        if (binding.rbMale.isChecked())
            gender = "Male";
        if (binding.rbFemale.isChecked())
            gender = "Female";
        builder.addFormDataPart("fcm_token", SharedPrefs.getInstance(this).getString(Constants.FCM_TOKEN))
                .addFormDataPart("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN))
                .addFormDataPart("name", et_first_name.getText().toString())
                .addFormDataPart("dob", binding.etDob.getText().toString())

                .addFormDataPart("gender", gender)
                .addFormDataPart("email", et_email.getText().toString())
                .addFormDataPart("city", city)
                .addFormDataPart("state", state)
                .addFormDataPart("country",country)
                .build();


        Call<BaseResponseGasaverTProperty> call = apiInterface.userDetailsUpdate(builder.build());
        call.enqueue(new Callback<BaseResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<BaseResponseGasaverTProperty> call, Response<BaseResponseGasaverTProperty> response) {
                CommonUtils.hideLoading();
                setResult(RESULT_OK);
                if (response.body() != null && response.body().getStatusCode()) {
                    if (response.body().getStatusCode()) {
                        Toast.makeText(EditProfileFragment.this, "Profile Details Updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditProfileFragment.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
                Toast.makeText(EditProfileFragment.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    private boolean isAllFieldsValidated() {

        if (TextUtils.isEmpty(et_first_name.getText().toString().trim())) {
            et_first_name.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(et_email.getText().toString().trim()) || !Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError("Not Valid");
            return false;
        }
        return true;
    }


    public void updateDetails(ProfileUserDataResponseGasaverT.Data dat) {
        this.data = dat;
        et_email.setText((CharSequence) data.getEmail());
        et_first_name.setText((CharSequence) data.getName());

        Glide.with(EditProfileFragment.this).load(getIntent().getStringExtra("basepath") + data.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);

    }


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileFragment.this);
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Take Photo")) {
                if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(EditProfileFragment.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditProfileFragment.this, new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "New Picture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                    imageUri = EditProfileFragment.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, REQUEST_TAKE_PHOTO);

                }
            } else if (items[item].equals("Choose from Library")) {
                if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(EditProfileFragment.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditProfileFragment.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_GALLERY_PHOTO);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), REQUEST_GALLERY_PHOTO);
                }
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    public interface DismissListener {
        public void onDismiss();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_PHOTO || requestCode == REQUEST_TAKE_PHOTO) {
            Log.e("selected profile img", requestCode + " " + resultCode);
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = null;
                    String path = " ";
                    if (requestCode == REQUEST_TAKE_PHOTO) {
                        selectedPath = FilePathUtils.getRealPath(EditProfileFragment.this, imageUri);

//                        selectedPath = imageUri.getPath();
                        bitmap = BitmapFactory.decodeFile(selectedPath);
                    } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                        selectedPath = FilePathUtils.getRealPath(EditProfileFragment.this, data.getData());
                        bitmap = BitmapFactory.decodeFile(selectedPath);
                    }
                    Log.e("selected profile img", selectedPath);
                    iv_profile.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_TAKE_PHOTO) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //launch camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                imageUri = EditProfileFragment.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            } else {
                Toast.makeText(EditProfileFragment.this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_GALLERY_PHOTO) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //launch gallery
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), REQUEST_GALLERY_PHOTO);
            } else {
                Toast.makeText(EditProfileFragment.this, "storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    ArrayList<LocationTableResponse.LocationTable> citys = new ArrayList<>();
    ArrayList<LocationTableResponse.LocationTable> states = new ArrayList<>();
    ArrayList<LocationTableResponse.LocationTable> countrys = new ArrayList<>();


    private void fetchCities() {
        CommonUtils.showLoading(EditProfileFragment.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileFragment.this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileFragment.this).getString(Constants.TOKEN));
        jsonObject.addProperty("table_name", "city");

        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
        call.enqueue(new Callback<LocationTableResponse>() {
            @Override
            public void onResponse(Call<LocationTableResponse> call, Response<LocationTableResponse> response) {
                LocationTableResponse locationTableResponse = response.body();
                citys = new ArrayList<>();
                if (locationTableResponse != null && locationTableResponse.getLocationTables() != null) {
                    citys.addAll(locationTableResponse.getLocationTables());
                }
                ArrayList<String> stateNames = new ArrayList<>();
                stateNames.add("Select");
                int selPos = 0;
                for (LocationTableResponse.LocationTable locationTable : citys) {
                    stateNames.add(locationTable.getName());
                    if (data.getCity()!=null&&data.getCity().equals(locationTable.getName()))
                        selPos = citys.indexOf(locationTable) + 1;
                }
                binding.spinnerCity.setAdapter(new ArrayAdapter<>(EditProfileFragment.this, R.layout.list_units, stateNames));
                binding.spinnerCity.setSelection(selPos);
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<LocationTableResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }


    private void fetchStates() {
        CommonUtils.showLoading(EditProfileFragment.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_name", "state");
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileFragment.this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileFragment.this).getString(Constants.TOKEN));

        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
        call.enqueue(new Callback<LocationTableResponse>() {
            @Override
            public void onResponse(Call<LocationTableResponse> call, Response<LocationTableResponse> response) {
                states = new ArrayList<>();
                LocationTableResponse locationTableResponse = response.body();
                if (locationTableResponse != null && locationTableResponse.getLocationTables() != null) {
                    states.addAll(locationTableResponse.getLocationTables());
                }
                ArrayList<String> stateNames = new ArrayList<>();
                stateNames.add("Select");
                int selPos = 0;

                for (LocationTableResponse.LocationTable locationTable : states) {
                    stateNames.add(locationTable.getName());
                    if (data.getState().equals(locationTable.getName()))
                        selPos = states.indexOf(locationTable) + 1;
                }

                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<LocationTableResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }


    private void fetchCountry() {
        CommonUtils.showLoading(EditProfileFragment.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileFragment.this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileFragment.this).getString(Constants.TOKEN));
        jsonObject.addProperty("table_name", "country");

        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
        call.enqueue(new Callback<LocationTableResponse>() {
            @Override
            public void onResponse(Call<LocationTableResponse> call, Response<LocationTableResponse> response) {
                countrys = new ArrayList<>();
                LocationTableResponse locationTableResponse = response.body();
                if (locationTableResponse != null && locationTableResponse.getLocationTables() != null) {
                    countrys.addAll(locationTableResponse.getLocationTables());
                }
                ArrayList<String> stateNames = new ArrayList<>();
                stateNames.add("Select");
                int selPos = 0;
                for (LocationTableResponse.LocationTable locationTable : countrys) {
                    stateNames.add(locationTable.getName());
                    if (data.getCountry()!=null&&data.getCountry().equals(locationTable.getName()))
                        selPos = countrys.indexOf(locationTable) + 1;
                }
                binding.spinnerCountry.
                        setAdapter(new ArrayAdapter<>(EditProfileFragment.this, R.layout.list_units, stateNames));
                binding.spinnerCountry.setSelection(selPos);
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<LocationTableResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }
}