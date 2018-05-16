package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Class for binding view and data
 *
 *
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

abstract class DataBinder<T : RecyclerView.ViewHolder> protected constructor(private val dataBindAdapter: DataBindAdapter?) {

    protected var itemPosition: Int = 0
    abstract val itemCount: Int

    abstract fun newViewHolder(parent: ViewGroup): T

    abstract fun bindViewHolder(holder: T, position: Int)

    protected fun notifyDataSetChanged() {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyDataSetChanged()
        }
    }

    protected fun notifyBinderDataSetChanged() {
        notifyBinderItemRangeChanged(0, itemCount)
    }

    protected fun notifyBinderItemChanged(position: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemChanged(this, position)
        }
    }

    protected fun notifyBinderItemRangeChanged(positionStart: Int, itemCount: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRangeChanged(this, positionStart, itemCount)
        }
    }

    protected fun notifyBinderItemInserted(position: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemInserted(this, position)
        }
    }

    protected fun notifyBinderItemMoved(fromPosition: Int, toPosition: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemMoved(this, fromPosition, toPosition)
        }
    }

    protected fun notifyBinderItemRangeInserted(positionStart: Int, itemCount: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRangeInserted(this, positionStart, itemCount)
        }
    }

    protected fun notifyBinderItemRemoved(position: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRemoved(this, position)
        }
    }

    protected fun notifyBinderItemRangeRemoved(positionStart: Int, itemCount: Int) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRangeRemoved(this, positionStart, itemCount)
        }
    }

}
