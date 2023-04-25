package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StationDataJsonSchema2PojoResponseGasaver {

    @SerializedName("data")
    @Expose
//    private Data data;
    private List<Data> data;

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;
    @SerializedName("message")
    @Expose
    private String message;

//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("0")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._0 _0;
        @SerializedName("2")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._2 _2;
        @SerializedName("3")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._3 _3;
        @SerializedName("4")
        @Expose
        private _4__2 _4;
        @SerializedName("5")
        @Expose
        private _5__1 _5;
        @SerializedName("6")
        @Expose
        private _6__3 _6;

        public com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._0 get_0() {
            return _0;
        }

        public void set_0(com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._0 _0) {
            this._0 = _0;
        }

        public com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._2 get_2() {
            return _2;
        }

        public void set_2(com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._2 _2) {
            this._2 = _2;
        }

        public com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._3 get_3() {
            return _3;
        }

        public void set_3(com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._3 _3) {
            this._3 = _3;
        }

        public _4__2 get_4() {
            return _4;
        }

        public void set_4(_4__2 _4) {
            this._4 = _4;
        }

        public _5__1 get_5() {
            return _5;
        }

        public void set_5(_5__1 _5) {
            this._5 = _5;
        }

        public _6__3 get_6() {
            return _6;
        }

        public void set_6(_6__3 _6) {
            this._6 = _6;
        }
    }

    public class Price {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class Price__1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class Prices {

        @SerializedName("0")
        @Expose
        private _0__1 _0;
        @SerializedName("1")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._1 _1;
        @SerializedName("4")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._4 _4;
        @SerializedName("5")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._5 _5;
        @SerializedName("6")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._6 _6;

        public _0__1 get_0() {
            return _0;
        }

        public void set_0(_0__1 _0) {
            this._0 = _0;
        }

        public StationDataJsonSchema2PojoResponseGasaver._1 get_1() {
            return _1;
        }

        public void set_1(StationDataJsonSchema2PojoResponseGasaver._1 _1) {
            this._1 = _1;
        }

        public StationDataJsonSchema2PojoResponseGasaver._4 get_4() {
            return _4;
        }

        public void set_4(StationDataJsonSchema2PojoResponseGasaver._4 _4) {
            this._4 = _4;
        }

        public StationDataJsonSchema2PojoResponseGasaver._5 get_5() {
            return _5;
        }

        public void set_5(StationDataJsonSchema2PojoResponseGasaver._5 _5) {
            this._5 = _5;
        }

        public StationDataJsonSchema2PojoResponseGasaver._6 get_6() {
            return _6;
        }

        public void set_6(StationDataJsonSchema2PojoResponseGasaver._6 _6) {
            this._6 = _6;
        }
    }

    public class Prices__1 {

        @SerializedName("0")
        @Expose
        private _0__2 _0;
        @SerializedName("2")
        @Expose
        private _2__1 _2;
        @SerializedName("4")
        @Expose
        private _4__1 _4;
        @SerializedName("6")
        @Expose
        private _6__1 _6;
        @SerializedName("8")
        @Expose
        private com.gasaver.Response.StationDataJsonSchema2PojoResponseGasaver._8 _8;

        public _0__2 get_0() {
            return _0;
        }

        public void set_0(_0__2 _0) {
            this._0 = _0;
        }

        public _2__1 get_2() {
            return _2;
        }

        public void set_2(_2__1 _2) {
            this._2 = _2;
        }

        public _4__1 get_4() {
            return _4;
        }

        public void set_4(_4__1 _4) {
            this._4 = _4;
        }

        public _6__1 get_6() {
            return _6;
        }

        public void set_6(_6__1 _6) {
            this._6 = _6;
        }

        public StationDataJsonSchema2PojoResponseGasaver._8 get_8() {
            return _8;
        }

        public void set_8(StationDataJsonSchema2PojoResponseGasaver._8 _8) {
            this._8 = _8;
        }
    }


    public class Prices__2 {

        @SerializedName("0")
        @Expose
        private _0__3 _0;
        @SerializedName("2")
        @Expose
        private _2__2 _2;
        @SerializedName("4")
        @Expose
        private _4__3 _4;
        @SerializedName("6")
        @Expose
        private _6__2 _6;
        @SerializedName("8")
        @Expose
        private _8__1 _8;

        public _0__3 get_0() {
            return _0;
        }

        public void set_0(_0__3 _0) {
            this._0 = _0;
        }

        public _2__2 get_2() {
            return _2;
        }

        public void set_2(_2__2 _2) {
            this._2 = _2;
        }

        public _4__3 get_4() {
            return _4;
        }

        public void set_4(_4__3 _4) {
            this._4 = _4;
        }

        public _6__2 get_6() {
            return _6;
        }

        public void set_6(_6__2 _6) {
            this._6 = _6;
        }

        public _8__1 get_8() {
            return _8;
        }

        public void set_8(_8__1 _8) {
            this._8 = _8;
        }
    }

    public class Prices__3 {

        @SerializedName("0")
        @Expose
        private _0__4 _0;
        @SerializedName("2")
        @Expose
        private _2__3 _2;
        @SerializedName("4")
        @Expose
        private _4__4 _4;
        @SerializedName("6")
        @Expose
        private _6__4 _6;

        public _0__4 get_0() {
            return _0;
        }

        public void set_0(_0__4 _0) {
            this._0 = _0;
        }

        public _2__3 get_2() {
            return _2;
        }

        public void set_2(_2__3 _2) {
            this._2 = _2;
        }

        public _4__4 get_4() {
            return _4;
        }

        public void set_4(_4__4 _4) {
            this._4 = _4;
        }

        public _6__4 get_6() {
            return _6;
        }

        public void set_6(_6__4 _6) {
            this._6 = _6;
        }
    }


    public class _0 {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("mobile")
        @Expose
        private Object mobile;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Integer distance;
        @SerializedName("prices")
        @Expose
        private Prices prices;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Prices getPrices() {
            return prices;
        }

        public void setPrices(Prices prices) {
            this.prices = prices;
        }
    }

    public class _0__1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _0__2 {


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _0__3 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }


    public class _0__4 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }


    public class _1 {


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _2 {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("mobile")
        @Expose
        private Object mobile;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Double distance;

        @SerializedName("prices")
        @Expose
        private List<Price> prices;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public List<Price> getPrices() {
            return prices;
        }

        public void setPrices(List<Price> prices) {
            this.prices = prices;
        }
    }

    public class _2__1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _2__2 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _2__3 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _3 {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("mobile")
        @Expose
        private Object mobile;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Double distance;
        @SerializedName("prices")
        @Expose
        private Prices__1 prices;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Prices__1 getPrices() {
            return prices;
        }

        public void setPrices(Prices__1 prices) {
            this.prices = prices;
        }
    }

    public class _4 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _4__1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _4__2 {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("mobile")
        @Expose
        private Object mobile;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Double distance;
        @SerializedName("prices")
        @Expose
        private List<Price__1> prices;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public List<Price__1> getPrices() {
            return prices;
        }

        public void setPrices(List<Price__1> prices) {
            this.prices = prices;
        }
    }

    public class _4__3 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _4__4 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _5 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _5__1 {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("mobile")
        @Expose
        private Object mobile;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Double distance;
        @SerializedName("prices")
        @Expose
        private Prices__2 prices;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Prices__2 getPrices() {
            return prices;
        }

        public void setPrices(Prices__2 prices) {
            this.prices = prices;
        }
    }

    public class _6 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _6__1{
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }


    }

    public class _6__2 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _6__3 {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("mobile")
        @Expose
        private Object mobile;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Double distance;
        @SerializedName("prices")
        @Expose
        private Prices__3 prices;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Prices__3 getPrices() {
            return prices;
        }

        public void setPrices(Prices__3 prices) {
            this.prices = prices;
        }
    }

    public class _6__4 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _8 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public class _8__1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }


}

//}
