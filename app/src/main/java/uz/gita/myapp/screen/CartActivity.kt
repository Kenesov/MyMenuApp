package uz.gita.myapp.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.myapp.R
import uz.gita.myapp.databinding.ActivityCartBinding
import uz.gita.myapp.model.ProductData
import uz.gita.myapp.presenter.adapter.CartAdapter
import uz.gita.myapp.presenter.mvp.cart.CartContract
import uz.gita.myapp.presenter.mvp.cart.CartPresenter

class CartActivity : AppCompatActivity(), CartContract.View{
    private lateinit var binding: ActivityCartBinding
    private lateinit var presenter: CartContract.Presenter
    private val adapter = CartAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter = CartPresenter(this)
        initRecycler()
        initListeners()
        presenter.loadCart()
    }

    private fun initRecycler() {
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCart.adapter = adapter

        adapter.setOnPlusClickListener { product, _ ->
            presenter.onPlusClick(product)
        }

        adapter.setOnMinusClickListener { product, _ ->
            presenter.onMinusClick(product)
        }
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            presenter.onBackClick()
        }

        binding.btnDelete.setOnClickListener {
            presenter.onClearClick()
        }

        binding.btnBuy.setOnClickListener {
            presenter.onBuyClick()
        }
    }

    override fun showCartItems(list: List<ProductData>) {
        adapter.submitList(list.toList())
    }

    override fun showTotalPrice(price: String) {
        binding.txtSum.text = "$price сум"
    }

    override fun close() {
        finish()
    }

    override fun showEmptyState() {
        finish()
    }

    override fun showOrderSuccess() {
        Toast.makeText(this, "Заказ успешно оформлен!", Toast.LENGTH_LONG).show()
    }
}