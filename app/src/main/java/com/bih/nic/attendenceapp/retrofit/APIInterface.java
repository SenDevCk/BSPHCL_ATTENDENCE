package com.bih.nic.attendenceapp.retrofit;





import com.bih.nic.attendenceapp.dto.AttendenceRequestDto;
import com.bih.nic.attendenceapp.dto.LoginResponse;
import com.bih.nic.attendenceapp.dto.LoginUserDto;
import com.bih.nic.attendenceapp.dto.MyResponse;
import com.bih.nic.attendenceapp.entities.User;
import com.bih.nic.attendenceapp.entities.UserAttendence;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("/auth/login")
    Call<LoginResponse> doLogin(@Body LoginUserDto loginUserDto);

    @POST("/attendence/makeAttendence")
    Call<MyResponse> makeAttendence(@Body AttendenceRequestDto attaAttendenceRequestDto);

//    @POST("/ewallet/api/nic/makePayment?")
//    Call<String> makePayment(@Query("reqStr") String reqStr);
//
//    @POST("/ewallet/api/nic/verify?")
//    Call<String> makeVerify(@Query("reqStr") String reqStr);
//    @POST("/ewallet/api/nic/balanceEnq?")
//    Call<String> loadBalance(@Query("reqStr") String reqStr);
//
//     @POST("/secureV05/webapi/myresource/smartStatus")
//    Call<SmartMeterDetail> getSmartMeterBalance(@Body SmartMeterBalanceRequest smartMeterBalanceRequest);
//
//    @GET("/servicesNB/webapi/myresource/getConsDetailsForOnlinePayment?")
//    Call<SmartConsumerDetail> getRuralConsumerDetails(@Query("ConsumerId") String ConsumerId);


//
//    @GET("/app/dashboard/m/all/{locationType}/{location}")
//    Call<DashboardResponse> doGetDashboardAllData(@Path("locationType") String locationType, @Path("location") String location);
//
//    @GET("/app/vendor/{vendorId}")
//    Call<VendorDataResponse> doGetVender(@Path("vendorId") String vendorId);
//
//    @GET("/app/manufacturer/{manufacurerId}")
//    Call<ManufacturerPoso> doGetManufacture(@Path("manufacurerId") String manufacurerId);
//
//    @GET("/lmd-api/lmd/getMarketInspectionDetails/{monthSelected}/{yearSelected}/{userid}")
//    Call<MyResponse<List<MarketInspectionDetail>>> doGetMarketInspectionDetails(@Path("monthSelected")int monthSelected, @Path("yearSelected")int yearSelected, @Path("userid")String userid);
//
//    @POST("/lmd-api/lmd/saveMarketInspectionDetails")
//    Call<MyResponse<String>> saveMarketInspectionDetails(@Body List<MarketInspectionDetail> marketInspectionDetail);
//
//    @GET("/lmd-api/lmd/getMonthlyRevenueDetails/{monthSelected}/{yearSelected}/{userid}")
//    Call<MyResponse<RequestForRevenueData>> doGetRevenueReportDetails(@Path("monthSelected")int monthSelected, @Path("yearSelected")int yearSelected, @Path("userid")String userid);
//
//    @POST("/lmd-api/lmd/saveRevenueReportDetails")
//    Call<MyResponse<String>> saveRevenueReport(@Body RequestForRevenueData requestForRevenueData);
//
//    @GET("/lmd-api/lmd/getRenevalRegData/{monthSelected}/{yearSelected}/{userid}")
//    Call<MyResponse<RenevalAndRegistrationFee>> doGetRenRegData(@Path("monthSelected")int monthSelected, @Path("yearSelected")int yearSelected, @Path("userid")String userid);
//
//    @POST("/lmd-api/lmd/saveRenevalResitrationFee")
//    Call<MyResponse<String>> saveRenRegFee(@Body RenevalAndRegistrationFee requestForRevenueData);
//
//    @POST("/app/adalat/saveAdalatDetails")
//    Call<MyResponse<AdalatPayDetails>> uploadingAdalatDetails(@Body AdalatPayDetails payDetails);

    /* @GET("/api/manufacturer/11112")
    Call<ManufacturerPoso> doGetManufacture();*/

    //locationType,loginRole,loginLocation
    //@GET("/api/dashboard/m/all")
    //Call<DashboardResponse> doGetDashboardData();

     /*@POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
