package com.company;

import java.util.*;

public class Restaurant {

    Map<Item, Double> menuWithPrices;
    ArrayList<Order> orders;

    public Restaurant(Map<Item, Double> menuWithPrices){
        this.menuWithPrices=menuWithPrices;
        this.orders=new ArrayList<>();
    }
    public void collectOrder(int orderId, ArrayList<Item> items,int numbertable,Date date) {

            orders.add(new Order(items,numbertable,date,isBig(items),orderId ));
        }
    public void editOrder(int orderId,Item oldOne,Item newOne){
        orders.get(orderId).items.remove(oldOne);
        orders.get(orderId).items.add(newOne);
    }
    public boolean isBig(ArrayList<Item> items){
        return items.size()>100;
    }
    //Array orders should be sorted by date

    public int calculateIncomeForSpecificDay(Date day){
        int sum=0;
        for(Order ord: orders){
            if(ord.date.equals(day))
               sum+=ord.calculateIncome();
        }
        return sum;
    }

    public class Order {
        private int id;
        private Date date;
        private ArrayList<Item> items;
        private int tablesNumber;

        Order(ArrayList<Item> items, int table, Date date, boolean isBig, int orderId){
            this.items=items;
            this.date=date;
            this.tablesNumber=table;
            if(isBig){
                Item i=new Item("Bordeaux",1);
                i.setCount(1);
                items.add(i);
            }
            this.id=orderId;
        }
        public int calculateIncome(){
            int sum=0;
            for(Item item:items){
                sum+=item.getCount()*menuWithPrices.get(item);
            }
            return  sum;
        }


    }
    public class Item {
        private int id;
        private String name;
        private int count;
        Item(String name, int id){
            this.name=name;
            this.id=id;
        }
        public int getId(){return id;}
        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }
        public void setCount(int count){
            this.count=count;
        }
        public void setName(String name){
            this.name=name;
        }
    }

    public void init() {
        menuWithPrices.put(new Item("Bordeaux",1), 150d);
        menuWithPrices.put(new Item("Schabowy",2), 40d);
        menuWithPrices.put(new Item("Sałatka Szefa",3), 25d);
        menuWithPrices.put(new Item("Szarlotka",4), 10d);
        menuWithPrices.put(new Item("Piwo Łomza",5), 9d);
        menuWithPrices.put(new Item("Woda",6), 5d);
    }
}