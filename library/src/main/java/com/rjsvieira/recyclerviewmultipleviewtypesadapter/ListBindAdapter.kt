package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import com.rjsvieira.recyclerviewmultipleviewtypesadapter.views.BaseViewHolder
import java.util.*

/**
 * Adapter class for managing data binders when the order of binder is in sequence
 *
 *
 * Created by yqritc on 2015/03/19, modified by rjsvieira.
 */

open class ListBindAdapter : DataBindAdapter() {

    private val binderList = ArrayList<DataBinder<BaseViewHolder>>()

    override fun getItemCount(): Int {
        var itemCount = 0
        if (!this.binderList.isEmpty()) {
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
        if (!this.binderList.isEmpty()) {
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

    override fun getDataBinder(viewType: Int): DataBinder<BaseViewHolder> = binderList[viewType]

    public override fun getPosition(binder: DataBinder<*>, binderPosition: Int): Int {
        val viewType = binderList.indexOf(binder)
        return binderPosition + (0 until viewType).sumBy { binderList[it].itemCount }
    }

    public override fun getBinderPosition(position: Int): Int {
        var newPosition = position
        var binderItemCount: Int
        var i = 0
        val size = binderList.size
        while (i < size) {
            binderItemCount = binderList[i].itemCount
            if (newPosition - binderItemCount < 0) {
                break
            }
            newPosition -= binderItemCount
            i++
        }
        return newPosition
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
        this.binderList.clear()
        return this
    }

    fun getBinderList(): List<DataBinder<BaseViewHolder>> {
        return binderList
    }

    fun removeRange(start: Int, end: Int) {
        val isAllowed = end >= start && this.binderList.size > end
        if (isAllowed) {
            for (i in end downTo start) {
                this.binderList.removeAt(i)
            }
        }
    }

    fun removeBinder(binder: DataBinder<BaseViewHolder>?) {
        if (binder != null) {
            binderList.remove(binder)
        }
    }

    fun addBinder(binder: DataBinder<BaseViewHolder>?) {
        if (binder != null) {
            binderList.add(binder)
        }
    }

    fun addAllBinder(dataSet: List<DataBinder<BaseViewHolder>>?) {
        if (dataSet != null) {
            binderList.addAll(dataSet)
        }
    }

    fun addAllBinder(vararg dataSet: DataBinder<BaseViewHolder>) {
        binderList.addAll(Arrays.asList(*dataSet))
    }

}
