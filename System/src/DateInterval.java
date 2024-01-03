import java.io.Serializable;

public class DateInterval  implements Serializable
{
  private MyDate start;
  private MyDate end;

  public DateInterval (MyDate start, MyDate end)
  {
    this.start=start.copy();
    this.end=end.copy();
  }

  public MyDate getStart()
  {
    return start;
  }

  public MyDate getEnd()
  {
    return end;
  }

  public String toString()
  {
    return (start.toString()+" - "+end.toString());
  }

  public DateInterval copy()
  {
    return new DateInterval(start, end);
  }

  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    DateInterval other = (DateInterval) obj;

    return start.equals(other.start) && end.equals(other.end);
  }



}
