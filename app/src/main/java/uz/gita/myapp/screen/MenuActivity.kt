package uz.gita.myapp.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import uz.gita.myapp.R
import uz.gita.myapp.databinding.ActivityMenuBinding
import uz.gita.myapp.model.ProductData
import uz.gita.myapp.presenter.adapter.MenuAdapter
import uz.gita.myapp.presenter.mvp.menu.MenuContract
import uz.gita.myapp.presenter.mvp.menu.MenuPresenter

class MenuActivity : AppCompatActivity(), MenuContract.View {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var presenter: MenuContract.Presenter
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter = MenuPresenter(this)
        initRecycler()
        initView()
        initListeners()
        presenter.loadProducts()
    }

    private fun initRecycler() {
        adapter = MenuAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
    private fun initListeners() {
        adapter.setOnPlusClickListener { product, position ->
            presenter.onPlusClick(product, position)
        }

        adapter.setOnMinusClickListener { product, position ->
            presenter.onMinusClick(product, position)

        }
        adapter.setOnImageClickListener { product, position ->
            presenter.onProductClick(product)
        }

        binding.btnNext.setOnClickListener {
            presenter.onCartClick()
        }
    }
    private fun initView(){
        binding.container.visibility = View.GONE
    }

    override fun showAllProductList(list: List<ProductData>) {
        adapter.submitList(list)
    }

    override fun updateProduct(position: Int, product: ProductData) {
        val currentList = adapter.currentList.toMutableList()
        currentList[position] = product
        adapter.submitList(currentList)
    }


    override fun showCartNext(count: Int, sum: String) {
        if (count>0){
            binding.container.visibility = View.VISIBLE
            binding.txtCounter.text = count.toString()
            binding.txtPrices.text = "${sum} сум"
        }else{
            binding.container.visibility = View.GONE
        }
    }

    override fun navigateCart() {
        startActivity(Intent(this, CartActivity::class.java))
    }
    override fun onResume() {
        super.onResume()
        presenter.loadProducts()
    }


    override fun navigateInfo(product: ProductData) {
        val intent = Intent(this, InfoActivity::class.java)
        val allProducts = adapter.currentList
        val position = allProducts.indexOf(product)

        intent.putExtra("product_position", position)
        intent.putExtra("product_name", product.name)
        intent.putExtra("product_price", product.price)
        intent.putExtra("product_description", product.description)
        intent.putExtra("product_image", product.image)
        intent.putExtra("product_count", product.itemCount)
        startActivity(intent)
    }


}
