import java.io.Serializable;

public class Event  implements Serializable
{
  private MyDate date;
  private String name;
  private String place;
  private boolean type;
  private StudentList attendanceList;

  public Event(String name, String place, MyDate date, boolean type)
  {
    this.name=name;
    this.place=place;
    this.date=date.copy();
    this.type=type;
    attendanceList=new StudentList();
  }

  public Event(String name, String place, MyDate date, boolean type, StudentList attendanceList)
  {
    this.name=name;
    this.place=place;
    this.date=date.copy();
    this.type=type;
    this.attendanceList=attendanceList;
  }


  public String getName()
  {
    return name;
  }

  public String getPlace()
  {
    return place;
  }

  public MyDate getDate()
  {
    return date;
  }

  public boolean getType()
  {
    return type;
  }

  public StudentList getAttendanceList()
  {
    return attendanceList;
  }

  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Event other = (Event) obj;

    return name.equals(other.name) && place.equals(other.place) && type==other.type
           && date.equals(other.date) && attendanceList.equals(other.attendanceList);
  }

  public String toString()
  {
    String eventType ="";
    if(type)
    {
      eventType="Special Event";
    }
    else
    {
      eventType="Regular Meeting";
    }
    return ("Name: "+name+", Date: "+date+", Type: "+eventType+", Place: "+place+"\n");
  }


  public void registerAttendance(Student student)
  {
      attendanceList.addStudent(student);
  }

  public void removeAttendance(int viaId)
  {
    attendanceList.removeStudentByID(viaId);
  }

  public int getPresence()
  {
    return attendanceList.getNumberOfStudents();
  }


}
