package ru.ksmail.samokat;

import pageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class QuestionTest {
    private WebDriver driver;
    private final int num;
    private final String question;
    private final String answer;

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
        // Скролл до Вопросы о важном
        homePage.scrollQuestion();
    }

    public QuestionTest(int num, String question, String answer) {
        this.num = num;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] testDate() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void QuestionAnswerTest() {
        // Создать объект с домашней страницей
        HomePage homePage = new HomePage(driver);
        // Проверка текста вопроса
        homePage.isCorrectText(question, homePage.getQuestion(num));
        // Раскрытие вопроса
        homePage.questionClick(num);
        // Проверка текста ответа
        homePage.isCorrectText(answer, homePage.getAnswer(num));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

