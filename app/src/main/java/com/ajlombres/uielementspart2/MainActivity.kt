package com.ajlombres.uielementspart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.google.android.material.snackbar.Snackbar

open class MainActivity : AppCompatActivity() {
    private lateinit var array: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.songList)

        this.array = arrayOf(
                "the 1", "cardigan", "the last great american dynasty",
                "exile", "my tears ricochet", "mirrorball", "seven", "august",
                "this is me trying", "illicit affairs", "invisible string", "mad woman", "epiphany",
                "betty", "peace", "hoax", "I Forgot That You Existed", "Cruel Summer", "Lover",
                "The Man", "The Archer", "I Think He Knows", "Miss Americana and the Heartbreak Prince",
                "Paper Rings", "Cornelia Street", "Death By A Thousand Cuts", "London Boy",
                "Soon You'll Get Better", "False God", "You Need To Calm Down", "Afterglow", "ME!",
                "It's Nice To Have A Friend", "Daylight", "...Ready For It", "End Game",
                "I Did Something Bad", "Don't Blame Me", "Delicate", "Look What Made You Do",
                "Don't Blame Me", "Delicate", "Look What You Made Me Do", "So It Goes...", "Gorgeous",
                "Getaway Car", "King Of My Heart", "Dancing With My Hands Tied", "Dress",
                "This Is Why We Can't Have Nice Things", "Call It What You Want", "New Year's Day"
        )

        val adp = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, array)
        listView.adapter = adp
        listView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
        }
        registerForContextMenu(listView)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, addQueue: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, addQueue, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

       // menu?.add(0, addQueue!!.id, 0, "Add To Queue")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo //Allows us to inherit from the class Adapterview.AdapterCOntextMenuInfo to get the position
        return when (item.itemId) {
            R.id.add_to_queue -> {
                queuedSongs.add(array[menuInfo.position])
                val snackbar = Snackbar.make(findViewById(R.id.songList), "${array[menuInfo.position]} was added to the queue.", Snackbar.LENGTH_LONG)
                snackbar.setAction("Queue", View.OnClickListener { //Lambda function
                    val intent = Intent(this, QueueSongsActivity::class.java)
                    startActivity(intent)
                })
                snackbar.show()
                true
            }
            else -> {
                return super.onContextItemSelected(item)
            }

        }

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

