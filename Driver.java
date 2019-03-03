public class Driver{

  public static void main(String[] args){
    String filename=args[0];
    try{
      Maze f= new Maze(filename);
      f.solve();
      System.out.println(toString(f));
    }
    catch(FileNotFoundException e){
      System.out.println("File not found: " + filename);
    }

  }




}
