package com.rjsvieira.recyclerviewmultipleviewtypesadapter

import java.util.HashMap

/**
 * Adapter class for managing data binders by mapping enum view type and data binder
 *
 *
 * Created by yqritc on 2015/03/25, modified by rjsvieira.
 */

abstract class EnumMapBindAdapter<E : Enum<E>> : DataBindAdapter() {

    private val binderMap = HashMap<E, DataBinder<*>>()

    override fun getItemCount(): Int {
        var itemCount = 0
        if (this.binderMap != null && !this.binderMap.isEmpty()) {
            for (binder in binderMap.values) {
                itemCount += binder.itemCount
            }
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return getEnumFromPosition(position).ordinal
    }

    override fun <T : DataBinder<*>> getDataBinder(viewType: Int): T? {
        return getDataBinder(getEnumFromOrdinal(viewType))
    }

    public override fun getPosition(binder: DataBinder<*>, binderPosition: Int): Int {
        var binderPosition = binderPosition
        val targetViewType = getEnumFromBinder(binder)
        var i = 0
        val count = itemCount
        while (i < count) {
            if (targetViewType != null && targetViewType === getEnumFromPosition(i)) {
                binderPosition--
                if (binderPosition < 0) {
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
        if (this.binderMap != null) {
            this.binderMap.clear()
        }
        return this
    }

    abstract fun getEnumFromPosition(position: Int): E

    abstract fun getEnumFromOrdinal(ordinal: Int): E

    fun getBinderMap(): Map<E, DataBinder<*>> {
        return binderMap
    }

    protected fun putBinder(enumerator: E?, binder: DataBinder<*>?) {
        if (this.binderMap != null && enumerator != null && binder != null) {
            binderMap.put(enumerator, binder)
        }
    }

    protected fun <T : DataBinder<*>> getDataBinder(e: E): T? {
        return if (this.binderMap != null) binderMap[e] as T else null
    }

    private fun getEnumFromBinder(binder: DataBinder<*>): E? {
        if (this.binderMap != null && !this.binderMap.isEmpty()) {
            for ((key, value) in binderMap) {
                if (value == binder) {
                    return key
                }
            }
        }
        return null
    }

}
