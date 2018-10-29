package ru.atc.fileReader;

import ru.atc.model.Sale;
import ru.atc.util.C;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileReader {

//    public static String FileName = ".\\DATA\\1500000 Sales Records.csv" ;
    public static String FileName = ".\\DATA\\500000 Sales Records.csv";

    HashMap<Long, Sale> salesMap = new HashMap<>();

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();

        C.println("\nreadFileToCollection()");
        fileReader.readFileToCollection();

        C.println("\nsearchMap()");
        fileReader.searchMap();

    }

    public static void writeFile() {

        try(FileWriter writer = new FileWriter(".\\DATA\\output.txt", false))
        {
            // запись всей строки
            String text = "Hello Gold!e";
            writer.write(text);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    /**
     *  Чтение файла в Map
     */
    public HashMap<Long, Sale> readFileToCollection() {


        try (BufferedReader br = new BufferedReader(new java.io.FileReader(FileName)))
        {

            long t1 = System.nanoTime();

            String line;
            long l=0;
            while ((line = br.readLine()) != null) {

                if(l++ == 0)
                    continue;

                String[] parts = line.split(",");

                Sale sale = new Sale();
                sale.fill(parts);

                salesMap.put(l, sale);


                if( l % 100_000 == 0) {

                    long t2 = System.nanoTime();
                    C.println("" + l + " time= " + (t2-t1)/1000_000 );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {

        }
        return salesMap;

    }

    void searchMap(){

        long accumulator = 0;
        long max_search = 10_000;
        final Random random = new Random();

        long t1 = System.nanoTime();
        t1 = System.nanoTime();

        for(int j=0; j < 10_000; j++){

            //long key = random.nextInt(100_000);
            //Sale sale = salesMap.get((long)key);
            Sale sale = salesMap.get(j);

            if(sale == null)
                C.println("l not found =" + j);
            else
                accumulator += sale.hashCode() ;
        }

        long t2 = System.nanoTime();
        C.println("" + max_search + " search time= " + (t2-t1)/1000_000 );
        C.println("accumulator =" + accumulator);
    }

}
