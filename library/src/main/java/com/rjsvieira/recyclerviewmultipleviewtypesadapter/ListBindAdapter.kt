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
        return binderList.size
    }

    /**
     * Return the stable ID for the item at `position`. If [.hasStableIds]
     * would return false this method should return [.NO_ID]. The default implementation
     * of this method returns [.NO_ID].
     *
     * @param position Adapter position to query
     * @return the stable ID of the item at position
     */
    override fun getItemId(position: Int): Long {
        return (this.hashCode()+position).toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getDataBinder(viewType: Int): DataBinder<BaseViewHolder> = binderList[viewType]

    override fun notifyBinderItemRangeChanged(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        notifyItemRangeChanged(positionStart, itemCount)
    }

    override fun notifyBinderItemRangeInserted(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun notifyBinderItemRangeRemoved(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        notifyItemRangeRemoved(positionStart, itemCount)
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
            binder.setItemId(binderList.size - 1)
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
