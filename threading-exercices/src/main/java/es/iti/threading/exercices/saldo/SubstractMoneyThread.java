package es.iti.threading.exercices.saldo;

import java.io.IOException;

public class SubstractMoneyThread extends Thread {
    public int moneyToSubstract;
    public PrintBalanceThread parentThread;
    
    public SubstractMoneyThread(int moneyToSubstract, PrintBalanceThread parentThread) {
        super("AddMoneyThread");
        this.moneyToSubstract = moneyToSubstract;
        this.parentThread = parentThread;
    }
    
    @Override
    public void run() {
        int nextBalance = this.parentThread.balance - this.moneyToSubstract;
        
        if(nextBalance < 0) {
            synchronized(this.parentThread.balance) {
                try {
                    wait();
                    
                    System.out.println("     --> REVIVIDO");
                    nextBalance = this.parentThread.balance - this.moneyToSubstract;
                    
                    // Ojo negativo posible
                    this.parentThread.updateBalance(nextBalance);
                }
                catch(InterruptedException ex) {
                    System.out.println("Error");
                }
            }
            
            System.out.println("CAUTION!! Your balance can't be lower than zero ;)");
        }
        else {
            this.parentThread.updateBalance(nextBalance);
        }
    }
}
