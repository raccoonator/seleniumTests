package Tests;

import RegardPages.Basket;
import RegardPages.MainPage;
import RegardPages.ProductPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RegardTests {
    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    Basket basket = new Basket();

    List<String> products;

    /**
     * 1. Открыть главную страницу
     * 2. В левом боковом меню выбрать категорию материнские платы - в подменю выбрать Intel socket 1200
     * 3. Найти 5-ю в списке материнскую плату, положить ее в корзину (маленькая круглая красная кнопочка около товара).
     * 4. В левом боковом меню выбрать категорию корпуса - в подменю выбрать Aerocool
     * 5. Найти 4-й в списке корпус, положить его в корзину (маленькая круглая красная кнопочка около товара).
     * 6. Перейти в раздел корпуса - подменю Corsair
     * 7. Найти 10-й в списке корпус, кликнуть на ссылку-название (откроется страница товара).
     * 8. Нажать на красную кнопку "Добавить в корзину" справа от товара.
     * 9. Нажать на голубую кнопку "Перейти в корзину".
     * 10. Убедиться, что корзина не пустая, и что в ней содержаться ровно те наименования товаров, которые вы покупали.
     */

    //1 сценарий
    @Test
    void firstScenario() {
        products = new ArrayList<>();
        mainPage.open();//1
        mainPage.clickOnMotherboard();//2
        String name = mainPage.addFifthMother();//3
        products.add(name);
        mainPage.clickOnBody();//4
        name = mainPage.addFortBody();//5
        products.add(name);
        mainPage.clickOnCorsairBody();//6
        mainPage.clickOnTenthBody();//7
        name = productPage.addTenthBody();//8
        products.add(name);
        productPage.goToBasket();//9
        basket.checkGoods(products);//10
        basket.deleteOneByOne();
    }

    /**
     * 1. Открыть главную страницу
     * 2. В строке поиска ввести Lenovo и выполнить поиск
     * 3. Проверить первую страницу поиска на наличие моделей именно бренда Lenovo
     */
    @Test
    void secondScenario() {
        mainPage.open();//1
        mainPage.searchLenovo();//2
        productPage.checkProductsName("lenovo");//3
    }

    /**
     * 1. Открыть главную страницу
     * 2. На главной странице выбрать второй товар и перейти на его страницу
     * 3. На странице товара нажать на кнопку Добавить в корзину
     * 4. Поставить курсор в окно количество добавленного товара, ввести 99 и нажать на плюс
     * 5. Перейти в корзину
     * 6. проверить, что добавлено 100 единиц товара
     * и наименование товара в корзине совпадает с добавленным
     */
    @Test
    void thirdScenario() {
        mainPage.open();//1
        mainPage.chooseSecond();//2
        String name = productPage.addGood();//3
        productPage.add99Goods();//4
        productPage.goToBasket();//5
        basket.check100Goods(name);//6
        basket.deleteOneByOne();
    }

    /**
     * 1. Открыть главную страницу
     * 2. На главной странице добавить в корзину 6ой товар в хитах продаж
     * 3. На главной странице добавить в корзину 1ый товар в предложениях дня
     * 4. На главной странице добавить в корзину 2oй товар в новинках
     * 5. Перейти в корзину с главной
     * 6. Последовательно удалить все добавленные товары
     * 7. Проверить, что корзина пуста
     */
    @Test
    void forthScenario() {
        mainPage.open();//1
        mainPage.addSixthProduct();//2
        mainPage.addFirstProduct();//3
        mainPage.addSecondProduct();//4
        mainPage.goToBasket();//5
        basket.deleteOneByOne();//6
        basket.checkEmptiness();//7
    }

    @AfterAll
    static void endTest() {
        WebDriverWrapper.getInstance().quit();
    }
}
