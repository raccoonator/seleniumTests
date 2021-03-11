package RegardPages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Basket extends BasePage {
    private static String nthBasketXpath = "//table[@id=\"table-basket\"]//tr[@class=\"goods_line\"][%d]//a[@class=\"underline\"]";
    private static String inputWindowXpath = "//div[@id=\"basket_tr\"]//input[@type=\"text\"]";
    private static String deleteButtonXpath = "//div[@id=\"basket_tr\"]//td[7]//a";
    private static String emptyMarkXpath = "//div[@id=\"hits\"]//h3";
    private static String basketGoodsXpath = "//table[@id=\"table-basket\"]//tr[@class=\"goods_line\"]";

    public Basket() {
        super("Корзина", "https://www.regard.ru/basket/");
    }

    public void checkGoods(List<String> compare) {
        log.info("Проверяем корзину");
        List<String> goods = basketGoods();
        Assertions.assertEquals(compare.size(), goods.size());
        compare.sort(String::compareTo);
        goods.sort(String::compareTo);
        boolean check = false;
        for (int i = 0; i < goods.size(); i++) {
            if (compare.get(i).contains(goods.get(i)) || goods.get(i).contains(compare.get(i))) {
                check = true;
            } else {
                check = false;
                break;
            }
        }
        Assertions.assertTrue(check);
    }

    public List<String> basketGoods() {
        List<WebElement> elements = driver.findElements(By.xpath(basketGoodsXpath));
        String name;
        List<String> goods = new ArrayList<>();
        for (int i = 1; i <= elements.size(); i++) {
            name = driver.findElement(By.xpath(String.format(nthBasketXpath, i))).getText();
            goods.add(name);
        }
        return goods;
    }

    public void check100Goods(String n) {
        log.info("Проверяем, что в корзине 100 товаров одного вида");
        String name = driver.findElement(By.xpath(String.format(nthBasketXpath, 1))).getText();
        boolean bool;
        if (n.contains(name) || name.contains(n)) {
            bool = true;
        } else {
            bool = false;
        }
        String amount = driver.findElement(By.xpath(inputWindowXpath)).getAttribute("value");
        Assertions.assertTrue(bool);
        Assertions.assertEquals("100", amount);
    }

    public void deleteOneByOne(){
        log.info("Последовательно удаляем товары из корзины");
        List<WebElement> elements = driver.findElements(By.xpath(basketGoodsXpath));
        for(int i = 0;i<elements.size();i++){
            WebElement deleteButton = driver.findElement(By.xpath(deleteButtonXpath));
            deleteButton.click();
        }
    }

    public void checkEmptiness(){
        log.info("Проверяем пустая ли корзина");
        WebElement emptyMark= driver.findElement(By.xpath(emptyMarkXpath));
        String txt = emptyMark.getText();
        Assertions.assertEquals(txt,"В корзине нет товаров.");
    }
}
