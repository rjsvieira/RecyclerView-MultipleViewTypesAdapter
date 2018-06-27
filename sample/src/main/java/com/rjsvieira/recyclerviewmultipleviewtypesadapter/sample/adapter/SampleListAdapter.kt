package com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.adapter

import com.rjsvieira.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.SampleData
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample1Binder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample2Binder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample3Binder

class SampleListAdapter : DataBindAdapter() {

    init {
        addBinder(Sample1Binder(this))
        addBinder(Sample2Binder(this))
        addBinder(Sample3Binder(this))
    }

    fun setSample2Data(dataSet: List<SampleData>) {
        (getBinderAtPosition(1) as Sample2Binder).addAll(dataSet)
    }

}
