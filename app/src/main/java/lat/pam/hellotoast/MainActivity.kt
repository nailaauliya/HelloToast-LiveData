package lat.pam.hellotoast

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    // ViewModel untuk menyimpan nilai Count
    private val model: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Menghubungkan komponen dengan layout
        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)

        // Observer LiveData sesuai modul
        val nameObserver = Observer<Int> { newName ->
            mShowCount.text = newName.toString()
        }

        // Mengamati perubahan nilai LiveData
        model.currentName.observe(this, nameObserver)

        // Tombol Count menggunakan LiveData
        buttonCountUp.setOnClickListener {
            val mCount = (model.currentName.value ?: 0) + 1
            model.currentName.value = mCount
        }

        // Tombol Toast
        buttonToast.setOnClickListener {
            val tulisan = mShowCount.text.toString()

            Toast.makeText(
                this,
                "Angka yang dimunculkan $tulisan",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Tombol Switch Page
        buttonSwitchPage.setOnClickListener {
            val intent = Intent(
                this,
                MainActivity2::class.java
            ).apply {
                putExtra("key_nama", "Aldy Rialdy")
                putExtra("key_umur", 25)
            }

            startActivity(intent)
        }

        // Tombol Camera
        buttonBrowser.setOnClickListener {
            val cameraIntent = Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
            )
            startActivity(cameraIntent)
        }

        // Pengaturan tampilan Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }
}
