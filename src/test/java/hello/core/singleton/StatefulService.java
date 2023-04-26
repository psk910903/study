package hello.core.singleton;

import org.junit.jupiter.api.Test;

public class StatefulService {
    //private int price;  // 상태를 유지하는 필드

//    public void order(String name, int price) {
    public int order(String name, int price) {

        System.out.println("name = " + name + " price = " + price);
        //this.price = price; // 여기가 문제!
        return price;
    }
    //클라이언트의 의도 :주문을해서 값을 price에 저장해놓고 getPrice()를 통해 값을 꺼내 쓰려고 한다.
//    public int getPrice() {
//        return price;
//    }
}
