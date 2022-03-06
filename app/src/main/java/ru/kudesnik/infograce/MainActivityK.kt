package ru.kudesnik.infograce


import android.content.res.Configuration
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import ru.kudesnik.infograce.databinding.ActivityMainBinding
import ru.kudesnik.infograce.databinding.FragmentLayerBinding


class MainActivityK : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
//    private var adapter: LayerFragmentAdapter? = null

    //Ширина экрана - 1080, шторка на 391dp закрывает полностью экран
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valuesString = listOf<String>(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )

//
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this@MainActivityK)
//        recyclerView.adapter = LayerFragmentAdapter(valuesString)


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
        val widthNav = navigationView.width
        val display: Display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val screenWidth: Int = point.x
        val screenHeight: Int = point.y

// Теперь получим необходимую информацию
        val width = Integer.toString(screenWidth)
        val height = Integer.toString(screenHeight)

        val info = "Ширина: $width; Высота: $height"

        Log.i("ScreenInfo", info)
        Log.i("ScreenInfo", getScreenSizeCategory())
        Log.i("ScreenInfo", widthNav.toString())
        getDeviceDensity()


//        val cardView = headerLayout.findViewById<CardView?>(R.id.base_cardview)
//        val arrow = headerLayout.findViewById<ImageButton?>(R.id.arrow_button)
//        val hiddenView = headerLayout.findViewById<LinearLayout?>(R.id.hidden_view)
//        arrow?.setOnClickListener {
//
//            Toast.makeText(this@MainActivityK, "ToastActivity", Toast.LENGTH_SHORT).show()
//            if (hiddenView.visibility == View.VISIBLE) {
//                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
//                hiddenView.visibility = View.GONE
//                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
//            } else {
//                TransitionManager.beginDelayedTransition(
//                    cardView,
//                    AutoTransition()
//                )
//                hiddenView.visibility = View.VISIBLE
//                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
//            }
//        }
        val tabLayout = headerLayout.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerDrawer, LayerFragment.newInstance())
                            .commitNow()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerDrawer, BackgroundFragment.newInstance())
                            .commitNowAllowingStateLoss()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerDrawer, MissionFragment.newInstance())
                            .commitNow()
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
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

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }


    private fun getScreenSizeCategory(): String {
        return when (resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> "XLarge screen"
            Configuration.SCREENLAYOUT_SIZE_LARGE -> "Large screen"
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> "Normal size screen"
            Configuration.SCREENLAYOUT_SIZE_SMALL -> "Small size screen"
            Configuration.SCREENLAYOUT_SIZE_UNDEFINED -> "Undefined screen size"
            else -> "Error"
        }
    }

    private fun getDeviceDensity() {
        val dpiDensity = resources.displayMetrics.densityDpi
        when (dpiDensity) {
            DisplayMetrics.DENSITY_LOW -> Log.i("Density", "DENSITY_LOW")
            DisplayMetrics.DENSITY_MEDIUM -> Log.i("Density", "DENSITY_MEDIUM")
            DisplayMetrics.DENSITY_HIGH -> Log.i("Density", "DENSITY_HIGH")
            DisplayMetrics.DENSITY_XHIGH -> Log.i("Density", "DENSITY_XHIGH")
            DisplayMetrics.DENSITY_XXHIGH -> Log.i("Density", "DENSITY_XXHIGH")
            DisplayMetrics.DENSITY_XXXHIGH -> Log.i("Density", "DENSITY_XXXHIGH")
        }
    }
}
