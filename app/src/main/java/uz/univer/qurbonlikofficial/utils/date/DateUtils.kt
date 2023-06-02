package uz.univer.qurbonlikofficial.utils.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
  fun getCurrentDate() = SimpleDateFormat("yyyy-MM-dd").format(Date()).toString()

  @SuppressLint("SimpleDateFormat")
  fun getCurrentTime() = SimpleDateFormat("HH:mm").format(Date()).toString()
