package to.pta.ado.ui

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import to.pta.ado.R

class BottomDrawerUtils {
    companion object {
        fun startBottomNavigationDrawer(activity: AppCompatActivity) {
            val bottomSheet = BottomSheetBehavior.from(activity.findViewById(R.id.bottom_navigation_drawer))
            val expandButton = activity.findViewById<FloatingActionButton>(R.id.button_expand_drawer)
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

            activity.findViewById<Button>(R.id.navigate_questions).setOnClickListener {
                activity.startActivity(Intent(activity, ManageQuestionsActivity::class.java))
            }
            activity.findViewById<Button>(R.id.navigate_stats).setOnClickListener {
                activity.startActivity(Intent(activity, StatsActivity::class.java))
            }
            activity.findViewById<Button>(R.id.navigate_settings).setOnClickListener {
                activity.startActivity(Intent(activity, SettingsActivity::class.java))
            }
        }
    }
}