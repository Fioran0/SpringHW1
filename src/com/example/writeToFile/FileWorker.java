package com.example.writeToFile;

import java.io.*;

public class FileWorker {
    public static File file = new File("data.txt");


   public static void write (String name, String surname, String patronymic, int age, int salary, String emale, String placeOfWork){

        try(PrintWriter out = new PrintWriter(new FileWriter(file, true)))
        {
                out.print(name + " ");
                out.print(surname + " ");
                out.print(patronymic + " ");
                out.print(age + " ");
                out.print(salary + " ");
                out.print(emale + " ");
                out.print(placeOfWork + " ");
                out.print("\n");

        } catch(FileNotFoundException e) {
            e.printStackTrace();
            }
        catch (IOException e){
            e.printStackTrace();
            }
        }
    }
