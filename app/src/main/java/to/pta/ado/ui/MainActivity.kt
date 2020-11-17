package to.pta.ado.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import to.pta.ado.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationButton = findViewById<FloatingActionButton>(R.id.button_navigate_manage)
        navigationButton.setOnClickListener {
            val intent = Intent(this, ManageActivity::class.java)
            startActivity(intent)
        }
    }
}