package com.example.surakshaedu.materials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surakshaedu.databinding.ActivityMaterialListBinding

class StudentMaterialListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMaterialListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMaterials.layoutManager = LinearLayoutManager(this)

        val materials = MaterialStorage.getMaterials(this)
        binding.recyclerMaterials.adapter = MaterialAdapter(materials)
    }
}
