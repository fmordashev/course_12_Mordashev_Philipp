package ru.atc.model;

public class Sale {
     public String  Region;
     public String  Country;
     public String  Item_Type;
     public String  Sales_Channel;
     public String  Order_Priority;
     public String  Order_Date;
     public String  Order_ID;
     public String  Ship_Date;
     public String  Units_Sold;
     public String  Unit_Price;
     public String  Unit_Cost;
     public String  Total_Revenue;
     public String  Total_Cost;
     public String  Total_Profit;


     public void fill(String ... s){

             Region =            s[0];
             Country =           s[1];
             Item_Type =         s[2];
             Sales_Channel =     s[3];
             Order_Priority =    s[4];
             Order_Date =        s[5];
             Order_ID =          s[6];
             Ship_Date =         s[7];
             Units_Sold =        s[8];
             Unit_Price =        s[9];
             Unit_Cost =         s[10];
             Total_Revenue =     s[11];
             Total_Cost =        s[12];
             Total_Profit =      s[13];

     }
}
