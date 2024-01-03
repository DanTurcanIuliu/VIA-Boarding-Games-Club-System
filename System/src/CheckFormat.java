import java.util.ArrayList;
public class CheckFormat
{
  public static boolean checkDateFormat(MyDate date)
  {
    if (date.getDay()>31)
      return false;
    if (date.getMonth()>12)
      return false;
    if (date.getMonth() == 1 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 3 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 5 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 7 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 8 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 10 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 12 && (date.getDay()>31 || date.getDay()<1))
      return false;
    if (date.getMonth() == 2 && (date.getDay()>29 || date.getDay()<1) && date.getYear()%4==0)
      return false;
    if (date.getMonth() == 2 && (date.getDay()>28 || date.getDay()<1) && date.getYear()%4!=0)
      return false;
    if (date.getMonth() == 4 && (date.getDay()>30 || date.getDay()<1))
      return false;
    if (date.getMonth() == 6 && (date.getDay()>30 || date.getDay()<1))
      return false;
    if (date.getMonth() == 9 && (date.getDay()>30 || date.getDay()<1))
      return false;
    if (date.getMonth() == 11 && (date.getDay()>30 || date.getDay()<1))
      return false;
    return true;
  }

  public static boolean checkViaIDFormat(int viaID)
  {
    if (viaID>999999 || viaID<100000)
      return false;
    return true;
  }

  public static boolean checkRateFormat(int rate)
  {
    if (rate<1 || rate>5)
      return false;
    return true;
  }

  public static boolean checkDateIntervalFormat(DateInterval dateInterval)
  {
    if (dateInterval.getStart().equals(dateInterval.getEnd()))
      return true;
    if (dateInterval.getStart().isBefore(dateInterval.getEnd()))
      return true;
    return false;
  }
}



