package com.example.surakshaedu.materials

import android.content.Context
import com.example.surakshaedu.core.model.Material
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MaterialStorage {

    private const val PREF_NAME = "materials_pref"
    private const val KEY_MATERIALS = "materials_list"

    fun saveMaterial(context: Context, material: Material) {
        val list = getMaterials(context).toMutableList()
        list.add(material)

        val json = Gson().toJson(list)
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_MATERIALS, json)
            .apply()
    }

    fun getMaterials(context: Context): List<Material> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_MATERIALS, null) ?: return emptyList()

        val type = object : TypeToken<List<Material>>() {}.type
        return Gson().fromJson(json, type)
    }
}
