package com.generosity.choobudo.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R
import com.generosity.choobudo.databinding.FragmentOnboardingBinding

class OnBoardFragment : Fragment() {

    private var ivBanners: ImageView? = null

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = OnBoardFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        ivBanners = view?.findViewById(R.id.ivBanners)
    }

    //take from array string
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        //get the contents from arrays
        if (activity != null) {
           ///
            val ta=resources.obtainTypedArray(R.array.banners_array)
            val icons: Array<Drawable?> = arrayOfNulls<Drawable>(ta.length())
            for (i in 0 until ta.length()) {
                val id=ta.getResourceId(i, 0)
                if (id != 0) {
                    icons[i]=ContextCompat.getDrawable(requireActivity(), id)
                }
            }
            ivBanners?.setImageDrawable(icons[position])

            ///
//            val onBoardingImages =
//                requireActivity().resources.getIntArray(R.array.banners_array)
//            ivBanners?.setImageResource(onBoardingImages[position])
        }
    }
}