package effectivejava.chapter2.item02.hierarchicalbuilder;

import static effectivejava.chapter2.item02.hierarchicalbuilder.NyPizza.Size.*;
import static effectivejava.chapter2.item02.hierarchicalbuilder.Pizza.Topping.*;

// 계층적 빌더 사용
public class PizzaTest {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();
        
        System.out.println(pizza);
        System.out.println(calzone);
    }
}
