import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Customer c1 = new Customer("jon");
    Account account = new Account(c1);
    //int newBalance = account.depositAmount(125);

    @Test
    void getBalance() {
        int expected = 125;
        int actual = account.getBalance();
        assertEquals(expected,actual);
    }

    @Test
    void withDrawAmount() {
        int expected = 105;
        int actual = account.withDrawAmount(20);
        assertEquals(expected,actual);
    }

    @Test
    void depositAmount() {
        int expected = 150;
        //int actual = account.depositAmount(25);
        //assertEquals(expected, actual);
    }
}