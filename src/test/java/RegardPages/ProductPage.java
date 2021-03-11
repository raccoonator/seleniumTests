package RegardPages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    //пути для первого сценария
    private static String nthBodyBasketXpath ="//a[@id=\"add_cart\"]";
    //пути для второго сценария
    private static String nthPageProductXpath ="//div[@class=\"content\"]//div[@class=\"block\"][%d]//a[@class=\"header\"]";
    private static String headerXpath = "//div[@class=\"top\"]//h1[contains(text(),\"Результаты поиска\")]";
    //пути для третьего сценария
    private static String goodXpath = "//div[@class=\"goods_price\"]//div[@id=\"cart_btn\"]";
    private static String littleWindowXpath = "//div[@class=\"goods_price\"]//input[@id=\"inbskt\"]";
    private static String plusButtonXpath = "//div[@class=\"goods_price\"]//span[@class=\"tplus\"]";

    public ProductPage() {
        super("Страница товара", " ");
    }

    public String addTenthBody(){
        log.info("Добавляем десятый корпус в корзину");
        WebElement toBasket = driver.findElement(By.xpath(nthBodyBasketXpath));
        String name = nameOfProduct();
        toBasket.click();
        return name;
    }

    public void goToBasket(){
        log.info("Переходим в корзину");
        WebElement toBasket = driver.findElement(By.xpath(nthBodyBasketXpath));
        toBasket.click();
    }

    public void checkProductsName(String brand){
        log.info("Проверяем соответствие найденного товара бренду "+brand);
        List<String> goods = goodsList();
        boolean bool = false;
        //проверяем, что в листе все того же бренда, что в аргументе
        for(String good:goods){
            if(good.toLowerCase().contains(brand)){
                bool=true;
            } else {
                bool = false;
                break;
            }
        }
        Assertions.assertTrue(bool);
    }

    public String addGood(){
        log.info("Добавляем товар в корзину");
        WebElement toBasket = driver.findElement(By.xpath(goodXpath));
        String name = nameOfProduct();
        toBasket.click();
        return name;
    }

    public void add99Goods(){
        log.info("Добавляем 99 единиц товар в корзину");
        WebElement littleWindow = driver.findElement(By.xpath(littleWindowXpath));
        littleWindow.sendKeys(Keys.BACK_SPACE);
        littleWindow.sendKeys("99");
        WebElement plusButton = driver.findElement(By.xpath(plusButtonXpath));
        plusButton.click();

    }

    private List<String> goodsList(){
        WebElement header = driver.findElement(By.xpath(headerXpath));//проверяем, что страница обновилась
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id=\"hits\"]//div[@class=\"block\"]//div[@class=\"bcontent\"]"));
        List<String> goods = new ArrayList<>();
        String name;
        //собираем лист названий товаров
        for (int i = 1; i <= elements.size(); i++) {
            name = driver.findElement(By.xpath(String.format(nthPageProductXpath, i))).getText();
            goods.add(name);
        }
        return goods;
    }

    private String nameOfProduct(){
        WebElement element = driver.findElement(By.xpath("//h1[@id=\"goods_head\"]"));
        return element.getText();
    }
}
