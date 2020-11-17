package to.pta.ado.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import to.pta.ado.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomDrawerUtils.startBottomNavigationDrawer(this)
    }
}