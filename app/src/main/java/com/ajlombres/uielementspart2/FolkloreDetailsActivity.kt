package com.ajlombres.uielementspart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.ajlombres.uielementspart2.models.Album
import com.ajlombres.uielementspart2.models.AlbumSong
import android.view.ContextMenu as ContextMenu1

class FolkloreDetailsActivity : AppCompatActivity() {
    private lateinit var array: Array<String>
    lateinit var albumTitle: TextView
    lateinit var albumReleaseDate: TextView
    lateinit var albumSongsListView : ListView
    lateinit var songsDatabaseHandler: SongsDatabaseHandler
    lateinit var album : Album
    //used for Album Songs
    lateinit var albumSongs: MutableList<AlbumSong>
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
        val album_id = intent.getIntExtra("album_id", 0)

        albumTitle = findViewById(R.id.albumTitle_new)
        albumReleaseDate = findViewById(R.id.releaseDate)
        albumSongsListView = findViewById(R.id.albumSongsListView)
        songsDatabaseHandler = SongsDatabaseHandler(this)

        album = songsDatabaseHandler.readOneAlbum(album_id)

        albumSongs = songsDatabaseHandler.readAlbumSongs(album.albumTitle)
        val adp = ArrayAdapter(this@FolkloreDetailsActivity, android.R.layout.simple_list_item_1, array)

        listView.adapter = adp

        listView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
            Log.i("Position","Position $position")
            val intent = Intent(this, QueueSongsActivity::class.java)
            intent.putExtra("queue", array[position])
            startActivity(intent)
        }
        registerForContextMenu(listView)
        albumTitle.setText(album.albumTitle)
        albumReleaseDate.setText(album.releaseDate)
    }

    override fun onCreateContextMenu(menu: ContextMenu1?, v: View?, menuInfo: ContextMenu1.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu?.add(0, v!!.id, 0, "Add To Queue")

    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo //Allows us to inherit from the class Adapterview.AdapterCOntextMenuInfo to get the position
        return when(item.itemId) {
            R.id.deleteSongOnAlbum -> {
                val albumSong = albumSongs[menuInfo.position]
                if(songsDatabaseHandler.deleteAlbumSongs(albumSong)){
                    albumSongs.removeAt(menuInfo.position)
                    albumSongsArrayAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Song deleted.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
                }

                true
            }
            else -> return super.onContextItemSelected(item)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_song_album, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.addSongToAlbum -> {
                val intent = Intent(this, AddSongToAlbumActivity::class.java)
                val albumTitle = album.albumTitle
                intent.putExtra("albumTitle", albumTitle)
                startActivity(intent)
                true
            }

        }

        return super.onOptionsItemSelected(item)
    }


}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_menu, menu)
        return true
    }
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