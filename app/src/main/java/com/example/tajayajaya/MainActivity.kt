import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tajayajaya.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mendapatkan referensi ke tombol menggunakan findViewById()
        val btn1: Button = findViewById(R.id.btn_1)

        // Menambahkan fungsi onClickListener ke tombol btn_1
        btn1.setOnClickListener {
            // Intent untuk memulai SecondActivity
            val intent = Intent(this, com.example.tajayajaya.SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
