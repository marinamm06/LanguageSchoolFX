/**
 * Class to define classrooms
 */
package data;

public class Classroom {
    protected String name;

    /**
     * Constructor with parameters
     * @param name A String with the classroom's name
     * @return It will return a Classroom instance with the specified attributes
     */
    public Classroom(String name) {
        this.name = name;
    }

    /**
     * Returns the classroom's name
     * @return Classroom's name
     */
    public String getName() {
        return name;
    }
}
