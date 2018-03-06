package com.rjsvieira.recyclerviewmultipleviewtypesadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Class for binding view and data
 *
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

abstract public class DataBinder<T extends RecyclerView.ViewHolder> {

    private DataBindAdapter dataBindAdapter;

    public DataBinder(DataBindAdapter dataBindAdapter) {
        this.dataBindAdapter = dataBindAdapter;
    }

    abstract public T newViewHolder(ViewGroup parent);

    abstract public void bindViewHolder(T holder, int position);

    abstract public int getItemCount();

    public final void notifyDataSetChanged() {
        dataBindAdapter.notifyDataSetChanged();
    }

    public final void notifyBinderDataSetChanged() {
        notifyBinderItemRangeChanged(0, getItemCount());
    }

    public final void notifyBinderItemChanged(int position) {
        dataBindAdapter.notifyBinderItemChanged(this, position);
    }

    public final void notifyBinderItemRangeChanged(int positionStart, int itemCount) {
        dataBindAdapter.notifyBinderItemRangeChanged(this, positionStart, itemCount);
    }

    public final void notifyBinderItemInserted(int position) {
        dataBindAdapter.notifyBinderItemInserted(this, position);
    }

    public final void notifyBinderItemMoved(int fromPosition, int toPosition) {
        dataBindAdapter.notifyBinderItemMoved(this, fromPosition, toPosition);
    }

    public final void notifyBinderItemRangeInserted(int positionStart, int itemCount) {
        dataBindAdapter.notifyBinderItemRangeInserted(this, positionStart, itemCount);
    }

    public final void notifyBinderItemRemoved(int position) {
        dataBindAdapter.notifyBinderItemRemoved(this, position);
    }

    public final void notifyBinderItemRangeRemoved(int positionStart, int itemCount) {
        dataBindAdapter.notifyBinderItemRangeRemoved(this, positionStart, itemCount);
    }

}
