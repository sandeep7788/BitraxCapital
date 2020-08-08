package com.bitrax.bitrax

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bitrax.bitrax.Services_API.Local_data
import com.bitrax.bitrax.Services_API.Services
import com.bitrax.bitrax.Variable.Var
import com.bitrax.bitrax.databinding.ActivityMainBinding
import com.bitrax.bitrax.pojo.Login_pojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    var TAG="@@login"
    lateinit var mainBinding : ActivityMainBinding
    var pref=Local_data(this@Login)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.btnSignIn.setOnClickListener {
            if (mainBinding.edtUsername.text.toString().isEmpty())
            {
                mainBinding.edtUsername.setError("Please Enter Valid Details")
            }else if (mainBinding.edtPassword.text.toString().isEmpty())
            {
                mainBinding.edtPassword.setError("Enter Minimum 6 Char....")
            }
            else {
                login()
            }
        }
        ////
        startActivity(Intent(this@Login,Dashbord::class.java))
        mainBinding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this@Login,Dashbord::class.java))
        }
        mainBinding.createNewAccount.setOnClickListener {
            startActivity(Intent(this@Login,CreateAC::class.java))
        }
    }

    fun login()
    {
        var preferences= Services()
        val loginResponseCall: Call<Login_pojo?>? = preferences!!.getlogin()!!.savePost(mainBinding.edtUsername.text.toString(),mainBinding.edtPassword.text.toString())
        Log.e(TAG,mainBinding.edtUsername.text.toString()+" "+mainBinding.edtPassword.text.toString())
        loginResponseCall!!.enqueue(object: Callback<Login_pojo?>
        {
            override fun onFailure(call: Call<Login_pojo?>, t: Throwable) {

                Log.d(TAG, t.message.toString())
                Toast.makeText(this@Login, "Check Your Connection Or Try Later", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Login_pojo?>, response: Response<Login_pojo?>) {

                Log.d(TAG+" status", response.body()!!.status.toString())

                    Toast.makeText(this@Login, ""+response!!.body()!!.message, Toast.LENGTH_LONG).show()
                if(response.isSuccessful) {
                    if (response.body()!!.status.toInt() == 1) {


                        pref.setMyappContext(this@Login)
                        pref.writeStringPreference(
                            Var.name,response.body()!!.userDetail.name)
                        pref.writeStringPreference(
                            Var.user_code,response.body()!!.userDetail.username)
                        pref.writeStringPreference(
                            Var.email,response.body()!!.userDetail.email)
                        pref.writeStringPreference(
                            Var.mobile,response.body()!!.userDetail.mobile)

                        Log.d(TAG + " id", response.body()!!.userDetail.username)
                        startActivity(Intent(this@Login, Dashbord::class.java))
                    } else {
                        Log.d(TAG + " else", response.message())
                    }
                }
                else {Toast.makeText(this@Login, "Get bad response", Toast.LENGTH_LONG).show()}

                }


        })
    }
}