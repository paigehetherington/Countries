import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {

    public static HashMap<Character, ArrayList<Country>> countryMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
       readFile();

        System.out.println("Please type the first letter of the country.");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();
        if (letter.length() != 1) {
            throw new Exception("Please enter only one letter");
        }
        if (letter.equalsIgnoreCase("x")) {
            throw new Exception("There are no countries listed under that letter. Please try again");

        }

        char firstLetter = letter.charAt(0);// first Letter comes from the string letter the user typed in
        ArrayList<Country> subsetCountries = countryMap.get(firstLetter);
        System.out.println(subsetCountries);
        String fileName = firstLetter + "_Countries.txt";
        String fileContent = new String();
        for (Country c : subsetCountries) {
            fileContent = fileContent + c.name + " " + c.abbreviation + "\n";
        }
        saveFile(fileName, fileContent);
        saveJson(firstLetter);
    }


    public static void saveJson(Character input) throws IOException { //create save method
        JsonSerializer s = new JsonSerializer(); //new object
        String json = s.include("*").serialize(countryMap.get(input)); //serialize into string -- (include("*") includes array list)

        File f = new File(input +  "_country.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }
    static void saveFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();

    }
    static void readFile() throws FileNotFoundException {
        ArrayList<Country> allCountries = new ArrayList<>();

        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine(); // grab a line and read it;
            String[] columns = line.split("\\|"); //to split 3 parts. escape escape character \\.gives array with 3 things
            Country country = new Country(columns[1], columns[0]); //convert string to int
            allCountries.add(country);
        }


        for (Country c : allCountries) {
            char firstLetter = c.name.charAt(0);
            countryMap.put(firstLetter, new ArrayList<Country>());
        }

        for (Country c : allCountries) {
            char firstLetter = c.name.charAt(0);
            countryMap.get(firstLetter).add(c);
        }
    }



}

