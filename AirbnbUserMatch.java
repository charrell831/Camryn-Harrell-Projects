class Airbnb {
  private String name;
  private String area;
  private double budget;
  
  public Airbnb (String name, String area, double budget)
  {
    this.name = name;
    this.area = area;
    this.budget = budget;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getArea()
  {
    return this.area;
  }
  
  public double getBudget()
  {
    return this.budget;
  }
  
  public String toString()
  {
    return this.name + ", " + this.area + ", " + this.budget;
  }
}
class Accounts {
  private Airbnb[] users;
  
  public Accounts (int numUsers)
  {
    users = new Airbnb[numUsers];
  }
  
  public void addPerson(String name, String area, double budget, int index)
  {
    if (index < users.length)
    {
      users[index] = new Airbnb(name, area, budget);
    }
  }
  
  public Airbnb[] sameAreas(Airbnb n)
  {
    int count = 0;
    for (int i = 0; i < users.length; i++)
    {
      if (users[i].getArea() == n.getArea())
      {
        count++;
      }
    }
    
    Airbnb[] areas = new Airbnb[count];
    int temp = 0;
    for (int i = 0; i < users.length; i++)
    {
      if (users[i].getArea() == n.getArea())
      {
        areas[temp] = users[i];
        temp++;
      }
    }
    return areas;
  }
  
  public Airbnb[] goodBudget(Airbnb n)
  {
    int count = 0;
    for (int i = 0; i < users.length; i++)
    {
      if (users[i].getBudget() <= n.getBudget())
      {
        count++;
      }
    }
    Airbnb[] budgets = new Airbnb[count];
    int temp = 0;
    for (int i = 0; i < users.length; i++)
    {
      if (users[i].getBudget() <= n.getBudget())
      {
        budgets[temp] = users[i];
        temp++;
      }
    }
    return budgets;
  }
  
  public Airbnb[] perfection(Airbnb n)
  {
    int count = 0;
    for (int i = 0; i < users.length; i++)
    {
      if (users[i].getArea() == n.getArea() && users[i].getBudget() <= n.getBudget())
      {
        count++;
      }
    }
    Airbnb[] perfect = new Airbnb[count];
    int temp = 0;
    for (int i = 0; i < users.length; i++)
    {
      if (users[i].getArea() == n.getArea() && users[i].getBudget() <= n.getBudget())
      {
        perfect[temp] = users[i];
        temp++;
      }
    }
    return perfect;
  }
  
  public void printAllUsers()
  {
    for (int i = 0; i < users.length; i++)
    {
      StdOut.println(users[i].toString());
    }
  }
}
class Test {
  public static void main(String[] args){
        Airbnb a1 = new Airbnb("Cam", "New Jersey", 800.0);
        Accounts s1 = new Accounts(6);
        s1.addPerson("Gordon", "New Jersey", 300.0, 0);
        s1.addPerson("Sara", "New Jersey", 900.0, 1);
        s1.addPerson("Timmy", "London", 500.0, 2);
        s1.addPerson("Louis", "New Jersey", 500.0, 3);
        s1.addPerson("Alan", "Los Angelos", 500.0, 4);
        s1.addPerson("Bernie", "New Jersey", 500.0, 5);
        for (Airbnb i : s1.sameAreas(a1))
        {
          StdOut.println(i);
        }
        StdOut.println();
        for (Airbnb i : s1.goodBudget(a1))
        {
          StdOut.println(i);
        }
        StdOut.println();
        for (Airbnb i : s1.perfection(a1))
        {
          StdOut.println(i);
        }
        s1.printAllUsers();
      }
  }
