package RegardPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    //пути для первого сценария
    private static String addFithMotherXpath = "//div[@id = \"hits\"]//div[@class = \"content\"]//div[@class = \"block\"][5]//span[@class=\"basket_button_class\"]//a";
    private static String leftMenuXpath = "//*[@id=\"lmenu\"]//a[text()=\"%s\"]";
    private static String fithMotherXpath = String.format(leftMenuXpath, "Материнские платы") + "//following-sibling::ul//a[text()=\"Intel Socket 1200\"]";
    private static String addforthBodyXpath = "//div[@id = \"hits\"]//div[@class = \"content\"]//div[@class = \"block\"][4]//span[@class=\"basket_button_class\"]//a";
    private static String bodyNameXpath = "//*[@id=\"lmenu\"]//a[text()=\"Корпуса\"]//following-sibling::ul//a[text() = \"%s\"]";
    private static String nth = "//div[@id = \"hits\"]//div[@class = \"content\"]//div[@class = \"block\"][%d]//a[@class=\"header\"]";
    //пути для второго сценария
    private static String searchBarXpath = "//div[@class=\"text-container\"]//input";
    //пути для третьего сценария
    private static String secondPieceXpath = "//div[@id=\"hits\"]//div[@class=\"block\"][2]//a[@class=\"header\"]";
    //пути для четвертого сценария
    private static String sixthButtonXpath = "//div[@id=\"hits\"]//div[@class=\"block\"][6]//a[@class=\"cart\"]";
    private static String firstButtonXpath = "//div[@id=\"offer\"]//div[@class=\"block\"][1]//a[@class=\"cart\"]";
    private static String secondButtonXpath = "//div[@id=\"novelty\"]//div[@class=\"block\"][2]//a[@class=\"cart\"]";
    private static String busketdButtonXpath = "//div[@id=\"basket\"]//a";

    public MainPage() {
        super("Главная страница", "https://www.regard.ru");
    }

    public void clickOnMotherboard() {
        log.info("Кликаем в боковом меню по Материнским платам");
        WebElement mother = driver.findElement(By.xpath(String.format(leftMenuXpath, "Материнские платы")));
        mother.click();
        log.info("Кликаем в меню плат по Intel socket 1200");
        WebElement motherIntel = driver.findElement(By.xpath(fithMotherXpath));
        motherIntel.click();
    }

    public String addFifthMother() {
        log.info("Добавляем пятую материнску плату в списке в корзину");
        WebElement fifthMother = driver.findElement(By.xpath(addFithMotherXpath));
        driver.scroll(100);
        String name = nameOfProduct(5);
        fifthMother.click();
        return name;
    }

    public void clickOnBody() {
        log.info("Кликаем в боковом меню по Корпусам");
        WebElement body = driver.findElement(By.xpath(String.format(leftMenuXpath, "Корпуса")));
        body.click();
        log.info("Кликаем в меню корпусов по Aerocool");
        WebElement bodyAerocool = driver.findElement(By.xpath(String.format(bodyNameXpath, "AEROCOOL")));
        bodyAerocool.click();
    }

    public String addFortBody() {
        log.info("Добавляем четвертый корпус в списке в корзину");
        WebElement forthBody = driver.findElement(By.xpath(addforthBodyXpath));
        driver.scroll(100);
        String name = nameOfProduct(4);
        forthBody.click();
        return name;
    }

    public void clickOnCorsairBody() {
        log.info("Кликаем в меню корпусов по Corsair");
        WebElement bodyCorsair = driver.findElement(By.xpath(String.format(bodyNameXpath, "CORSAIR")));
        bodyCorsair.click();
    }

    public void clickOnTenthBody() {
        log.info("Открываем страницу десятого в списке корпуса");
        WebElement tenthBody = driver.findElement(By.xpath(String.format(nth, 10)));
        driver.scroll(500);
        tenthBody.click();
    }

    public void searchLenovo() {
        log.info("Выполняем поиск товаров Lenovo");
        WebElement searchBar = driver.findElement(By.xpath(searchBarXpath));
        searchBar.sendKeys("Lenovo");
        searchBar.sendKeys(Keys.ENTER);
    }

    public void chooseSecond() {
        log.info("Проходим на страницу второго товара");
        WebElement secondPiece = driver.findElement(By.xpath(secondPieceXpath));
        secondPiece.click();
    }

    public void addSixthProduct() {
        log.info("Добавляем шестой товар из хитов продаж в списке в корзину");
        driver.scroll(200);
        WebElement sixthButton = driver.findElement(By.xpath(sixthButtonXpath));
        sixthButton.click();
    }

    public void addFirstProduct() {
        log.info("Добавляем первый товар из прдложений дня в списке в корзину");
        driver.scroll(600);
        WebElement firstButton = driver.findElement(By.xpath(firstButtonXpath));
        firstButton.click();
    }

    public void addSecondProduct() {
        log.info("Добавляем второй товар из новинок в списке в корзину");
        driver.scroll(700);
        WebElement secondButton = driver.findElement(By.xpath(secondButtonXpath));
        secondButton.click();
    }

    public void goToBasket() {
        log.info("Переходим в корзину");
        driver.scroll(-900);
        WebElement basketButton = driver.findElement(By.xpath(busketdButtonXpath));
        basketButton.click();
    }

    private String nameOfProduct(int i) {
        WebElement element = driver.findElement(By.xpath(String.format(nth, i)));
        return element.getText();
    }


}
