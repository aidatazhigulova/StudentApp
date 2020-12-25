package com.example.studentchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.studentchat.Adapter.TabSwitcher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {

    var firebaseAuth : FirebaseAuth ?= null
    var currentUser : FirebaseUser ? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(maim_toolbar as Toolbar?)
        supportActionBar?.title ="StudentApp"

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth!!.currentUser

        main_view_pager.adapter = TabSwitcher(supportFragmentManager)

        tab_switcher.setupWithViewPager(main_view_pager)

    }

    override fun onStart() {
        super.onStart()

        if (currentUser == null)
        {
            sendToLoginActivity()
        }
    }

    private fun sendToLoginActivity() {
        var intent = Intent(this@MainActivity, LoginActivity :: class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.option_find_friend)
        {

        }
        else if(item.itemId == R.id.option_settings)
        {
            sendToSettingsActivity()
        }
        else if(item.itemId == R.id.option_logout)
        {
            firebaseAuth!!.signOut()
            sendToLoginActivity()
        }


        return true
    }

    private fun sendToSettingsActivity() {
        var intent = Intent(this@MainActivity, SettingsActivity::class.java)
        startActivity(intent)
    }
}