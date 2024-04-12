package kr.co.lion.team4.mrco.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.team4.mrco.MainActivity
import kr.co.lion.team4.mrco.MainFragmentName
import kr.co.lion.team4.mrco.R
import kr.co.lion.team4.mrco.Tools
import kr.co.lion.team4.mrco.databinding.FragmentJoinCoordinatorNextBinding
import kr.co.lion.team4.mrco.viewmodel.JoinCoordinatorNextViewModel

class JoinCoordinatorNextFragment : Fragment() {

    lateinit var fragmentJoinCoordinatorNextBinding: FragmentJoinCoordinatorNextBinding
    lateinit var mainActivity: MainActivity

    lateinit var joinCoordinatorNextViewModel: JoinCoordinatorNextViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //fragmentJoinCoordinatorNextBinding = FragmentJoinCoordinatorNextBinding.inflate(inflater)

        fragmentJoinCoordinatorNextBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_join_coordinator_next, container, false)
        // ViewModel 객체를 생성한다.
        joinCoordinatorNextViewModel = JoinCoordinatorNextViewModel()
        // 생성한 ViewModel 객체를 layout 파일에 설정해준다.
        fragmentJoinCoordinatorNextBinding.joinCoordinatorNextViewModel = joinCoordinatorNextViewModel
        // ViewModel의 생명 주기를 Fragment와 일치시킨다. Fragment가 살아 있을 때 ViewModel 객체도 살아 있겠금 해준다.
        fragmentJoinCoordinatorNextBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingButtonJoinCoordinatorNextSubmit()
        settingButtonJoinCoordinatorNextPrevious()
        settingTextField()

        return fragmentJoinCoordinatorNextBinding.root
    }

    fun settingButtonJoinCoordinatorNextSubmit(){
        fragmentJoinCoordinatorNextBinding.buttonJoinCoordinatorNextSubmit.setOnClickListener {
            // 입력을 검사한다.
            val validation = checkJoinCoordinatorNextTextInput()
            // 입력이 모두 잘 되어 있다면..
            if(validation == true) {
                // 키보드를 내려준다.
                Tools.hideSoftInput(mainActivity)

                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(mainActivity)
                materialAlertDialogBuilder.setTitle("등록 신청 완료")
                materialAlertDialogBuilder.setMessage("신청이 완료되었습니다")

                materialAlertDialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    mainActivity.removeFragment(MainFragmentName.JOIN_COORDINATOR_NEXT_FRAGMENT)
                    mainActivity.removeFragment(MainFragmentName.JOIN_COORDINATOR_FRAGMENT)
                }

                materialAlertDialogBuilder.show()
            }

        }
    }

    fun settingButtonJoinCoordinatorNextPrevious(){
        fragmentJoinCoordinatorNextBinding.buttonJoinCoordinatorNextPrevious.setOnClickListener {
            mainActivity.removeFragment(MainFragmentName.JOIN_COORDINATOR_NEXT_FRAGMENT)
        }
    }


    // 입력요소 초기설정
    fun settingTextField(){
        // 입력 요소들을 초기화 한다.
        //출고지 주소
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextWarehouseAddress.value = ""

        //출고지 상세 주소
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextWarehouseAddressDetail.value = ""

        //반품지 주소
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextReturnAddress.value = ""

        //반품지 상세 주소
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextReturnAddressDetail.value = ""

        //은행
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextSettlementBank.value = ""

        //예금주
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextSettlementAccountHolder.value = ""

        //계좌번호
        joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextSettlementAccountNumber.value = ""


        // 첫 번째 입력 요소에 포커스를 준다.
        Tools.showSoftInput(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextWarehouseAddress)
    }

    // 입력요소 유효성 검사 메서드
    fun checkJoinCoordinatorNextTextInput():Boolean{

        // 사용자가 입력한 내용을 가져온다
        //출고지 주소
        val coordinatorWarehouseAddress = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextWarehouseAddress.value!!
        //출고지 상세 주소
        val coordinatorWarehouseAddressDetail = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextWarehouseAddressDetail.value!!
        //반품지 주소
        val coordinatorReturnAddress = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextReturnAddress.value!!
        //반품지 상세 주소
        val coordinatorReturnAddressDetail = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextReturnAddressDetail.value!!
        //은행
        val coordinatorSettlementBank = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextSettlementBank.value!!
        //예금주
        val coordinatorSettlementAccountHolder = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextSettlementAccountHolder.value!!
        //계좌번호
        val coordinatorSettlementAccountNumber = joinCoordinatorNextViewModel.textFieldJoinCoordinatorNextSettlementAccountNumber.value!!

        // 출고지 주소를 입력하지 않았다면
        if(coordinatorWarehouseAddress.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextWarehouseAddress, "출고지 주소 입력 오류",
                "출고지 주소를 입력해주세요")
            return false
        }

        //  출고지 상세 주소 입력하지 않았다면
        if(coordinatorWarehouseAddressDetail.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextWarehouseAddressDetail, "출고지 상세 주소 입력 오류",
                "출고지 상세 주소를 입력해주세요")
            return false
        }

        //  반품지 주소 입력하지 않았다면
        if(coordinatorReturnAddress.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextReturnAddress, "반품지 주소 입력 오류",
                "반품지 주소를 입력해주세요")
            return false
        }

        //  반품지 상세 주소 입력하지 않았다면
        if(coordinatorReturnAddressDetail.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextReturnAddressDetail, "반품지 상세 주소 입력 오류",
                "반품지 상세 주소를 입력해주세요")
            return false
        }

        //  은행 입력하지 않았다면
        if(coordinatorSettlementBank.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextSettlementBank, "은행 입력 오류",
                "은행을 입력해주세요")
            return false
        }

        //  예금주 입력하지 않았다면
        if(coordinatorSettlementAccountHolder.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextSettlementAccountHolder, "예금주 입력 오류",
                "예금주를 입력해주세요")
            return false
        }

        //  계좌번호 입력하지 않았다면
        if(coordinatorSettlementAccountNumber.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinCoordinatorNextBinding.textFieldJoinCoordinatorNextSettlementAccountNumber, "계좌번호 입력 오류",
                "계좌번호를 입력해주세요")
            return false
        }

        return true
    }


}