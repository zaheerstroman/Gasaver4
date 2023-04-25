package com.gasaver.viewmodal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gasaver.network.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuelDistanceEmployeeList extends ViewModel {

    private MutableLiveData<ResponseBody> loginlist;

    public LiveData<ResponseBody> getsample() {
        //if the list is null
        if (loginlist == null) {
            loginlist = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            setsample();
        }

        //finally we will return the list
        return loginlist;
    }

    public void setsample() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
                .baseUrl(ApiInterface.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();


//        Api api = retrofit.create(Api.class);
        ApiInterface api = retrofit.create(ApiInterface.class);



    }
}
