package com.example.koneksidatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import java.net.CacheResponse
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var arrayList = ArrayList<Fakultas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Data Fakultas"
        recycle_view.setHasFixedSize(true)
        recycle_view.layoutManager = LinearLayoutManager(this)
        
        mFloatingActionButton.setOnClickListener{
            startActivity(Intent(this, ManagerFakultasActivity::class.java))
        }
        loadAllFakultas()
    }

    override fun onResume() {
        super.onResume()
        loadAllFakultas()
    }
    private fun loadAllFakultas(){
        val loading = ProgressDialog(this)
        loading.setManager("memuat data...")
        loading show()
        
        AndroidNetworking.get(ApiEndPoint.READ)
            .setPriority(priority.MEDIUM)
            .build()
            .getAsJSONobject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()

                    var jsonArray = response?.optJSONArray("result")
                    if (jsonArray?.length()==0){
                        loading.dismiss()
                        Toast.makeText(
                            applicationContext, "fakultas data is empty, add the data first",
                            Toast.LENGTH_SHORT
                        ). show()
                    }
                    for (i in 0 until jsonArray?.length()!!){
                        val jsonObject = jsonArray?.optJSONObject(i)
                        arrayList.add (
                            jsonObject.getString("kode_fakultas"),
                            jsonObject.getString("nama_fakultas")
                        )
                        if (jsonArray?.length()-1 == i){
                            loading.dismiss()
                            val adapter = RVAAdapterFakultas(applicationContext,arrayList)
                            adapter.notifyDataSetChanged()
                            recycle_view.adapter = adapter
                        }

                    }
                }
                override fun onError(anError: ANError?){
                    loading.dismiss()
                    log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(aplicationContext, "Connection Failure", Toast.LENGHT_SHORT).show()
                }

            })
    }
}
