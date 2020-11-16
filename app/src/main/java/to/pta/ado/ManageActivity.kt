package to.pta.ado

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ManageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ValuesFragment())
                .commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = when(item.itemId) {
                R.id.navigate_manage_values -> ValuesFragment()
                R.id.navigate_manage_stats -> StatsFragment()
                R.id.navigate_manage_settings -> SettingsFragment()
                else -> return@setOnNavigationItemSelectedListener false
            }

            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()

            true
        }
    }
}