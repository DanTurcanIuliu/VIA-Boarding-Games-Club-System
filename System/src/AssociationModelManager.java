import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class AssociationModelManager implements Serializable
  {
     private String fileName;
     private String xmlGameFileName;
     private  String xmlWishGameFileName;
     private  String xmlEventFileName;



    public AssociationModelManager(String fileName, String xmlGameFileName, String xmlWishGameFile, String xmlEventFile)
    {
      this.fileName = fileName;
      this.xmlGameFileName=xmlGameFileName;
      this.xmlWishGameFileName=xmlWishGameFile;
      this.xmlEventFileName=xmlEventFile;
    }

    public Association getAssociation()
    {
      Association association = new Association();

      try
      {
        association = (Association) MyFileHandler.readFromBinaryFile(fileName);
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
      catch (IOException e)
      {
        System.out.println("IO Error reading file");
      }
      catch (ClassNotFoundException e)
      {
        System.out.println("Class Not Found");
      }
      association = Update.updateSystem(association);
      return association;
    }

    public void saveAssociation(Association association)
    {
      association = Update.updateSystem(association);
      try
      {
        MyFileHandler.writeToBinaryFile(fileName, association);
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
      catch (IOException e)
      {
        System.out.println("IO Error writing to file");
      }
    }

    public void updateCatalogueInXML(String xmlCatalogue)
    {
      try
      {
        MyFileHandler.writeToTextFile(xmlGameFileName, xmlCatalogue );
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
    }

    public void updateWishListInXML(String xmlWishList)
    {
      try
      {
        MyFileHandler.writeToTextFile(xmlWishGameFileName, xmlWishList );
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
    }

    public void updateEventListInXML(String xmlEventList)
    {
      try
      {
        MyFileHandler.writeToTextFile(xmlEventFileName, xmlEventList );
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
    }

    public GameList getCatalogue()
    {
      Association association = getAssociation();
      return association.getCatalogue();
    }

    public GameList getWishList()
    {
      Association association = getAssociation();
      return association.getWishList();
    }

    public BRList getReservationList()
    {
      Association association = getAssociation();
      return association.getReservationList();
    }

    public BRList getActiveBorrowList()
    {
      Association association = getAssociation();
      return association.getActiveBorrowList();
    }


    public StudentList getStudentList()
    {
      Association association = getAssociation();
      return association.getStudentList();
    }


    public EventList getFutureEventList()
    {
      Association association = getAssociation();
      return association.getFutureEventList();
    }

    public EventList getPastEventList()
    {
      Association association = getAssociation();
      return association.getPastEventList();
    }


    public Student getStudent(int viaID)
    {
      Association association = getAssociation();
      return association.getStudentList().getStudentByViaID(viaID);
    }


    public void addStudent(String name, int viaId, boolean status)
    {
      Association association = getAssociation();
      association.addStudent(name, viaId, status);
      association = Update.updateSystem(association);
      saveAssociation(association);
    }

    public void changeStudentStatus(int viaId, boolean status)
    {
      Association association = getAssociation();
      association.changeStudentStatus(viaId, status);
      association = Update.updateSystem(association);
      saveAssociation(association);
    }

    public void changeStudentName(int viaId, String name)
    {
      Association association = getAssociation();
      association.changeStudentName(viaId, name);
      association = Update.updateSystem(association);
      saveAssociation(association);
    }

    public void removeStudent(int viaId)
    {
      Association association = getAssociation();
      association.removeStudent(viaId);
      association = Update.updateSystem(association);
      saveAssociation(association);
    }

    public void addGame(String name, int number, String type, int id)
    {
      Association association = getAssociation();
      association.addGame(name, number, type, id);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void removeEvent(int day, int month, int year)
    {
      Association association = getAssociation();
      association.removeEvent(day,month,year);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.eventsToXML(association.getFutureEventList());
      updateEventListInXML(xml);
      saveAssociation(association);
    }

    public void removeGame(String name)
    {
      Association association = getAssociation();
      association.removeGame(name);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void addRate(int viaId, String name, int rate)
    {
      Association association = getAssociation();
      association.addRate(viaId, name, rate);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void removeAllRates(String name)
    {
      Association association = getAssociation();
      association.removeAllRates(name);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void addWishGame(String name, int number, String type)
    {
      Association association = getAssociation();
      association.addWishGame(name, number, type);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.wishGamesToXML(association.getWishList());
      updateWishListInXML(xml);
      saveAssociation(association);
    }

    public void removeWishGame(String name)
    {
      Association association = getAssociation();
      association.removeWishGame(name);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.wishGamesToXML(association.getWishList());
      updateWishListInXML(xml);
      saveAssociation(association);
    }

    public void addVote(String name, int viaId)
    {
      Association association = getAssociation();
      association.addVote(name, viaId);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.wishGamesToXML(association.getWishList());
      updateWishListInXML(xml);
      saveAssociation(association);
    }

    public void removeAllVotes(String name)
    {
      Association association = getAssociation();
      association.removeAllVotes(name);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.wishGamesToXML(association.getWishList());
      updateWishListInXML(xml);
      saveAssociation(association);
    }

    public  void addEvent(String name, boolean type, String place, int day, int month, int year)
    {
      Association association = getAssociation();
      association.addEvent(name, type, place, day, month, year);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.eventsToXML(association.getFutureEventList());
      updateEventListInXML(xml);
      saveAssociation(association);
    }

    public void registerAttendance(int viaId, int day, int month, int year )
    {
      Association association = getAssociation();
      association.registerAttendance(viaId, day, month, year);
      association = Update.updateSystem(association);
      saveAssociation(association);
    }

    public void removeAttendance(int viaId, int day, int month, int year )
    {
      Association association = getAssociation();
      association.removeAttendance(viaId,day,month,year);
      association = Update.updateSystem(association);
      saveAssociation(association);
    }

    public void openBorrow(int viaId, String gameName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
    {
      Association association = getAssociation();
      association.openBorrow(viaId, gameName, startDay, startMonth, startYear, endDay, endMonth, endYear);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void closeBorrow(String name)
    {
      Association association = getAssociation();
      association.closeBorrow(name);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void addReservation(int viaId, String gameName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
    {
      Association association = getAssociation();
      association.addReservation(viaId,gameName, startDay, startMonth, startYear, endDay, endMonth, endYear);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void removeReservation(int viaId, String name, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
    {
      Association association = getAssociation();
      association.removeReservation(viaId, name, startDay ,startMonth, startYear, endDay, endMonth, endYear);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public void activateReservation(BR br)
    {
      Association association = getAssociation();
      association.activateReservation(br);
      association = Update.updateSystem(association);
      String xml = XMLConvertor.catalogueToXML(association.getCatalogue());
      updateCatalogueInXML(xml);
      saveAssociation(association);
    }

    public String getEventInfo(Event event)
    {
      Association association = getAssociation();
      return association.getEventInfo(event);
    }

    public String getPastEventInfo(Event event)
    {
      Association association = getAssociation();
      return association.getPastEventInfo(event);
    }

    public String getStudentInfo(Student student)
    {
      Association association = getAssociation();
      return association.getStudentInfo(student);
    }

    public String getGameInfo(Game game)
    {
      Association association = getAssociation();
      return association.getGameInfo(game);
    }

    public BRList getUnclosedBorrowsList()
    {
      Association association = getAssociation();
      return association.getUnclosedBorrowList();
    }


























  }

