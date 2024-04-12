package kr.co.lion.team4.mrco.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.team4.mrco.MainActivity
import kr.co.lion.team4.mrco.R
import kr.co.lion.team4.mrco.databinding.FragmentPwChangeBottomSheetBinding
import kr.co.lion.team4.mrco.viewmodel.PwChangeBottomSheetViewModel


class PwChangeBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var fragmentPwChangeBottomSheetBinding: FragmentPwChangeBottomSheetBinding
    lateinit var mainActivity: MainActivity

    lateinit var pwChangeBottomSheetViewModel: PwChangeBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //fragmentPwChangeBottomSheetBinding = FragmentPwChangeBottomSheetBinding.inflate(layoutInflater)

        fragmentPwChangeBottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pw_change_bottom_sheet, container, false)
        pwChangeBottomSheetViewModel = PwChangeBottomSheetViewModel()
        fragmentPwChangeBottomSheetBinding.pwChangeBottomSheetViewModel = pwChangeBottomSheetViewModel
        fragmentPwChangeBottomSheetBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity
        return fragmentPwChangeBottomSheetBinding.root
    }


}