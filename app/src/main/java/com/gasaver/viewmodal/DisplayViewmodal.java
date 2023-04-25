package com.gasaver.viewmodal;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver;

public class DisplayViewmodal extends ViewModel {

    private MutableLiveData<StationDataJsonSchema2PojoResponseGasaver> successlist;
    @SuppressLint("StaticFieldLeak")
    Context context;

    public LiveData<StationDataJsonSchema2PojoResponseGasaver> getDisplayMenuDetails() {
        //if the list is null
        if (successlist == null) {
            successlist = new MutableLiveData<StationDataJsonSchema2PojoResponseGasaver>();
            //we will load it asynchronously from server in this method
//            HomePageMethod();
        }

        //finally we will return the list
        return successlist;
    }


}
