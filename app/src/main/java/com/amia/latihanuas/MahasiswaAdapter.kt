package com.amia.latihanuas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amia.latihanuas.databinding.ItemMahasiswaBinding

class MahasiswaAdapter(
    private val context: Context,
    private val mahasiswaEntityList: List<MahasiswaEntity> = listOf(),
    private val onItemDelete: (MahasiswaEntity) -> Unit,
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    private lateinit var binding: ItemMahasiswaBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaAdapter.ViewHolder {
        binding = ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            context,
            binding,
            onItemDelete
        )
    }

    override fun onBindViewHolder(holder: MahasiswaAdapter.ViewHolder, position: Int) {
        val mahasiswaEntity = mahasiswaEntityList[position]

        holder.bind(mahasiswaEntity)
    }

    override fun getItemCount(): Int {
        return mahasiswaEntityList.size
    }

    class ViewHolder(
        private val context: Context,
        private val binding: ItemMahasiswaBinding,
        private val onItemDelete: (MahasiswaEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mahasiswaEntity: MahasiswaEntity) {
            binding.apply {
                tvNama.text = "Nama : " + mahasiswaEntity.nama
                tvNrp.text = "NRP : " + mahasiswaEntity.nrp
                tvKelas.text = "Kelas : " + mahasiswaEntity.kelas
                tvNilai.text = "Nilai : " + mahasiswaEntity.nilai.toString()
            }
            binding.btnLihatData.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("nama", "Nama : " + mahasiswaEntity.nama)
                bundle.putString("nrp", "NRP : " + mahasiswaEntity.nrp)
                bundle.putString("kelas", "Kelas : " + mahasiswaEntity.kelas)
                bundle.putInt("nilai", mahasiswaEntity.nilai)

                val intent = Intent(context, ResultActivity::class.java)
                intent.putExtras(bundle)

                context.startActivity(intent)

            }
            binding.btnHapus.setOnClickListener {
                onItemDelete(mahasiswaEntity)
            }
        }
    }
}