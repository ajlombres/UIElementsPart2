package com.ajlombres.uielementspart2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class AlbumsActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private var albumNames = arrayOf("folklore", "lover", "reputation" )
    private var albumImages = intArrayOf(R.drawable.folklore, R.drawable.lover, R.drawable.reputation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        title = "Albums"
        gridView = findViewById(R.id.gridView)
        val mainAdapter = MainAdapter(this@AlbumsActivity, albumNames, albumImages)
        gridView.adapter = mainAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            Log.i("Position", "Position $position")

            var myIntent: Intent? = null
            if (position === 0)
            {
                myIntent = Intent(view.context, FolkloreDetailsActivity::class.java)
            }
            if (position === 1)
            {
                myIntent = Intent(view.context, LoverAlbumDetails::class.java)
            }
            if (position === 2)
            {
                myIntent = Intent(view.context, ReputationDetailsActivity::class.java)
            }
            startActivity(myIntent)
        }

                }
        }