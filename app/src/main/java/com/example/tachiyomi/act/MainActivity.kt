package com.example.tachiyomi.act

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.tachiyomi.R
import com.example.tachiyomi.databinding.ActivityMainBinding
import com.example.tachiyomi.frg.FragmentSearch
import com.example.tachiyomi.frg.FragmentTrangChu
import com.example.tachiyomi.frg.FragmentXepHang
import com.example.tachiyomi.viewmodel.TrangChuVM
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by inject<TrangChuVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.statusBarColor = resources.getColor(R.color.black)
        }

        setUpUI()
        listenAppEvent()
        listenLiveData()
    }

    private fun setUpUI() {
        viewModel.bottomSheetStatus.value = 1
    }

    private fun listenAppEvent() {
        viewModel.bottomSheetStatus.observe(this) {
            if (it == 1) {
                binding?.bottomSheet?.btnTrangChu?.visibility = View.GONE
                binding?.bottomSheet?.btnTrangChuCheck?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTimKiem?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTimKiemCheck?.visibility = View.GONE
                binding?.bottomSheet?.btnTuTruyen?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTuTruyenCheck?.visibility = View.GONE
                binding?.bottomSheet?.tvTrangChu?.setTextColor(resources.getColor(R.color.yellow1))
                binding?.bottomSheet?.tvTruyenKieuMoi?.setTextColor(resources.getColor(R.color.bottom))
                binding?.bottomSheet?.tvTuTruyen?.setTextColor(resources.getColor(R.color.bottom))
                supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentTrangChu()).commit()
            } else if (it == 2) {
                binding?.bottomSheet?.btnTrangChu?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTrangChuCheck?.visibility = View.GONE
                binding?.bottomSheet?.btnTimKiem?.visibility = View.GONE
                binding?.bottomSheet?.btnTimKiemCheck?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTuTruyen?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTuTruyenCheck?.visibility = View.GONE
                binding?.bottomSheet?.tvTrangChu?.setTextColor(resources.getColor(R.color.bottom))
                binding?.bottomSheet?.tvTruyenKieuMoi?.setTextColor(resources.getColor(R.color.yellow1))
                binding?.bottomSheet?.tvTuTruyen?.setTextColor(resources.getColor(R.color.bottom))
                supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentSearch()).commit()
            } else if (it == 3) {
                binding?.bottomSheet?.btnTrangChu?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTrangChuCheck?.visibility = View.GONE
                binding?.bottomSheet?.btnTimKiem?.visibility = View.VISIBLE
                binding?.bottomSheet?.btnTimKiemCheck?.visibility = View.GONE
                binding?.bottomSheet?.btnTuTruyen?.visibility = View.GONE
                binding?.bottomSheet?.btnTuTruyenCheck?.visibility = View.VISIBLE
                binding?.bottomSheet?.tvTrangChu?.setTextColor(resources.getColor(R.color.bottom))
                binding?.bottomSheet?.tvTruyenKieuMoi?.setTextColor(resources.getColor(R.color.bottom))
                binding?.bottomSheet?.tvTuTruyen?.setTextColor(resources.getColor(R.color.yellow1))
                supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentXepHang()).commit()
            }
        }
    }

    private fun listenLiveData() {
        binding?.bottomSheet?.llTrangChu?.setOnClickListener {
            viewModel.bottomSheetStatus.value = 1
        }
        binding?.bottomSheet?.llTruyenKieuMoi?.setOnClickListener {
            viewModel.bottomSheetStatus.value = 2
        }
        binding?.bottomSheet?.llTuTruyen?.setOnClickListener {
            viewModel.bottomSheetStatus.value = 3
        }
    }
}