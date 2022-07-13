package com.amia.latihanuas

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amia.latihanuas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var databaseHelper: DatabaseHelper? = null
    private var database: SQLiteDatabase? = null
    private val listMahasiswa: MutableList<MahasiswaEntity> = mutableListOf()
    private lateinit var appHelper: AppHelper
    private lateinit var mahasiswaAdapter: MahasiswaAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        database = databaseHelper?.writableDatabase

        mahasiswaAdapter = MahasiswaAdapter(
            context = this,
            mahasiswaEntityList = listMahasiswa,
            onItemDelete = {
                appHelper.deleteData(it)
                refreshData()
            }
        )
        binding.rvItem.adapter = mahasiswaAdapter
        binding.rvItem.layoutManager = LinearLayoutManager(this)

        database?.let {
            appHelper = AppHelper(it)
            refreshData()
        }

        binding.btnRegistrasi.setOnClickListener {
            if (binding.edNama.text.toString().isNotEmpty()
                && binding.edKelas.text.toString().isNotEmpty()
                && binding.edNrp.text.toString().isNotEmpty()
                && binding.edNilai.text.toString().isNotEmpty()){
                val mahasiswaEntity = MahasiswaEntity(
                    nama = binding.edNama.text.toString(),
                    kelas = binding.edKelas.text.toString(),
                    nrp = binding.edNrp.text.toString(),
                    nilai = binding.edNilai.text.toString().toInt()
                )
                val insert = appHelper.insertData(mahasiswaEntity)
                if (insert < 0) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Berhasil insert data", Toast.LENGTH_SHORT).show()
                    refreshData()
                }
            } else {
                Toast.makeText(this, "Harap masukan data", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshData() {
        listMahasiswa.clear()
        listMahasiswa.addAll(appHelper.lihatSeluruhData())
        mahasiswaAdapter.notifyDataSetChanged()
    }
}