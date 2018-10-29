package ru.atc.properties;


import ru.atc.util.C;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertyReader {

    public static final String PROP_FILE_NAME = ".\\Properties\\config.properties";

    public static HashMap<String, String> readProperties(String filePath) {

        HashMap<String, String> map = new HashMap<String, String>();

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(".\\Properties\\config.properties");
            property.load(fis);

/*
            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");
*/

            C.println( "Свойства из файла" + PROP_FILE_NAME);
            for(Object key : property.keySet()){
                String sKey   = (String)key;
                String sValue = (String) property.get(key) ;
                map.put(sKey, sValue )  ;

                C.println( sKey + " = " + sValue );
            }

            String login = map.get("db.login");
            C.println( "login =" + login );

/*
            if(!map.containsKey("db.login")){
                System.err.println("Нет такого ключа! " + "db.login");
                throw new RuntimeException("Нет такого ключа! " + "db.login");

            }
*/

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            throw new RuntimeException(e.getMessage());
        }

        return map;

    }


    public static void main(String[] args) {
        PropertyReader.readProperties(PROP_FILE_NAME);
    }

}
