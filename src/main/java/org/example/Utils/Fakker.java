package org.example.Utils;
import com.github.javafaker.Faker;
public class Fakker {
    static Faker faker;
    public static String  getFname(){
        faker=new Faker();
        String Fname=faker.name().firstName();
        return Fname;
    }
    public static String  getLname(){
        faker=new Faker();
        String Lname=faker.name().lastName();
        return Lname;
    }
    public static Integer  price(){
        faker=new Faker();
        Integer Price=faker.number().numberBetween(300,900);
        return Price;
    }
}
