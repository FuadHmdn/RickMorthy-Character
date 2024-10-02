package com.rickmorthy

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rickmorthy.databinding.ActivityDetailBinding
import com.rickmorthy.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity(){

    lateinit var binding: ActivityDetailBinding

    companion object {
        val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val characterData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Character>(EXTRA_DATA, Character::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Character>(EXTRA_DATA)
        }

        if (characterData != null) {

            binding.nameDetail.text = characterData.name
            binding.statusDetail.text = characterData.status
            binding.speciesDetail.text = characterData.species
            binding.genderDetail.text = characterData.gender
            binding.imageDetail.setImageResource(characterData.photo)
            binding.descriptionDetail.text = characterData.description

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.descriptionDetail.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        binding.actionShare.setOnClickListener {
            val text = characterData?.description
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(intent, "Bagikan dengan"))
        }

    }
}