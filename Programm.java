import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.Scanner;

public class Programm{


    public static void main(String[] args) throws Exception{
        String str_inf_people = " ";
        String arr_inf_about_people[] = new String[6];
        Scanner in =new Scanner(System.in);
        
        while (true) {
            for (int i  = 0; i < 6; i+=1){
                arr_inf_about_people[i] = "";
            }
            System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");
            str_inf_people = in.nextLine();
            try{
                parse_inf_people(arr_inf_about_people, str_inf_people);
            }
            catch(Exception e){
                System.out.println("Произошла ошибка при считывании данных - ");
                System.err.println(e.getMessage());
            }
            //System.out.println("Все прошло успешно" );
           // System.out.println(arr_inf_about_people[0]);
           // System.out.println(arr_inf_about_people[1]);
           // System.out.println(arr_inf_about_people[2]);
           // System.out.println(arr_inf_about_people[3]);
           // System.out.println(arr_inf_about_people[4]);
           // System.out.println(arr_inf_about_people[5]);
           str_inf_people = "";
           for (int i = 0; i < 6; i+=1){
                str_inf_people += arr_inf_about_people[i] + " ";
           }
           str_inf_people = str_inf_people + '\n';
           try {
            File file = new File(arr_inf_about_people[0] + ".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(str_inf_people);
            writer.flush();

           } 
           catch(IOException a){
            FileWriter writer = new FileWriter(arr_inf_about_people[0] + ".txt", true);
            writer.write(str_inf_people);
            writer.flush();
           }
           catch (Exception e) {
            
           }

          
           
        }
        
        
        
    }

    public static void parse_inf_people( String[] arr_inf_about_people, String str_inf) throws Exception{
        String arr_inf_befor_parse[] = str_to_arry(str_inf);
        Long number ;
        String str;
        // 0 - фамилия, 1- имя, 2 - отчество, 3 - дата рождения, 4 - номер телефона, 5 - пол


        for (int i = 0; i < arr_inf_befor_parse.length; i+=1){
            str = arr_inf_befor_parse[i];
            try {
                number = Long.parseLong(str); // номер телефона
                arr_inf_about_people[4] = str;
            }
            catch(NumberFormatException e){
                if (str.length() == 10 && (str.charAt(2) == str.charAt(5)) && (str.charAt(2) == '.') ){
                    arr_inf_about_people[3] = str;  //дата рождения
                }
                if (str.equals("m") || str.equals("f")){
                    arr_inf_about_people[5] = str;  //пол
                }
                if (str.length() > 1 && str.charAt(0)<= 90 && str.charAt(0) >= 65){
                    if (arr_inf_about_people[0].length() < 1){
                        arr_inf_about_people[0] = str;  //фамилия 
                    }
                    else if (arr_inf_about_people[1].length() < 1) {
                        arr_inf_about_people[1] = str;  //имя 
                    }
                    else if (arr_inf_about_people[2].length() < 1) {
                        arr_inf_about_people[2] = str;  //отчество 
                    }
                }

            }
            
            
        }
    

    }

    public static String[] str_to_arry(String str){
        String[] result_arr = new String[6];
        String tecStr = "";
        int i = 0;
        int n = str.length();
        for (int j = 0; j < n; j +=1){
            if (str.charAt(j) != ' '){               
                tecStr += str.charAt(j);
                if (j == n-1){
                    result_arr[i] = tecStr;
                    break;
                }
                if ((str.charAt(j+1) == ' ')){
                    result_arr[i] = tecStr;
                    i +=1;
                    tecStr = "";
                }
                
            }
            
        }
        return result_arr;
    }
}