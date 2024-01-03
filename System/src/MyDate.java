import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class MyDate  implements Serializable
{
  private int day;
  private int month;
  private int year;

  public MyDate(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public MyDate(MyDate obj)
  {
    this.day = obj.day;
    this.month = obj.month;
    this.year = obj.year;
  }

  public void setDate(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public int getDay()
  {
    return day;
  }

  public int getMonth()
  {
    return month;
  }

  public int getYear()
  {
    return year;
  }

  public boolean isBefore(MyDate other)
  {
    if(year < other.year)
    {
      return true;
    }
    else if(year > other.year)
    {
      return false;
    }
    else
    {
      if(month < other.month)
      {
        return true;
      }
      else if(month > other.month)
      {
        return false;
      }
      else
      {
        if(day < other.day)
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
  }

  public String toString()
  {
    String str = "";

    if(day < 10)
    {
      str += "0";
    }
    str += day + "/";

    if(month < 10)
    {
      str += "0";
    }
    str += month + "/";

    str += year;

    return str;
  }

  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }

  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    MyDate other = (MyDate) obj;

    return day == other.day && month == other.month && year == other.year;
  }


}

