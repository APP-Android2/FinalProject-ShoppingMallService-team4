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
import kr.co.lion.team4.mrco.databinding.FragmentModifyUserBinding
import kr.co.lion.team4.mrco.viewmodel.ModifyUserViewModel


class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var mainActivity: MainActivity

    lateinit var modifyUserViewModel: ModifyUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)

        fragmentModifyUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_user, container, false)
        modifyUserViewModel = ModifyUserViewModel()
        fragmentModifyUserBinding.modifyUserViewModel = modifyUserViewModel
        fragmentModifyUserBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingTextField()
        settingButtonModifyUserSubmit()
        settingMBTIEditText()

        return fragmentModifyUserBinding.root
    }

    fun settingMBTIEditText(){
        fragmentModifyUserBinding.textFieldModifyUserUserMBTI.setOnClickListener {
            showMbtiBottomSheet()
        }
    }

    // BottomSheet를 띄워준다.
    fun showMbtiBottomSheet(){
        val mbtiBottomSheetFragment = MbtiBottomSheetFragment()
        mbtiBottomSheetFragment.show(mainActivity.supportFragmentManager, "MbtiBottomSheet")
    }

    // 입력요소 초기설정
    fun settingTextField(){
        // 입력 요소들을 초기화 한다.

        //이름
        modifyUserViewModel.textFieldModifyUserUserName.value = "홍길동"
        //이메일
        modifyUserViewModel.textFieldModifyUserUserEmail.value = "test@example.com"
        //MBTI
        modifyUserViewModel.textFieldModifyUserUserMBTI.value = "INTP"

        //휴대폰 번호
        modifyUserViewModel.textFieldModifyUserUserPhone.value = "01012345678"
        //휴대폰 인증번호
        modifyUserViewModel.textFieldModifyUserUserAuthNumber.value = ""
        //주소
        modifyUserViewModel.textFieldModifyUserUserAddress.value = "서울"
        //상세주소
        modifyUserViewModel.textFieldModifyUserUserAddressDetail.value = "종로구"
        //환불계좌 은행명
        modifyUserViewModel.textFieldModifyUserUserRefundBankName.value = "국민은행"
        //은행계좌 예금주
        modifyUserViewModel.textFieldModifyUserUserRefundBankAccountHolder.value = "홍길동"
        //계좌번호
        modifyUserViewModel.textFieldModifyUserUserRefundBankAccountNumber.value = "12345678"
        //키
        modifyUserViewModel.textFieldModifyUserUserHeight.value = "175"
        //몸무게
        modifyUserViewModel.textFieldModifyUserUserWeight.value = "70"
        //앱 알림설정
        modifyUserViewModel.switchModifyUserNotification.value = true


        // 첫 번째 입력 요소에 포커스를 준다.
        Tools.showSoftInput(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserName)
    }

    fun settingButtonModifyUserSubmit(){
        fragmentModifyUserBinding.buttonModifyUserSubmit.setOnClickListener {
            // 입력을 검사한다.
            val validation = checkModifyUserTextInput()
            // 입력이 모두 잘 되어 있다면..
            if(validation == true) {
                // 키보드를 내려준다.
                Tools.hideSoftInput(mainActivity)

                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(mainActivity)
                materialAlertDialogBuilder.setTitle("수정완료")
                materialAlertDialogBuilder.setMessage("수정이 완료되었습니다")

                materialAlertDialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    mainActivity.removeFragment(MainFragmentName.MODIFY_USER_FRAGMENT)
                }

                materialAlertDialogBuilder.show()
            }

        }
    }


    // 입력요소 유효성 검사 메서드
    fun checkModifyUserTextInput():Boolean{

        // 사용자가 입력한 내용을 가져온다

        //이름
        val userName = modifyUserViewModel.textFieldModifyUserUserName.value!!
        //이메일
        val userEmail = modifyUserViewModel.textFieldModifyUserUserEmail.value!!
        //MBTI
        val userMBTI = modifyUserViewModel.textFieldModifyUserUserMBTI.value!!

        //휴대폰 번호
        val userPhone = modifyUserViewModel.textFieldModifyUserUserPhone.value!!
        //휴대폰 인증번호
        val userAuthNumber = modifyUserViewModel.textFieldModifyUserUserAuthNumber.value!!
        //주소
        val userAddress = modifyUserViewModel.textFieldModifyUserUserAddress.value!!
        //상세주소
        val userAddressDetail = modifyUserViewModel.textFieldModifyUserUserAddressDetail.value!!
        //환불계좌 은행명
        val userRefundBankName = modifyUserViewModel.textFieldModifyUserUserRefundBankName.value!!
        //은행계좌 예금주
        val userRefundBankAccountHolder = modifyUserViewModel.textFieldModifyUserUserRefundBankAccountHolder.value!!
        //계좌번호
        val userRefundBankAccountNumber = modifyUserViewModel.textFieldModifyUserUserRefundBankAccountNumber.value!!
        //키
        val userHeight = modifyUserViewModel.textFieldModifyUserUserHeight.value!!
        //몸무게
        val userWeight = modifyUserViewModel.textFieldModifyUserUserWeight.value!!

        // 이름을 입력하지 않았다면
        if(userName.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserName, "이름 입력 오류",
                "이름을 입력해주세요")
            return false
        }

        // 이메일을 입력하지 않았다면
        if(userEmail.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserEmail, "이메일 입력 오류",
                "이메일을 입력해주세요")
            return false
        }

        // MBTI 입력하지 않았다면
        if(userMBTI.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserMBTI, "MBTI 입력 오류",
                "MBTI를 입력해주세요")
            return false
        }

        // 휴대폰 번호를 입력하지 않았다면
        if(userPhone.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserPhone, "휴대폰 번호 입력 오류",
                "휴대폰 번호를 입력해주세요")
            return false
        }

        // 휴대폰 인증번호를 입력하지 않았다면
        if(userAuthNumber.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserAuthNumber, "휴대폰 인증번호 입력 오류",
                "휴대폰 인증번호를 입력해주세요")
            return false
        }

        // 주소를 입력하지 않았다면
        if(userAddress.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserAddress, "주소 입력 오류",
                "주소를 입력해주세요")
            return false
        }

        // 상세주소를 입력하지 않았다면
        if(userAddressDetail.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserAddressDetail, "상세주소 입력 오류",
                "상세주소를 입력해주세요")
            return false
        }

        // 환불계좌 은행명을 입력하지 않았다면
        if(userRefundBankName.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserRefundBankName, "환불계좌 은행명 입력 오류",
                "환불계좌 은행명을 입력해주세요")
            return false
        }

        // 예금주를 입력하지 않았다면
        if(userRefundBankAccountHolder.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserRefundBankAccountHolder, "예금주 입력 오류",
                "예금주를 입력해주세요")
            return false
        }

        // 계좌번호를 입력하지 않았다면
        if(userRefundBankAccountNumber.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserRefundBankAccountNumber, "계좌번호 입력 오류",
                "계좌번호를 입력해주세요")
            return false
        }

        // 키를 입력하지 않았다면
        if(userHeight.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserHeight, "키 입력 오류",
                "키를 입력해주세요")
            return false
        }

        // 몸무게를 입력하지 않았다면
        if(userWeight.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentModifyUserBinding.textFieldModifyUserUserWeight, "몸무게 입력 오류",
                "몸무게를 입력해주세요")
            return false
        }

        return true
    }


}