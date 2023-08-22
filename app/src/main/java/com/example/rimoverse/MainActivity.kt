package com.example.rimoverse


import android.os.Bundle
import android.util.Log

import androidx.fragment.app.FragmentActivity
import com.example.rimoverse.models.Character
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}