package es.iti.threading.exercices;

public class PrintStringThread extends Thread {
    private final String textToPrint;
    
    public PrintStringThread(String textToPrint) {
        super("Creado PrintStringThread");
        
        this.textToPrint = textToPrint;
    }
    
    @Override
    public void run() {
        System.out.println(this.textToPrint);
    }
}
