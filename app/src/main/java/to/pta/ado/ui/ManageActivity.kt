package to.pta.ado.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import to.pta.ado.R

class ManageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)

        val fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ManageQuestionsFragment())
                .commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = when(item.itemId) {
                R.id.navigate_manage_values -> ManageQuestionsFragment()
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