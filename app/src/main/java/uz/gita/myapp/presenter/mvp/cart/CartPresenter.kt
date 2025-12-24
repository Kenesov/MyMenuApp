package uz.gita.myapp.presenter.mvp.cart

import uz.gita.myapp.model.ProductData
import uz.gita.myapp.model.Products

class CartPresenter(val view: CartContract.View? = null): CartContract.Presenter{
    private val products = Products
    private var productsList = mutableListOf<ProductData>()

    override fun loadCart() {
        productsList = products.getAll()
            .filter { it.itemCount > 0 }
            .toMutableList()
        if (productsList.isEmpty()){
            view?.close()
            return
        }
        view?.showCartItems(productsList.toList())
        updateTotalPrice()
    }

    override fun onBackClick() {
        view?.close()
    }

    override fun onClearClick() {
        products.getAll().forEachIndexed { index, product ->
            if (product.itemCount > 0) {
                products.updateProduct(index, product.copy(itemCount = 0))
            }
        }

        productsList.clear()
        view?.showCartItems(emptyList())
        updateTotalPrice()
        view?.close()
    }

    override fun onPlusClick(product: ProductData) {
        val index = findIndex(product)
        if (index != -1) {
            val updatedProduct = product.copy(itemCount = product.itemCount + 1)
            products.updateProduct(index, updatedProduct)
            updateLocal(updatedProduct)
        }
    }

    override fun onMinusClick(product: ProductData) {
        val index = findIndex(product)
        if (index != -1) {
            if (product.itemCount > 1) {
                val updatedProduct = product.copy(itemCount = product.itemCount - 1)
                products.updateProduct(index, updatedProduct)
                updateLocal(updatedProduct)
            } else {
                products.updateProduct(index, product.copy(itemCount = 0))
                productsList.removeIf { it.name == product.name }
                view?.showCartItems(productsList.toList())
                updateTotalPrice()
                if (productsList.isEmpty()) view?.close()
            }
        }
    }

    override fun onBuyClick() {
        if (productsList.isNotEmpty()) {
            view?.showOrderSuccess()
            onClearClick()
        }
    }

    private fun updateLocal(updatedProduct: ProductData) {
        val position = productsList.indexOfFirst { it.name == updatedProduct.name }
        if (position != -1) {
            productsList[position] = updatedProduct
            view?.showCartItems(productsList.toList())
            updateTotalPrice()
        }
    }

    private fun updateTotalPrice() {
        var totalPrice = 0
        productsList.forEach {
            val price = it.price.replace(" ", "").toIntOrNull() ?: 0
            totalPrice += price * it.itemCount
        }
        view?.showTotalPrice(formatPrice(totalPrice))
    }

    private fun findIndex(product: ProductData): Int {
        return products.getAll()
            .indexOfFirst { it.name == product.name && it.description == product.description }
    }

    private fun formatPrice(price: Int): String {
        return price.toString().reversed().chunked(3).joinToString(" ").reversed()
    }


}