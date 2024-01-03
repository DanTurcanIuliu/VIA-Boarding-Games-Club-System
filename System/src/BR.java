import java.io.Serializable;

public class BR  implements Serializable
{
  private Game game;
  private Student student;
  private DateInterval dateInterval;
  private String gameName;
  private int studentId;

  public BR(Game game, Student student, DateInterval dateInterval)
  {
    this.game=game;
    this.student=student.copy();
    this.dateInterval=dateInterval.copy();
    this.gameName=game.getName();
    this.studentId=student.getViaID();
  }

  public DateInterval getDateInterval()
  {
    return dateInterval;
  }

  public Game getGame()
  {
    return game;
  }

  public Student getStudent()
  {
    return student;
  }

  public String getGameName()
  {
    return gameName;
  }

  public int getStudentId()
  {
    return studentId;
  }

  public String toString()
  {
    return ("Game: "+game.getName()+", Student: "+student.getViaID()+
    ", Date Interval: "+dateInterval+"\n");
  }

  public boolean equals(Object obj)
  {
    if(obj==null || getClass()!=obj.getClass())
    {
      return false;
    }
    BR other = (BR) obj;
    return student.equals(other.student) && game.equals(other.game) && dateInterval.equals(other.dateInterval);
  }

}
