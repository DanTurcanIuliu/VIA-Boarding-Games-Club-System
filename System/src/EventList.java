import java.io.Serializable;
import java.util.ArrayList;

public class EventList  implements Serializable
{
  private ArrayList<Event> events;

  public EventList()
  {
    events=new ArrayList<Event>();
  }

  public void addEvent(Event event)
  {
    events.add(event);
  }

  public void removeEvent(int index)
  {
    events.remove(index);
  }

  public int getNumberOfEvents()
  {
    return events.size();
  }

  public Event getEvent(int index)
  {
    return events.get(index);
  }

  public Event getEvent(MyDate date)
  {

    for(int i=0; i<events.size(); i++)
    {
      if(events.get(i).getDate().equals(date))
      {
        return events.get(i);
      }
    }
    return null;
  }


  public String toString()
  {
    String stringArrayList = "";
    for(int i=0; i<events.size(); i++)
    {
      stringArrayList = stringArrayList + events.get(i).toString();
    }
    return stringArrayList;
  }





}
