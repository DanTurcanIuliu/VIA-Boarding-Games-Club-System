import java.io.Serializable;

/**
 * A class defining Student object. It has 4 private attributes: name(String), viaID(int), status(boolean), statusText(String).
 * @author Manisha Gurung
 */
public class Student  implements Serializable
{
  private String name;
  private int viaID;
  private boolean status;
  private String statusText;

  /**
   * 3 arguments constructor for Student object.
   * statusText attribute value is generated depending on status attribute.
   * @param name the name (String) of the student
   * @param viaID unique student ID (int)
   * @param status takes True if student is member or False if student is guest
   * @author Manisha Gurung
   */
  public Student(String name, int viaID,boolean status)
  {
    this.name=name;
    this.viaID=viaID;
    this.status=status;
    if (status)
      statusText="Member";
    if (!status)
      statusText="Guest";
  }

  /**
   * Sets a new (String) value to the name attribute of the student object.
   * @param name the new String value of name attribute of the object.
   * @author Manisha Gurung
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Sets a new boolean value to the status attribute of the student object.
   * Changes the (String) value of the statusText attribute of the object using setStatusTextMember() and setStatusTextGuest() methods
   * @param status new boolean value for the status attribute of the object
   * @author Manisha Gurung
   */
  public void setStatus(boolean status)
  {
    this.status = status;
    if (status)
      setStatusTextMember();
    if (!status)
      setStatusTextGuest();
  }

  /**
   * Sets the String value of the statusText attribute of the Student object to "Member".
   * @author Manisha Gurung
   */
  public void setStatusTextMember()
  {
    this.statusText="Member";
  }

  /**
   * Sets the String value of the statusText attribute of the Student object to "Guest".
   * @author Manisha Gurung
   */
  public void setStatusTextGuest()
  {
    this.statusText="Guest";
  }

  /**
   * Gets a String with the String value of the statusText attribute of the object.
   * @return String value of statusText attribute
   * @author Manisha Gurung
   */
  public String getStatusText()
  {
    return statusText;
  }

  /**
   * Gets the int value of the viaID attribute of the object.
   * @return int value of viaID attribute
   * @author Manisha Gurung
   */
  public int getViaID()
  {
    return viaID;
  }

  /**
   * Gets the String value of the name attribute of the object.
   * @return String value of the name attribute
   * @author Manisha Gurung
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the boolean value of the status attribute of the object.
   * @return boolean value of the status attribute
   * @author Manisha Gurung
   */
  public boolean getStatus()
  {
    return status;
  }


  /**
   * Returns a string representation of the object easy for a person to read.
   * @return a string representation of the Student object
   * @author Manisha Gurung
   */
  public String toString()
  {
    String studentString="";
    studentString = ("Name: " + name + ", ID: " + viaID +"\n");
    return studentString;
  }

  /**
   * Indicates whether some other object is "equal to" this one, comparing class types and the values of the attributes.
   * @param obj the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   * @author Manisha Gurung
   */
  public boolean equals(Object obj)
  {
    if(obj==null || getClass()!=obj.getClass())
    {
      return false;
    }
    Student other = (Student) obj;
    return name.equals(other.name) && viaID==other.viaID && status==other.status;
  }

  /**
   * Creates and returns a copy of the Student object.
   * @return a clone of this instance
   * @author Manisha Gurung
   */
  public Student copy()
  {
    return new Student(name, viaID, status);
  }
}
