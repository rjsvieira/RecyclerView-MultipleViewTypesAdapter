package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.views.BaseViewHolder
import java.util.ArrayList
import java.util.HashMap
import kotlin.collections.LinkedHashMap

/**
 * Adapter class for managing a set of data binders
 *
 *
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

open class DataBindAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var recyclerView: RecyclerView? = null
    private var binderList: ArrayList<DataBinder<BaseViewHolder>> = ArrayList()
    private var viewTypeMapping: HashMap<Int, Int>? = LinkedHashMap()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val position = viewTypeMapping?.get(viewType)!!
        val dataBinder = binderList[position]
        return dataBinder.newViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        val dataBinder = binderList[position]
        dataBinder.setPosition(position)
        dataBinder.bindViewHolder(viewHolder, position)
    }

    override fun getItemId(position: Int): Long = binderList[position].hashCode().toLong()

    override fun getItemCount(): Int = this.binderList.size

    override fun getItemViewType(position: Int): Int = this.binderList[position].itemViewType

    fun clearRecycledViewPool() {
        this.recyclerView?.recycledViewPool?.clear()
    }

    fun addBinder(binder: DataBinder<BaseViewHolder>?) {
        binder?.let {
            this.binderList.add(binder)
            val position = binderList.indexOf(binder)
            this.viewTypeMapping?.put(binderList[position].itemViewType, position)
            notifyItemInserted(position)
        }
    }

    fun addBinderAtPosition(position: Int, binder: DataBinder<BaseViewHolder>?) {
        binder?.let {
            if (position >= 0) {
                this.binderList.add(position, binder)
                this.viewTypeMapping?.put(binderList[position].itemViewType, position)
                notifyItemInserted(position)
            }
        }
    }

    fun removeBinder(binder: DataBinder<BaseViewHolder>?) {
        binder?.let {
            removeBinderAtPosition(binderList.indexOf(binder))
        }
    }

    fun removeBinderAtPosition(index: Int) {
        if (index >= 0) {
            this.binderList.removeAt(index)
            notifyItemRemoved(index)
            notifyItemRangeChanged(index, itemCount.minus(index).minus(1))
        }
    }

    fun removeBindersInRange(startIndex: Int, endIndex: Int = itemCount.minus(1)) {
        if (startIndex >= 0 && endIndex >= 0 && itemCount > startIndex && itemCount > endIndex) {
            this.binderList.subList(startIndex, itemCount).clear()
            notifyItemRangeRemoved(startIndex, endIndex)
            notifyItemRangeChanged(startIndex, endIndex)
        }
    }

    fun getBinderAtPosition(position: Int): DataBinder<BaseViewHolder>? = if (position < binderList.size) binderList[position] else null

    open fun clearDataBinderAdapter(): DataBindAdapter {
        this.viewTypeMapping?.clear()
        this.binderList.clear()
        return this
    }

}
