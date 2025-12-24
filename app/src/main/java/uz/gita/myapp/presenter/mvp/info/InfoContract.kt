package uz.gita.myapp.presenter.mvp.info

import uz.gita.myapp.model.ProductData

interface InfoContract {
    interface View{
        fun showProduct(product: ProductData)
        fun updateCount(count: String)
        fun updatePrice(price: String)
        fun close()
        fun openCart()
    }
    interface Presenter{
        fun loadData(position: Int)
        fun onPlusClick(product: ProductData, position: Int)
        fun onMinusClick(product: ProductData, position: Int)
        fun onAddToCart()
        fun onBack()
    }
}