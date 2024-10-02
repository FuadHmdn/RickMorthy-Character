package com.rickmorthy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickmorthy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    private val list: MutableList<Character> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        list.addAll(getCharacterDataList())
        showRecycleList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getCharacterDataList(): Collection<Character> {
        val name = resources.getStringArray(R.array.data_name)
        val status = resources.getStringArray(R.array.data_status)
        val species = resources.getStringArray(R.array.data_species)
        val gender = resources.getStringArray(R.array.data_gender)
        val description = resources.getStringArray(R.array.data_description)
        val photo = resources.obtainTypedArray(R.array.data_photo)

        val listChara: MutableList<Character> = mutableListOf()

        for (i in name.indices) {
            val chara =
                Character(
                    name[i],
                    status[i],
                    species[i],
                    gender[i],
                    description[i],
                    photo.getResourceId(i, -1)
                )
            listChara.add(chara)
        }

        return listChara
    }

    private fun showRecycleList() {
        bind.rvCharacter.layoutManager = LinearLayoutManager(this)
        val listCharAdapter = ListCharacterAdapter(list)
        bind.rvCharacter.adapter = listCharAdapter

        listCharAdapter.setOnClickCallback(object : ListCharacterAdapter.OnItemClickCallback {
            override fun onClickedItem(data: Character) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }

        })
    }
}