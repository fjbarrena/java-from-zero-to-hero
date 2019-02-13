package es.iti.threading.exercices;

import org.junit.Test;

public class PrintStringThreadTest {
    
    public PrintStringThreadTest() {
    }

    @Test
    public void testRun() {
        for(int i = 0; i <= 100; i++) {
            PrintStringThread thread = new PrintStringThread("Number:" + i);
            
            thread.start();
        }
    }
    
}
