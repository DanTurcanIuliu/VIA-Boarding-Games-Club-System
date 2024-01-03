import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list (array list) of Student objects.
 * @author Manisha Gurung
 */
public class StudentList  implements Serializable
{
  private ArrayList<Student> students;

  /**
   * No-argument constructor initializing the StudentList.
   * @author Manisha Gurung
   */
  public StudentList()
  {
    students = new ArrayList<Student>();
  }

  /**
   * Adds a Student to the list.
   * @param student the student object to add to the list
   * @author Manisha Gurung
   */
  public void addStudent(Student student)
  {
    students.add(student);
  }

  /**
   * Removes a Student from the list by searching for it, using unique viaId attribute.
   * @param viaID the Via Id ( int attribute ) of the student object
   * @author Manisha Gurung
   */
  public void removeStudentByID(int viaID)
  {
    for (int i=0; i<students.size(); i++)
    {
      if (students.get(i).getViaID()==viaID)
      {
        students.remove(i);
      }
    }
  }

  /**
   * Gets a Student object from position index from the list.
   * @param index the position in the list of the Student object
   * @return the Student at index
   * @author Manisha Gurung
   */
  public Student getStudent(int index)
  {
    return students.get(index);
  }

  /**
   * Gets number of Student objects in the list.
   * @return number ( int ) of the Student objects
   * @author Manisha Gurung
   */
  public int getNumberOfStudents()
  {
    return students.size();
  }

  /**
   * Returns a string representation of the object easy for a person to read.
   * @return a string representation of the StudentList object
   * @author Manisha Gurung
   */
  public String toString()
  {
    String stringArrayList = "";
    for(int i=0; i<students.size(); i++)
    {
      stringArrayList = stringArrayList + students.get(i).toString();
    }
    return stringArrayList;
  }

  /**
   * Returns a Student from the list by searching for it, using unique viaId attribute.
   * @param viaId the Via Id ( int attribute ) of the student object
   * @author Manisha Gurung
   */
  public Student getStudentByViaID(int viaId)
  {

    for(int i=0; i<students.size(); i++)
    {
      if(students.get(i).getViaID()==viaId)
      {
        return students.get(i);
      }
    }
    return null;
  }

}
