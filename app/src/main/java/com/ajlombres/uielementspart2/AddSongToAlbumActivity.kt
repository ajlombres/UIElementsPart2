package com.ajlombres.uielementspart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ui_elements_p2.models.AlbumSong

class AddSongToAlbumActivity : AppCompatActivity() {
    lateinit var albumSongTitle: EditText
    lateinit var addAlbumSong: Button
    lateinit var albumSong: AlbumSong
    lateinit var albumTitleEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song_to_album)

        albumTitleEditText = findViewById(R.id.titleOftheAlbum)

        var albumTitle = intent.getStringExtra("albumTitle")
        albumSongTitle = findViewById(R.id.albumSongTitleEditText)
        addAlbumSong = findViewById(R.id.addSongToAlbumBtn)

        val databaseHandler = SongsDatabaseHandler(this)

        albumTitleEditText.setText(albumTitle)

        addAlbumSong.setOnClickListener{
            //get the field from the forms
            val title_string = albumSongTitle.text.toString()
            val album_title = albumTitleEditText.text.toString()
            //assign it to a book model
            val albumSong = AlbumSong(albumSong = title_string, albumTitle = album_title)
            //save it to the database
            if (databaseHandler.createAlbumSongs(albumSong)){
                Toast.makeText(this, "Song added to the Album.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Oooops!", Toast.LENGTH_SHORT).show()
            }
            albumSongsArrayAdapter.notifyDataSetChanged()
            clear()
        }

    }


    fun clear(){

        albumSongTitle.text.clear()
    }
}