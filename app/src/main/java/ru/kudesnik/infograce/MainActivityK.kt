package ru.kudesnik.infograce


import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivityK : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, LayerFragment.newInstance())
//                .commitNow()
//        }

//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        // Поиск navigation view
        val navigationView = findViewById<View>(R.id.navigationView) as NavigationView
// Раздуть header view во время выполнения
// Раздуть header view во время выполнения
        val headerLayout: View = navigationView.inflateHeaderView(R.layout.header_navigation_drawer)
// Теперь, при необходимости, мы можем найти элементы внутри
// header'а

//        val ivHeaderPhoto: ImageView = headerLayout.findViewById(R.id.imageView)

        val cardView = headerLayout.findViewById<CardView?>(R.id.base_cardview)
        val arrow = headerLayout.findViewById<ImageButton?>(R.id.arrow_button)
        val hiddenView = headerLayout.findViewById<LinearLayout?>(R.id.hidden_view)
        arrow?.setOnClickListener {

            Toast.makeText(this@MainActivityK, "Toast", Toast.LENGTH_SHORT).show()
            if (hiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenView.visibility = View.GONE
                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            } else {
                TransitionManager.beginDelayedTransition(
                    cardView,
                    AutoTransition()
                )
                hiddenView.visibility = View.VISIBLE
                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
        }
        val tabLayout = headerLayout.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(
                    this@MainActivityK,
                    "Выбран ${tab?.position} таб ${tab?.id.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                when (tab?.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerDrawer, LayerFragment.newInstance())
                            .commitNow()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerDrawer, BackgroundFragment.newInstance())
                            .commitNow()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerDrawer, MissionFragment.newInstance())
                            .commitNow()
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }
}
