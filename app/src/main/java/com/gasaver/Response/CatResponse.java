package com.gasaver.Response;

import com.gasaver.model.CategoryModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatResponse extends BaseResponse{

    @SerializedName("data")
    ArrayList<CategoryModel> data;

    public ArrayList<CategoryModel> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryModel> data) {
        this.data = data;
    }

    //Zaheer Edits After VINEELA
//    public class CategoryModel {
//
//        @SerializedName("id")
//        @Expose
//        private String id;
//        @SerializedName("name")
//        @Expose
//        private String name;
//        @SerializedName("attachment")
//        @Expose
//        private String attachment;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getAttachment() {
//            return attachment;
//        }
//
//        public void setAttachment(String attachment) {
//            this.attachment = attachment;
//        }
//    }

}
