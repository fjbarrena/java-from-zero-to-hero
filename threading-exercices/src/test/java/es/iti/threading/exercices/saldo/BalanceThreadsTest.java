package es.iti.threading.exercices.saldo;

import java.util.Random;
import org.junit.Test;

public class BalanceThreadsTest {
    
    public BalanceThreadsTest() {
    }
    
    @Test
    public void testRun() {
        PrintBalanceThread printThread = new PrintBalanceThread();
        
        printThread.start();
        
        Random rand = new Random(System.currentTimeMillis());
        
        for(int i = 0; i <= 100; i++) {
            AddMoneyThread addThread = new AddMoneyThread(
                    Math.abs(rand.nextInt()%100), printThread);
            
            SubstractMoneyThread subsThread = new SubstractMoneyThread(
                    Math.abs(rand.nextInt()%100), printThread);
            
            addThread.start();
            subsThread.start();
        }
        
        
    }
}
