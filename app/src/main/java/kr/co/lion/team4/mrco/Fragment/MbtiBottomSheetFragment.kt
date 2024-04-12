package kr.co.lion.team4.mrco.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.team4.mrco.MainActivity
import kr.co.lion.team4.mrco.MbtiEI
import kr.co.lion.team4.mrco.MbtiPJ
import kr.co.lion.team4.mrco.MbtiSN
import kr.co.lion.team4.mrco.MbtiTF
import kr.co.lion.team4.mrco.R
import kr.co.lion.team4.mrco.databinding.FragmentMbtiBottomSheetBinding
import kr.co.lion.team4.mrco.viewmodel.MbtiBottomSheetViewModel


class MbtiBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var fragmentMbtiBottomSheetBinding: FragmentMbtiBottomSheetBinding
    lateinit var mainActivity: MainActivity

    lateinit var mbtiBottomSheetViewModel: MbtiBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //fragmentMbtiBottomSheetBinding = FragmentMbtiBottomSheetBinding.inflate(layoutInflater)
        fragmentMbtiBottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mbti_bottom_sheet, container, false)
        mbtiBottomSheetViewModel = MbtiBottomSheetViewModel()
        fragmentMbtiBottomSheetBinding.mbtiBottomSheetViewModel = mbtiBottomSheetViewModel
        fragmentMbtiBottomSheetBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingMbtiUI()

        return fragmentMbtiBottomSheetBinding.root
    }

    fun settingMbtiUI(){
        mbtiBottomSheetViewModel.settingMbtiEI(MbtiEI.E)
        mbtiBottomSheetViewModel.settingMbtiSN(MbtiSN.N)
        mbtiBottomSheetViewModel.settingMbtiTF(MbtiTF.F)
        mbtiBottomSheetViewModel.settingMbtiPJ(MbtiPJ.P)

    }


}