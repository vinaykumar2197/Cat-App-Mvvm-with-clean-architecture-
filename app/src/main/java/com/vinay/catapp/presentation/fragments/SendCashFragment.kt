package com.vinay.catapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vinay.catapp.R
import com.vinay.catapp.databinding.FragmentSendCashBinding
import com.vinay.catapp.domain.model.CatBreedModel

class SendCashFragment : Fragment(R.layout.fragment_send_cash){

    private lateinit var fragmentSendCashBinding: FragmentSendCashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSendCashBinding = FragmentSendCashBinding.inflate(inflater, container, false)
        return fragmentSendCashBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartBreedModel =  arguments?.getParcelable<CatBreedModel>("catBreedModel")
        fragmentSendCashBinding.cartModel = cartBreedModel
        fragmentSendCashBinding.executePendingBindings()
        Log.e("SendCashFragment", "onViewCreated: "+cartBreedModel?.name)
        context?.let {
            Glide
                .with(it)
                .load(cartBreedModel?.image?.url)
                .centerInside()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(object : CustomViewTarget<ImageView, Drawable>(fragmentSendCashBinding.ivCat) {
//                    override fun onLoadFailed(errorDrawable: Drawable?) {
//                        // error handling
//                    }
//
//                    override fun onResourceCleared(placeholder: Drawable?) {
//                        // clear all resources
//                    }
//
//                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
//                        fragmentSendCashBinding.ivCat.setImageDrawable(resource)
//                    }
//                })
                .into(fragmentSendCashBinding.ivCat)
        }
    }
}