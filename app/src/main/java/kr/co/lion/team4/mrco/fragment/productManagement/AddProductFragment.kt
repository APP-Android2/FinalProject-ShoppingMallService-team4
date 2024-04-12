package kr.co.lion.team4.mrco.fragment.productManagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.team4.mrco.viewmodel.productManagement.AddProductDetailViewModel
import kr.co.lion.team4.mrco.viewmodel.productManagement.AddProductViewModel
import kr.co.lion.team4.mrco.MainActivity
import kr.co.lion.team4.mrco.MainFragmentName
import kr.co.lion.team4.mrco.R
import kr.co.lion.team4.mrco.databinding.FragmentAddProductBinding
import kr.co.lion.team4.mrco.databinding.ItemAddproductDetailBinding
import kr.co.lion.team4.mrco.databinding.ItemAddproductPhotoBinding

/* (판매자) 코디 상품 등록 화면 */
class AddProductFragment : Fragment() {
    lateinit var fragmentAddProductBinding: FragmentAddProductBinding
    lateinit var addProductViewModel: AddProductViewModel

    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAddProductBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_add_product, container, false)
        addProductViewModel = AddProductViewModel()
        fragmentAddProductBinding.addProductViewModel = addProductViewModel
        fragmentAddProductBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        // setting toolbar, bottom navigation
        mainActivity.removeBottomSheet() // 하단 메뉴 숨기기
        settingToolbarAddProduct()

        settingCategoryClickEvent()

        settingButtonAddProductDetail()

        // 리사이클러뷰 어댑터
        settingAddProductPhotoRecyclerView()
        settingAddProductDetailRecyclerView()

        return fragmentAddProductBinding.root
    }

    // setting toolbar
    fun settingToolbarAddProduct(){
        fragmentAddProductBinding.toolbarAddProduct.apply {
            setNavigationOnClickListener {
                backProcess()
            }
        }
    }

    // 코디 상품 및 재고 등록
    fun settingButtonAddProductDetail(){
        fragmentAddProductBinding.buttonAddProductDetail.setOnClickListener {
            val width = getDeviceWidth()
            AddProductDialog(width!!).show(childFragmentManager, null)
        }
    }

    // 카테고리 클릭 이벤트 설정
    fun settingCategoryClickEvent(){
        showSubCategoryTPO()
        showSubCategorySeason()
        showSubCategoryMood()
    }

    // 카테고리 TPO 선택하면 하위 카테고리 보이게 표시
    fun showSubCategoryTPO(){
        fragmentAddProductBinding.apply {
            chipAddProductTpo.setOnCheckedChangeListener { chip, isChecked ->
                // 체크된 상태
                if(isChecked){
                    textviewAddProductSubCategory.visibility = View.VISIBLE
                    chipgroupAddProductTpoSub.visibility = View.VISIBLE
                }
                // 체크 해제된 상태
                else{
                    textviewAddProductSubCategory.visibility = View.GONE
                    chipgroupAddProductTpoSub.visibility = View.GONE
                }
            }
        }
    }

    // 카테고리 SEASON 선택하면 하위 카테고리 보이게 표시
    fun showSubCategorySeason(){
        fragmentAddProductBinding.apply {
            chipAddProductSeason.setOnCheckedChangeListener { chip, isChecked ->
                // 체크된 상태
                if(isChecked){
                    textviewAddProductSubCategory.visibility = View.VISIBLE
                    chipgroupAddProductSeasonSub.visibility = View.VISIBLE
                }else{
                    textviewAddProductSubCategory.visibility = View.GONE
                    chipgroupAddProductSeasonSub.visibility = View.GONE
                }
            }
        }
    }

    // 카테고리 MOOD 선택하면 하위 카테고리 보이게 표시
    fun showSubCategoryMood(){
        fragmentAddProductBinding.apply {
            chipAddProductMood.setOnCheckedChangeListener { chip, isChecked ->
                // 체크된 상태
                if(isChecked){
                    textviewAddProductSubCategory.visibility = View.VISIBLE
                    chipgroupAddProductMoodSub.visibility = View.VISIBLE
                }else{
                    textviewAddProductSubCategory.visibility = View.GONE
                    chipgroupAddProductMoodSub.visibility = View.GONE
                }
            }
        }
    }

    // 이전 화면으로 돌아가기
    fun backProcess(){
        mainActivity.removeFragment(MainFragmentName.ADD_PRODUCT_FRAGMENT)
    }

    // 코디 사진의 리사이클러뷰 setting
    fun settingAddProductPhotoRecyclerView(){
        fragmentAddProductBinding.apply {
            recyclerviewAddProductPhotos.apply {
                adapter = AddPhotoRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    // 입력된 코디 상품 및 재고 리사이클러뷰
    fun settingAddProductDetailRecyclerView(){
        fragmentAddProductBinding.apply {
            recyclerviewAddProductDetail.apply {
                adapter = AddDetailRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                deco.isLastItemDecorated = false
                addItemDecoration(deco)
            }
        }
    }

    // 코디 사진의 리사이클러뷰 Adapter
    inner class AddPhotoRecyclerViewAdapter : RecyclerView.Adapter<AddPhotoRecyclerViewAdapter.AddPhotoViewHolder>(){
        inner class AddPhotoViewHolder(itemAddProductPhotoBinding: ItemAddproductPhotoBinding) : RecyclerView.ViewHolder(itemAddProductPhotoBinding.root){
            val itemAddProductPhotoBinding : ItemAddproductPhotoBinding
            init {
                this.itemAddProductPhotoBinding = itemAddProductPhotoBinding
                this.itemAddProductPhotoBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPhotoViewHolder {
            val itemAddProductPhotoBinding = ItemAddproductPhotoBinding.inflate(layoutInflater)
            val addPhotoViewHolder = AddPhotoViewHolder(itemAddProductPhotoBinding)

            return addPhotoViewHolder
        }

        override fun onBindViewHolder(holder: AddPhotoViewHolder, position: Int) {
            // 이미지 세팅
        }

        override fun getItemCount(): Int {
            return 4
        }
    }

    // 입력된 코디 상품 및 재고 리사이클러뷰 Adapter
    inner class AddDetailRecyclerViewAdapter : RecyclerView.Adapter<AddDetailRecyclerViewAdapter.AddDetailViewHolder>(){
        inner class AddDetailViewHolder(itemAddproductDetailBinding: ItemAddproductDetailBinding): RecyclerView.ViewHolder(itemAddproductDetailBinding.root){
            val itemAddproductDetailBinding: ItemAddproductDetailBinding
            init {
                this.itemAddproductDetailBinding = itemAddproductDetailBinding
                this.itemAddproductDetailBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddDetailViewHolder {
            val itemAddproductDetailBinding = DataBindingUtil.inflate<ItemAddproductDetailBinding>(
                layoutInflater, R.layout.item_addproduct_detail, parent, false)
            val addProductDetailViewModel = AddProductDetailViewModel()
            itemAddproductDetailBinding.addProductDetailViewModel = addProductDetailViewModel
            itemAddproductDetailBinding.lifecycleOwner = this@AddProductFragment

            val addDetailViewHolder = AddDetailViewHolder(itemAddproductDetailBinding)

            return addDetailViewHolder
        }

        override fun onBindViewHolder(holder: AddDetailViewHolder, position: Int) {
            holder.itemAddproductDetailBinding.addProductDetailViewModel?.textviewAddProductDetailName?.value = "코디 상품명"
            holder.itemAddproductDetailBinding.addProductDetailViewModel?.textviewAddProductDetailOption?.value = "색상 / 사이즈 / 수량 등"
        }

        override fun getItemCount(): Int {
            return 4
        }
    }

    // Dialog의 가로 길이를 조정하기 위해 필요한 '디바이스의 가로 길이' 구하기
   fun getDeviceWidth() : Int? {
        val display = mainActivity.applicationContext?.resources?.displayMetrics
        val deviceWidth = display?.widthPixels // 디바이스의 가로길이

        return deviceWidth
    }
}