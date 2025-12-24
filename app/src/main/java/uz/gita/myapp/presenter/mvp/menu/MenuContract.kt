package uz.gita.myapp.presenter.mvp.menu

import uz.gita.myapp.model.ProductData

interface MenuContract {
    interface View{
        fun showAllProductList(list: List<ProductData>)
        fun updateProduct(position: Int, product: ProductData)
        fun showCartNext(count: Int, sum: String)
        fun navigateCart()
        fun navigateInfo(product: ProductData)
    }
    interface Presenter{
        fun loadProducts()
        fun onPlusClick(product: ProductData, position: Int)
        fun onMinusClick(product: ProductData, position: Int)
        fun onCartClick()

        fun onProductClick(product: ProductData)

    }
}