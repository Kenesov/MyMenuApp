package uz.gita.myapp.presenter.mvp.menu

import uz.gita.myapp.model.ProductData
import uz.gita.myapp.model.Products
import kotlin.collections.forEach

class MenuPresenter(val view: MenuContract.View? = null): MenuContract.Presenter {
    private val products = Products
    private var productsList = mutableListOf<ProductData>()

    override fun loadProducts() {
        productsList = products.getAll().toMutableList()
        view?.showAllProductList(productsList.toList())
        updateCartInfo()
    }

    override fun onPlusClick(product: ProductData, position: Int) {
        val updatedProduct = product.copy(itemCount = product.itemCount + 1)
        productsList[position] = updatedProduct
        products.updateProduct(position, updatedProduct)
        view?.showAllProductList(productsList.toList())
        updateCartInfo()
    }

    override fun onMinusClick(product: ProductData, position: Int) {
        if (product.itemCount > 0){
            val updatedProduct = product.copy(itemCount = product.itemCount - 1)
            productsList[position] = updatedProduct
            products.updateProduct(position, updatedProduct)
            view?.showAllProductList(productsList.toList())
            updateCartInfo()
        }
    }

    override fun onCartClick() {
        view?.navigateCart()
    }

    override fun onProductClick(product: ProductData) {
        view?.navigateInfo(product)
    }
    private fun updateCartInfo() {
        var totalCount = 0
        var totalPrice = 0

        productsList.forEach { product ->
            totalCount += product.itemCount
            val price = product.price.replace(" ", "").toIntOrNull() ?: 0
            totalPrice += price * product.itemCount
        }

        val formattedPrice = formatPrice(totalPrice)
        view?.showCartNext(totalCount, formattedPrice)
    }
    private fun formatPrice(price: Int): String {
        return price.toString().reversed().chunked(3).joinToString(" ").reversed()
    }
}