package to.pta.ado

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

const val TAG = "ManageActivity"

class ManageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fragmentManager = supportFragmentManager
        run {
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ValuesFragment()
            fragmentTransaction.add(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigate_manage_values -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = ValuesFragment()
                    fragmentTransaction.replace(R.id.fragment_container, fragment)
                    fragmentTransaction.commit()
                    true
                }
                R.id.navigate_manage_stats -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = StatsFragment()
                    fragmentTransaction.replace(R.id.fragment_container, fragment)
                    fragmentTransaction.commit()
                    true
                }
                R.id.navigate_manage_settings -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = SettingsFragment()
                    fragmentTransaction.replace(R.id.fragment_container, fragment)
                    fragmentTransaction.commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}