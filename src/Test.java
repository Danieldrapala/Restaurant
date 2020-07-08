import com.company.Restaurant;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void testCreate() {
        Map<Restaurant.MenuPosition, Double> menu=new HashMap<>();
        menu.put(new Restaurant.MenuPosition("Bordeaux",1), 150d);
        menu.put(new Restaurant.MenuPosition("Schabowy",2), 40d);
        menu.put(new Restaurant.MenuPosition("Sałatka Szefa",3), 25d);
        menu.put(new Restaurant.MenuPosition("Szarlotka",4), 10d);
        menu.put(new Restaurant.MenuPosition("Piwo Łomza",5), 9d);
        menu.put(new Restaurant.MenuPosition("Woda",6), 5d);
        System.out.println(menu.get(new Restaurant.MenuPosition("Bordeaux",1)));

        Restaurant mcDonald=new Restaurant();

        Assert.assertEquals(menu,mcDonald.menuWithPrices);

    }

    @org.junit.Test
    public void testIncome() {
        Restaurant mcDonald=new Restaurant();
        ArrayList<Restaurant.OrderItem> items=new ArrayList<>();
        items.add(new Restaurant.OrderItem(new Restaurant.MenuPosition("Bordeaux",1),4));
        items.add(new Restaurant.OrderItem(new Restaurant.MenuPosition("Schabowy",2),3));
        items.add(new Restaurant.OrderItem(new Restaurant.MenuPosition("Sałatka Szefa",3),4));
        LocalDateTime localtime=LocalDateTime.now();
        mcDonald.collectOrder(localtime,items,1);
        items.add(new Restaurant.OrderItem(new Restaurant.MenuPosition("Sałatka Szefa",3),4));
        mcDonald.collectOrder(LocalDateTime.now(),items,4);

        double d = mcDonald.calculateIncomeForSpecificDay(LocalDate.now());
        Assert.assertEquals(1740,d,Math.pow(10,-13));
    }

    @org.junit.Test
    public void testIncomeWithMoreThanOneDay() {

    }

    @org.junit.Test
    public void testSurvive() {

    }
}