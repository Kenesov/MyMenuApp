package uz.gita.myapp.model

import uz.gita.myapp.R

object Products {
    private val list = arrayListOf(
        ProductData(
            image = R.drawable.img_4,
            name = "Макс Бургер",
            price = "25 000",
            description = "Закручен со вкусом! Кусочки нежнейшего куриного филе в хрустящей острой чили оригинальной панировке с сочными листьями салата, кусочками помидора и нежным соусом мы завернули в пшеничную лепешку и поджарили в тостере.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_5,
            name = "Чикен Бургер",
            price = "22 000",
            description = "Сочный куриный бургер с хрустящей корочкой, свежими овощами и фирменным соусом. Отличный выбор для быстрого и вкусного перекуса.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_6,
            name = "Чиз Бургер",
            price = "20 000",
            description = "Классический бургер с говяжьей котлетой, расплавленным сыром, маринованными огурцами и ароматным соусом.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_5,
            name = "Хот-Дог",
            price = "18 000",
            description = "Аппетитная сосиска в мягкой булочке с горчицей, кетчупом и хрустящим луком.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_4,
            name = "Картофель Фри",
            price = "12 000",
            description = "Золотистый картофель фри с хрустящей корочкой и мягкой серединой. Подается горячим.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_4,
            name = "Макс Бургер",
            price = "25 000",
            description = "Закручен со вкусом! Кусочки нежнейшего куриного филе в хрустящей острой чили оригинальной панировке с сочными листьями салата, кусочками помидора и нежным соусом мы завернули в пшеничную лепешку и поджарили в тостере.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_5,
            name = "Чикен Бургер",
            price = "22 000",
            description = "Сочный куриный бургер с хрустящей корочкой, свежими овощами и фирменным соусом. Отличный выбор для быстрого и вкусного перекуса.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_6,
            name = "Чиз Бургер",
            price = "20 000",
            description = "Классический бургер с говяжьей котлетой, расплавленным сыром, маринованными огурцами и ароматным соусом.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_5,
            name = "Хот-Дог",
            price = "18 000",
            description = "Аппетитная сосиска в мягкой булочке с горчицей, кетчупом и хрустящим луком.",
            itemCount = 0
        ),
        ProductData(
            image = R.drawable.img_4,
            name = "Картофель Фри",
            price = "12 000",
            description = "Золотистый картофель фри с хрустящей корочкой и мягкой серединой. Подается горячим.",
            itemCount = 0
        )
    )
    fun getAll(): List<ProductData> = list
    fun getByPosition(position: Int): ProductData = list[position]
    fun updateProduct(position: Int, product: ProductData){
        if (position in list.indices){
            list[position] = product
        }
    }
}