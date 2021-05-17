/**
 * Class to define languages
 */
package data;

public class Language {
    protected String name;
    protected String level;
    protected Teacher teacher;
    protected Classroom classroom;
    protected String schedule;

    /**
     * Constructor with parameters
     * @param name A String with the the language's name
     * @param level A String with the language's level
     * @param teacher A Teacher with the language's teacher
     * @param classroom A Classroom with the language's classroom
     * @param schedule A String with the language's schedule
     * @return It will return a Person instance with the specified attributes
     */
    public Language(String name, String level, Teacher teacher, Classroom classroom, String schedule) {
        this.name = name;
        this.level = level;
        this.teacher = teacher;
        this.classroom = classroom;
        this.schedule = schedule;
    }

    /**
     * Returns the language's classroom
     * @return Language's classroom
     */
    public Classroom getClassroom() {
        return classroom;
    }

    /**
     * Return's the language's teacher
     * @return Language's teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Return the language's name
     * @return Language's name
     */
    public String getName() { return name; }

    /**
     * Return the language's level
     * @return Language's level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Establishes the language's teacher
     * @param teacher Language's teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Returns the language's information
     * @return Language's name, level, teacher, classroom and schedule
     */
    @Override
    public String toString() {
        return "Language: " + name + " (" + level + ") - Teacher: " + teacher.getName() + " " + teacher.getSurname()
                + " - Classroom: " + classroom.getName() + " - Schedule: " + schedule;
    }
}
