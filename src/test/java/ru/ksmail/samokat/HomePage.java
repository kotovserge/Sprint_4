package ru.ksmail.samokat;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    // ЛОКАТОРЫ

    // Куки
    private final String cookieButton = ".//button[text()='да все привыкли']";
    // Кнопка Заказать на чердаке страница
    private final String  headerOrderButton = ".//button[@class='Button_Button__ra12g']";
    // Кнопка Заказать  на страницы
    private final String orderButton =".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
    // Текст Вопросы о важном
    private final String questionText = ".//div[text()='Вопросы о важном']";
    // Вопрос
    private final String question = ".//div[@class='accordion__item'][%d]";
    // Ответ
    private final String answer = ".//div[@id='accordion__panel-%d']";

    public HomePage(WebDriver driver) {
        super(driver);
    }
    // МЕТОДЫ
    // Подтвеждение куки
    public void cookieButtonClick() {

        driver.findElement(By.xpath(cookieButton)).click();
    }
    // Клик по кнопке Заказать на чердаке
    public void headerOrderButtonClick() {

        driver.findElement(By.xpath(headerOrderButton)).click();
    }
    //Скролл до кнопки Заказать и клик по кнопке
    public void orderButtonClick() {
        WebElement orderButtonScroll =
                driver.findElement(By.xpath(orderButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderButtonScroll);
        driver.findElement(By.xpath(orderButton)).click();
    }
    // Скролл страницы до Вопросы о важном
    public void scrollQuestion() {
        WebElement questionTextScroll = driver.findElement(By.xpath(questionText));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionTextScroll);
    }

    // Открытие вопросов
    public void question1Click(int numQuestion) {
        driver.findElement(By.xpath(String.format(question,numQuestion))).click();
    }
    // Ответы на вопросы, numAnswer - номер ответа, начиная с 1
    public String getAnswer( int numAnswer) {
        -- numAnswer;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement element = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(answer, numAnswer))));
        return driver.findElement(By.xpath(String.format(answer, numAnswer))).getText();
    }
    // Метод для проверки ответа на вопрос
    public  void testAnswer(String answerExpected, String answerActual) {
        Assert.assertEquals(answerExpected, answerActual);
    }
}
