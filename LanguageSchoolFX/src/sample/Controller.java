/**
 * Class to control the FX project
 */
package sample;

import data.Classroom;
import data.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    /**
     * Defining three static Classroom
     */
    private static Classroom blueRoom = new Classroom("Blue classroom");
    private static Classroom redRoom = new Classroom("Red classroom");
    private static Classroom yellowRoom = new Classroom("Yellow classroom");

    /**
     * Defining the elements from sample.fxml which we'll need to control the programme
     */
    @FXML
    private Label labelResult;
    @FXML
    private TextField textName;
    @FXML
    private TextField textSurname;
    @FXML
    private TextField textID;
    @FXML
    private ComboBox comboLanguage;
    @FXML
    private ComboBox comboLevel;
    @FXML
    private Button buttonPaid;

    /**
     * Defining three ArrayLists : one of Students, other of Teachers and the last one of Languages
     */
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Language> languages = new ArrayList<>();

    /**
     * Method to load stats of the Teachers from a file
     * @param teachers ArrayList of teachers
     * @return teachers ArrayList of teachers saved on the file
     */
    public static ArrayList<Teacher> loadStatsTeachers(ArrayList<Teacher> teachers)
    {
        try {
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("teachers.txt")));
            String line = inputFile.readLine();
            while(line != null) {
                String[] partsOfLine = line.split(";");
                teachers.add(new Teacher(partsOfLine[0], partsOfLine[1], partsOfLine[2]));
                line = inputFile.readLine();
            }
            inputFile.close();
        }
        catch (IOException fileError) {
            System.out.println("There has been some problems: " + fileError.getMessage());
        }
        return teachers;
    }

    /**
     * Method to load stats of the Teachers from a file
     * @param languages ArrayList of languages
     * @param teachers ArrayList of teachers
     * @return languages ArrayList of languages saved on the file
     */
    public static ArrayList<Language> loadStatsLanguages(ArrayList<Language> languages, ArrayList<Teacher> teachers)
    {
        try{
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("languages.txt")));
            String line = inputFile.readLine();
            while(line != null) {
                String[] partsOfLine = line.split(";");
                Teacher teacher = null;
                Classroom classroom = null;
                for(int i = 0; i < teachers.size(); i++)
                {
                    if(partsOfLine[2].equals(teachers.get(i).getID()))
                        teacher = teachers.get(i);
                }
                switch (partsOfLine[3]) {
                    case "blueRoom":
                        classroom = blueRoom;
                        break;
                    case "redRoom":
                        classroom = redRoom;
                        break;
                    case "yellowRoom":
                        classroom = yellowRoom;
                        break;
                }
                languages.add(new Language(partsOfLine[0], partsOfLine[1], teacher, classroom, partsOfLine[4]));
            line = inputFile.readLine();
            }
            inputFile.close();
        }
        catch(IOException fileError) {
            System.out.println("There has been some problems: " + fileError.getMessage());
        }
        return languages;
    }

    /**
     * Method to search a language from a name and a level
     * @param languages ArrayList of languages
     * @param name A String with the language's name we want to search
     * @param level A String with the language's level we want to search
     * @return language The language we're searching if it exists
     */
    public static Language searchLanguage(ArrayList<Language> languages, String name, String level)
    {
        Language languageFound = null;
        for(int i = 0; i < languages.size(); i++)
        {
            if(languages.get(i).getName().toUpperCase(Locale.ROOT).equals(name.toUpperCase(Locale.ROOT)))
                if(languages.get(i).getLevel().toUpperCase(Locale.ROOT).equals(level.toUpperCase(Locale.ROOT)))
                {
                   languageFound = languages.get(i);
                }
        }
        return languageFound;
    }

    /**
     * Method to load stats of the Students from a file
     * @param students ArrayList of students
     * @param languages ArrayList of languages
     * @return students ArrayList of students saved on the file if the file exists
     */
    public static ArrayList<Student> loadStatsStudents(ArrayList<Student> students, ArrayList<Language> languages) {
        if(! (new File("students.txt").exists()))
        {
            return students;
        }
        try {
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("students.txt")));
            String line = inputFile.readLine();
            while (line != null) {
                String[] partsOfLine = line.split(";");
                Student student = null;
                int numberLanguages = Integer.parseInt(partsOfLine[0]);
                ArrayList<Language> languagesStudent = new ArrayList<>();

                boolean paid;
                if(partsOfLine[partsOfLine.length -1].equals("YES"))
                {
                    paid = true;
                }
                else
                    paid = false;

                for(int l = 4; l < partsOfLine.length - 1; l += 2)
                {
                    for(int i = 0; i < numberLanguages; i++)
                    {
                        Language language = null;
                        if(i == 0)
                        {
                            student = new Student(partsOfLine[1], partsOfLine[2], partsOfLine[3],
                                    searchLanguage(languages, partsOfLine[4], partsOfLine[5]), paid);
                        }
                        else
                        {
                            student.setLanguages(searchLanguage(languages, partsOfLine[l], partsOfLine[l+1]));
                        }
                    }
                }
                students.add(student);
                line = inputFile.readLine();
            }
            inputFile.close();
        }
        catch(IOException fileError){
                    System.out.println("There has been some problems: " + fileError.getMessage());
                }
                return students;
    }

    /**
     * Method to add items to the combo boxes
     * Initialize the teachers', languages' and student's array lists
     * Change the button when the user interacts with it
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboLevel.getItems().addAll("A1", "A2", "B1", "B2");
        comboLanguage.getItems().addAll("English", "French", "Spanish");

        teachers = loadStatsTeachers(teachers);
        languages = loadStatsLanguages(languages, teachers);
        students = loadStatsStudents(students, languages);

        changeButton(buttonPaid);
    }

    /**
     * Method to change the button when the user interacts with it
     * @param button Button to know if the student's has paid the fees
     */
    public static void changeButton(Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(button.getText().equals("YES"))
                {
                    button.setText("NO");
                    button.setStyle("-fx-background-color: #DA0A0A");
                }
                else
                {
                    button.setText("YES");
                    button.setStyle("-fx-background-color: #117A65");
                }
            }
        });
    }

    /**
     * Method to read the button's text and relate it to the student's payment
     * @param button Button to know if the student's has paid the fees
     * @return boolean
     */
    public static boolean getButton(Button button)
    {
        if(button.getText().equals("YES"))
            return true;
        else
            return false;
    }

    /**
     * Method to search if a student is enrolled in a certain language
     * @param students ArrayList of students
     * @param id The id of the student we want to search to register in a language
     * @param language The language we want to know if the student's enrolled in it
     * @return boolean
     */
    public static boolean searchStudentLanguage(ArrayList<Student> students, String id, Language language)
    {
        for(int i = 0; i < students.size(); i++)
        {
            for(int l = 0; l < students.get(i).getLanguage().size(); l++)
            {
                if(students.get(i).getID().toUpperCase(Locale.ROOT).equals(id.toUpperCase(Locale.ROOT)))
                    if(students.get(i).getLanguage().get(l).equals(language))
                        return true;
            }
        }
        return false;
    }

    /**
     * Method to search a student in the students' array list
     * @param students ArrayList of students
     * @param id ID of the student we want to search
     * @return an integer (-1) if it doesn't find the student or its position in the ArrayList
     */
    public static int searchStudent(ArrayList<Student> students, String id)
    {
        for(int i = 0; i < students.size(); i++)
        {
            if(students.get(i).getID().toUpperCase(Locale.ROOT).equals(id.toUpperCase(Locale.ROOT)))
                return i;
        }
        return -1;
    }

    /**
     * Method to register an student and save in a file the students saved in the ArrayList
     * @param actionEvent
     */
    public void register(ActionEvent actionEvent) {
        String name = textName.getText();
        String surname = textSurname.getText();
        String id = textID.getText();
        Language languageStudent = null;

        languageStudent = searchLanguage(
                languages, String.valueOf(comboLanguage.getValue()), String.valueOf(comboLevel.getValue()));

        int posStudentRegistered = searchStudent(students, id);
        if(searchStudentLanguage(students, id, languageStudent))
        {
            labelResult.setStyle("-fx-background-color: #b41111");
            labelResult.setText("The student is already registered in that language");
        }
        else if(posStudentRegistered != -1 && !searchStudentLanguage(students, id, languageStudent))
        {
            students.get(posStudentRegistered).setLanguages(languageStudent);
            students.get(posStudentRegistered).setPaid(getButton(buttonPaid));
            labelResult.setStyle("-fx-background-color: #2874A6");
            labelResult.setText("Student registered in a new language");
        }
        else
        {
            students.add(new Student(name, surname, id, languageStudent, getButton(buttonPaid)));
            labelResult.setStyle("-fx-background-color: #2874A6");
            labelResult.setText("Student registered");
        }
        try {
            PrintWriter printWriter = new PrintWriter("students.txt");
            for(Student s:students)
            {
                printWriter.println(s.toString());
            }
            printWriter.close();
        }
        catch (IOException fileError)
        {
            System.out.println("There has been some problems: " + fileError.getMessage());
        }
    }
}
