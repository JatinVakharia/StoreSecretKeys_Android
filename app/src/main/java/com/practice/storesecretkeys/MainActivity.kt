package com.practice.storesecretkeys

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.practice.storesecretkeys.secure.SecureUtil
import com.practice.storesecretkeys.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            println("Original Data : ${BuildConfig.SECRET_KEY}")
            val util = SecureUtil()
//            val string = util.generateSecureDataFromAES(BuildConfig.SECRET_KEY)
//            println("string : $string")
//            util.getSecretFromAES(string)
//            println("Original Data : ${BuildConfig.SECRET_KEY}")

            util.generateSecretDataEncodedStringArray(">|>enJS09pL_TM<Q>}PkH9%>@@l5Tw")
            util.generateOriginalDataFromScateredData(getString(R.string.random_text), getString(R.string.random_text_2))
        }
    }
}