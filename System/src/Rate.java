import java.io.Serializable;

/**
 * A class defining Rate object. It has 2 private attributes rate (int) and student(Student object).
 * @author Dan Turcan
 */
public class Rate  implements Serializable
{

  private int rate;
  private Student student;

  /**
   * 2 arguments constructor for Rate object.
   * @param student value of student attribute Student object
   * @param rate int value of rate attribute
   * @author Dan Turcan
   */
  public Rate(Student student, int rate)
  {
    this.student=student;
    this.rate=rate;
  }

  /**
   * Gets the int value of the (int) rate attribute of Rate object.
   * @return (int) value of the rate attribute of the object
   * @author Dan Turcan
   */
  public int getRate()
  {
    return rate;
  }

  /**
   * Gets the (Student object) value of the student attribute of Rate object.
   * @return the value of student attribute of type Student Object
   * @author Dan Turcan
   */
  public Student getStudent()
  {
    return student;
  }

  /**
   * Returns a string representation of the object easy for a person to read.
   * @return a string representation of the Rate object
   * @author Dan Turcan
   */
  public String toString()
  {
    return (student.getName()+":  "+rate);
  }

}
