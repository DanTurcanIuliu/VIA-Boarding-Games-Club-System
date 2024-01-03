import java.io.Serializable;
import java.util.ArrayList;

public class GameList  implements Serializable
{
  private ArrayList<Game> games;

  public GameList()
  {
    games=new ArrayList<Game>();
  }

  public void addGame(Game game)
  {
    games.add(game);
  }

  public Game getGame(int index)
  {
    return games.get(index);
  }

  public int getNumberOfGames()
  {
    return games.size();
  }


  public String toString()
  {
    String stringArrayList = "";
    for(int i=0; i<games.size(); i++)
    {
      stringArrayList = (stringArrayList + games.get(i).toString());
    }
    return stringArrayList;
  }

  public Game getGameByName(String name)
  {
    for(int i=0; i<games.size(); i++)
    {
      if(games.get(i).getName().equals(name))
      {
        return games.get(i);
      }
    }
    return null;
  }

  public void removeGameByName(String name)
  {
    for(int i=0; i<games.size(); i++)
    {
      if(games.get(i).getName().equals(name))
      {
        games.remove(i);
      }
    }
  }






}
