package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Adapter class for managing data binders when the order of binder is in sequence
 *
 *
 * Created by yqritc on 2015/03/19, modified by rjsvieira.
 */

open class ListBindAdapter : DataBindAdapter() {

    private val binderList = ArrayList<DataBinder<RecyclerView.ViewHolder>>()

    override fun getItemCount(): Int {
        var itemCount = 0
        if (this.binderList != null && !this.binderList.isEmpty()) {
            var i = 0
            val size = binderList.size
            while (i < size) {
                val binder = binderList[i]
                itemCount += binder.itemCount
                i++
            }
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        var itemCount = 0
        if (this.binderList != null && !this.binderList.isEmpty()) {
            var viewType = 0
            val size = binderList.size
            while (viewType < size) {
                itemCount += binderList[viewType].itemCount
                if (position < itemCount) {
                    return viewType
                }
                viewType++
            }
        }
        return position
    }

    override fun getDataBinder(viewType: Int): DataBinder<RecyclerView.ViewHolder> = binderList[viewType]

    public override fun getPosition(binder: DataBinder<*>, binderPosition: Int): Int {
        if (this.binderList != null) {
            val viewType = binderList.indexOf(binder)
            var position = binderPosition
            for (i in 0 until viewType) {
                position += binderList[i].itemCount
            }
            return position
        }
        return binderPosition
    }

    public override fun getBinderPosition(position: Int): Int {
        var position = position
        if (this.binderList != null) {
            var binderItemCount: Int
            var i = 0
            val size = binderList.size
            while (i < size) {
                binderItemCount = binderList[i].itemCount
                if (position - binderItemCount < 0) {
                    break
                }
                position -= binderItemCount
                i++
            }
        }
        return position
    }

    override fun notifyBinderItemRangeChanged(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        notifyItemRangeChanged(getPosition(binder, positionStart), itemCount)
    }

    override fun notifyBinderItemRangeInserted(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(getPosition(binder, positionStart), itemCount)
    }

    override fun notifyBinderItemRangeRemoved(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        notifyItemRangeRemoved(getPosition(binder, positionStart), itemCount)
    }

    override fun clearDataBinderAdapter(): ListBindAdapter {
        if (this.binderList != null) {
            this.binderList.clear()
        }
        return this
    }

    fun getBinderList(): List<DataBinder<*>> {
        return binderList
    }

    fun removeRange(start: Int, end: Int) {
        val isAllowed = this.binderList != null && end >= start && this.binderList.size > end
        if (isAllowed) {
            for (i in end downTo start) {
                this.binderList.removeAt(i)
            }
        }
    }

    fun removeBinder(binder: DataBinder<RecyclerView.ViewHolder>?) {
        if (this.binderList != null && binder != null) {
            binderList.remove(binder)
        }
    }

    fun addBinder(binder: DataBinder<RecyclerView.ViewHolder>?) {
        if (this.binderList != null && binder != null) {
            binderList.add(binder)
        }
    }

    fun addAllBinder(dataSet: List<DataBinder<RecyclerView.ViewHolder>>?) {
        if (this.binderList != null && dataSet != null) {
            binderList.addAll(dataSet)
        }
    }

    fun addAllBinder(vararg dataSet: DataBinder<RecyclerView.ViewHolder>) {
        if (this.binderList != null && dataSet != null) {
            binderList.addAll(Arrays.asList(*dataSet))
        }
    }

}
