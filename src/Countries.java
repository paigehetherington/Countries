import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Read and Write Files

public class Countries {

    public static HashMap<Character, ArrayList<Country>> countryMap = new HashMap<>(); //created HM key = abbrev and AL of countries

    public static void main(String[] args) throws Exception { //main method
       readFile(); //calling read file method



        char firstLetter = receiveSingleLetter();// create variable.firstLetter comes from the string letter the user typed in

        saveFile(firstLetter); //call save method
        saveJson(firstLetter); //call save json method
    }


    public static void saveJson(char input) throws IOException { //create save method. start with empty and see what need to pass in
        JsonSerializer s = new JsonSerializer(); //new object
        String json = s.include("*").serialize(countryMap.get(input)); //serialize into string -- (include("*") includes array list)

        File f = new File(input +  "_country.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }
    static void saveFile(char firstLetter) throws IOException { //define save file method
        ArrayList<Country> subsetCountries = countryMap.get(firstLetter); //list of countries based on firstLetter
        System.out.println(subsetCountries); //prints arrayList
        String fileName = firstLetter + "_Countries.txt"; //string object to name file
        String fileContent = new String(); //empty string object for file content
        for (Country c : subsetCountries) { //for loop fileContent; loop subsetCountries AL. local variable c
            fileContent = fileContent + c.name + " " + c.abbreviation + "\n"; //put this in fileContent
        }
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();

    }
    static void readFile() throws FileNotFoundException {  //define readFile method
        ArrayList<Country> allCountries = new ArrayList<>();

        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine(); // grab a line and read it;
            String[] columns = line.split("\\|"); //to split 3 parts. escape escape character \\.gives array with 3 things
            Country country = new Country(columns[1], columns[0]); //convert string to int
            allCountries.add(country);
        }


        for (Country c : allCountries) { // for temp variable c in array list AL
            char firstLetter = c.name.charAt(0); //char variable firstLetter  = the country's name's first character
            countryMap.put(firstLetter, new ArrayList<Country>()); // add to the hashMap
        }

        for (Country c : allCountries) {
            char firstLetter = c.name.charAt(0);
            countryMap.get(firstLetter).add(c);
        }
    }

    static char receiveSingleLetter () throws Exception {
        System.out.println("Please type the first letter of the country."); //asks user for input
        Scanner scanner = new Scanner(System.in); //scanner receives input
        String letter = scanner.nextLine(); //create string object for user's input
        if (letter.length() != 1) { //checking to see user entered only 1 letter, if not throw exception
            throw new Exception("Please enter only one letter");
        }
        if (letter.equalsIgnoreCase("x")) { //checking to see if they entered x which has no countries listed
            throw new Exception("There are no countries listed under that letter. Please try again");


        }
        return letter.charAt(0);
    }



}

