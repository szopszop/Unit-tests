package pl.devfoundry.testing.order;

import org.junit.jupiter.api.*;
import pl.devfoundry.testing.Meal;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderBackup;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {

    static private OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void appendAtTheStartOfTheLine() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void appendAtTheEndOfTheLine() throws IOException {
        orderBackup.getWriter().append(" backed up!");
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

    @Test
    void backupOrderWithOneMeal() throws IOException {
        // given
        Meal meal = new Meal(7, "Fries");
        pl.devfoundry.testing.order.Order order = new Order();
        order.addMealToOrder(meal);

        // when
        orderBackup.backupOrder(order);

        // then
        System.out.println("Order: " + order.toString() + " backed up.");
    }

}