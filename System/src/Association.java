import javax.swing.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Association implements Serializable
{
  private StudentList studentList;
  private GameList catalogue;
  private GameList wishList;
  private Calendar calendar;
  private MyDate currentDate;
  private EventList futureEventList;
  private EventList pastEventList;
  private BRList reservationList;
  private BRList activeBorrowList;
  private BRList closedBorrowList;
  private BRList unclosedBorrowList;



  public Association()
  {
    studentList = new StudentList();
    catalogue = new GameList();
    wishList = new GameList();
    calendar=Calendar.getInstance();
    calendar.setTime(new Date());
    currentDate=getCurrentDate();
    futureEventList = new EventList();
    pastEventList = new EventList();
    reservationList= new BRList();
    activeBorrowList=new BRList();
    closedBorrowList=new BRList();
    unclosedBorrowList=new BRList();

  }

  public void setUnclosedBorrowList(BRList unclosedBorrowList)
  {
    this.unclosedBorrowList=unclosedBorrowList;
  }
  public StudentList getStudentList() { return studentList; }

  public GameList getCatalogue()
  {
    return catalogue;
  }

  public GameList getWishList()
  {
    return wishList;
  }

  public EventList getFutureEventList()
  {
    return futureEventList;
  }

  public EventList getPastEventList()
  {
    return pastEventList;
  }

  public BRList getActiveBorrowList() { return activeBorrowList; }

  public BRList getReservationList() { return reservationList; }

  public MyDate getCurrentDate() { return new MyDate(getCurrentDay(),getCurrentMonth(),getCurrentYear()); }

  public BRList getUnclosedBorrowList() { return unclosedBorrowList; }

  public int getCurrentDay()
  {
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  public int getCurrentMonth()
  { return calendar.get(Calendar.MONTH)+1; }

  public int getCurrentYear()
  { return calendar.get(Calendar.YEAR); }

  public boolean checkIfIDIsFree(int viaId)
  {
    for (int i=0; i<studentList.getNumberOfStudents(); i++)
    {
      if (studentList.getStudent(i).getViaID()==viaId)
        return false;
    }
    return true;
  }

  public boolean checkIfGameNameFree(String name)
  {
    for (int i=0; i<catalogue.getNumberOfGames(); i++)
    {
      if (catalogue.getGame(i).getName().equals(name))
         return false;
    }
    return true;
  }

  public boolean checkIfWishGameNameFree(String name)
  {
    for (int i=0; i<wishList.getNumberOfGames(); i++)
    {
      if (wishList.getGame(i).getName().equals(name))
        return false;
    }
    return true;
  }

  public boolean checkIfStudentHasRateGame(int viaId, String name)
  {
    if (!checkIfIDIsFree(viaId))
    {
      if (!checkIfGameNameFree(name))
      {
        for (int i=0; i<catalogue.getGameByName(name).getNumberOfRates(); i++)
        {
          if (catalogue.getGameByName(name).getRate(i).getStudent().getViaID()==viaId)
          {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean checkIfStudentHasPlayed(int viaId, String name)
  {
    if (!checkIfIDIsFree(viaId))
    {
      if (!checkIfGameNameFree(name))
      {
        for (int i=0; i<closedBorrowList.getAllBRByStudent(viaId).getTotalNumber(); i++)
        {
          if (closedBorrowList.getAllBRByStudent(viaId).getBR(i).getGame().getName().equals(name))
            return true;
        }
      }
    }
   return false;
  }

  public boolean checkIfDateIsFree(MyDate date)
  {
    for (int i=0; i<futureEventList.getNumberOfEvents(); i++)
    {
      if (futureEventList.getEvent(i).getDate().equals(date))
      {
        return false;
      }
    }
    return true;
  }

  public boolean checkIfDateIsInFuture(MyDate date)
  {
    if (currentDate.equals(date))
    {
      return true;
    }
    return currentDate.isBefore(date);
  }

  public  boolean checkIfStudentHasVoted(String name, int viaId)
  {
        for (int i=0; i<wishList.getGameByName(name).getVotes().getNumberOfStudents(); i++)
        {
          if (wishList.getGameByName(name).getVotes().getStudent(i).getViaID()==viaId)
          {
            return true;
          }

    }
    return false;
  }

  public boolean checkIfStudentAttendanceIsRegistered(int viaId, int day, int month, int year)
  {
    MyDate date = new MyDate(day,  month,  year);
    if (!checkIfDateIsFree(date))
    {
      for (int i =0; i<futureEventList.getEvent(date).getAttendanceList().getNumberOfStudents(); i++)
      {
        if (futureEventList.getEvent(date).getAttendanceList().getStudent(i).getViaID()==viaId)
        {
          return true;
        }
      }
    }
    return false;
  }

  public boolean checkIfIsEventOnDate(MyDate date)
  {
    for (int i=0; i<futureEventList.getNumberOfEvents(); i++)
    {
      if (futureEventList.getEvent(i).getDate().equals(date))
      {
        return true;
      }
    }
    return false;
  }

  public int checkHowManyActiveBorrowsStudentHas(int viaId)
  {
    int a=0;
    for (int i=0; i<activeBorrowList.getTotalNumber(); i++)
    {
      if (activeBorrowList.getBR(i).getStudent().getViaID()==viaId)
      {
        a=a+1;
      }
    }
    return a;
  }

  public boolean checkIfGameIsAvailable(String name, DateInterval dateInterval)
  {
    boolean b1;
    boolean b2;
    for (int i=0; i<activeBorrowList.getTotalNumber(); i++)
    {
      if (activeBorrowList.getBR(i).getGame().getName().equals(name))
      {
        if ((dateInterval.getStart().isBefore(activeBorrowList.getBR(i).getDateInterval().getStart())
            && dateInterval.getEnd().isBefore(activeBorrowList.getBR(i).getDateInterval().getStart()))
            || (activeBorrowList.getBR(i).getDateInterval().getStart().isBefore(dateInterval.getStart())
            && activeBorrowList.getBR(i).getDateInterval().getEnd().isBefore(dateInterval.getStart())))
        {
          b1 = true;
        }
        else
        {
          return false;
        }
      }
    }

    for (int i=0; i<reservationList.getTotalNumber(); i++)
    {
      if (reservationList.getBR(i).getGame().getName().equals(name))
      {
        if ((dateInterval.getStart().isBefore(reservationList.getBR(i).getDateInterval().getStart())
            && dateInterval.getEnd().isBefore(reservationList.getBR(i).getDateInterval().getStart()))
            || (reservationList.getBR(i).getDateInterval().getStart().isBefore(dateInterval.getStart())
            && reservationList.getBR(i).getDateInterval().getEnd().isBefore(dateInterval.getStart())))
        {
          b2 = true;
        }
        else
        {
          return false;
        }
      }
    }
    return true;
  }

  public boolean checkIfGameIsBorrowed(String name)
  {
    for (int i=0; i<activeBorrowList.getTotalNumber(); i++)
      if (activeBorrowList.getBR(i).getGame().getName().equals(name))
      {
        return true;
      }
    return false;
  }

  public boolean checkIfEventIsNotUsed(int day, int month, int year)
  {
    boolean a = true;
    Event event = null;
    for (int i =0; i<futureEventList.getNumberOfEvents(); i++)
    {
      if (futureEventList.getEvent(i).getDate().equals(new MyDate(day, month, year)))
      {
        event = futureEventList.getEvent(i);
      }
    }
    if (event!=null)
    {
      for (int i = 0; i < activeBorrowList.getTotalNumber(); i++)
      {
        if (activeBorrowList.getBR(i).getDateInterval().getStart().equals(event.getDate()) || activeBorrowList.getBR(i)
            .getDateInterval().getEnd().equals(event.getDate()))
        {
          a = false;
        }
      }
      for (int i = 0; i < reservationList.getTotalNumber(); i++)
      {
        if (reservationList.getBR(i).getDateInterval().getStart().equals(event.getDate()) || reservationList.getBR(i)
            .getDateInterval().getEnd().equals(event.getDate()))
        {
          a = false;
        }
      }
    }
    return a;
  }

  public boolean checkIfStudentCouldBeRemoved(int viaId)
  {
    Student student = studentList.getStudentByViaID(viaId);
    if (student!=null)
    {
      for (int i=0; i<catalogue.getNumberOfGames(); i++)
      {
        if (catalogue.getGame(i).getOwner().getViaID()==viaId)
        {
          JOptionPane.showMessageDialog(null, "Student is the owner of a game. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
      for (int i=0; i<reservationList.getTotalNumber(); i++)
      {
        if (reservationList.getBR(i).getStudent().getViaID()==viaId)
        {
          JOptionPane.showMessageDialog(null, "Student has reserved a game. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
      for (int i=0; i<activeBorrowList.getTotalNumber(); i++)
      {
        if (activeBorrowList.getBR(i).getStudent().getViaID()==viaId)
        {
          JOptionPane.showMessageDialog(null, "Student has borrowed a game. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
      for (int i=0; i<futureEventList.getNumberOfEvents(); i++)
      {
        for (int j=0; j<futureEventList.getEvent(i).getAttendanceList().getNumberOfStudents(); j++)
        {
          if (futureEventList.getEvent(i).getAttendanceList().getStudent(j).getViaID()==viaId)
          {
            JOptionPane.showMessageDialog(null, "Student is registered for attendance at a future event. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
            return false;
          }
        }
      }

    }
    return true;
  }

  public boolean checkIfGameIsNotUsed(String name)
  {
    boolean a = true;
    Game game = catalogue.getGameByName(name);
    if (game!=null)
    {
      for (int i = 0; i < activeBorrowList.getTotalNumber(); i++)
      {
        if (activeBorrowList.getBR(i).getGame().getName().equals(name))
        {
          a = false;
        }
      }
      for (int i = 0; i < reservationList.getTotalNumber(); i++)
      {
        if (reservationList.getBR(i).getGame().getName().equals(name))
        {
          a = false;
        }
      }
    }
    return a;
  }



  public void addStudent(String name, int viaId, boolean status)
  {
    if(checkIfIDIsFree(viaId))
    {
      if (CheckFormat.checkViaIDFormat(viaId))
      {
        studentList.addStudent(new Student(name, viaId, status));
        JOptionPane.showMessageDialog(null, "Student was added",
            "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      else
      {
        JOptionPane.showMessageDialog(null, "VIA ID Must Contain SIX Numbers. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Student With This VIA ID Already Exists. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void changeStudentStatus(int viaId, boolean status)
  {
    if(!checkIfIDIsFree(viaId))
    {
      studentList.getStudentByViaID(viaId).setStatus(status);
    }
  }

  public void changeStudentName(int viaId, String name)
  {
    if(!checkIfIDIsFree(viaId))
    {
      studentList.getStudentByViaID(viaId).setName(name);
      JOptionPane.showMessageDialog(null, "Student's name was changed.", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public void removeStudent(int viaId)
  {
    if(!checkIfIDIsFree(viaId))
    {
      if (checkIfStudentCouldBeRemoved(viaId))
      {
        studentList.removeStudentByID(viaId);
        JOptionPane.showMessageDialog(null, "Student was removed.", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  public void addGame(String name, int number, String type, int id)
  {
    if (checkIfGameNameFree(name))
    {
      if (!checkIfIDIsFree(id))
      {
        if (studentList.getStudentByViaID(id).getStatus())
        {
          catalogue.addGame(new Game(name, number, type, (studentList.getStudentByViaID(id))));
          JOptionPane.showMessageDialog(null, "Game was added",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Owner is not a member. Invalid action",
              "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Invalid VIA Id. Invalid action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Game with same name already exist. Invalid action",
          "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void removeGame(String name)
  {
    if (!checkIfGameNameFree(name))
    {
      if (checkIfGameIsNotUsed(name))
      {
        catalogue.removeGameByName(name);
        JOptionPane.showMessageDialog(null, "Game was removed",
            "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Game is borrowed or reserved. Invalid action","VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void addRate(int viaId, String name, int rate)
  {
    if (!checkIfIDIsFree(viaId))
    {
      if (!checkIfGameNameFree(name))
      {
        if (CheckFormat.checkRateFormat(rate))
        {
          if (checkIfStudentHasPlayed(viaId, name))
          {
            if (!checkIfStudentHasRateGame(viaId, name))
            {
              catalogue.getGameByName(name).addRate(rate, studentList.getStudentByViaID(viaId));
              JOptionPane.showMessageDialog(null,"Rate was added", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
              JOptionPane.showMessageDialog(null,"This student has already rated selected game. Invalid Action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
            }
          }
          else
          {
            JOptionPane.showMessageDialog(null,"This student have not played selected game. Invalid Action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Invalid Rate Format. Invalid Action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Invalid VIA ID. Invalid Action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void removeAllRates(String name)
  {
    if (!checkIfGameNameFree(name))
    {
      catalogue.getGameByName(name).removeAllRates();
      JOptionPane.showMessageDialog(null, "All rates were deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public void addWishGame(String name, int number, String type)
  {
    if (checkIfWishGameNameFree(name))
    {
      wishList.addGame(new Game(name, number, type));
      JOptionPane.showMessageDialog(null,"Wish game was added.", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Wish game with this name already exists. Invalid Action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }


  public void removeWishGame(String name)
  {
    if (!checkIfWishGameNameFree(name))
    {
      wishList.removeGameByName(name);
      JOptionPane.showMessageDialog(null,"Wish game was removed.", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public void addVote(String name, int viaId)
  {
    if (!checkIfWishGameNameFree(name))
    {
      if (!checkIfIDIsFree(viaId))
      {
        if (!checkIfStudentHasVoted(name, viaId))
        {
          wishList.getGameByName(name).addVote(studentList.getStudentByViaID(viaId));
          JOptionPane.showMessageDialog(null,"Vote was added", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
          JOptionPane.showMessageDialog(null,"This student has already voted for selected game. Invalid Action", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Invalid VIA ID. Invalid Action", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void removeAllVotes(String name)
  {
    if (!checkIfWishGameNameFree(name))
    {
      wishList.getGameByName(name).removeAllVotes();
      wishList.getGameByName(name).setNumberOfVotes(0);
      JOptionPane.showMessageDialog(null, "All votes were deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public  void addEvent(String name, boolean type, String place, int day, int month, int year)
  {
    MyDate date = new MyDate(day, month, year);
    if (CheckFormat.checkDateFormat(date))
    {
      if (checkIfDateIsFree(date))
      {
        if (checkIfDateIsInFuture(date))
        {
          futureEventList.addEvent(new Event(name,place, date, type));
          JOptionPane.showMessageDialog(null, "Event was added", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Date is in the past. Invalid Action.", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Date is already used. Invalid Action.", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void removeEvent(int day, int month, int year)
  {
    if (!checkIfDateIsFree(new MyDate(day, month, year)))
    {
      if (checkIfEventIsNotUsed(day, month, year))
      {
        for (int i = 0; i < futureEventList.getNumberOfEvents(); i++)
        {
          if (futureEventList.getEvent(i).getDate()
              .equals(new MyDate(day, month, year)))
          {
            futureEventList.removeEvent(i);
            JOptionPane.showMessageDialog(null, "Event was deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
          }
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Event is used is register and borrow system. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Event is not registered. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void registerAttendance(int viaId, int day, int month, int year )
  {
    if(!checkIfIDIsFree(viaId))
    {
      MyDate date = new MyDate(day, month, year);
      if (!checkIfDateIsFree(date))
      {
          if (!checkIfStudentAttendanceIsRegistered(viaId,  day,  month,  year))
          {
            futureEventList.getEvent(date).registerAttendance(studentList.getStudentByViaID(viaId));
              JOptionPane.showMessageDialog(null,"Student was registered", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Student was already registered. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
          }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Invalid VIA ID. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void removeAttendance(int viaId, int day, int month, int year )
  {
      MyDate date = new MyDate(day, month, year);
      if (!checkIfDateIsFree(date))
      {
        if (!checkIfIDIsFree(viaId))
        {
          if (checkIfStudentAttendanceIsRegistered(viaId,  day,  month,  year))
          {
            futureEventList.getEvent(date).removeAttendance(viaId);
            JOptionPane.showMessageDialog(null,"Student Attendance was deleted.", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Student was not registered. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Game is not registered in the system. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Event is not registered in the system. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
  }

  public void openBorrow(int viaId, String gameName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
  {
    MyDate startDate= new MyDate(startDay,startMonth,startYear);
    MyDate endDate=new MyDate(endDay,endMonth,endYear);
    MyDate currentDate=getCurrentDate();
    DateInterval dateInterval = new DateInterval(startDate,endDate);
    if (!checkIfIDIsFree(viaId))
    {
      if (!checkIfGameNameFree(gameName))
      {
        if (CheckFormat.checkDateIntervalFormat(dateInterval))
        {
          if (currentDate.equals(startDate))
          {
            if (checkIfIsEventOnDate(startDate))
            {
              if (checkIfIsEventOnDate(endDate))
              {
                if(studentList.getStudentByViaID(viaId).getStatus())
                {
                  if (checkIfGameIsAvailable(gameName, dateInterval))
                  {
                    activeBorrowList.addBR(new BR(catalogue.getGameByName(gameName), studentList.getStudentByViaID(viaId),  dateInterval));
                    catalogue.getGameByName(gameName).setStatusBorrowedBy(studentList.getStudentByViaID(viaId));
                    JOptionPane.showMessageDialog(null,"Borrow was added", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
                  }
                  else
                  {
                    JOptionPane.showMessageDialog(null,"Game is not available. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
                  }
                }
                if(!studentList.getStudentByViaID(viaId).getStatus())
                {
                  if (startDate.equals(endDate))
                  {
                    if (checkHowManyActiveBorrowsStudentHas(viaId)<1)
                    {
                      if (checkIfGameIsAvailable(gameName, dateInterval))
                      {
                        activeBorrowList.addBR(new BR(catalogue.getGameByName(gameName), studentList.getStudentByViaID(viaId),  dateInterval));
                        catalogue.getGameByName(gameName).setStatusBorrowedBy(studentList.getStudentByViaID(viaId));
                        JOptionPane.showMessageDialog(null,"Borrow was added", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
                      }
                      else
                      {
                        JOptionPane.showMessageDialog(null,"Game is not available. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
                      }
                    }
                    else
                    {
                      JOptionPane.showMessageDialog(null,"Guests could borrow only one game at the same time. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
                    }
                  }
                  else
                  {
                    JOptionPane.showMessageDialog(null,"Guests are not able to borrow for more than 1 day. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
                  }
                }
              }
              else
              {
                JOptionPane.showMessageDialog(null,"No event on end date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
              }
            }
            else
            {
              JOptionPane.showMessageDialog(null,"No event on start date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
            }
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Borrowing is possible only on current date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Date Interval is not valid. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Game is not registered in the system or wrong name. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Student is not registered in the system or wrong ID. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void closeBorrow(String name)
  {
    if (!checkIfGameNameFree(name))
    {
      if (checkIfGameIsBorrowed(name))
      {
        closedBorrowList.addBR(activeBorrowList.getBR(name));
        activeBorrowList.getBR(name).getGame().setStatusAvailable();
        activeBorrowList.removeBR(name);
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Game is not registered in the system. Invalid action..", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Student is not registered in the system. Invalid action..", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void addReservation(int viaId, String gameName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
  {
    MyDate startDate= new MyDate(startDay,startMonth,startYear);
    MyDate endDate=new MyDate(endDay,endMonth,endYear);
    DateInterval dateInterval = new DateInterval(startDate,endDate);
    if (!checkIfIDIsFree(viaId))
    {
      if (!checkIfGameNameFree(gameName))
      {
        if (CheckFormat.checkDateIntervalFormat(dateInterval))
        {
            if (checkIfIsEventOnDate(startDate))
            {
              if (checkIfIsEventOnDate(endDate))
              {
                if(studentList.getStudentByViaID(viaId).getStatus())
                {
                  if (checkIfGameIsAvailable(gameName, dateInterval))
                  {
                    reservationList.addBR(new BR(catalogue.getGameByName(gameName), studentList.getStudentByViaID(viaId),  dateInterval));
                    JOptionPane.showMessageDialog(null,"Reservation was Added.", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
                  }
                  else
                  {
                    JOptionPane.showMessageDialog(null,"Game is not available. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
                  }
                }
                else
                {
                  JOptionPane.showMessageDialog(null,"Guests are not able to reserve. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
                }
              }
              else
              {
                JOptionPane.showMessageDialog(null,"No event on end date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
              }
            }
            else
            {
              JOptionPane.showMessageDialog(null,"No event on start date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Date Interval is not valid. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Game is not registered in the system or wrong name. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Student is not registered in the system or wrong ID. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void removeReservation(int viaId, String name, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
  {
    if (!checkIfGameNameFree(name))
    {
      MyDate startDate= new MyDate(startDay,startMonth,startYear);
      MyDate endDate=new MyDate(endDay,endMonth,endYear);
      DateInterval dateInterval = new DateInterval(startDate,endDate);
      for (int i=0; i<reservationList.getTotalNumber(); i++)
      {
        if (reservationList.getBR(i).equals(new BR(catalogue.getGameByName(name), studentList.getStudentByViaID(viaId),  dateInterval)))
        {
          reservationList.removeBR(i);
          JOptionPane.showMessageDialog(null,"Reservation was deleted.", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Game is not registered in the system. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void activateReservation(BR br)
  {
    if ((!currentDate.isBefore(br.getDateInterval().getStart())) || br.getDateInterval().getStart().equals(currentDate))
    {
      if (currentDate.isBefore(br.getDateInterval().getEnd()) || br.getDateInterval().getEnd().equals(currentDate))
      {
        if (br.getGame().getStatus().equals("Available"))
        {
          for (int i=0; i<reservationList.getTotalNumber(); i++)
          {
            if (reservationList.getBR(i).equals(br))
            {
              activeBorrowList.addBR(br);
              br.getGame().setStatusBorrowedBy(br.getStudent());
              reservationList.removeBR(i);
              JOptionPane.showMessageDialog(null,"Reservation was activated.", "VIA Board Games Association", JOptionPane.INFORMATION_MESSAGE);
            }
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Game is not available. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"End date is after current date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
      }
    }
     else
    {
      JOptionPane.showMessageDialog(null,"Start date is before current date. Invalid action.", "VIA Board Games Association", JOptionPane.ERROR_MESSAGE);
    }
  }

  public String getEventInfo(Event event)
  {
    if (event!=null)
    {
      String info = "";
      if (futureEventList.getEvent(event.getDate()).getType())
      {
        info = "Special Event \n";
      }
      if (!futureEventList.getEvent(event.getDate()).getType())
      {
        info = "Regular Event \n";
      }
      info = info +"Attendance List: \n";
      for (int i = 0; i < event.getAttendanceList().getNumberOfStudents(); i++)
      {
        info = info + event.getAttendanceList().getStudent(i).getName() + "  "
            + event.getAttendanceList().getStudent(i).getViaID() + "\n";
      }
      return info;
    }
    else
    {
      return "";
    }
  }

  public String getPastEventInfo(Event event)
  {
    if (event!=null && event.getDate()!=null)
    {
      String info = "";
      info = info +"Attendance List: \n";
      for (int i = 0; i < event.getAttendanceList().getNumberOfStudents(); i++)
      {
        info = info + event.getAttendanceList().getStudent(i).getName() + "  "
            + event.getAttendanceList().getStudent(i).getViaID() + "\n";
      }
      return info;
    }
    else
    {
      return "";
    }
  }

  public String getStudentInfo(Student student)
  {
    if (student!=null)
    {
      String info="";
      info = "Active Borrows: \n";
      for (int i=0; i<activeBorrowList.getTotalNumber(); i++)
      {
        if (activeBorrowList.getBR(i).getStudent().getViaID()==student.getViaID())
        {
          info = info +activeBorrowList.getBR(i).getGame().getName()+ "  " + activeBorrowList.getBR(i).getDateInterval().toString()+"\n";
        }
      }
      info = info +"Reservations: \n";
      for (int i=0; i<reservationList.getTotalNumber(); i++)
      {
        if (reservationList.getBR(i).getStudent().getViaID()==student.getViaID())
        {
          info = info +reservationList.getBR(i).getGame().getName()+ "  " + reservationList.getBR(i).getDateInterval().toString()+"\n";
        }
      }
      info = info +"Personal games: \n";
      for (int i=0; i<catalogue.getNumberOfGames(); i++)
      {
        if (catalogue.getGame(i).getOwner().getViaID()==student.getViaID())
        {
          info = info + catalogue.getGame(i).getName()+ "\n";
        }
      }
      info = info +"Borrowing History: \n";
      for (int i=0; i<closedBorrowList.getTotalNumber(); i++)
      {
        if (closedBorrowList.getBR(i).getStudent().getViaID()==student.getViaID())
        {
          info = info +closedBorrowList.getBR(i).getGame().getName()+ "  " + closedBorrowList.getBR(i).getDateInterval().toString()+"\n";
        }
      }
      info = info +"Attendance History: \n";
      for (int i=0; i<pastEventList.getNumberOfEvents(); i++)
      {
        for (int j=0; j<pastEventList.getEvent(i).getAttendanceList().getNumberOfStudents(); i++)
        {
          if (pastEventList.getEvent(i).getAttendanceList().getStudent(j).getViaID()==student.getViaID())
          {
            info = info + pastEventList.getEvent(i).getName()+ "  " +pastEventList.getEvent(i).getDate().toString()+"\n";
          }
        }
      }
      info = info +"Voting History: \n";
      for (int i=0; i< wishList.getNumberOfGames(); i++)
      {
        for (int j = 0; j < wishList.getGame(i).getVotes().getNumberOfStudents(); j++)
        {
          if (wishList.getGame(i).getVotes().getStudent(j).getViaID() == student.getViaID())
          {
            info = info + wishList.getGame(i).getName() + "\n";
          }
        }
      }
      info = info +"Rating History: \n";
      for (int i=0; i< catalogue.getNumberOfGames(); i++)
      {
        for (int j = 0; j < catalogue.getGame(i).getRates().size(); j++)
        {
          if (catalogue.getGame(i).getRates().get(j).getStudent().getViaID()==student.getViaID())
          {
            info = info + catalogue.getGame(i).getName()+ "  " + catalogue.getGame(i).getRates().get(j).getRate() + "\n";
          }
        }
      }
      return info;
    }
    else
    {
      return "";
    }
  }

  public String getGameInfo(Game game)
  {
    if (game != null)
    {
      String info;
      info = catalogue.getGameByName(game.getName()).getStatus() +"\n";
      info = info + "Reservations: \n";
      for (int i =0; i<reservationList.getTotalNumber(); i++)
      {
        if (reservationList.getBR(i).getGame().getName().equals(game.getName()))
        {
          info = info + reservationList.getBR(i).getStudent().getName() + " " + reservationList.getBR(i).getDateInterval().toString() + "\n";
        }
      }
      info = info + "Rates: \n";
      for (int i =0; i<catalogue.getGameByName(game.getName()).getRates().size(); i++)
      {
          info = info + catalogue.getGameByName(game.getName()).getRates().get(i).getStudent().getName()+ " " +catalogue.getGameByName(game.getName()).getRates().get(i).getRate() + "\n";
      }
      info = info + "History of borrowing: \n";
      for (int i =0; i<closedBorrowList.getTotalNumber(); i++)
      {
        if (closedBorrowList.getBR(i).getGame().getName().equals(game.getName()))
        {
          info = info + closedBorrowList.getBR(i).getStudent().getName() + " " + closedBorrowList.getBR(i).getDateInterval().toString() + "\n";
        }
      }
      return info;
    }
    else
    {
      return "";
    }
  }


}
