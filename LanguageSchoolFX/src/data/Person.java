/**
 * Abstract class to define people
 */
package data;

public abstract class Person {
    protected String name;
    protected String id;
    protected String surname;

    /**
     * Constructor with parameters
     * @param name A String with the person's name
     * @param id A String with the person's id
     * @param surname A String with the person's surname
     * @return It will return a Person instance with the specified attributes
     */
    public Person(String name, String surname, String id) {
        this.name = name;
        this.id = id;
        this.surname = surname;
    }

    /**
     * Returns the person's name
     * @return Person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the person's surname
     * @return Person's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the person's id
     * @return Person's id
     */
    public String getID() { return id; }

    /**
     * Returns the person's information
     * @return Person's name, surname and id
     */
    @Override
    public String toString() {
        return name + ";" + surname + ";" + id;
    }
}
