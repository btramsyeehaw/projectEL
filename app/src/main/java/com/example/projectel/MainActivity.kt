package com.example.projectel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    val sheetId = "1pYb2Di5YoF0IbpV0kq1D25J_Vv-JGPWu7_yE0kAxFS0"
    val apiKEY = "AIzaSyCfkvR4qvJbbL7ObotNmf68XA1Ghc4abnM"

    val players = ArrayList<PlayersModel>()

    var jsonArray = JSONArray()

    fun nameFormatter(number: String, name: String) : String {
        return "#$number $name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://sheets.googleapis.com/v4/spreadsheets/"+sheetId+"/values/BAT?key="+apiKEY
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                jsonArray = response.getJSONArray("values")
            } catch (e: Exception) {
                Log.i("JSONExc", e.toString())
            }

            for (i in 1 until jsonArray.length()) {
                try {
                    val json = jsonArray.getJSONArray(i)
                    val strName = json.getString(1)
                    val strNumber = json.getString(0)
                    val g = json.getString(2)
                    val pa = json.getString(3)
                    val rbi = json.getString(12)
                    val ops = json.getString(36)


                    val player = PlayersModel(nameFormatter(strNumber,strName),
                        "$g G", "$pa PA", "$rbi RBI", "$ops OPS",R.drawable.player)
                    players.add(player)
                } catch(e: Exception) {
                    Log.i("DBe", e.toString())
                }
            }
    },
            { error ->
                Log.i("DBError",error.toString())
                // TODO: Handle error
            }
        )
        queue.add(jsonObjectRequest)

    }


    fun playersIntent(view: View) {

//        val bundle = Bundle()
//        bundle.putParcelableArrayList("players",players)

        val intent = Intent(this, PlayersActivity::class.java)
//        intent.putExtra("bundle",bundle)
//        intent.putExtra("myWorth",0)
        intent.putParcelableArrayListExtra("players",players)
        startActivity(intent);
    }


}