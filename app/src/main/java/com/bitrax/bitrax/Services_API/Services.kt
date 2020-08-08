package com.bitrax.bitrax.Services_API

import com.bitrax.bitrax.Variable.Var
import com.bitrax.bitrax.pojo.Login_pojo
import com.bitrax.bitrax.pojo.register_pojo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class Services
{

    var login_api:Login_api? = null
    fun getlogin():Login_api? {
        if (login_api == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Var.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            login_api = retrofit.create(Login_api::class.java)
        }
        return login_api
    }
    var createNewAC:CreateNewAC? = null
    fun getCrete_new_AC():CreateNewAC? {
        if (createNewAC == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Var.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            createNewAC = retrofit.create(CreateNewAC::class.java)
        }
        return createNewAC
    }

    interface demo {
        @GET("demo")
        fun
                getProducts(
            @Query("mobno") one: String?,
            @Query("password") two: String?,
            @Query("remember_token") key: String?
        ): Call<Login_pojo>//Call<UserLogin?>?
    }

    interface Login_api {
        @POST("loginAuth")
        @FormUrlEncoded
        fun savePost(
            @Field("username") title: String?,
            @Field("password") body: String?
        ): Call<Login_pojo?>?
    }
    interface CreateNewAC {
        @POST("userRegAuth")
        @FormUrlEncoded
        fun setpost(
            @Field("name") name: String?,
            @Field("email") email: String?,
            @Field("mobile") mobile: String?,
            @Field("password") password: String?,
            @Field("referral_id") referral_id: String?,
            @Field("member_position") member_position: String?
        ): Call<register_pojo?>?
    }

   /* //http://skoolstarr.com/sspanel/API/holiday/profileupdate?school_id=32&userid=42920&address=jaipur&file=/upload/profile/test.jpg
    interface updatedata {
        @Multipart
        @POST("profileupdate?")
        fun account(
            @Part("school_id") school_id: RequestBody?,@Part("userid") userid: RequestBody?,@Part("address") address: RequestBody?, @Part pro_image: MultipartBody.Part?
        ): Call<Profileupdateapi>
    }*/
}