package my.project.rickandmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import my.project.rickandmorty.R

class MainActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    navController = findNavController(R.id.navHostFragment)

    setupActionBarWithNavController(navController!!)
}

override fun onSupportNavigateUp(): Boolean {
    return navController!!.navigateUp() || super.onSupportNavigateUp()
}
}