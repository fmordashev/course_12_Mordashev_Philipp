package ru.atc.sqllite;

import java.sql.*;


//https://habr.com/sandbox/88039/

public class sqlLiteDAO {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void sqlLiteDAO() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:DataBase/TEST1.s3db");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT, " +
                "course_id INT );");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  phone = resSet.getString("phone");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

    //http://java-course.ru/begin/database03/
    //
    public static void InsertPrepared() throws SQLException {

        PreparedStatement stmt = conn.prepareStatement(
                //"INSERT INTO jc_contact (first_name, last_name, phone, email) VALUES (?, ?, ?, ?)"
                    "INSERT INTO 'users' ('name', 'phone') VALUES (?, ?) "
        );


        for (int i = 0; i < 10; i++) {
            // Заполняем параметры запроса
            String name = "name" + i;
            Integer phone =  8_915_000_00 + i;
            stmt.setString(1, name);
            stmt.setInt(2, phone);
            // Запрос не выполняется, а укладывается в буфер,
            //  который потом выполняется сразу для всех команд
            stmt.addBatch();
        }
// Выполняем все запросы разом
        stmt.executeBatch();

        // Получить список сгенерированных contact_id
        ResultSet gk = stmt.getGeneratedKeys();
        while(gk.next()) {
            System.out.println("Inserted:" + gk.getString(1));
        }
    }

}