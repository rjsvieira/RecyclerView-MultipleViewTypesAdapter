package com.rjsvieira.recyclerviewmultipleviewtypesadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Class for binding view and data
 * <p>
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

public abstract class DataBinder<T extends RecyclerView.ViewHolder> {

    private DataBindAdapter dataBindAdapter;

    protected DataBinder(DataBindAdapter dataBindAdapter) {
        this.dataBindAdapter = dataBindAdapter;
    }

    protected abstract T newViewHolder(ViewGroup parent);

    protected abstract void bindViewHolder(T holder, int position);

    protected abstract int getItemCount();

    protected void notifyDataSetChanged() {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyDataSetChanged();
        }
    }

    protected void notifyBinderDataSetChanged() {
        notifyBinderItemRangeChanged(0, getItemCount());
    }

    protected void notifyBinderItemChanged(int position) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemChanged(this, position);
        }
    }

    protected void notifyBinderItemRangeChanged(int positionStart, int itemCount) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRangeChanged(this, positionStart, itemCount);
        }
    }

    protected void notifyBinderItemInserted(int position) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemInserted(this, position);
        }
    }

    protected void notifyBinderItemMoved(int fromPosition, int toPosition) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemMoved(this, fromPosition, toPosition);
        }
    }

    protected void notifyBinderItemRangeInserted(int positionStart, int itemCount) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRangeInserted(this, positionStart, itemCount);
        }
    }

    protected void notifyBinderItemRemoved(int position) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRemoved(this, position);
        }
    }

    protected void notifyBinderItemRangeRemoved(int positionStart, int itemCount) {
        if (this.dataBindAdapter != null) {
            this.dataBindAdapter.notifyBinderItemRangeRemoved(this, positionStart, itemCount);
        }
    }

}
