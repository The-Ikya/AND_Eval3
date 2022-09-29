package com.technipixl.exo1

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.technipixl.exo1.models.Heroes
import com.technipixl.exo1.recyclerViews.HeroRecyclerView

@BindingAdapter("listData")
fun bindHeroRecyclerView(recyclerView: RecyclerView, data: List<Heroes.Data.Hero>) {
	val adapter = recyclerView.adapter as HeroRecyclerView
	Log.d("DATAS", data.toString())
	adapter.submitList(data)
}

@BindingAdapter("imageSrc")
fun bindHeroImage(imageView: ImageView, url: String?) {
	url?.let {
		Picasso.get()
			.load(url)
			.placeholder(R.drawable.image_placeholder)
			.error(R.drawable.error_image)
			.fit()
			.centerInside()
			.into(imageView)
	}
}