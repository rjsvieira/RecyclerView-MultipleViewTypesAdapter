package com.rjsvieira.recyclerviewmultipleviewtypesadapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter class for managing data binders by mapping enum view type and data binder
 * <p>
 * Created by yqritc on 2015/03/25, modified by rjsvieira.
 */

public abstract class EnumMapBindAdapter<E extends Enum<E>> extends DataBindAdapter {

    private Map<E, DataBinder> binderMap = new HashMap<>();

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (this.binderMap != null && !this.binderMap.isEmpty()) {
            for (DataBinder binder : binderMap.values()) {
                itemCount += binder.getItemCount();
            }
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return getEnumFromPosition(position).ordinal();
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return getDataBinder(getEnumFromOrdinal(viewType));
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        E targetViewType = getEnumFromBinder(binder);
        for (int i = 0, count = getItemCount(); i < count; i++) {
            if (targetViewType != null && targetViewType == getEnumFromPosition(i)) {
                binderPosition--;
                if (binderPosition < 0) {
                    return i;
                }
            }
        }
        return getItemCount();
    }

    @Override
    public int getBinderPosition(int position) {
        E targetViewType = getEnumFromPosition(position);
        int binderPosition = -1;
        for (int i = 0; i <= position; i++) {
            if (targetViewType == getEnumFromPosition(i)) {
                binderPosition++;
            }
        }
        return binderPosition;
    }

    @Override
    public void notifyBinderItemRangeChanged(DataBinder binder, int positionStart, int itemCount) {
        for (int i = positionStart; i <= itemCount; i++) {
            notifyItemChanged(getPosition(binder, i));
        }
    }

    @Override
    public void notifyBinderItemRangeInserted(DataBinder binder, int positionStart, int itemCount) {
        for (int i = positionStart; i <= itemCount; i++) {
            notifyItemInserted(getPosition(binder, i));
        }
    }

    @Override
    public void notifyBinderItemRangeRemoved(DataBinder binder, int positionStart, int itemCount) {
        for (int i = positionStart; i <= itemCount; i++) {
            notifyItemRemoved(getPosition(binder, i));
        }
    }

    @Override
    public EnumMapBindAdapter clearDataBinderAdapter() {
        if (this.binderMap != null) {
            this.binderMap.clear();
        }
        return this;
    }

    public abstract E getEnumFromPosition(int position);

    public abstract E getEnumFromOrdinal(int ordinal);

    public Map<E, DataBinder> getBinderMap() {
        return binderMap;
    }

    protected void putBinder(E enumerator, DataBinder binder) {
        if (this.binderMap != null && enumerator != null && binder != null) {
            binderMap.put(enumerator, binder);
        }
    }

    protected <T extends DataBinder> T getDataBinder(E e) {
        return this.binderMap != null ? (T) binderMap.get(e) : null;
    }

    private E getEnumFromBinder(DataBinder binder) {
        if (this.binderMap != null && !this.binderMap.isEmpty()) {
            for (Map.Entry<E, DataBinder> entry : binderMap.entrySet()) {
                if (entry.getValue().equals(binder)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

}
