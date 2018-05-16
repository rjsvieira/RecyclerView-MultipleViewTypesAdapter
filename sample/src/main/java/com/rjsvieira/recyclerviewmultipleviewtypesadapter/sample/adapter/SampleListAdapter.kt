package com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.adapter

import com.rjsvieira.recyclerviewmultipleviewtypesadapter.DataBinder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.SampleData
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample1Binder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample2Binder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample3Binder

class SampleListAdapter : ListBindAdapter() {
    init {
        addAllBinder(Sample1Binder(this),
                Sample2Binder(this),
                Sample3Binder(this))
    }

    fun setSample2Data(dataSet: List<SampleData>) {
        (getDataBinder<DataBinder<*>>(1) as Sample2Binder).addAll(dataSet)
    }

}
