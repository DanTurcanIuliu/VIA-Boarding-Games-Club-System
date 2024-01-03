import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.concurrent.atomic.AtomicInteger;

import static javax.swing.JOptionPane.OK_OPTION;

public class MyFxmlController
{
  //Manage student part**********************************
  @FXML private TextField ViaIdText;
  @FXML private Button addStudentButton;
  @FXML private CheckBox statusGuestBox;
  @FXML private CheckBox statusMemberBox;
  @FXML private TextField studentNameText;
  @FXML private TableColumn<Student,Integer> ViaIDStudentColumn;
  @FXML private TableColumn<Student,String> nameStudentColumn;
  @FXML private TableColumn<Student,String> statusStudentColumn;
  @FXML private TableView<Student> studentTable;
  @FXML private ListView<String> listStudentView;
  @FXML private Button removeStudentButton;
  @FXML private Button editStudentButton;
  @FXML private Button statusGuestButton;
  @FXML private Button statusMemberButton;

  //Manage games part*****************************

  @FXML
  private Button addGameButton;

  @FXML
  private Button addRateGameButton;
  @FXML
  private Button addVoteWishGameButton;

  @FXML
  private Button addWishGameButton;
  @FXML
  private TableView<Game> editCatalagueTable;
  @FXML
  private TableView<Game> editWishGameTable;

  @FXML
  private ListView<String> gameInfoViewTable;
  @FXML
  private TableColumn<Game, String> nameCatalogueColumn;

  @FXML
  private TextField nameGame;
  @FXML
  private TextField numberGame;

  @FXML
  private TextField numberOfPlayersWishGameText;
  @FXML
  private TableColumn<Game, Integer> ownerCatalogueColumn;

  @FXML
  private TextField ownerIdGame;

  @FXML
  private TableColumn<Game, Integer> playersCatalogueColumn;

  @FXML
  private TableColumn<Game, Integer> playersWishGameColumn;

  @FXML
  private TableColumn<Game, Double> rankCatalogueColumn;

  @FXML
  private Button removeGameButton;

  @FXML
  private Button removeRateGaneButton;
  @FXML
  private Button removeVoteWishGameButton;

  @FXML
  private Button removeWishGameButton;
  @FXML
  private TableColumn<Game, String> typeCatalogueColumn;

  @FXML
  private TextField typeGame;
  @FXML
  private TableColumn<Game, String> typeWishGameColumn;

  @FXML
  private TextField typeWishGameText;

  @FXML
  private TableColumn<Game, Integer> voteWishGameColumn;

  @FXML
  private TextField nameWishGameText;

  @FXML
  private TableColumn<Game, String> nameWishGameColumn;



  //Manage Event*****************

  @FXML
  private Button addEventButton;

  @FXML
  private DatePicker eventDatePicker;

  @FXML
  private TableColumn<Event, MyDate> futureEventDateColumn;

  @FXML
  private TableColumn<Event, String> futureEventNameColumn;

  @FXML
  private TableColumn<Event, String> futureEventPlaceColumn;

  @FXML
  private TableView<Event> futureEventTable;

  @FXML
  private ListView<String> futureEventView;
  @FXML
  private TextField nameEventText;
  @FXML
  private TextField placeEventText;
  @FXML
  private TableColumn<Event, MyDate> previousEventDateColumn;

  @FXML
  private TableColumn<Event, String> previousEventNameColumn;

  @FXML
  private TableColumn<Event, String> previousEventPlaceColumn;

  @FXML
  private TableView<Event> previousEventTable;

  @FXML
  private Tab previousEventView;
  @FXML
  private Button removeAttedanceButton;

  @FXML
  private Button removeFutureEventButton;
  @FXML
  private CheckBox typeRegularEventBox;

  @FXML
  private CheckBox typeSpecialEventBox;

  @FXML
  private Button addAttendanceButton;

  @FXML
  private ListView<String> petEventView;

  //Manage Borrows and Reservations part**************************

  @FXML private TextField borrowStudentViaIdText;

  @FXML private TextField borrowGameNameText;

  @FXML private DatePicker borrowDatePicker;

  @FXML private Button addBorrowButton;

  @FXML private TextField reservationStudentViaIdText;

  @FXML private TextField reservationGameNameText;

  @FXML private DatePicker reservationBorrowDatePicker;

  @FXML private DatePicker reservationReturnDatePicker;

  @FXML private Button addReservationButton;

  @FXML private TableView<BR> activeBorrowsTable;

  @FXML private TableColumn<BR,String> activeBorrowsGameNameColumn;
  @FXML private TableColumn<BR,Integer> activeBorrowsViaIdColumn;
  @FXML private TableColumn<BR,DateInterval> activeBorrowsDateInterval;

  @FXML private Button activeBorrowsReturnButton;

  @FXML private TableView<BR> reservationTable;
  @FXML private TableColumn<BR,String> reservationGameNameColumn;
  @FXML private TableColumn<BR,Integer> reservationViaIdColumn;
  @FXML private TableColumn<BR,DateInterval> reservationDateColumn;

  @FXML private Button removeReservationButton;

  @FXML private Button activateReservationButton;

  @FXML private  TableView<BR> unclosedBorrowsTable;

  @FXML private TableColumn<BR,String> unclosedBorrowsGameNameColumn;
  @FXML private TableColumn<BR,Integer> unclosedBorrowsViaIdColumn;
  @FXML private TableColumn<BR,DateInterval> unclosedBorrowsDateColumn;





  public void handleActions(ActionEvent event) throws InvocationTargetException
  {
    AssociationModelManager modelManager=new AssociationModelManager("association.bin","catalogueXML.txt","wishListXML.txt","eventsListXML.txt");

    if(event.getSource() == addStudentButton)
    {
      if(!studentNameText.getText().isEmpty() && !ViaIdText.getText().isEmpty())
      {
        if (statusMemberBox.isSelected() || statusGuestBox.isSelected())
        {
          try
          {
            Integer.parseInt(ViaIdText.getText());

            if (statusMemberBox.isSelected())
            {
              modelManager.addStudent(studentNameText.getText(), Integer.parseInt(ViaIdText.getText()),
                  true);
              initialize();
            }
            if (statusGuestBox.isSelected())
            {
              modelManager.addStudent(studentNameText.getText(), Integer.parseInt(ViaIdText.getText()),
                  false);
              initialize();
            }
          }
            catch (NumberFormatException e)
            {
              JOptionPane.showMessageDialog(null, "VIA ID Must Contain Only Numbers . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
      studentNameText.clear();
      ViaIdText.clear();
      statusMemberBox.setSelected(false);
      statusGuestBox.setSelected(false);
    }



    if(event.getSource() == removeStudentButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF STUDENT " + " \n " + studentTable.getSelectionModel().getSelectedItem().getViaID());
        if(choice==JOptionPane.YES_OPTION)
        {
          modelManager.removeStudent(studentTable.getSelectionModel().getSelectedItem().getViaID());
          initialize();
        }
        else if(choice==JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null, "Deleting was denied. Student was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Student was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Student was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null, "Select a Student to Remove . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }



    if(event.getSource() == editStudentButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm Changing Student NAME:  " + studentTable.getSelectionModel().getSelectedItem().getViaID());
        if(choice==JOptionPane.YES_OPTION)
        {
          String input = "";
          input = JOptionPane.showInputDialog(null, "Enter new student name:", "VIA Board Game Association", JOptionPane.WARNING_MESSAGE);
          if (!input.equals(""))
          {
            modelManager.changeStudentName(studentTable.getSelectionModel().getSelectedItem().getViaID(), input);
            initialize();
          }
          if (input.equals(""))
          {
            JOptionPane.showMessageDialog(null, "Action was closed. Student was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
          }
        }
        else if(choice==JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null, "Changing was denied. Student was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Student was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Student was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null, "Select a Student to Edit . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }



    if (event.getSource()==statusGuestButton)
    {
     try
     {
       int choice = JOptionPane.showConfirmDialog(null,
           "Confirm Changing Student Status to Guest:  " + studentTable.getSelectionModel().getSelectedItem().getViaID());
       if(choice==JOptionPane.YES_OPTION)
       {
         if (modelManager.getStudent(studentTable.getSelectionModel().getSelectedItem().getViaID()).getStatus())
         {
           modelManager.changeStudentStatus(studentTable.getSelectionModel().getSelectedItem().getViaID(), false);
           initialize();
         }
         else
         {
           JOptionPane.showMessageDialog(null, "Student is already a guest. Student was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
         }
       }
       if (choice==JOptionPane.NO_OPTION || choice==JOptionPane.CANCEL_OPTION || choice==JOptionPane.CLOSED_OPTION)
       {
         JOptionPane.showMessageDialog(null, "Action was closed. Student status was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
       }
     }
     catch (NullPointerException e)
     {
       JOptionPane.showMessageDialog(null, "Select a Student to Edit . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
     }
    }



    if (event.getSource()==statusMemberButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm Changing Student Status to Member:  " + studentTable.getSelectionModel().getSelectedItem().getViaID());
        if(choice==JOptionPane.YES_OPTION)
        {
          if (!modelManager.getStudent(studentTable.getSelectionModel().getSelectedItem().getViaID()).getStatus())
          {
            modelManager.changeStudentStatus(studentTable.getSelectionModel().getSelectedItem().getViaID(), true);
            initialize();
          }
          else
          {
            JOptionPane.showMessageDialog(null, "Student is already a member. Student was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
          }
        }
        if (choice==JOptionPane.NO_OPTION || choice==JOptionPane.CANCEL_OPTION || choice==JOptionPane.CLOSED_OPTION)
        {
          JOptionPane.showMessageDialog(null, "Action was closed. Student status was NOT edited", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        }
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null, "Select a Student to Edit . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if(event.getSource() == addEventButton)
    {
      if (!nameEventText.getText().isEmpty() && !placeEventText.getText().isEmpty())
      {
        if (typeRegularEventBox.isSelected() || typeSpecialEventBox.isSelected())
        {
          try
          {
            try
            {
              if (typeRegularEventBox.isSelected())
              {
                LocalDate localDate = eventDatePicker.getValue();
                modelManager.addEvent(nameEventText.getText(), false,
                    placeEventText.getText(), localDate.getDayOfMonth(),
                    localDate.getMonthValue(), localDate.getYear());
                initialize();
              }
              if (typeSpecialEventBox.isSelected())
              {
                LocalDate localDate = eventDatePicker.getValue();
                modelManager.addEvent(nameEventText.getText(), true,
                    placeEventText.getText(), localDate.getDayOfMonth(),
                    localDate.getMonthValue(), localDate.getYear());
                initialize();
              }
            }
            catch (NumberFormatException e)
            {
              JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
            }
          }
          catch (NullPointerException e)
          {
            JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
      nameEventText.clear();
      placeEventText.clear();
      typeRegularEventBox.setSelected(false);
      typeSpecialEventBox.setSelected(false);
      eventDatePicker.setValue(null);
    }

    if (event.getSource() == addGameButton)
    {
      if (!nameGame.getText().isEmpty() && !numberGame.getText().isEmpty() && !typeGame.getText().isEmpty() && !ownerIdGame.getText().isEmpty())
      {
        try
        {
          modelManager.addGame(nameGame.getText(), Integer.parseInt(numberGame.getText()),
              typeGame.getText(), Integer.parseInt(ownerIdGame.getText()));
          initialize();
        }
        catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog(null, "Invalid Format. Invalid Action",
              "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
      nameGame.clear();
      typeGame.clear();
      numberGame.clear();
      ownerIdGame.clear();
    }

    if(event.getSource() == removeGameButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF Game: " + " \n " + editCatalagueTable.getSelectionModel().getSelectedItem().getName());
        if(choice==JOptionPane.YES_OPTION)
        {
          modelManager.removeGame(editCatalagueTable.getSelectionModel().getSelectedItem().getName());
          initialize();
        }
        else if(choice==JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null, "Deleting was denied. Game was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Game was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Game was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null, "Select a Game to Remove . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if(event.getSource() == removeFutureEventButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF Game: " + " \n " + futureEventTable.getSelectionModel().getSelectedItem().getName());
        if(choice==JOptionPane.YES_OPTION)
        {
          modelManager.removeEvent(futureEventTable.getSelectionModel().getSelectedItem().getDate().getDay(), futureEventTable.getSelectionModel().getSelectedItem().getDate().getMonth(), futureEventTable.getSelectionModel().getSelectedItem().getDate().getYear());
          initialize();
        }
        else if(choice==JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null, "Deleting was denied. Event was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Event was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Event was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null, "Select a Event to Remove . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource() == addWishGameButton)
    {
      if (!nameWishGameText.getText().isEmpty() && !numberOfPlayersWishGameText.getText().isEmpty() && !typeWishGameText.getText().isEmpty())
      {
        try
        {
          modelManager.addWishGame(nameWishGameText.getText(), Integer.parseInt(numberOfPlayersWishGameText.getText()), typeWishGameText.getText());
          initialize();
        }
        catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog(null, "Invalid Format. Invalid Action",
              "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Empty Boxes. Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
      nameWishGameText.clear();
      numberOfPlayersWishGameText.clear();
      typeWishGameText.clear();
    }

    if (event.getSource()==addRateGameButton)
    {
      try
      {
        try
        {
          int inputViaId;
          inputViaId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student VIA ID:", "VIA Board Game Association", JOptionPane.WARNING_MESSAGE));
          int inputRate ;
          inputRate = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter rate:", "VIA Board Game Association", JOptionPane.WARNING_MESSAGE));
          modelManager.addRate(inputViaId, editCatalagueTable.getSelectionModel().getSelectedItem().getName(), inputRate);
          initialize();
        }
        catch (NumberFormatException e2)
        {
          JOptionPane.showMessageDialog(null, "Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      catch (NullPointerException e3)
      {
        JOptionPane.showMessageDialog(null, "Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource()==addVoteWishGameButton)
    {
      try
      {
        try
        {
         int inputViaId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student VIA ID:", "VIA Board Game Association", JOptionPane.WARNING_MESSAGE));
         modelManager.addVote(editWishGameTable.getSelectionModel().getSelectedItem().getName(), inputViaId);
         initialize();
        }
        catch (NullPointerException e3)
        {
          JOptionPane.showMessageDialog(null, "Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      catch (NumberFormatException e2)
      {
        JOptionPane.showMessageDialog(null, "Invalid Action or Game was not selected", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource() == removeRateGaneButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF Rate: " + editCatalagueTable.getSelectionModel().getSelectedItem().getName());
        if (choice == JOptionPane.YES_OPTION)
        {
          modelManager.removeAllRates(editCatalagueTable.getSelectionModel().getSelectedItem().getName());
          initialize();
        }
        else if (choice == JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null,
              "Deleting was denied. Rate was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Rate was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Rate was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null,
            "Select a Rate to Remove . Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource() == removeVoteWishGameButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF Rate: " + " \n " + editWishGameTable.getSelectionModel().getSelectedItem().getName());
        if (choice == JOptionPane.YES_OPTION)
        {
          modelManager.removeAllVotes(editWishGameTable.getSelectionModel().getSelectedItem().getName());
          initialize();
        }
        else if (choice == JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null,
              "Deleting was denied. Rate was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Vote was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Vote was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null,
            "Select a Vote to Remove . Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource()==addAttendanceButton)
    {
      try
      {
        try
        {
          int inputViaId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student VIA ID:", "VIA Board Game Association", JOptionPane.WARNING_MESSAGE));
          modelManager.registerAttendance(inputViaId, futureEventTable.getSelectionModel().getSelectedItem().getDate().getDay(), futureEventTable.getSelectionModel().getSelectedItem().getDate().getMonth(), futureEventTable.getSelectionModel().getSelectedItem().getDate().getYear() );
          initialize();
        }
        catch (NullPointerException e3)
        {
          JOptionPane.showMessageDialog(null, "Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      catch (NumberFormatException e2)
      {
        JOptionPane.showMessageDialog(null, "Invalid Action or Game was not selected", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource() == removeAttedanceButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF Attendance: " + " \n " + futureEventTable.getSelectionModel().getSelectedItem().getName());
        if (choice == JOptionPane.YES_OPTION)
        {
          int inputViaId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student VIA ID:", "VIA Board Game Association", JOptionPane.WARNING_MESSAGE));
          modelManager.removeAttendance(inputViaId, futureEventTable.getSelectionModel().getSelectedItem().getDate().getDay(), futureEventTable.getSelectionModel().getSelectedItem().getDate().getMonth(), futureEventTable.getSelectionModel().getSelectedItem().getDate().getYear() );
          initialize();
        }
        else if (choice == JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null,
              "Deleting was denied. Student Attendance was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Student Attendance was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Student Attendance was NOT deleted",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null,
            "Select a Vote to Remove . Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if(event.getSource() == removeWishGameButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm DELETING OF Game: " + " \n " + editWishGameTable.getSelectionModel().getSelectedItem().getName());
        if(choice==JOptionPane.YES_OPTION)
        {
          modelManager.removeWishGame(editWishGameTable.getSelectionModel().getSelectedItem().getName());
          initialize();
        }
        else if(choice==JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null, "Deleting was denied. Game was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Game was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if(choice==JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null, "Action was closed. Game was NOT deleted", "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null, "Select a Game to Remove . Invalid Action", "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (event.getSource() == addBorrowButton)
    {
      if (!borrowStudentViaIdText.getText().isEmpty() && !borrowGameNameText.getText().isEmpty())
      {
        try
        {
          int inputId = Integer.parseInt(borrowStudentViaIdText.getText());

          MyDate currentDate = modelManager.getAssociation().getCurrentDate();
          LocalDate localDate = borrowDatePicker.getValue();
          modelManager.openBorrow(inputId, borrowGameNameText.getText(),
              currentDate.getDay(), currentDate.getMonth(), currentDate.getYear(), localDate.getDayOfMonth(),
              localDate.getMonthValue(), localDate.getYear());
          initialize();

        }
        catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog(null, "Invalid VIA ID Format",
              "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Empty box or Wrong Input Format. Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
      borrowStudentViaIdText.clear();
      borrowGameNameText.clear();
      borrowDatePicker.setValue(null);
    }

    if (event.getSource() == addReservationButton)
    {
      if (!reservationStudentViaIdText.getText().isEmpty() && !reservationGameNameText.getText().isEmpty())
      {
        try
        {
          int inputId = Integer.parseInt(reservationStudentViaIdText.getText());
          LocalDate localDate = reservationBorrowDatePicker.getValue();
          LocalDate localDate1 = reservationReturnDatePicker.getValue();
          modelManager.addReservation(inputId, reservationGameNameText.getText(),
              localDate.getDayOfMonth(),
              localDate.getMonthValue(), localDate.getYear(), localDate1.getDayOfMonth(),
              localDate1.getMonthValue(), localDate1.getYear());
          initialize();
        }
        catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog(null, "Invalid VIA ID Format",
              "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Empty box or Wrong Input Format. Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
      reservationGameNameText.clear();
      reservationStudentViaIdText.clear();
      reservationReturnDatePicker.setValue(null);
      reservationBorrowDatePicker.setValue(null);
    }

    if (event.getSource() == activeBorrowsReturnButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm RETURNING Of Game: " + " \n " + activeBorrowsTable.getSelectionModel().getSelectedItem().getGame().getName());
        if (choice == JOptionPane.YES_OPTION)
        {
          modelManager.closeBorrow(activeBorrowsTable.getSelectionModel().getSelectedItem().getGame().getName());
          initialize();
        }
        else if (choice == JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null,
              "Returning was denied. Game was NOT returned",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Game was NOT returned",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Game was NOT returned",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null,
            "Select a Game to Return . Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }




    if (event.getSource() == removeReservationButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm REMOVING Of Reservation: " + " \n " + reservationTable.getSelectionModel().getSelectedItem().getGame().getName());
        if (choice == JOptionPane.YES_OPTION)
        {
          modelManager.removeReservation(reservationTable.getSelectionModel().getSelectedItem().getStudentId(), reservationTable.getSelectionModel().getSelectedItem().getGameName(),
              reservationTable.getSelectionModel().getSelectedItem().getDateInterval().getStart().getDay(),
              reservationTable.getSelectionModel().getSelectedItem().getDateInterval().getStart().getMonth(),
              reservationTable.getSelectionModel().getSelectedItem().getDateInterval().getStart().getYear(),
              reservationTable.getSelectionModel().getSelectedItem().getDateInterval().getEnd().getDay(),
              reservationTable.getSelectionModel().getSelectedItem().getDateInterval().getEnd().getMonth(),
              reservationTable.getSelectionModel().getSelectedItem().getDateInterval().getEnd().getYear());
          initialize();
        }
        else if (choice == JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null,
              "REMOVING Of Reservation was denied. Reservation was NOT removed",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Reservation was NOT removed",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Reservation was NOT removed",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null,
            "Select a Reservation to Remove . Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }


    if (event.getSource() == activateReservationButton)
    {
      try
      {
        int choice = JOptionPane.showConfirmDialog(null,
            "Confirm REMOVING Of Reservation: " + " \n " + reservationTable.getSelectionModel().getSelectedItem().getGame().getName());
        if (choice == JOptionPane.YES_OPTION)
        {
          modelManager.activateReservation(reservationTable.getSelectionModel().getSelectedItem());
          initialize();
        }
        else if (choice == JOptionPane.NO_OPTION)
          JOptionPane.showMessageDialog(null,
              "REMOVING Of Reservation was denied. Reservation was NOT removed",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CANCEL_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Reservation was NOT removed",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
        else if (choice == JOptionPane.CLOSED_OPTION)
          JOptionPane.showMessageDialog(null,
              "Action was closed. Reservation was NOT removed",
              "VIA Board Game Association", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        JOptionPane.showMessageDialog(null,
            "Select a Reservation to Remove . Invalid Action",
            "VIA Board Game Association", JOptionPane.ERROR_MESSAGE);
      }
    }


  }





  @FXML
  private void handleMemberBox(ActionEvent event)
  {
    if(statusMemberBox.isSelected())
    {
      statusGuestBox.setSelected(false);
    }
  }
  @FXML
  private void handleGuestBox(ActionEvent event)
  {
    if(statusGuestBox.isSelected())
    {
      statusMemberBox.setSelected(false);
    }
  }


  @FXML
  void handleRegularTypeBox(ActionEvent event)
  {
    if (typeRegularEventBox.isSelected())
    {
      typeSpecialEventBox.setSelected(false);
    }
  }

  @FXML
  void handleSpecialTypeBox(ActionEvent event)
  {
    if (typeSpecialEventBox.isSelected())
    {
      typeRegularEventBox.setSelected(false);
    }
  }



  public void initialize()
  {
    AssociationModelManager modelManager=new AssociationModelManager("association.bin","catalogueXML.txt","wishListXML.txt","eventsListXML.txt");
    studentTable.getItems().remove(0, studentTable.getItems().size());
    nameStudentColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
    ViaIDStudentColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("viaID"));
    statusStudentColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("statusText"));
    StudentList studentList=modelManager.getStudentList();
    for (int i = 0; i <studentList.getNumberOfStudents() ;i ++)
    {
      studentTable.getItems().add(studentList.getStudent(i));
    }
    studentTable.getSelectionModel().getSelectedItems().addListener
        ((ListChangeListener<Student>) change ->
        {
          listStudentView.getItems().clear();
          listStudentView.getItems().add(modelManager.getStudentInfo(studentTable.getSelectionModel().getSelectedItem()));
        });
    listStudentView.getItems().clear();

    editCatalagueTable.getItems().remove(0, editCatalagueTable.getItems().size());
    nameCatalogueColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
    playersCatalogueColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("numberOfPlayers"));
    typeCatalogueColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("type"));
    ownerCatalogueColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("ownerID"));
    rankCatalogueColumn.setCellValueFactory(new PropertyValueFactory<Game, Double>("rank"));
    GameList gameList = modelManager.getCatalogue();
    for (int i = 0; i <gameList.getNumberOfGames() ;i ++)
    {
      editCatalagueTable.getItems().add(gameList.getGame(i));
    }
    editCatalagueTable.getSelectionModel().getSelectedItems().addListener
        ((ListChangeListener<Game>) change ->
        {
          gameInfoViewTable.getItems().clear();
          gameInfoViewTable.getItems().add(modelManager.getGameInfo(editCatalagueTable.getSelectionModel().getSelectedItem()));
        });
    gameInfoViewTable.getItems().clear();

    futureEventTable.getItems().remove(0, futureEventTable.getItems().size());
    futureEventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
    futureEventPlaceColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("place"));
    futureEventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, MyDate>("date"));
    EventList eventList = modelManager.getFutureEventList();
    for (int i = 0; i <eventList.getNumberOfEvents() ;i ++)
    {
      futureEventTable.getItems().add(eventList.getEvent(i));
    }
      futureEventTable.getSelectionModel().getSelectedItems()
          .addListener((ListChangeListener<Event>) change -> {
            futureEventView.getItems().clear();
            futureEventView.getItems().add(
                modelManager.getEventInfo(futureEventTable.getSelectionModel().getSelectedItem()));
          });
      futureEventView.getItems().clear();


    editWishGameTable.getItems().remove(0, editWishGameTable.getItems().size());
    nameWishGameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
    playersWishGameColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("numberOfPlayers"));
    typeWishGameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("type"));
    voteWishGameColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("numberOfVotes"));
    GameList gameList1=modelManager.getWishList();
    for (int i = 0; i <gameList1.getNumberOfGames() ;i ++)
    {
      editWishGameTable.getItems().add(gameList1.getGame(i));
    }

    previousEventTable.getItems().remove(0, previousEventTable.getItems().size());
    previousEventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
    previousEventPlaceColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("place"));
    previousEventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, MyDate>("date"));
    EventList eventList1 = modelManager.getPastEventList();
    for (int i = 0; i <eventList1.getNumberOfEvents() ;i++)
    {
      previousEventTable.getItems().add(eventList1.getEvent(i));
    }
    previousEventTable.getSelectionModel().getSelectedItems()
        .addListener((ListChangeListener<Event>) change -> {
          petEventView.getItems().clear();
          petEventView.getItems().add(
              modelManager.getPastEventInfo(previousEventTable.getSelectionModel().getSelectedItem()));
        });
    petEventView.getItems().clear();

    activeBorrowsTable.getItems().remove(0, activeBorrowsTable.getItems().size());
    activeBorrowsGameNameColumn.setCellValueFactory(new PropertyValueFactory<BR, String>("gameName"));
    activeBorrowsViaIdColumn.setCellValueFactory(new PropertyValueFactory<BR, Integer>("studentId"));
    activeBorrowsDateInterval.setCellValueFactory(new PropertyValueFactory<BR, DateInterval>("dateInterval"));
    BRList brList = modelManager.getActiveBorrowList();
    for (int i = 0; i <brList.getTotalNumber();i ++)
    {
      activeBorrowsTable.getItems().add(brList.getBR(i));
    }

    reservationTable.getItems().remove(0, reservationTable.getItems().size());
    reservationGameNameColumn.setCellValueFactory(new PropertyValueFactory<BR, String>("gameName"));
    reservationViaIdColumn.setCellValueFactory(new PropertyValueFactory<BR, Integer>("studentId"));
    reservationDateColumn.setCellValueFactory(new PropertyValueFactory<BR, DateInterval>("dateInterval"));
    BRList brList1 = modelManager.getReservationList();
    for (int i = 0; i <brList1.getTotalNumber();i ++)
    {
      reservationTable.getItems().add(brList1.getBR(i));
    }

    unclosedBorrowsTable.getItems().remove(0, unclosedBorrowsTable.getItems().size());
    unclosedBorrowsGameNameColumn.setCellValueFactory(new PropertyValueFactory<BR, String>("gameName"));
    unclosedBorrowsViaIdColumn.setCellValueFactory(new PropertyValueFactory<BR, Integer>("studentId"));
    unclosedBorrowsDateColumn.setCellValueFactory(new PropertyValueFactory<BR, DateInterval>("dateInterval"));
    BRList brList2 = modelManager.getUnclosedBorrowsList();
    for (int i = 0; i <brList2.getTotalNumber();i ++)
    {
      unclosedBorrowsTable.getItems().add(brList2.getBR(i));
    }




  }



}