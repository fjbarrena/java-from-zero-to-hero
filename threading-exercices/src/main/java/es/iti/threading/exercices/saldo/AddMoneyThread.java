package es.iti.threading.exercices.saldo;

public class AddMoneyThread extends Thread {
    public int moneyToAdd;
    public PrintBalanceThread parentThread;
    
    public AddMoneyThread(int moneyToAdd, PrintBalanceThread parentThread) {
        super("AddMoneyThread");
        this.moneyToAdd = moneyToAdd;
        this.parentThread = parentThread;
    }
    
    @Override
    public void run() {
        int nextBalance = this.parentThread.balance + this.moneyToAdd;
        
        this.parentThread.updateBalance(nextBalance);
        
        synchronized (this.parentThread.balance) {
            try {
                notifyAll();
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}
