package com.gasaver.network;

import com.gasaver.Response.BannersResponse;
import com.gasaver.Response.BaseResponse;
import com.gasaver.Response.BaseResponseGasaverTProperty;
import com.gasaver.Response.CatResponse;
import com.gasaver.Response.CopunsResponse;
import com.gasaver.Response.GetRewardDataResponse;
import com.gasaver.Response.GraphReportsResponse;
import com.gasaver.Response.LocationTableResponse;
import com.gasaver.Response.OtpResponseGasaverTProperty;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.Response.StationDataResponse;
import com.gasaver.Response.UploadDataResponse;
import com.gasaver.Response.UserRegResponseGasaverTProperty;
import com.gasaver.Response.WishlistResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    String BASE_URL = "https://houseofspiritshyd.in/gasaver/admin/api/";

//    ----------------------- SIGNING LOGIN ---------------------------------------------


//    UserSignInActivity:--------------------
//    @POST("login_controller/index")
    @POST("sendOtp")
//    Call<UserRegResponseGasaver> userLogin(@Body RequestBody postObj);
    Call<UserRegResponseGasaverTProperty> userLogin(@Body RequestBody postObj);

    //    OtpActivity:---------------------------
    @POST("verifyOtp")
//    Call<OtpResponseGasaver> verifyOtp(@Body RequestBody postObj);
    Call<OtpResponseGasaverTProperty> verifyOtp(@Body RequestBody postObj);


// for otp verification
//    @POST("login_controller/index")
    @POST("sendOtp")
    Call<BaseResponseGasaverTProperty> resendOtp(@Body RequestBody postObj);

    //----------------------------------------------------------------------------------------------------


    //    ----------------------- PROFILE & EDIT PROFILE ---------------------------------------------


    //    PROFILE:-------------------------- PROFILE FRAGMENT & EDIT PROFILE FRAGMENT(Use)
    @POST("getUserData")
//    Call<ProfileUserDataResponseGasaverT> fetchProfileDetails(@Body RequestBody postObj);
    Call<ProfileUserDataResponseGasaverT> fetchProfileDetails(@Body JsonObject postObj);

    //    Profile:-------------------------- PROFILE MAIN ACTIVITY & USER PROFILE ACTIVITY(Not Use)
    @POST("getUserData")
//    Call<ProfileUserDataResponseGasaverT> fetchProfileDetails(@Body RequestBody postObj);
    Call<ProfileUserDataResponseGasaverT> fetchProfileDetails(@Body RequestBody postObj);


    //    EDIT PROFILE:--------------------------- EDIT PROFILE FRAGMENT() ------
    @POST("userDetailsUpdate")
    Call<BaseResponseGasaverTProperty> userDetailsUpdate(@Body RequestBody postObj);

    // EditProfileFragment + fetchCities + fetchStates + fetchCountry
    @POST("getDefaultData")
    Call<LocationTableResponse> getDefaultDataLoc(@Body JsonObject postObj);
    //----------------------------------------------------------------------------------------------------


    //    FEEDBACK ACTIVITY:-
    @POST("userDetailsUpdate")
    Call<BaseResponseGasaverTProperty> updateProfile(@Body JsonObject postObj);

    @POST("getUserData")
    Call<BaseResponseGasaverTProperty> updateProfile(@Body MultipartBody postObj);

    //----------------------------------------------------------------------------------------------------


//    HOME FRAGMENT GASAVER + MAIN PICKMAN ACTIVITY
//    HomeFragmentGasaver + MainPickmanActivity:-----------------------------

    //    Category/SubCategory(Spinner(FUEL+E10))
    @POST("getDefaultData")
    Call<CatResponse> getDefaultData(@Body JsonObject postObj);

//    // EditProfileFragment + fetchCities + fetchStates + fetchCountry
//    @POST("getDefaultData")
//    Call<LocationTableResponse> getDefaultDataLoc(@Body JsonObject postObj);


    //    HomeFragmentGasaver:-------------------------
    @POST("getStationsData_v1")
//    @GET("getStationsData")
//    Call<ResponseBody> getemployeelistapi();
    Call<StationDataResponse> getStationsData(@Body JsonObject jsonObject);
//    Call<StationDataResponse.StationDataModel> getStationsData(@Body JsonObject jsonObject);


    //Marker Purpose Change:-------
//    Call<StationDataResponse.StationDataModel> getStationsData(@Body JsonObject jsonObject);

//    Call<ResponseBody> getFuelDistanceemployeelistapi(@Body RequestBody postObj);


    //    MainPickmanActivity:------------------------
    @POST("updatepriceing")
    Call<BaseResponse> updatepriceing(@Body JsonObject postObj);


    //    UploadActivity:---------
    @POST("getReportsData")
    Call<UploadDataResponse> getReportsData(@Body JsonObject jsonObject);

    //--------------------------------------------------------------------------------------

    //    HomeFragmentGasaver:----WISHLIST(LOVE&LIKE)
    @POST("saveWishlist")
    Call<StationDataResponse> saveWishlist(@Body JsonObject jsonObject);
//    Call<WishlistResponse> saveWishlist(@Body JsonObject jsonObject);

    //    WishListActivity:------ WISHLIST(LOVE YES) Recyclerview, (LOVE NO) Remove from Recyclerview
    @POST("getWishlist")
    Call<StationDataResponse> getWishlist(@Body JsonObject jsonObject);
//    Call<WishlistResponse> getWishlist(@Body JsonObject jsonObject);

    //    removeWishList
    @POST("getWishlist")
//    Call<StationDataResponse> removeWishList(@Body JsonObject jsonObject);

    Call<StationDataResponse> removeWishList(@Body RequestBody jsonObject);
//    Call<WishlistResponse> removeWishList(@Body RequestBody jsonObject);


    //-------------------------------------


    //Admin Accept i.e., Rewards will come
    @POST("getRewards")
    Call<GetRewardDataResponse> getRewardsData(@Body JsonObject jsonObject);

    @POST("userSettings")
    Call<BaseResponse> settUserSettings(@Body JsonObject jsonObject);

    @POST("feedback")
    Call<BaseResponse> feedback(@Body JsonObject jsonObject);


////    getRemoveNull
//    @POST("getStationsData_v1")
//    Call<BaseResponse> getRemoveNull(@Body RequestBody postObj);

    //    adds
    @POST("adds")
//    Call<BannersResponse> fetchBanners(@Body JsonObject postObj);
//    Call<BannersResponse.AddsDetail> fetchBanners(@Body JsonObject postObj);
    Call<BannersResponse> fetchBanners(@Body JsonObject postObj);

    //--------------------------

//    Call<BannersResponse> fetchBanners1(@Body JsonObject postObj);

    @POST("adds")
    Call<BannersResponse> fetchBanners1(@Body RequestBody postObj);
//    Call<BannersResponse.CompanyDetail> fetchBanners1(@Body RequestBody postObj);

    //------------------------------------------------------------------------------------

    //    GraphReportsResponse:--
    @POST("getGraphReports")

    Call<GraphReportsResponse> fetchGraphReports(@Body JsonObject postObj);

//----------------------------------------------------------------------------------------------------------

    @POST("copuns")
    Call<CopunsResponse> getCopunsData(@Body JsonObject jsonObject);



}
