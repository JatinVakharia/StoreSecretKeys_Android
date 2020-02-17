package com.practice.storesecretkeys

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
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
            val util = SecureUtil()
            /* Here data can be any secret key that we want to store at client side
             * and mykey should be the key through which we will encrypt secret key.
             * ToDo : Below mentioned line should not be present in production code. */
            util.generateSecretDataEncodedStringArray("d3605a1452202d42d175f5051389565ac450f4f18fad3763b28c147ac40079dc",
                "VtUfrB3leCNMHI6f17DjnLxGfFlaA6gk")

            // This function will bring you original secret key
            util.generateOriginalDataFromScatteredData(getString(R.string.random_text), getString(R.string.random_text_2))
        }
    }
}