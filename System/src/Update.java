import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * A class that contains updateSystem method.
 * @author Dan Turcan
 */
public class Update
{
  /**
   * Static Method that Updates Association object by
   * Moving all Event objects that have the date (MyDate) before current date (MyDate) from FutureEventList of the association to the PastEventList
   * Coping all BR objects that have the value of End Date (MyDate) of the DateInterval attribute before current date (MyDate) from ActiveBorrowList to UnClosedBorrowList
   * @param association the Association object to update
   * @return the new value of Association object after updating
   * @author Dan Turcan
   */
  public static Association updateSystem(Association association)
  {
    MyDate date = association.getCurrentDate();
    for (int i =0; i<association.getFutureEventList().getNumberOfEvents(); i++)
    {
      if (association.getFutureEventList().getEvent(i).getDate().isBefore(date))
      {
        association.getPastEventList().addEvent(association.getFutureEventList().getEvent(i));
        association.getFutureEventList().removeEvent(i);
      }
    }
    association.setUnclosedBorrowList(new BRList());
    for (int i=0; i<association.getActiveBorrowList().getTotalNumber(); i++)
    {
      if (association.getActiveBorrowList().getBR(i).getDateInterval().getEnd().isBefore(association.getCurrentDate()))
      {
        association.getUnclosedBorrowList().addBR(association.getActiveBorrowList().getBR(i));
      }
    }

    return association;
  }


}
