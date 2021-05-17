/**
 * Class to define teachers
 */
package data;

public class Teacher extends Person{

    /**
     * Constructor with parameters@param name A String with the person's name
     * @param name A String with the the teacher's name
     * @param id A String with the teacher's id
     * @param surname A String with the teacher's surname
     * @return It will return a Teacher instance with the specified attributes
     */
    public Teacher(String name, String surname, String id) {
        super(name, surname, id);
    }
}
