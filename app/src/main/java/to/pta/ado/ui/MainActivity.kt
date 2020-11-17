package to.pta.ado.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import to.pta.ado.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheet = BottomSheetBehavior.from(findViewById(R.id.bottom_navigation_drawer))
        val expandButton = findViewById<FloatingActionButton>(R.id.button_expand_drawer)
        expandButton.setOnClickListener {
            when (bottomSheet.state) {
                BottomSheetBehavior.STATE_COLLAPSED -> bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                else -> bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        bottomSheet.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                expandButton.rotation = slideOffset * 180f
            }
        })
        expandButton.post { bottomSheet.peekHeight = expandButton.measuredHeight }

        findViewById<Button>(R.id.navigate_questions).setOnClickListener {
            startActivity(Intent(this, ManageQuestionsActivity::class.java))
        }
        findViewById<Button>(R.id.navigate_stats).setOnClickListener {
            startActivity(Intent(this, StatsActivity::class.java))
        }
        findViewById<Button>(R.id.navigate_settings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}