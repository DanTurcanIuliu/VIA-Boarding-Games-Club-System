import java.util.ArrayList;

/**
 * A class that contains methods for transcripting objects to their String representation using xml formatting
 * @author Dan Turcan
 */
public class XMLConvertor
{

  /**
   * Converts game object with not null owner attribute to String with xml formatting with all necessary information
   * @param game to be transcripted
   * @return String in xml format
   * @author Dan Turcan
   */
  public static String gameToXML(Game game)
  {
    String xmlFile =
        "<game>\n"
            + "  <name>" + game.getName() + "</name>\n"
            + "  <type>" + game.getType() + "</type>\n"
            + "  <number>" + game.getNumberOfPlayers() + "</number>\n"
            + "  <rank>" + game.getAverageRank() + "</rank>\n"
            + "  <owner>" + game.getOwner().getName() + "</owner>\n"
            + "  <status>" + game.getStatus() + "</status>\n"
            + "</game>\n";
    return xmlFile;
  }

  /**
   * Converts game object with null owner attribute to String with xml formatting with all necessary information
   * @param game to be transcripted
   * @return String in xml format
   * @author Dan Turcan
   */
  public static String wishGameToXML(Game game)
  {
    String xmlFile =
        "<wishGame>\n"
            + "  <name>" + game.getName() + "</name>\n"
            + "  <type>" + game.getType() + "</type>\n"
            + "  <number>" + game.getNumberOfPlayers() + "</number>\n"
            + "  <votes>" + game.getNumberOfVotes() + "</votes>\n"
            + "</wishGame>\n";
    return xmlFile;
  }

  /**
   * Converts event object to String with xml formatting with all necessary information
   * @param event to be transcripted
   * @return String in xml format
   * @author Dan Turcan
   */
  public static String eventToXML(Event event)
  {
    String xmlFile =
        "<event>\n"
            + "  <name>" + event.getName() + "</name>\n"
            + "  <type>" + event.getType() + "</type>\n"
            + "  <place>" + event.getPlace() + "</place>\n"
            + "  <date>" + event.getDate().toString() + "</date>\n"
            + "</event>\n";
    return xmlFile;
  }

  /**
   * Converts GameList object to String with xml formatting with all necessary information
   * for GameList object that contains Game objects that have not null value of the owner attribute of type Student
   * @param games to be transcripted
   * @return String in xml format
   * @author Dan Turcan
   */
  public static String catalogueToXML(GameList games)
  {
    String xmlFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    xmlFile += "<catalogue>\n";
    for (int i = 0; i < games.getNumberOfGames(); i++)
    {
      xmlFile += gameToXML(games.getGame(i));
    }
    xmlFile += "</catalogue>\n";
    return xmlFile;
  }

  /**
   * Converts GameList object to String with xml formatting with all necessary information
   * for GameList object that contains Game objects that have null value of the owner attribute of type Student
   * @param wishGames to be transcripted
   * @return String in xml format
   * @author Dan Turcan
   */
  public static String wishGamesToXML(GameList wishGames)
  {
    String xmlFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    xmlFile += "<wishList>\n";
    for (int i = 0; i < wishGames.getNumberOfGames(); i++)
    {
      xmlFile += wishGameToXML(wishGames.getGame(i));
    }
    xmlFile += "</wishList>\n";
    return xmlFile;
  }

  /**
   * Converts EventList object to String with xml formatting with all necessary information
   * @param events to be transcripted
   * @return String in xml format
   * @author Dan Turcan
   */
  public static String eventsToXML(EventList events)
  {
    String xmlFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    xmlFile += "<eventList>\n";
    for (int i = 0; i < events.getNumberOfEvents(); i++)
    {
      xmlFile += eventToXML(events.getEvent(i));
    }
    xmlFile += "</eventList>\n";
    return xmlFile;
  }

}
