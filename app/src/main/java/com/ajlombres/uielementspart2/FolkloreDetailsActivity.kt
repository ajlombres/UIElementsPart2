package com.ajlombres.uielementspart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.view.ContextMenu as ContextMenu1

class FolkloreDetailsActivity : AppCompatActivity() {
    private lateinit var array: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folklore_details)
        val listView = findViewById<ListView>(R.id.folkloreListView)

        this.array = arrayOf(
                "the 1", "cardigan", "the last great american dynasty",
                "exile", "my tears ricochet", "mirrorball", "seven", "august",
                "this is me trying", "illicit affairs", "invisible string", "mad woman", "epiphany",
                "betty", "peace", "hoax"
        )

        val adp = ArrayAdapter(this@FolkloreDetailsActivity, android.R.layout.simple_list_item_1, array)

        listView.adapter = adp

        listView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
            Log.i("Position","Position $position")
            val intent = Intent(this, QueueSongsActivity::class.java)
            intent.putExtra("queue", array[position])
            startActivity(intent)
        }
        registerForContextMenu(listView)

    }

    override fun onCreateContextMenu(menu: ContextMenu1?, v: View?, menuInfo: ContextMenu1.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu?.add(0, v!!.id, 0, "Add To Queue")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val name = array[listPosition]

        Toast.makeText(this@FolkloreDetailsActivity, "$name was added to queue", Toast.LENGTH_LONG).show()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_to_albums_page -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_queue_page -> {
                startActivity(Intent(this, QueueSongsActivity::class.java))
                true
            }
            R.id.go_to_songs_page -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}