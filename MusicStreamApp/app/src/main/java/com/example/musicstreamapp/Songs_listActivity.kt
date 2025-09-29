package com.example.musicstreamapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicstreamapp.adapter.SongsListAdaptar
import com.example.musicstreamapp.databinding.ActivitySongsListBinding
import com.example.musicstreamapp.models.CategoryModel

class Songs_listActivity : AppCompatActivity() {

    companion object{
        lateinit var category : CategoryModel
    }
     lateinit var binding : ActivitySongsListBinding
     lateinit var songsListAdaptar: SongsListAdaptar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTextView.text = category.name
        Glide.with(binding.coverImageView).load(category.coverUrl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .into(binding.coverImageView)

        setupSongsListRecyclerView()
    }
    fun setupSongsListRecyclerView(){
        songsListAdaptar = SongsListAdaptar(category.songs)
        binding.songsListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songsListRecyclerView.adapter = songsListAdaptar
    }
}