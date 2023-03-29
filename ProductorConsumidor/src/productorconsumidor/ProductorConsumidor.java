package productorconsumidor;
/*
    @author Diego Andres
*/
public class ProductorConsumidor {

    public static void main(String[] args) {
        Buffer buff = new Buffer();
        Producer p1 = new Producer(buff, 1);
        Consumer c1 = new Consumer(buff, 1);
        p1.start(); 
        c1.start();
    }
}
