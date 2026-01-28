package com.example.surakshaedu.materials

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.core.model.Material
import com.example.surakshaedu.databinding.ActivityTeacherUploadMaterialBinding

class TeacherUploadMaterialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherUploadMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherUploadMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val desc = binding.etDescription.text.toString().trim()

            if (title.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val material = Material(title, desc)
            MaterialStorage.saveMaterial(this, material)

            Toast.makeText(this, "Material added successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
