package uz.gita.myapp.presenter.mvp.cart

import uz.gita.myapp.model.ProductData

interface CartContract {
    interface View{
        fun showCartItems(list: List<ProductData>)
        fun showTotalPrice(price: String)
        fun close()
        fun showEmptyState()
        fun showOrderSuccess()
    }
    interface Presenter{
        fun loadCart()
        fun onBackClick()
        fun onClearClick()
        fun onPlusClick(product: ProductData)
        fun onMinusClick(product: ProductData)
        fun onBuyClick()
    }

}