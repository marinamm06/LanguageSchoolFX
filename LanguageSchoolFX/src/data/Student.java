/**
 * Class to define students
 */
package data;

import java.util.ArrayList;

public class Student extends Person{
    protected boolean paid;

    /**
     * @param languages An ArrayList of Language with the languages in which the student's enrolled
     */
    protected ArrayList<Language> languages = new ArrayList<>();

    /**
     * Constructor with parameters
     * @param name A String with the student's name
     * @param surname A String with the student's surname
     * @param id A String with the student's id
     * @param language A Language with the language in which the student is enrolled
     * @param paid A boolean with which we can know if the student has paid the fees
     * @return It will return a Student instance with the specified attributes
     */
    public Student(String name, String surname, String id, Language language, boolean paid) {
        super(name, surname, id);
        languages.add(language);
        this.paid = paid;
    }

    /**
     * Return's the student's paid attribute
     * @return Student's paid attribute
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Establishes the student's paid attribute
     * @param paid Student's paid attribute
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Return the ArrayList with all languages in which the student's enrolled
     * @return languages
     */
    public ArrayList<Language> getLanguage() { return languages; }

    /**
     * Establishes the language in which the student is enrolled
     * @param language Student's language
     */
    public void setLanguages(Language language) { languages.add(language); }

    /**
     * Returns the student's information
     * @return Student's name, surname, id, languages, payment and number of languages in which the student's enrolled
     */
    @Override
    public String toString() {
        String sentence = getLanguage().size() +";" + super.toString() + ";";
        for(int i = 0; i < getLanguage().size(); i++)
        {
            sentence += getLanguage().get(i).getName() + ";" +
                    getLanguage().get(i).getLevel() + ";";
        }
        if(paid)
        {
            sentence += "YES";
        }
        else
        {
            sentence += "NO";
        }
        return sentence;
    }
}
