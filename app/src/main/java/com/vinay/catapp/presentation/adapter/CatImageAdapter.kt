package com.vinay.catapp.presentation.adapter

/**
 * Created by vinaymishra on 20,July,2022
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vinay.catapp.R
import com.vinay.catapp.databinding.RowItemCatBinding
import com.vinay.catapp.domain.model.CatBreedModel


class CatImageAdapter :
    PagingDataAdapter<CatBreedModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CatBreedModel>() {
            override fun areItemsTheSame(oldItem: CatBreedModel, newItem: CatBreedModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: CatBreedModel, newItem: CatBreedModel): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catBreedModel: CatBreedModel? = getItem(position)
        when (holder) {
            is DoggoImageViewHolder -> {
                catBreedModel?.let {
                    holder.bindItems(it)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoggoImageViewHolder(
            RowItemCatBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        }

    /**
     * view holder class
     */
    inner class DoggoImageViewHolder(var binding: RowItemCatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(catBreedModel: CatBreedModel) {
            itemView.setOnClickListener{ view ->

                val bundle = Bundle()
                bundle.putParcelable("catBreedModel", catBreedModel)
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_sendCashFragment, bundle)
            }
            binding.cartModel = catBreedModel

            itemView?.let {
                Glide
                    .with(it)
                    .load(catBreedModel?.image?.url)
                    .placeholder(R.mipmap.ic_launcher)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivCatImage)
            }

            binding.executePendingBindings()
        }
    }



}