package ru.atc.application;

import ru.atc.sqllite.SalesDAO;

import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SalesDAO.connectToDB();
        SalesDAO.createDB();
        SalesDAO.writeDB();
//        SalesDAO.readDB();            //неудобный вывод
        SalesDAO.closeDB();
    }
}
