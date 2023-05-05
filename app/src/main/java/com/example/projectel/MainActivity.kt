package com.example.projectel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val sheetId = "1pYb2Di5YoF0IbpV0kq1D25J_Vv-JGPWu7_yE0kAxFS0"
    val apiKEY = "AIzaSyCfkvR4qvJbbL7ObotNmf68XA1Ghc4abnM"
    val listName = ArrayList<String>()
    val listNumber = ArrayList<String>()

    var jsonArray = JSONArray()

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

                    listName.add(strName)
                    listNumber.add(strNumber)
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

    fun dataTest(view: View) {
        Toast.makeText(this, "Data size: "+listName.size, Toast.LENGTH_LONG).show()
        Toast.makeText(this, listName[13], Toast.LENGTH_LONG).show()
    }


}