package kutalekjk.earth

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart : Button = findViewById(R.id.btn_get_location)

        btnStart.setOnClickListener {
            // Move from this activity to the "Location" activity
            val intent = Intent(this, LocationActivity::class.java)

            startActivity(intent)
            finish()
        }
    }
}