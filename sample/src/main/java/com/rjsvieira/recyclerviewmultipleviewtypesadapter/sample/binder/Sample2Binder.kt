package com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.DataBinder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.R
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.SampleData
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.views.BaseViewHolder
import java.util.*

class Sample2Binder(var dataBindAdapter: DataBindAdapter) : DataBinder<BaseViewHolder>(dataBindAdapter) {

    private val mDataSet = ArrayList<SampleData>()

    private lateinit var rootView: View
    private lateinit var titleText: TextView
    private lateinit var imageView: ImageView
    private lateinit var content: TextView


    override fun newViewHolder(parent: ViewGroup): BaseViewHolder {
        this.rootView = LayoutInflater.from(parent.context).inflate(R.layout.layout_sample2, parent, false)
        this.titleText = rootView.findViewById<View>(R.id.title_type2) as TextView
        this.imageView = rootView.findViewById<View>(R.id.image_type2) as ImageView
        this.content = rootView.findViewById<View>(R.id.content_type2) as TextView
        return BaseViewHolder(this.rootView)
    }

    override fun bindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = mDataSet[position]
        this.titleText.text = data.mTitle
        this.content.text = data.mContent
        this.imageView.setImageResource(data.mDrawableResId)
    }

    fun addAll(dataSet: List<SampleData>) {
        mDataSet.addAll(dataSet)
        dataBindAdapter.notifyDataSetChanged()
    }

    fun clear() {
        mDataSet.clear()
        dataBindAdapter.notifyDataSetChanged()
    }

}
