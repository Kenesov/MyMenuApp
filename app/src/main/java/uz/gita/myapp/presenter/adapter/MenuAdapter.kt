package uz.gita.myapp.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.myapp.databinding.ItemProductBinding
import uz.gita.myapp.model.ProductData
import uz.gita.myapp.util.ProductDiffUtil

class MenuAdapter : ListAdapter<ProductData, MenuAdapter.MenuViewHolder>(ProductDiffUtil()) {


    private var onItemClickListener: ((ProductData, Int) -> Unit)? = null
    private var onPlusClickListener: ((ProductData, Int) -> Unit)? = null
    private var onMinusClickListener: ((ProductData, Int) -> Unit)? = null
    private var onImageClickListener: ((ProductData, Int) -> Unit)? = null

    fun setOnItemClickListener(block: (ProductData, Int) -> Unit) {
        onItemClickListener = block
    }

    fun setOnImageClickListener(block: (ProductData, Int) -> Unit) {
        onImageClickListener = block
    }

    fun setOnPlusClickListener(block: (ProductData, Int) -> Unit) {
        onPlusClickListener = block
    }

    fun setOnMinusClickListener(block: (ProductData, Int) -> Unit) {
        onMinusClickListener = block
    }

    inner class MenuViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardMenu.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    onImageClickListener?.invoke(getItem(position),position)
                }
            }

            binding.btnPlus.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = getItem(position)
                    onPlusClickListener?.invoke(product, position)
                }
            }
            binding.btnMinus.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = getItem(position)
                    onMinusClickListener?.invoke(product, position)
                }
            }
            binding.btnPrice.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onPlusClickListener?.invoke(getItem(position), position)
                }
            }

        }

        fun bind(product: ProductData) {
            binding.image.setImageResource(product.image)
            binding.txtName.text = product.name
            binding.txtPrice.text = product.price

            if (product.itemCount > 0){
                binding.btnPrice.visibility = android.view.View.GONE
                binding.btnMinus.visibility = android.view.View.VISIBLE
                binding.btnPlus.visibility = android.view.View.VISIBLE
                binding.txtCount.visibility = android.view.View.VISIBLE
                binding.txtCount.text = product.itemCount.toString()
            }else{
                binding.btnPrice.visibility = android.view.View.VISIBLE
                binding.btnMinus.visibility = android.view.View.GONE
                binding.btnPlus.visibility = android.view.View.GONE
                binding.txtCount.visibility = android.view.View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



}
