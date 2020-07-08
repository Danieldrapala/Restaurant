package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Restaurant {

    public Map<MenuPosition, Double> menuWithPrices;
    public Map<LocalDateTime,Order> orders;

    public Restaurant(){
        this.menuWithPrices= new HashMap<>();
        this.orders= new HashMap<>();
        init();
    }
    public void collectOrder(LocalDateTime dateTime, ArrayList<OrderItem> items,int numbertable ) {
        ArrayList<OrderItem> items1 = (ArrayList<OrderItem>) items.clone();
        orders.put(dateTime,new Order(items1,numbertable,100 ));
        }
    public void editOrder(LocalDateTime orderId,OrderItem oldOne,OrderItem newOne){
        orders.get(orderId).items.remove(oldOne);
        orders.get(orderId).items.add(newOne);
    }
    //Array orders should be sorted by date

    public double calculateIncomeForSpecificDay(LocalDate day){
        double sum=0.0;
        for(Map.Entry<LocalDateTime,Order> entry :orders.entrySet()){
            LocalDate localDate = entry.getKey().toLocalDate();
            if(localDate.equals(day))
                sum+=entry.getValue().calculateIncome();

        }
        return sum;
    }

    public class Order {
        private int id;
        public ArrayList<OrderItem> items;
        private int tablesNumber;
        private int border;

        Order(ArrayList<OrderItem> items, int table,int border){
            this.items=items;
            this.tablesNumber=table;
            this.border=border;
            if(isBig(items,border)){
                items.add(new OrderItem(new MenuPosition("Bordeaux",1),1));
            }
        }
        public int calculateIncome(){
            int sum=0;
            for(OrderItem item:items){
                sum+=item.getCount()*menuWithPrices.get(item.product);
            }
            return  sum;
        }

        public boolean isBig(ArrayList<OrderItem> items,int border){
            return items.size()> border;
        }


    }

    public static class OrderItem {
        MenuPosition product;
        private int count;
        public OrderItem(MenuPosition product, int count){
            this.product=product;
            this.count=count;
        }
        @Override
        public int hashCode() {
            return Objects.hash(product, count);
        }
        @Override
        public boolean equals(Object o) {

            if (o == this) return true;
            if (!(o instanceof OrderItem)) {
                return false;
            }
            OrderItem item = (OrderItem) o;
            return count == item.count &&
                    Objects.equals(product, item.product);
        }
        public int getCount() {
            return count;
        }
        public void setCount(int count){
            this.count=count;
        }
    }
    public static class MenuPosition{
        private int numberOnMenu;
        private String name;
        public MenuPosition(String name,int number){
            this.numberOnMenu=number;
            this.name=name;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj==null) return false;
            if (!(obj instanceof MenuPosition))
                return false;
            if (obj == this)
                return true;
            return this.name.equals(((MenuPosition) obj).name)
                    && ((MenuPosition) obj).numberOnMenu==this.numberOnMenu;
        }
        @Override
        public int hashCode(){
            return Objects.hash(numberOnMenu, name);
        }
        public int getNumberOnMenu() { return numberOnMenu; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        public void setNumberOnMenu(int numberOnMenu) { this.numberOnMenu = numberOnMenu; }
    }
    public void init() {
        menuWithPrices.put(new MenuPosition("Bordeaux",1), 150d);
        menuWithPrices.put(new MenuPosition("Schabowy",2), 40d);
        menuWithPrices.put(new MenuPosition("Sałatka Szefa",3), 25d);
        menuWithPrices.put(new MenuPosition("Szarlotka",4), 10d);
        menuWithPrices.put(new MenuPosition("Piwo Łomza",5), 9d);
        menuWithPrices.put(new MenuPosition("Woda",6), 5d);
    }
}