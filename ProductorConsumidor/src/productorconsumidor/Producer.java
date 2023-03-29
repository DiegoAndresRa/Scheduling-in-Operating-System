
package productorconsumidor;
import java.util.*;

class Producer extends Thread{
    Buffer buff;
    int number;
    // stack to store products produced
    Stack <Integer> pila = new Stack<>();

    public Producer(Buffer buff, int number) {
      this.buff = buff;
      this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            // to fill the production stack
            int numeroAleatorio = (int) (Math.random() * (10 - 5 + 1) + 5);
            for (int j = 1; j < numeroAleatorio;j++){
                int rellenoAleatorio = (int) (Math.random() * (50 - 40 + 1) + 40);
                pila.push(rellenoAleatorio);
            }
            // be able to print the production
            Stack <Integer> copia = (Stack<Integer>) pila.clone();
            buff.put(pila);
            System.out.println("Productor #" + this.number + "." + i + " put: " + copia.toString());
        try {
            sleep((int)(Math.random() * 1000));
        } catch (InterruptedException e) {}
        }
        // for the case where more is produced than consumed
        System.exit(1);
    }
}
