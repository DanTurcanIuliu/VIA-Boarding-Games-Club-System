import java.io.Serializable;
import java.util.ArrayList;

public class Game  implements Serializable
{
  private String name;
  private int numberOfPlayers;
  private String type;
  private String status;
  private ArrayList<Rate> rates;
  private StudentList votes;
  private Student owner;
  private int ownerID;
  private double rank;
  private int numberOfVotes;

  public Game(String name, int numberOfPlayers, String type, Student owner)
  {
    this.name=name;
    this.numberOfPlayers=numberOfPlayers;
    this.type=type;
    this.owner=owner;
    this.rates = new ArrayList<Rate>();
    this.status="Available";
    ownerID=owner.getViaID();
    rank=0.0;
  }

  public Game(String name, int numberOfPlayers, String type)
  {
    this.name=name;
    this.numberOfPlayers=numberOfPlayers;
    this.type=type;
    this.votes = new StudentList();
    this.numberOfVotes=0;
  }


  public void setStatusAvailable()
  {
    this.status="Available";
  }

  public void setStatusBorrowedBy(Student student)
  {
    this.status=("Borrowed by:  " + student.getName());
  }

  public String getName()
  {
    return name;
  }

  public int getNumberOfPlayers()
  {
    return numberOfPlayers;
  }

  public String getStatus()
  {
    return status;
  }

  public Student getOwner()
  {
    return owner;
  }

  public String getType()
  {
    return type;
  }

  public String toString()
  {
    String gameString="";
    gameString=("Name: "+ name+", Number of players: "+numberOfPlayers+", Type: "+type);
    if (owner!=null)
    {
      gameString = (gameString + ", Owner: "+owner);
    }
    if (rates!=null)
    {
      gameString = (gameString + ", Average Rank: "+getAverageRank());
    }
    if (votes!=null)
    {
      gameString = (gameString + ", Number of votes: "+getNumberOfVotes() +"\n");
    }
    if (status!=null)
    {
      gameString = (gameString + ", Status "+getStatus() +"\n");
    }
    return gameString;
  }



  public boolean equals(Object obj)
  {
    if(obj==null || getClass()!=obj.getClass())
    {
      return false;
    }
    if (owner==null)
    {
      Game other = (Game) obj;
      return name.equals(other.name) && numberOfPlayers==other.numberOfPlayers
             && type.equals(other.type) && votes.equals(other.votes);
    }
    else
    {
      Game other = (Game) obj;
      return name.equals(other.name) && numberOfPlayers==other.numberOfPlayers
          && type.equals(other.type) && owner.equals(other.owner) &&
          status.equals(other.status) && rates.equals(other.rates);
    }
  }

  public void addRate(int rate, Student student)
  {
    rates.add(new Rate(student,rate));
    setRank();
  }

  public void removeAllRates()
  {
    rates=new ArrayList<Rate>();
    setRank();
  }


  public double getAverageRank()
  {
    double sum =0;
    for (int i=0; i<rates.size(); i++)
    {
      sum=sum+rates.get(i).getRate();
    }
    setRank();
    return sum/rates.size();
  }

  public void setRank()
  {
    double sum =0;
    for (int i=0; i<rates.size(); i++)
    {
      sum=sum+rates.get(i).getRate();
    }
    rank=sum/rates.size();
  }

  public double getRank()
  {
    return rank;
  }

  public int getOwnerID()
  {
    return ownerID;
  }

  public int getNumberOfRates()
  {
    return rates.size();
  }

  public Rate getRate(int index)
  {
    return rates.get(index);
  }


  public ArrayList<Rate> getRates()
  {
    return rates;
  }


  public void addVote(Student student)
  {
    votes.addStudent(student);
    updateNumberOfVotes();
  }

  public void removeAllVotes()
  {
    votes = new StudentList();
    updateNumberOfVotesToZero();
  }

  public int getNumberOfVotes()
  {
    return votes.getNumberOfStudents();
  }

  public void updateNumberOfVotes()
  {
    numberOfVotes=votes.getNumberOfStudents();
  }

  public void updateNumberOfVotesToZero()
  {
    numberOfVotes=votes.getNumberOfStudents();
  }

  public void setNumberOfVotes(int a)
  {
    numberOfVotes=a;
  }

  public StudentList getVotes()
  {
    return votes;
  }


}
