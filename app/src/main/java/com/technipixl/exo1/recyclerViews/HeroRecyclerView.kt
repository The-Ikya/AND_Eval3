package com.technipixl.exo1.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.HeroCellBinding
import com.technipixl.exo1.models.Heroes

class HeroRecyclerView : ListAdapter<Heroes.Data.Hero, HeroRecyclerView.HeroRecyclerViewHolder>(DiffCallBack) {

	companion object DiffCallBack : DiffUtil.ItemCallback<Heroes.Data.Hero>() {
		override fun areItemsTheSame(oldItem: Heroes.Data.Hero, newItem: Heroes.Data.Hero) =
			oldItem.id == newItem.id

		override fun areContentsTheSame(oldItem: Heroes.Data.Hero, newItem: Heroes.Data.Hero) =
			oldItem.name == newItem.name
	}

	class HeroRecyclerViewHolder(private val binding: HeroCellBinding)
		: RecyclerView.ViewHolder(binding.root) {
		fun bind (hero: Heroes.Data.Hero) {
			binding.hero = hero
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		HeroRecyclerViewHolder(HeroCellBinding.inflate(LayoutInflater.from(parent.context)))

	override fun onBindViewHolder(holder: HeroRecyclerViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}