package com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.DataBinder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.R
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.views.BaseViewHolder

/**
 * Created by yqritc on 2015/03/20.
 */

class Sample3Binder(dataBindAdapter: DataBindAdapter) : DataBinder<BaseViewHolder>(dataBindAdapter) {

    private lateinit var rootView: View
    private lateinit var titleText: TextView
    private lateinit var imageView: ImageView
    private lateinit var content: TextView

    override fun newViewHolder(parent: ViewGroup): BaseViewHolder {
        this.rootView = LayoutInflater.from(parent.context).inflate(R.layout.layout_sample3, parent, false)
        this.titleText = rootView.findViewById<View>(R.id.title_type3) as TextView
        this.imageView = rootView.findViewById<View>(R.id.image_type3) as ImageView
        this.content = rootView.findViewById<View>(R.id.content_type3) as TextView
        return BaseViewHolder(this.rootView)
    }

    override fun bindViewHolder(holder: BaseViewHolder, position: Int) {
        this.imageView.setImageResource(R.drawable.bird)
    }


}
