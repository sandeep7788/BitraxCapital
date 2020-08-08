package com.bitrax.bitrax

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.bitrax.bitrax.databinding.ActivityDashbordBinding
import com.google.android.material.tabs.TabLayout


class Dashbord : AppCompatActivity() {

    lateinit var mainBinding : ActivityDashbordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        mainBinding = DataBindingUtil.setContentView(this, com.bitrax.bitrax.R.layout.activity_dashbord)

        setupViewPager(mainBinding.viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {

        mainBinding.tablayout!!.addTab(mainBinding.tablayout!!.newTab().setText("Home"))
        mainBinding.tablayout!!.addTab(mainBinding.tablayout!!.newTab().setText("Wallet"))
        mainBinding.tablayout!!.addTab(mainBinding.tablayout!!.newTab().setText("Profile"))
        mainBinding.tablayout!!.addTab(mainBinding.tablayout!!.newTab().setText("Setting"))
        mainBinding.tablayout!!.getTabAt(0)!!.setIcon(com.bitrax.bitrax.R.drawable.ic_baseline_home_24)
        mainBinding.tablayout!!.getTabAt(1)!!.setIcon(com.bitrax.bitrax.R.drawable.wallet)
        mainBinding.tablayout!!.getTabAt(2)!!.setIcon(com.bitrax.bitrax.R.drawable.ic_baseline_account_circle_24)
        mainBinding.tablayout!!.getTabAt(3)!!.setIcon(com.bitrax.bitrax.R.drawable.ic_baseline_settings_24)

        mainBinding.tablayout!!.getTabAt(0)!!.getIcon()!!.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
        mainBinding.tablayout!!.getTabAt(1)!!.getIcon()!!.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
        mainBinding.tablayout!!.getTabAt(2)!!.getIcon()!!.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
        mainBinding.tablayout!!.getTabAt(2)!!.getIcon()!!.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);

//        mainBinding.tablayout!!.setupWithViewPager(viewPager)
        mainBinding.tablayout.getTabAt(0)!!.select()
        mainBinding.tablayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = Dashbord_Adapter(this, supportFragmentManager, mainBinding.tablayout!!.tabCount)
        mainBinding.viewpager!!.adapter = adapter

        mainBinding.viewpager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mainBinding.tablayout))

        mainBinding.tablayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mainBinding.viewpager!!.currentItem = tab.position
                mainBinding.tablayout!!.getTabAt(tab.position)!!.getIcon()!!.setColorFilter(getResources().getColor(R.color.Gold_color), PorterDuff.Mode.SRC_IN);


            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



    }

}