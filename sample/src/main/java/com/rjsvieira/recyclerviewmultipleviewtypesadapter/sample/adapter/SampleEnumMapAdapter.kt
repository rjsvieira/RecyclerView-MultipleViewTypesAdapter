package com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.adapter

import com.rjsvieira.recyclerviewmultipleviewtypesadapter.EnumMapBindAdapter
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.SampleData
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample1Binder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample2Binder
import com.rjsvieira.recyclerviewmultipleviewtypesadapter.sample.binder.Sample3Binder

class SampleEnumMapAdapter : EnumMapBindAdapter<SampleEnumMapAdapter.SampleViewType>() {

    enum class SampleViewType {
        SAMPLE1, SAMPLE2, SAMPLE3
    }

    init {
        putBinder(SampleViewType.SAMPLE1, Sample1Binder(this))
        putBinder(SampleViewType.SAMPLE2, Sample2Binder(this))
        putBinder(SampleViewType.SAMPLE3, Sample3Binder(this))
    }

    fun setSample2Data(dataSet: List<SampleData>) {
        (getDataBinder(SampleViewType.SAMPLE2) as Sample2Binder).addAll(dataSet)
    }

    override fun getEnumFromPosition(position: Int): SampleViewType {
        return if (position == 1) {
            SampleViewType.SAMPLE1
        } else if (position == 3) {
            SampleViewType.SAMPLE3
        } else {
            SampleViewType.SAMPLE2
        }
    }

    override fun getEnumFromOrdinal(ordinal: Int): SampleViewType {
        return SampleViewType.values()[ordinal]
    }

}
