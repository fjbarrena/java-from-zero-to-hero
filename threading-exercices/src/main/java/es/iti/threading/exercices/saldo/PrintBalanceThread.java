package es.iti.threading.exercices.saldo;

public class PrintBalanceThread extends Thread {
    public volatile Integer balance;
    
    public PrintBalanceThread() {
        super("PrintBalanceThread");
        this.balance = 0;
    }
    
    @Override
    public void run() {
        while(true) { ; }
    }
    
    public void updateBalance(int balance) {
        this.balance = balance;
        System.out.println("New balance: " + this.balance);
    }
    
}
