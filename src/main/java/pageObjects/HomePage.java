package pageObjects;

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
    // URL Самокат
    private final String url = "https://qa-scooter.praktikum-services.ru/";
    // Куки
    private final String cookieButton = ".//button[text()='да все привыкли']";
    // Кнопка Заказать на чердаке страница
    private final String headerOrderButton = ".//button[@class='Button_Button__ra12g']";
    // Кнопка Заказать  на страницы
    private final String orderButton = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
    // Текст Вопросы о важном
    private final String questionTitleText = ".//div[text()='Вопросы о важном']";
    // Вопрос
    private final String question = ".//div[@id='accordion__heading-%d']";
    // Ответ
    private final String answer = ".//div[@id='accordion__panel-%d']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // МЕТОДЫ
    // Получить URL
    public String getURL() {
        return url;
    }

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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderButtonScroll);
        driver.findElement(By.xpath(orderButton)).click();
    }

    // Скролл страницы до Вопросы о важном
    public void scrollQuestion() {
        WebElement questionTextScroll = driver.findElement(By.xpath(questionTitleText));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionTextScroll);
    }

    // Открытие вопросов
    public void questionClick(int numQuestion) {
        driver.findElement(By.xpath(String.format(question, numQuestion))).click();
    }

    // Текст вопроса
    public String getQuestion(int numQuestion) {
        return driver.findElement(By.xpath(String.format(question, numQuestion))).getText();
    }

    // Ответы на вопросы, numAnswer - номер ответа, начиная с 0
    public String getAnswer(int numAnswer) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement element = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(answer, numAnswer))));
        return driver.findElement(By.xpath(String.format(answer, numAnswer))).getText();
    }

    public void isCorrectText(String expectedText, String actualText) {
        Assert.assertEquals(expectedText, actualText);
    }
}

