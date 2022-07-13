package com.amia.latihanuas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amia.latihanuas.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        binding.tvResultNama.text = bundle?.getString("nama")
        binding.tvResultNrp.text = bundle?.getString("nrp")
        binding.tvResultKelas.text = bundle?.getString("kelas")
        binding.tvResultNilai.text = "Nilai : " + bundle?.getInt("nilai").toString()
    }
}