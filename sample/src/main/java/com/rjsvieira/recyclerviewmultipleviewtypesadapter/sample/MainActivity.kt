package com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.adapter.SampleEnumMapAdapter
import java.util.*


class MainActivity : AppCompatActivity() {

    private val sampleData: List<SampleData>
        get() {
            val dataSet = ArrayList<SampleData>()
            for (i in 1..4) {
                val data = SampleData()
                data.mTitle = getString(R.string.title_type2)
                data.mDrawableResId = resources.getIdentifier(getString(R.string.drawable_animal_name, i), "drawable", packageName)
                data.mContent = getString(R.string.content_type2, i)
                dataSet.add(data)
            }

            return dataSet
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<View>(R.id.recyclerview_main) as RecyclerView
        val adapter = SampleEnumMapAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sampleData = sampleData
        adapter.setSample2Data(sampleData)
    }
}
