package com.rjsvieira.recyclerviewmultipleviewtypesadapter;

/**
 * Adapter class for managing data binders by mapping an enumerator
 *
 * Created by yqritc on 2015/03/01, modified by rjsvieira.
 */

public abstract class EnumListBindAdapter<E extends Enum<E>> extends ListBindAdapter {

    protected <T extends DataBinder> T getDataBinder(E e) {
        return getDataBinder(e.ordinal());
    }

}
