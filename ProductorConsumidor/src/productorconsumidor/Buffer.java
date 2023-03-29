package productorconsumidor;
import java.util.*;

class Buffer {
    //added stack to simulate buffer size of 50 units
    Stack <Integer> pila = new Stack<>();
    private int contents;
    private boolean available = false;

    public synchronized Stack<Integer> get() {
      Stack <Integer> pilaSalida = new Stack<>();
      while (available == false || pila.size() == 0 ) {
          System.out.println("no pude tomar " + "Pila: " + pila.size());
          try {
              notifyAll();
              wait();
          }
          catch (InterruptedException e) {}
      }
      
      System.out.println("Pude tomar");
      /*
        Generating rando variable between 3 and 2
        to extract more than one product
      */
      int aleatorio = (int) (Math.random() * (3 - 2 + 1) + 2);
      while(aleatorio != 0){
        pilaSalida.push(pila.pop());
        aleatorio --;
        if(pila.size() == 0)aleatorio = 0;
      }
      available = false;
      notifyAll();
      return pilaSalida;
   }
   
    public synchronized void put(Stack<Integer> value) {
        while (available == true || pila.size() == 50) {
            System.out.println("no pude agregar");
            try {
                wait();
            }catch (InterruptedException e) {} 
        }

        System.out.println("Agregando");

        while(value.size() != 0){
            pila.push(value.pop());
            if (pila.size() == 50){
                available = true;
                notifyAll();
                try {
                    // to don't lose value data
                    wait();
                }catch (InterruptedException e) {}
            }
        }

        available = true;
        notifyAll();
      }
}