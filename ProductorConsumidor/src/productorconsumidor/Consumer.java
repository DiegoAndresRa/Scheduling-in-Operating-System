
package productorconsumidor;
import java.util.*;

class Consumer extends Thread {
   Buffer buff;
   int number;
   // stack to store consumed products
   Stack <Integer> pila = new Stack<>();
   public Consumer(Buffer c, int number) {
      buff = c;
      this.number = number;
   }
   @Override
   public void run() {
      int value = 0;
         for (int i = 0; i < 50; i++) {
            pila = buff.get();
            System.out.println("Consumidor" + this.number + "." + i + " got: " + pila.toString());
         }
      // for the case where more is produced than consumed
      System.exit(1);
    }
}
