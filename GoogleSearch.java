class Articles {
  private String title;
  public Articles (String title)
  {
    this.title = title;
  }
  public String toString ()
  {
    return title;
  }
}
class Google {
  private Articles[] articles;
  public Google (int numArticles)
  {
    articles = new Articles[numArticles];
  }
  public void addArticles(String title, int index)
  {
    if (index < articles.length)
    {
      articles[index] = new Articles(title);
    }
  }
  public Articles[] searchBar(String keyword)
  {
    int count = 0;
    for (int i = 0; i < articles.length; i++)
    {
      // for (int j = 0; j < articles[i].length(); j++)
      // {
        String word = articles[i].toString();
        String[] words = word.split(" ");
        for (int a = 0; a < words.length; a++)
        {
          if (keyword.equals(words[a]))
          {
            count++;
          }
        }
      //}
    }
    Articles[] matches = new Articles[count];
    int temp = 0;
    for (int i = 0; i < articles.length; i++)
    {
      // for (int j = 0; j < articles[i].length(); j++)
      // {
        String word = articles[i].toString();
        String[] words = word.split(" ");
        for (int a = 0; a < words.length; a++)
        {
          if (keyword.equals(words[a]))
          {
            matches[temp] = articles[i];
            temp++;
          }
        }
    //  }
    }
    return matches;
  }
  public void printAllResults()
  {
    for (int i = 0; i < articles.length; i++)
    {
      StdOut.println(articles.toString());
    }
  }
}
class Test {
  public static void main(String[] args){
        Google g1 = new Google(8);
        g1.addArticles("Coffee hacks", 0);
        g1.addArticles("Colorblindness in dogs", 1);
        g1.addArticles("The color of trees", 2);
        g1.addArticles("How Disney grew into an empire", 3);
        g1.addArticles("Who is the biggest Disney star", 4);
        g1.addArticles("How Disney bought the Avengers", 5);
        g1.addArticles("Nickelodeon vs Disney", 6);
        g1.addArticles("The best President", 7);
        for (Articles i : g1.searchBar("Disney"))
        {
          StdOut.println(i);
        }
  }
}
