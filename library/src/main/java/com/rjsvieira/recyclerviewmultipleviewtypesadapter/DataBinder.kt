package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import android.view.ViewGroup

/**
 * Class for binding view and data
 *
 *
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */


abstract class DataBinder<BaseViewHolder> protected constructor(private val dataBindAdapter: DataBindAdapter?) {

    var itemPosition: Int = 0
    var itemViewType = this.hashCode()

    abstract fun newViewHolder(parent: ViewGroup): BaseViewHolder

    abstract fun bindViewHolder(holder: BaseViewHolder, position: Int)

    fun setPosition(position: Int) {
        this.itemPosition = position
    }

    fun notifyBinderItemChanged() {
        dataBindAdapter?.clearRecycledViewPool()
        dataBindAdapter?.notifyItemChanged(itemPosition)
    }

}
