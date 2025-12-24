package uz.gita.myapp.util

import androidx.recyclerview.widget.DiffUtil
import uz.gita.myapp.model.ProductData

class ProductDiffUtil: DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem == newItem
    }
}