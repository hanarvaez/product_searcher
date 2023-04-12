package co.com.monkeymobile.product_searcher.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.com.monkeymobile.product_searcher.R
import co.com.monkeymobile.product_searcher.presentation.search.SearchActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        startActivity(SearchActivity.getIntent(this))
        finish()
    }
}
