import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) throws Exception {
        ArrayList<Country> allCountries = new ArrayList<>();

        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine(); // grab a line and read it;
            String[] columns = line.split("\\|"); //to split 3 parts. escape escape character \\.gives array with 3 things
            Country country = new Country(columns[1],columns[0]); //convert string to int
            allCountries.add(country);
        }

        HashMap<Character, ArrayList<Country>> countryMap = new HashMap<>();

        for (Country c : allCountries) {
            char firstLetter = c.name.charAt(0);
            countryMap.put(firstLetter, new ArrayList<Country>());
        }

        for (Country c : allCountries) {
            char firstLetter = c.name.charAt(0);
            countryMap.get(firstLetter).add(c);
        }

        System.out.println("Please type the first letter of the country.");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();
        if (letter.length() != 1) {
            throw new Exception ("Please type only the first letter.");
        }

        char firstLetter = letter.charAt(0);
        ArrayList<Country> subsetCountries = countryMap.get(firstLetter);
        System.out.println(subsetCountries);
        String fileName = firstLetter + "_Countries.txt";
        String fileContent = new String();
        for (Country c : subsetCountries) {

        }
    }

    static void saveFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}
