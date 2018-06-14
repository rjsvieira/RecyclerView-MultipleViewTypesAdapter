package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.views.BaseViewHolder

/**
 * Adapter class for managing a set of data binders
 *
 *
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

abstract class DataBindAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return getDataBinder(viewType).newViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        getDataBinder(viewHolder.itemViewType).bindViewHolder(viewHolder, position)
    }

    abstract override fun getItemCount(): Int

    abstract override fun getItemViewType(position: Int): Int

    protected abstract fun getDataBinder(viewType: Int): DataBinder<BaseViewHolder>

    fun notifyBinderItemChanged(binder: DataBinder<*>, binderPosition: Int) {
        notifyItemChanged(binderPosition)
    }

    abstract fun notifyBinderItemRangeChanged(binder: DataBinder<*>, positionStart: Int, itemCount: Int)

    fun notifyBinderItemInserted(binder: DataBinder<*>, binderPosition: Int) {
        notifyItemInserted(binderPosition)
    }

    fun notifyBinderItemMoved(binder: DataBinder<*>, fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition, toPosition)
    }

    abstract fun notifyBinderItemRangeInserted(binder: DataBinder<*>, positionStart: Int, itemCount: Int)

    fun notifyBinderItemRemoved(binder: DataBinder<*>, binderPosition: Int) {
        notifyItemRemoved(binderPosition)
    }

    abstract fun notifyBinderItemRangeRemoved(binder: DataBinder<*>, positionStart: Int, itemCount: Int)

    abstract fun clearDataBinderAdapter(): DataBindAdapter
}
