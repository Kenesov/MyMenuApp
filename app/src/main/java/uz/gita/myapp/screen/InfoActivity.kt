package uz.gita.myapp.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.gita.myapp.R
import uz.gita.myapp.databinding.ActivityInfoBinding
import uz.gita.myapp.model.ProductData
import uz.gita.myapp.presenter.mvp.info.InfoContract
import uz.gita.myapp.presenter.mvp.info.InfoPresenter

class InfoActivity : AppCompatActivity(), InfoContract.View {

    private lateinit var binding: ActivityInfoBinding
    private lateinit var presenter: InfoContract.Presenter

    private var productPosition = -1
    private lateinit var product: ProductData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getIntentFull()

        presenter = InfoPresenter(this)
        presenter.loadData(productPosition)

        initListeners()
    }

    private fun getIntentFull() {
        productPosition = intent.getIntExtra("product_position", -1)

        product = ProductData(
            name = intent.getStringExtra("product_name") ?: "",
            price = intent.getStringExtra("product_price") ?: "0",
            description = intent.getStringExtra("product_description") ?: "",
            image = intent.getIntExtra("product_image", 0),
            itemCount = intent.getIntExtra("product_count", 0)
        )
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            presenter.onBack()
        }

        binding.btnPlus.setOnClickListener {
            presenter.onPlusClick(product, productPosition)
        }

        binding.btnMinus.setOnClickListener {
            presenter.onMinusClick(product, productPosition)
        }

        binding.btnToCart.setOnClickListener {
            presenter.onAddToCart()
        }
    }

    override fun showProduct(product: ProductData) {
        this.product = product

        binding.txtName.text = product.name
        binding.txtNames.text = product.name
        binding.txtDescription.text = product.description
        binding.imgProduct.setImageResource(product.image)
        binding.btnCount.text = product.itemCount.toString()
    }

    override fun updateCount(count: String) {
        binding.btnCount.text = count
    }

    override fun updatePrice(price: String) {
        binding.txtPrice.text = "$price сум"
    }
    override fun onResume() {
        super.onResume()
        presenter.loadData(position = productPosition)
    }

    override fun openCart() {
        startActivity(Intent(this, CartActivity::class.java))
    }

    override fun close() {
        finish()
    }
}
