/**
 * Created by vajrayogini on 2/11/16.
 */
public class Country { //created class Country with 2 strings
    String name;
    String abbreviation;

    public Country(String name, String abbreviation) { //created a constructor
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() { //created getters so could encode output as json
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
