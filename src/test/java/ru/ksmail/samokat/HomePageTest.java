package ru.ksmail.samokat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.ksmail.samokat.Resources.orderHeader;

public class HomePageTest {
    private WebDriver driver;

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void openOrderPageHeaderButtonClick() {
        // Открыть страницу Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Создать объект с домашней страницей
        HomePage homePage = new HomePage(driver);
        // Подтверждаем Куки
        homePage.cookieButtonClick();
        // Нажать кнопку Заказать на чердаке
        homePage.headerOrderButtonClick();
        // Создать объект со страницей Заказа
        OrderPage orderPage = new OrderPage(driver);
        // Проверить открытие страницы Заказа
        orderPage.isOrderPageOpen( orderHeader, orderPage.getOrderHeader());
    }

    @Test
    public void openOrderPageButtonClick() {
        // Открыть страницу Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Созать объект  класса с Домашней страницей
        HomePage homePage = new HomePage(driver);
        // Подтверждаем Куки
        homePage.cookieButtonClick();
        // Скролл до кнопки  Заказать и нажать кнопку Заказать
        homePage.orderButtonClick();
        // Созать объект со страницей Заказа
        OrderPage orderPage = new OrderPage(driver);
        // Проверить открытие страницы Заказа
        orderPage.isOrderPageOpen( orderHeader, orderPage.getOrderHeader());
    }


    @After
    public void teardown() {
        driver.quit();
    }

}
