package com.example.tachiyomi

interface OnMainCallBack {
    fun showFragment(tag: String, data: Any?, isBack: Boolean)
}