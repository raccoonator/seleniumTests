package RegardPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Tests.WebDriverWrapper;

public class BasePage {
    static Logger log = LogManager.getRootLogger();
    protected WebDriverWrapper driver = WebDriverWrapper.getInstance();
    protected String pageName;
    protected String address;
    protected BasePage(String pageName, String address) {
        this.pageName = pageName;
        this.address = address;
    }

    public void close(){
        log.info("Закрываю текущую страницу.");
        driver.close();
    }

    public void open(){
        log.info("Открываю страницу {} по адресу {}",
                this.pageName,
                this.address);
        driver.get(this.address);
    }


}
