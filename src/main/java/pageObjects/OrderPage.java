package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {
    // ЛОКАТОРЫ
    // Заголовок первой cтраницы Заказа
    private final String orderHeader = ".//div[text()='Для кого самокат']";
    // Заголовок второй страницы заказа
    private final String order2Header = ".//div[text()='Про аренду']";
    // Имя
    private final String nameField = ".//input[@placeholder='* Имя']";
    // Фамилия
    private final String surnameField = ".//input[@placeholder='* Фамилия']";
    // Адрес
    private final String addressField = ".//input[@placeholder='* Адрес: куда привезти заказ']";
    // Список станций метро
    private final String subwayField = ".//input[@placeholder='* Станция метро']";
    private final String subwayField2 = ".//div[text()='%s']";
    // Телефон
    private final String telephoneField = ".//input[@placeholder='* Телефон: на него позвонит курьер']";
    // Кнопка Далее
    private final String nextButton = ".//button[text()='Далее']";
    // Когда привезти самокат
    private final String dateField = ".//input[@placeholder='* Когда привезти самокат']";
    // Срок аренды самоката
    private final String periodRentalField = ".//div[@class='Dropdown-placeholder']";
    private final String periodRentalField2 = ".//div[text()='%s']";
    // Цвет самоката
    private final String colorField = ".//label[text()='%s']";
    // Комментарий для курьера
    private final String commentField = ".//input[@placeholder='Комментарий для курьера']";
    // Кнопка Заказать
    private final String completeOrderButton = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
    // Кнопка завершения заказа
    private final String createOrderButton = ".//button[text()='Да']";
    // Кнопка статуса Заказа
    private final String statusOrderButton = ".//button[text()='Посмотреть статус']";

    // Конструктор
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    // МЕТОДЫ
    // Получаем заголовок страницы Заказа
    public String getOrderHeader() {
        return driver.findElement(By.xpath(orderHeader)).getText();
    }

    // Метод проверки открытия страницы заказа
    public void isOrderPageOpen(String title, String headertitle) {
        Assert.assertEquals(title, headertitle);
    }

    // Заполнение поля Имя
    public void fillName(String name) {
        driver.findElement(By.xpath(nameField)).sendKeys(name);
    }

    // Заполнение поля Фамилия
    public void fillSurname(String surname) {
        driver.findElement(By.xpath(surnameField)).sendKeys(surname);
    }

    // Заполнение поля Адрес
    public void fillAddress(String address) {
        driver.findElement(By.xpath(addressField)).sendKeys(address);
    }

    // Заполнение станции метро
    public void fillSubway(String subway) {
        driver.findElement(By.xpath(subwayField)).click();
        driver.findElement(By.xpath(String.format(subwayField2, subway))).click();
    }

    // Заполнение поля телефона
    public void fillTelephone(String telephone) {
        driver.findElement(By.xpath(telephoneField)).sendKeys(telephone);
    }

    // Нажатие кнопки Далее на первой странице заказа
    public void orderNextButtonClick() {
        driver.findElement(By.xpath(nextButton)).click();
    }

    // Заполнение поля Когда привезти
    public void fillDate(String date) {
        driver.findElement(By.xpath(dateField)).sendKeys(date);
        driver.findElement(By.xpath(order2Header)).click();
    }

    // Заполнение поля срок аренды
    public void fillPeriodRental(String periodRentalDays) {
        driver.findElement(By.xpath(periodRentalField)).click();
        driver.findElement(By.xpath(String.format(periodRentalField2, periodRentalDays))).click();
    }

    // Заполнение поля цвет самоката
    public void fillColor(String color) {
        //    driver.findElement(By.xpath(".//label[text()='"+color+"']")).click();
        driver.findElement(By.xpath(String.format(colorField, color))).click();
    }

    // Заполнение поля комментарий
    public void fillComment(String comment) {
        driver.findElement(By.xpath(commentField)).sendKeys(comment);
    }

    // Нажатие кнопки Заказать
    public void completeButtonClick() {

        driver.findElement(By.xpath(completeOrderButton)).click();
    }

    // Подтвеждение заказа кнопкой Да
    public void createOrderButtonClick() {
        driver.findElement(By.xpath(createOrderButton)).click();
    }

    // Кнопка просмотра статуса заказа
    public String statusOrderButtonText() {
        return driver.findElement(By.xpath(statusOrderButton)).getText();
    }

    public void enteringOrderData( String name, String surname, String address,
                                   String subway, String telephone, String date,
                                   String periodRentalDays, String color, String comment) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillSubway(subway);
        fillTelephone(telephone);
        orderNextButtonClick();
        fillDate(date);
        fillPeriodRental(periodRentalDays);
        fillColor(color);
        fillComment(comment);
        completeButtonClick();
        createOrderButtonClick();
    }
}
