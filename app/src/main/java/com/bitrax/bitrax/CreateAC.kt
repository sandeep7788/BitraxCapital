package com.bitrax.bitrax

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bitrax.bitrax.Services_API.Local_data
import com.bitrax.bitrax.Services_API.Services
import com.bitrax.bitrax.Variable.Var
import com.bitrax.bitrax.databinding.ActivityCreateACBinding
import com.bitrax.bitrax.databinding.ActivityMainBinding
import com.bitrax.bitrax.pojo.Login_pojo
import com.bitrax.bitrax.pojo.register_pojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAC : AppCompatActivity() {
    var TAG="@@CreetAC"
    var pref=Local_data(this@CreateAC)
    lateinit var mainBinding : ActivityCreateACBinding
    var mposition = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_a_c)

        val position_data = resources.getStringArray(R.array.Position)

        if (mainBinding.positionSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, position_data
            )
            mainBinding.positionSpinner.adapter = adapter

            mainBinding.positionSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                    Toast.makeText(
                        this@CreateAC," "+position_data[position], Toast.LENGTH_SHORT).show()
                    mposition=position_data[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        mainBinding.btnSignIn.setOnClickListener {
            if (mainBinding.username.text.toString().isEmpty())
            {
                mainBinding.username.setError("Please Enter Name")
            }else if (mainBinding.email.text.toString().isEmpty())
            {
                mainBinding.email.setError("Please Enter E-mail")
            }
            else if (mainBinding.mobileno.text.toString().isEmpty() && mainBinding.mobileno.text.length<9)
            {
                mainBinding.mobileno.setError("Please Enter Valid Number")
            }else if (mainBinding.password.text.toString().isEmpty() && mainBinding.mobileno.text.length<6)
            {
                mainBinding.password.setError("Please Enter min.. 7 char")
            }
            else if (mposition.equals("0"))
            {
                mainBinding.refereld.setError("Please Select Position")
            }
            else {
                creteAC()
            }
            }
    }

    fun creteAC()
    {
        var preferences= Services()
        val loginResponseCall: Call<register_pojo?>? = preferences!!.getCrete_new_AC()!!.setpost(
            mainBinding.username.text.toString(),mainBinding.email.text.toString(),mainBinding.mobileno.text.toString(),
            mainBinding.password.text.toString(),mainBinding.refereld.text.toString(),"R"
        )
        Log.e(TAG,mainBinding.username.text.toString()+" "+mainBinding.email.text.toString())
        loginResponseCall!!.enqueue(object: Callback<register_pojo?>
        {
            override fun onFailure(call: Call<register_pojo?>, t: Throwable) {

                Log.d(TAG, t.message.toString())
                Toast.makeText(this@CreateAC, "Check Your Connection Or Try Later", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<register_pojo?>, response: Response<register_pojo?>) {

                Log.d(TAG+" id", response.body()!!.status.toString()+"_")


                    Toast.makeText(this@CreateAC, ""+response.message(), Toast.LENGTH_LONG).show()
                    if(response.body()!!.status.equals("1"))
                    {
                        pref.setMyappContext(this@CreateAC)
                        pref.writeStringPreference(
                            Var.name,response.body()!!.memberData.member)
                        pref.writeStringPreference(
                            Var.user_code,response.body()!!.memberData.userCode)
                        pref.writeStringPreference(
                            Var.transaction_password,response.body()!!.memberData.transactionPassword)
                        pref.writeStringPreference(
                            Var.mobile,mainBinding.mobileno.text.toString())
                        Log.d(TAG+" id",response.body()!!.memberData.userCode)
                        startActivity(Intent(this@CreateAC,Dashbord::class.java))
                    }
                    else
                    {
                        Log.d(TAG+" error",response.body()!!.message)
                    }



                }


        })
    }

}