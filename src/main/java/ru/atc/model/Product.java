package ru.atc.model;

public class Product {
    public String product_id;
    public String name;

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    private String categories;

    public String main_categories;
    public String sku;
    public String upc;
    public String location;
    public String quantity;
    public String model;


    public Product(String product_id, String name, String categories){
        this.product_id = product_id;
        this.name = name;
        this.categories = categories;
    }

    public void printMe() {

        System.out.println(product_id + " " + name + "" + categories);
        //System.out.println(Integer.parseInt(product_id.replace("\"", "")));


/*      //заготовка по обработке исключения
        try {
            System.out.println(Integer.parseInt(product_id.replace("\"", "")));
        }
        catch(Exception e){
            System.out.println("ERROR "+ product_id.replace("\"", ""));
        }
*/


    }

    public void printMeNoQuotes() {

        System.out.println(
                product_id.replace  ("\"", "") + " " +
                name.replace        ("\"", "") + " " +
                categories.replace  ("\"", ""));

    }

    public void printMeNoQuotes2() {

        System.out.println(
                noQ( product_id )+ " " +
                noQ( name)+ " " +
                noQ( categories) );

    }

    String noQ(String s){
        return name.replace        ("\"", "");
    }


}


/*
   Оставшиеся колонки:
   manufacturer ;
   image_name ;
   requires
   shipping ;
   price ;
*/
//points ; date_added ; date_modified ; date_available ; weight ; unit ; length ; width ; height ; length unit ; status enabled ; tax_class_id ; viewed ; language_id ; seo_keyword ; description ; meta_description ; meta_keywords ; seo_title ; seo_h1 ; stock_status_id ; store_ids ; layout ; related_ids ; tags ; sort_order ; subtract ; minimum

