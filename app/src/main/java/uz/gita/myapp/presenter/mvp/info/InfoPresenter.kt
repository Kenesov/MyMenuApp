package uz.gita.myapp.presenter.mvp.info

import uz.gita.myapp.model.ProductData
import uz.gita.myapp.model.Products

class InfoPresenter(val view: InfoContract.View? = null): InfoContract.Presenter{
    private val products = Products
    private var productsList = mutableListOf<ProductData>()
    override fun loadData(position: Int) {
        productsList = products.getAll().toMutableList()
        view?.showProduct(products.getByPosition(position))
        updateCartInfo()
    }


    override fun onPlusClick(product: ProductData, position: Int) {
        val updatedProduct = product.copy(itemCount = product.itemCount + 1)
        productsList[position] = updatedProduct
        products.updateProduct(position, updatedProduct)
        view?.showProduct(productsList[position])
        updateCartInfo()
    }

    override fun onMinusClick(product: ProductData, position: Int) {
        if (product.itemCount > 0){
            val updatedProduct = product.copy(itemCount = product.itemCount - 1)
            productsList[position] = updatedProduct
            products.updateProduct(position, updatedProduct)
            view?.showProduct(productsList[position])
            updateCartInfo()
        }
    }

    override fun onAddToCart() {
        view?.openCart()
    }

    override fun onBack() {
        view?.close()
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
        view?.updatePrice(formattedPrice)
        view?.updateCount(totalCount.toString())
    }
    private fun formatPrice(price: Int): String {
        return price.toString().reversed().chunked(3).joinToString(" ").reversed()
    }
}