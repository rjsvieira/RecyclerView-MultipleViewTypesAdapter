package com.rjsvieira.recyclerviewmultipleviewtypesadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter class for managing data binders when the order of binder is in sequence
 * <p>
 * Created by yqritc on 2015/03/19, modified by rjsvieira.
 */

public class ListBindAdapter extends DataBindAdapter {

    private List<DataBinder> binderList = new ArrayList<>();

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (this.binderList != null && !this.binderList.isEmpty()) {
            for (int i = 0, size = binderList.size(); i < size; i++) {
                DataBinder binder = binderList.get(i);
                itemCount += binder.getItemCount();
            }
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = 0;
        if (this.binderList != null && !this.binderList.isEmpty()) {
            for (int viewType = 0, size = binderList.size(); viewType < size; viewType++) {
                itemCount += binderList.get(viewType).getItemCount();
                if (position < itemCount) {
                    return viewType;
                }
            }
        }
        return position;
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return binderList != null ? (T) binderList.get(viewType) : null;
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        if (this.binderList != null) {
            int viewType = binderList.indexOf(binder);
            int position = binderPosition;
            for (int i = 0; i < viewType; i++) {
                position += binderList.get(i).getItemCount();
            }
            return position;
        }
        return binderPosition;
    }

    @Override
    public int getBinderPosition(int position) {
        if (this.binderList != null) {
            int binderItemCount;
            for (int i = 0, size = binderList.size(); i < size; i++) {
                binderItemCount = binderList.get(i).getItemCount();
                if (position - binderItemCount < 0) {
                    break;
                }
                position -= binderItemCount;
            }
        }
        return position;
    }

    @Override
    public void notifyBinderItemRangeChanged(DataBinder binder, int positionStart, int itemCount) {
        notifyItemRangeChanged(getPosition(binder, positionStart), itemCount);
    }

    @Override
    public void notifyBinderItemRangeInserted(DataBinder binder, int positionStart, int itemCount) {
        notifyItemRangeInserted(getPosition(binder, positionStart), itemCount);
    }

    @Override
    public void notifyBinderItemRangeRemoved(DataBinder binder, int positionStart, int itemCount) {
        notifyItemRangeRemoved(getPosition(binder, positionStart), itemCount);
    }

    @Override
    protected void clearDataBinderAdapter() {
        if (this.binderList != null) {
            this.binderList.clear();
        }
    }

    public List<DataBinder> getBinderList() {
        return binderList;
    }

    public void addBinder(DataBinder binder) {
        if (this.binderList != null && binder != null) {
            binderList.add(binder);
        }
    }

    public void addAllBinder(List<DataBinder> dataSet) {
        if (this.binderList != null && dataSet != null) {
            binderList.addAll(dataSet);
        }
    }

    public void addAllBinder(DataBinder... dataSet) {
        if (this.binderList != null && dataSet != null) {
            binderList.addAll(Arrays.asList(dataSet));
        }
    }

}
