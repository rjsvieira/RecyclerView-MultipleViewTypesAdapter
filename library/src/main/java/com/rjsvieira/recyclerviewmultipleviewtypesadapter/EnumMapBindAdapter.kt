package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import com.rjsvieira.recyclerviewmultipleviewtypesadapter.views.BaseViewHolder
import java.util.*

/**
 * Adapter class for managing data binders by mapping enum view type and data binder
 *
 *
 * Created by yqritc on 2015/03/25, modified by rjsvieira.
 */

abstract class EnumMapBindAdapter<E : Enum<E>> : DataBindAdapter() {

    private val binderMap : HashMap<E, DataBinder<BaseViewHolder>> = HashMap()

    override fun getItemCount(): Int {
        var itemCount = 0
        if (!this.binderMap.isEmpty()) {
            for (binder in binderMap.values) {
                itemCount += binder.itemCount
            }
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return getEnumFromPosition(position).ordinal
    }

    override fun getDataBinder(viewType: Int): DataBinder<BaseViewHolder> {
        return getDataBinder(getEnumFromOrdinal(viewType))
    }

    public override fun getPosition(binder: DataBinder<*>, binderPosition : Int): Int {
        var newBinderPosition = binderPosition
        val targetViewType = getEnumFromBinder(binder)
        var i = 0
        val count = itemCount
        while (i < count) {
            if (targetViewType != null && targetViewType == getEnumFromPosition(i)) {
                newBinderPosition--
                if (newBinderPosition < 0) {
                    return i
                }
            }
            i++
        }
        return itemCount
    }

    public override fun getBinderPosition(position: Int): Int {
        val targetViewType = getEnumFromPosition(position)
        var binderPosition = -1
        for (i in 0..position) {
            if (targetViewType === getEnumFromPosition(i)) {
                binderPosition++
            }
        }
        return binderPosition
    }

    override fun notifyBinderItemRangeChanged(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        for (i in positionStart..itemCount) {
            notifyItemChanged(getPosition(binder, i))
        }
    }

    override fun notifyBinderItemRangeInserted(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        for (i in positionStart..itemCount) {
            notifyItemInserted(getPosition(binder, i))
        }
    }

    override fun notifyBinderItemRangeRemoved(binder: DataBinder<*>, positionStart: Int, itemCount: Int) {
        for (i in positionStart..itemCount) {
            notifyItemRemoved(getPosition(binder, i))
        }
    }

    override fun clearDataBinderAdapter(): EnumMapBindAdapter<*> {
            this.binderMap.clear()
        return this
    }

    abstract fun getEnumFromPosition(position: Int): E

    abstract fun getEnumFromOrdinal(ordinal: Int): E

    fun getBinderMap(): Map<E, DataBinder<BaseViewHolder>> {
        return binderMap
    }

    protected fun putBinder(enumerator: E?, binder: DataBinder<BaseViewHolder>) {
        if (enumerator != null) {
            binderMap.put(enumerator, binder)
        }
    }

    protected fun getDataBinder(e: E): DataBinder<BaseViewHolder> {
        return binderMap[e]!!
    }

    private fun getEnumFromBinder(binder: DataBinder<*>): E? {
        if (!this.binderMap.isEmpty()) {
            for ((key, value) in binderMap) {
                if (value == binder) {
                    return key
                }
            }
        }
        return null
    }

}
