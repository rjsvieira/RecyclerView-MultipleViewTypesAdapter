package com.rjsvieira.recyclerviewmultipleviewtypesadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Adapter class for managing a set of data binders
 * <p>
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

public abstract class DataBindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).newViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int binderPosition = getBinderPosition(position);
        getDataBinder(viewHolder.getItemViewType()).bindViewHolder(viewHolder, binderPosition);
    }

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position);

    protected abstract <T extends DataBinder> T getDataBinder(int viewType);

    protected abstract int getPosition(DataBinder binder, int binderPosition);

    protected abstract int getBinderPosition(int position);

    protected void notifyBinderItemChanged(DataBinder binder, int binderPosition) {
        notifyItemChanged(getPosition(binder, binderPosition));
    }

    protected abstract void notifyBinderItemRangeChanged(DataBinder binder, int positionStart, int itemCount);

    protected void notifyBinderItemInserted(DataBinder binder, int binderPosition) {
        notifyItemInserted(getPosition(binder, binderPosition));
    }

    protected void notifyBinderItemMoved(DataBinder binder, int fromPosition, int toPosition) {
        notifyItemMoved(getPosition(binder, fromPosition), getPosition(binder, toPosition));
    }

    protected abstract void notifyBinderItemRangeInserted(DataBinder binder, int positionStart, int itemCount);

    protected void notifyBinderItemRemoved(DataBinder binder, int binderPosition) {
        notifyItemRemoved(getPosition(binder, binderPosition));
    }

    protected abstract void notifyBinderItemRangeRemoved(DataBinder binder, int positionStart, int itemCount);

    public abstract DataBindAdapter clearDataBinderAdapter();
}
