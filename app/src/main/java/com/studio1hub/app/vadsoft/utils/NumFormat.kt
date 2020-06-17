package com.studio1hub.app.vadsoft.utils

object NumFormat {

    private val c = charArrayOf('k', 'm', 'b', 't')

    fun numConvert(n: Double, iteration: Int): String? {
        val d = n.toLong() / 100 / 10.0
        val isRound =
            d * 10 % 10 == 0.0
        return if (d < 1000)
            (if (d > 99.9 || isRound || !isRound && d > 9.99)
                d.toInt() * 10 / 10 else d.toString() + ""
                    ).toString() + "" + c[iteration] else numConvert(d, iteration + 1)
    }
}