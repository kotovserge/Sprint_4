package ru.ksmail.samokat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.ksmail.samokat.Resources.*;

public class QuestionTest {

    private WebDriver driver;

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testQuestion() {
        // Открыть страницу Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Создать объект с домашней страницей
        HomePage homePage = new HomePage(driver);
        // Подтверждаем Куки
        homePage.cookieButtonClick();
        // Скролл до Вопросы о важном
        homePage.scrollQuestion();
        // Проверка вопросов
        for (int i=1 ; i<9 ; i++) {
            homePage.question1Click(i);
            homePage.testAnswer(answerExpected[i], homePage.getAnswer(i));
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }

}

