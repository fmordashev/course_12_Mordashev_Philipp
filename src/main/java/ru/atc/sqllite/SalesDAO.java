package ru.atc.sqllite;

import ru.atc.fileReader.FileReader;
import ru.atc.model.Sale;

import java.sql.*;
import java.util.HashMap;

public class SalesDAO {

//https://habr.com/sandbox/88039/

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void connectToDB() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:DataBase/TEST1.s3db");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void createDB() throws ClassNotFoundException, SQLException {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'sales' (" +
                "'id' LONG PRIMARY KEY, " +
                "'Region' text," +
                "'Country' text," +
                "'Item_Type' text," +
                "'Sales_Channel' text," +
                "'Order_Priority' text," +
                "'Order_Date' text," +
                "'Order_ID' text," +
                "'Ship_Date' text," +
                "'Units_Sold' text," +
                "'Unit_Price' text," +
                "'Unit_Cost' text," +
                "'Total_Revenue' text," +
                "'Total_Cost' text," +
                "'Total_Profit' text);");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void writeDB() throws SQLException {
        FileReader fileReader = new FileReader();
        insertPrepared(fileReader.readFileToCollection());

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void readDB() throws ClassNotFoundException, SQLException {
        resSet = statmt.executeQuery("SELECT * FROM sales");

        while (resSet.next()) {
            int id = resSet.getInt("id");
            String Region = resSet.getString("Region");
            String Country = resSet.getString("Country");
            String Item_Type = resSet.getString("Item_Type");
            String Sales_Channel = resSet.getString("Sales_Channel");
            String Order_Priority = resSet.getString("Order_Priority");
            String Order_Date = resSet.getString("Order_Date");
            String Order_ID = resSet.getString("Order_ID");
            String Ship_Date = resSet.getString("Ship_Date");
            String Units_Sold = resSet.getString("Units_Sold");
            String Unit_Price = resSet.getString("Unit_Price");
            String Unit_Cost = resSet.getString("Unit_Cost");
            String Total_Revenue = resSet.getString("Total_Revenue");
            String Total_Cost = resSet.getString("Total_Cost");
            String Total_Profit = resSet.getString("Total_Profit");
            System.out.println("ID = " + id);
            System.out.println("Region = " + Region);
            System.out.println("Country  = " + Country);
            System.out.println("Item_Type = " + Item_Type);
            System.out.println("Sales_Channel  = " + Sales_Channel);
            System.out.println("Order_Priority = " + Order_Priority);
            System.out.println("Order_Date  = " + Order_Date);
            System.out.println("Order_ID = " + Order_ID);
            System.out.println("Ship_Date  = " + Ship_Date);
            System.out.println("Units_Sold = " + Units_Sold);
            System.out.println("Unit_Price  = " + Unit_Price);
            System.out.println("Unit_Cost = " + Unit_Cost);
            System.out.println("Total_Revenue  = " + Total_Revenue);
            System.out.println("Total_Cost = " + Total_Cost);
            System.out.println("Total_Profit  = " + Total_Profit);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

    //http://java-course.ru/begin/database03/
    //
    private static void insertPrepared(HashMap<Long, Sale> map) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO 'sales' ('id','Region', 'Country','Item_Type','Sales_Channel','Order_Priority','Order_Date','Order_ID','Ship_Date','Units_Sold','Unit_Price','Unit_Cost','Total_Revenue','Total_Cost','Total_Profit') " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
        );
        int i = 0;

        for (Long key : map.keySet()) {

            Sale saleByKey = map.get(key);
            // Заполняем параметры запроса
            Long id = key;
            String Region = saleByKey.Region;
            String Country = saleByKey.Country;
            String Item_Type = saleByKey.Item_Type;
            String Sales_Channel = saleByKey.Sales_Channel;
            String Order_Priority = saleByKey.Order_Priority;
            String Order_Date = saleByKey.Order_Date;
            String Order_ID = saleByKey.Order_ID;
            String Ship_Date = saleByKey.Ship_Date;
            String Units_Sold = saleByKey.Units_Sold;
            String Unit_Price = saleByKey.Unit_Price;
            String Unit_Cost = saleByKey.Unit_Cost;
            String Total_Revenue = saleByKey.Total_Revenue;
            String Total_Cost = saleByKey.Total_Cost;
            String Total_Profit = saleByKey.Total_Profit;
            stmt.setLong(1, id);
            stmt.setString(2, Region);
            stmt.setString(3, Country);
            stmt.setString(4, Item_Type);
            stmt.setString(5, Sales_Channel);
            stmt.setString(6, Order_Priority);
            stmt.setString(7, Order_Date);
            stmt.setString(8, Order_ID);
            stmt.setString(9, Ship_Date);
            stmt.setString(10, Units_Sold);
            stmt.setString(11, Unit_Price);
            stmt.setString(12, Unit_Cost);
            stmt.setString(13, Total_Revenue);
            stmt.setString(14, Total_Cost);
            stmt.setString(15, Total_Profit);

            // Запрос не выполняется, а укладывается в буфер,
            //  который потом выполняется сразу для всех команд
            stmt.addBatch();

            // упаковка по 10000 записей
            // Выполняем все запросы разом
            if (++i % 10000 == 0) {
                conn.setAutoCommit(false);
                stmt.executeBatch();
                conn.commit();
                System.out.println("Inserted: " + i);
            }
        }

    }

}
