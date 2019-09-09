/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PayStationImplTest {

    PayStation ps;

    @Before
    public void setup() {
        ps = new PayStationImpl();
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
        
    }
    
    /**
     * Call to empty should return the total amount entered.
     */
    @Test
    public void shouldReturnTotalAmountEnteredWhenCallingToEmpty()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.addPayment(25);
        assertEquals("Empty should return amount entered (50)", 50, ps.empty());
    }
    
    /**
     * Cancel does not add to the total amount in the machine.
     */
    @Test
    public void cancelDoesNotAddtoAmountReturned()
        throws IllegalCoinException {
        ps.empty();
        ps.addPayment(25);
        assertEquals("Paypemt should currently be (25)", 25, ps.readDisplayInCents());
        
    }
    
    /**
     * Call to empty function should successfully reset the machine to being completely empty.
     */
    @Test
    public void callToEmptyShouldSuccessfullyEmptyOutTheMachine()
            throws IllegalCoinException {
        ps.empty();
        ps.addPayment(25);
        ps.empty();
        assertEquals("Read Display in cents should be empty (0).", 0, ps.readDisplayInCents());
    }
    
    /**
     * Call to cancel returns a map of one coin entered.
     */
    @Test
    public void calltoCancelReturnsMapOfOneCoinEntered()
            throws IllegalCoinException {
        ps.empty();
        ps.addPayment(25);
        HashMap<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        tempMap.put(25, 1);
        assertEquals("The two maps should be identical containing 1 coin.", ps.cancel(), tempMap);
    }
    
    /**
     * Call to cancel returns correct coin type, not the lowest amount of coins.
     */
    @Test
    public void callToCancelReturnsTheCorrectCoinType()
            throws IllegalCoinException {
        ps.empty();
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(5);
        HashMap<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        tempMap.put(10, 1);
        tempMap.put(10, 1);
        tempMap.put(5, 1);
        assertEquals("The two maps should be identical containing 3 correct coins.", ps.cancel(), tempMap);
    }
    
    @Test
    public void callToCancelDoesntReturnCoinsNotEntered() throws IllegalCoinException{
    
            ps.empty();
            ps.addPayment(25);
            ps.addPayment(10);
            assertEquals("The 5 coin should come back null", ps.cancel().get(5), null);
    }
}
