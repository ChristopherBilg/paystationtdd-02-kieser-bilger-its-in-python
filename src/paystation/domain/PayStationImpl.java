package paystation.domain;

import java.util.HashMap;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private HashMap<Integer, Integer> currentContents = new HashMap<Integer, Integer>();

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                currentContents.put(5, 1);
                break;
            case 10:
                currentContents.put(10, 1);
                break;
            case 25:
                currentContents.put(25, 1);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }
    
    @Override
    public int readDisplayInCents() {
        return insertedSoFar;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }

    @Override
    public HashMap<Integer, Integer> cancel() {
        HashMap <Integer, Integer> temp = new HashMap<Integer, Integer>();
        temp.putAll(currentContents);
        reset();
        return temp;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        currentContents.clear();
    }
    
    @Override
    public int empty() {
        int temp = insertedSoFar;
        reset();
        return temp;
    }
}
