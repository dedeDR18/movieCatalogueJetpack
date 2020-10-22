package id.learn.android.proyekacademy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.learn.android.proyekacademy.R
import id.learn.android.proyekacademy.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}