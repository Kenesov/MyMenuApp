package uz.gita.myapp.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.myapp.databinding.ItemCartsProductBinding
import uz.gita.myapp.model.ProductData
import uz.gita.myapp.util.ProductDiffUtil

class CartAdapter : ListAdapter<ProductData, CartAdapter.CartViewHolder>(ProductDiffUtil()) {

    private var onPlusClickListener: ((ProductData, Int) -> Unit)? = null
    private var onMinusClickListener: ((ProductData, Int) -> Unit)? = null

    inner class CartViewHolder(private val binding: ItemCartsProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnPlus.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = getItem(position)
                    onPlusClickListener?.invoke(product, position)
                }
            }

            binding.btnMinus.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = getItem(position)
                    onMinusClickListener?.invoke(product, position)
                }
            }
        }

        fun bind(product: ProductData) {
            binding.img.setImageResource(product.image)
            binding.txtName.text = product.name
            binding.txtPrice.text = "${product.price} сум"
            binding.btnCount.text = product.itemCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartsProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnPlusClickListener(block: (ProductData, Int) -> Unit) {
        onPlusClickListener = block
    }

    fun setOnMinusClickListener(block: (ProductData, Int) -> Unit) {
        onMinusClickListener = block
    }
}
