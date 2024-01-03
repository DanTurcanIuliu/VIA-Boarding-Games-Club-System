import java.io.Serializable;
import java.util.ArrayList;

public class BRList  implements Serializable
{
  private ArrayList<BR> brs;

  public BRList()
  {
    brs = new ArrayList<BR>();
  }

  public void addBR(BR br)
  {
    brs.add(br);
  }

  public void setBR(BR br, int index)
  {
    brs.set(index, br);
  }

  public void removeBR(int index)
  {
    brs.remove(index);
  }

  public BR getBR(int index)
  {
   return brs.get(index);
  }

  public BR getBR(String name)
  {
    for (int i = 0; i<brs.size(); i++)
    {
      if (brs.get(i).getGame().getName().equals(name))
      {
        return brs.get(i);
      }
    }
    return null;
  }

  public void removeBR(String name)
  {
    for (int i = 0; i<brs.size(); i++)
    {
      if (brs.get(i).getGame().getName().equals(name))
      {
        brs.remove(i);
      }
    }
  }

  public String toString()
  {
    String stringArrayList = "";
    for(int i=0; i<brs.size(); i++)
    {
      stringArrayList = stringArrayList + brs.get(i).toString();
    }
    return stringArrayList;
  }

  public int getTotalNumber()
  {
    return brs.size();
  }



  public BRList getAllBRByStudent(int viaId)
  {
    BRList temp = new BRList();
    for(int i=0; i<brs.size(); i++)
    {
      if (brs.get(i).getStudent().getViaID()==viaId)
      {
        temp.addBR(brs.get(i));
      }
    }
    return temp;
  }







}
