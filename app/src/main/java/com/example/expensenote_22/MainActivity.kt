package com.example.expensenote_22

import android.os.Bundle















import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensenote_22.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //สร้างตัวแปรสำหรับUI
    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnadd: Button
    private lateinit var btnclear: Button
    private lateinit var tvLastItem : TextView
    private lateinit var tvTotal : TextView

    //ตัวแปรสำหรับเก็บค่ายอดรวมทั้งหมด
    private var total: Double = 0.0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.main.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnadd = findViewById<Button>(R.id.btnadd)
        btnclear = findViewById<Button>(R.id.btnclear)
        tvLastItem = findViewById<TextView>(R.id.tvLastItem)
        tvTotal = findViewById<TextView>(R.id.tvTotal)

        //กำหนด
        btnadd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            if(amountText.isEmpty()){
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                //แจ้งเตือนผู้ใช้ว่าให้กรอกตัวเลขมา ไม่ใช่ตัวหนังสือ
                Toast.makeText(this, "กรุณากรอกเฉพาะตัวเลข ในช่องจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //เอาขำนวนมารวมกัน
            total += amount

            //ตรวจสอบว่ามีการกรอกคำอธิบายมั้ย ถ้าไม่มี ให้เป็นคำว่า "ไม่ระบุ"
            val showNote = if (noteText.isNotEmpty()) noteText else "ไม่ระบุ"

            tvLastItem.text = "รายการล่าสุด: $showNote - $.2f บาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้: %.2f บาท".format(total)

            etAmount.text.clear()
            etNote.text.clear()
        }
        btnclear.setOnClickListener {
            total = 0.0

            etAmount.text.clear()
            etNote.text.clear()

            tvLastItem.text = "รายการล่าสุด: - บาท"
            tvTotal.text = "ยอดรวมวันนี้: 0.00 บาท"
        }


        // TODO: เขียนโค้ดเกี่ยวกับการบันทึกรายการของคุณตรงนี้
        binding.btnadd.setOnClickListener {
            // เขียนโค้ดบันทึกรายการที่นี่
        }

        // TODO: เขียนโค้ดเกี่ยวกับการล้างข้อมูลของคุณตรงนี้
        binding.btnclear.setOnClickListener {
            // เขียนโค้ดล้างข้อมูลที่นี่
        }
    }
}