package ru.ksmail.samokat;

import pageObjects.HomePage;
import pageObjects.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static ru.ksmail.samokat.Resources.textStatusOrderButton;

@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String telephone;
    private final String date;
    private final String periodRental;
    private final String color;
    private final String comment;

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();
        // Создать объект с домашней страницей
        HomePage homePage = new HomePage(driver);
        // Открыть страницу Яндекс Самокат
        driver.get(homePage.getURL());
        // Подтверждаем Куки
        homePage.cookieButtonClick();
    }

    public OrderPageTest(String name, String surname, String address,
                         String subway, String telephone, String date,
                         String periodRental, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.telephone = telephone;
        this.date = date;
        this.periodRental = periodRental;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testDate() {
        return new Object[][]{
                {"Петр", "Сидоров", "г.Москва, ул.Некрасовская, д.58",
                        "Октябрьская", "89655124587", "30.11.2024",
                        "трое суток", "серая безысходность", "позвонить от метро"},
                {"Мария", "Васильева", "г.Москва, ул.Ходынская, д.23",
                        "Улица 1905 года", "89164893214", "25.11.2024",
                        "сутки", "чёрный жемчуг", "не звонить"}
        };
    }

    @Test
    public void OrderTest() {
        // Создаем объект с домашней старницей
        HomePage homePage = new HomePage(driver);
        // Нажимаем кнопку Заказать на чердаке страницы
        homePage.headerOrderButtonClick();
        // Создаем объект со старницей Заказа
        OrderPage orderPage = new OrderPage(driver);
        // заполняем поля страницы Заказа
        orderPage.enteringOrderData(name, surname, address, subway,
                telephone, date, periodRental, color, comment);
        orderPage.isOrderPageOpen(textStatusOrderButton, orderPage.statusOrderButtonText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
