package com.example.projectel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal class PlayersActivity : AppCompatActivity() {
    var playersModel = ArrayList<PlayersModel>()
    var playerImage = R.drawable.player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPlayers)
        setUpPlayerModels()
        val adapter = PlayersRecyclerViewAdapter(this, playersModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpPlayerModels() {
        //tu nazwy z bazy danych??
        val playerName = getString(R.string.defName)
        val stat1 = getString(R.string.stats)
        val stat2 = getString(R.string.stats2)
        val stat3 = getString(R.string.stats3)
        for (i in 0..99) {
            playersModel.add(PlayersModel(playerName, stat1, stat2, stat3, playerImage))
        }
    }
}