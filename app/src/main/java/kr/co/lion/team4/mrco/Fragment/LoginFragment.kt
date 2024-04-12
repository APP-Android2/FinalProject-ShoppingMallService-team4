package kr.co.lion.team4.mrco.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kr.co.lion.team4.mrco.MainActivity
import kr.co.lion.team4.mrco.MainFragmentName
import kr.co.lion.team4.mrco.R
import kr.co.lion.team4.mrco.Tools
import kr.co.lion.team4.mrco.databinding.FragmentLoginBinding
import kr.co.lion.team4.mrco.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        fragmentLoginBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        loginViewModel = LoginViewModel()
        fragmentLoginBinding.loginViewModel = loginViewModel
        fragmentLoginBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingLoginInput()
        settingButtonLoginJoin()
        settingButtonLoginSubmit()


        return fragmentLoginBinding.root
    }

    fun settingButtonLoginJoin() {
        // 회원가입 화면으로 이동
        fragmentLoginBinding.buttonLoginJoin.setOnClickListener {
            mainActivity.replaceFragment(MainFragmentName.JOIN_FRAGMENT, true, true, null )

            //테스트 용 회원정보 수정 화면으로 이동
//        fragmentLoginBinding.buttonLoginJoin.setOnClickListener {
//            mainActivity.replaceFragment(MainFragmentName.MODIFY_USER_FRAGMENT, true, true, null )

//        //테스트 용 코디네이터 등록 신청 화면으로 이동
//        fragmentLoginBinding.buttonLoginJoin.setOnClickListener {
//            mainActivity.replaceFragment(MainFragmentName.JOIN_COORDINATOR_FRAGMENT, true, true, null )

//            //테스트 용 코디네이터 등록 신청 **계속** 화면으로 이동
//        fragmentLoginBinding.buttonLoginJoin.setOnClickListener {
//            mainActivity.replaceFragment(MainFragmentName.JOIN_COORDINATOR_NEXT_FRAGMENT, true, true, null )


            //테스트 용 코디네이터 정보 수정 화면으로 이동
//        fragmentLoginBinding.buttonLoginJoin.setOnClickListener {
//            mainActivity.replaceFragment(MainFragmentName.MODIFY_COORDINATOR_FRAGMENT, true, true, null )

        }
    }

    fun settingButtonLoginSubmit() {
        fragmentLoginBinding.buttonLoginSubmit.setOnClickListener {
            //mainActivity.replaceFragment(MainFragmentName.TEST_HOME_SCREEN_FRAGMENT, true, true, null )
            mainActivity.replaceFragment(MainFragmentName.JOIN_COORDINATOR_FRAGMENT, true, true, null )
        }
    }

    // 유효성 검사
    fun checkLoginInput():Boolean{

        // 입력한 값들을 가져온다.
        val userId = loginViewModel.textFieldLoginUserId.value!!
        val userPw = loginViewModel.textFieldLoginUserPw.value!!

        if(userId.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentLoginBinding.textFieldLoginUserId, "아이디 입력 오류",
                "아이디를 입력해주세요")
            return false
        }
        if(userPw.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentLoginBinding.textFieldLoginUserPw, "비밀번호 입력 오류",
                "비밀번호를 입력해주세요")
            return false
        }

        return true
    }

    // 입력 요소들 초기화
    fun settingLoginInput(){
        loginViewModel.textFieldLoginUserId.value = ""
        loginViewModel.textFieldLoginUserPw.value = ""
    }


}