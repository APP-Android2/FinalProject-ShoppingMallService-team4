package kr.co.lion.team4.mrco.Fragment.product.codi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.team4.mrco.Activity.MainActivity
import kr.co.lion.team4.mrco.R
import kr.co.lion.team4.mrco.ViewModel.CodiProductInfoAllViewModel
import kr.co.lion.team4.mrco.databinding.FragmentCodiProductInfoAllBinding
import kr.co.lion.team4.mrco.databinding.FragmentCodiProductInfoBinding

class CodiProductInfoAllFragment : Fragment() {

    private lateinit var binding: FragmentCodiProductInfoAllBinding
    private lateinit var viewModel: CodiProductInfoAllViewModel
    private lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codi_product_info_all, container, false)

        // setClipToOutline : 이미지를 배경에 맞게 자른다.
        // ImageView.setClipToOutline(true)를 사용한다.
        // binding.imageView.clipToOutline = true
    }
}