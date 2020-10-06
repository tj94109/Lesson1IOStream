package com.terrance.Lesson1IOStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.time.LocalDate;

public class Lesson1IOStream {

    //Test Employee Data
    private static Employee test1 = new Employee("Bob Jones", 20000, LocalDate.of(2017,12,31) );
    private static Employee test2 = new Employee("Mary Smith", 30000, LocalDate.of(2018,11,29) );
    private static Employee test3 = new Employee("Moe Green", 40000, LocalDate.of(2020,10,28) );
    private static Employee test4 = new Employee("Mike Stevens", 50000, LocalDate.of(2020,9,27) );

    private static Employee testEmployeeArray[] ={test1, test2, test3, test4 };

    //List to hold data from read from text file
    private static List<Employee> employeesRead = new LinkedList<Employee>();

    public static void main(String[] args) throws IOException {

        String arg = null;

        if(args.length > 0){
            arg = args[0];
        }

        switch(arg){
            case "--help":
                printHelp();
                break;
            case "--text":
                writeTextFile();
                readTextFile("file.txt");
                break;
            case "--binary":
                writeBinaryFile();
                readBinaryFile();
                break;
            case "--object":
                writeObjectFile();
                readObjectFile();
                break;
         }



    }
    //Print methods
    public static void printEmployees(List<Employee> employeeList){
        for (Employee e: employeeList){
            System.out.println(e.toString());
        }
    }

    public static void printHelp(){
        System.out.println("\n***  Welcome to Lesson 1!  ***\n"
               + "--help : display options \n"
               + "--text : writes & reads as text file and displays results on console\n"
               + "--binary : writes & reads as binary file and displays results on console\n"
               + "--object : writes/reads as object file and displays results on console"
        );
    }

    //Text File Methods
    public static void writeTextFile(){
        System.out.println("-- Writing Text File --");
        try{
            FileWriter fw = new FileWriter("file.txt");
            for (Employee e: testEmployeeArray){
                fw.write(e.toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readTextFile(String fileName){
        System.out.println("-- Reading Text File --");
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null){
                employeesRead.add(readEmployee(line));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printEmployees(employeesRead);

    }

    //Binary File Methods
    public static void writeBinaryFile() throws FileNotFoundException {
        File file = new File("file.bin");
        FileOutputStream stream = new FileOutputStream(file);

        try {
            for (Employee e : testEmployeeArray) {
                stream.write(e.toString().getBytes(StandardCharsets.UTF_8));
            }
            stream.close();
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void readBinaryFile() throws IOException {

        File file = new File ("file.bin");
        FileInputStream input = new FileInputStream(file);
        byte[] byteArray = new byte[(int) file.length()];
        input.read(byteArray);
        input.close();

        String s  = new String(byteArray);
        String[] lines = s.split("\\r?\\n");
        for(String line : lines){
            employeesRead.add(readEmployee(line));
        }

        printEmployees(employeesRead);
    }

    // Object File Methods
    public static void writeObjectFile() throws IOException {
        try{
            File file = new File("file.obj");
            FileOutputStream stream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);

            for (Employee e : testEmployeeArray) {
                objectStream.writeObject(e);
            }
            objectStream.close();
            stream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void readObjectFile() throws FileNotFoundException {
        List<Employee> newList = null;
        try{
            FileInputStream stream = new FileInputStream("file.obj");
            ObjectInputStream inputStream = new ObjectInputStream(stream);

            while (stream != null) {
                try{
                    employeesRead.add((Employee) inputStream.readObject());
                }catch(EOFException ex){
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        printEmployees(employeesRead);
    }


    //Helper method to readTextFile
    private static Employee readEmployee(String line){

        String[] token = line.replace("\n", "").replace("\r", "").split("\\|");
        String name = token[0];
        double salary = Double.parseDouble(token[1]);
        LocalDate hireDate = LocalDate.parse(token[2]);

        return new Employee(name, salary, hireDate);

    }

}






