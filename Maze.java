import java.util.*;
import java.io.*;

public class Maze{
    private char[][] maze;
    private boolean animate;//false by default

    public Maze(String filename){
      try{
        animate=false;
        int c=0;
        int r=0;
        int s=0;
        int e=0;
        char ch=' ';
        String currline="";
        File dimensions = new File(filename);
        Scanner go = new Scanner(dimensions);
        while(go.hasNextLine()){

        c=go.nextLine().length();
        r++;
      }

        char[][] n = new char[r][c];
        int l=0;
        File text = new File(filename);
        Scanner inf = new Scanner(text);
        while(inf.hasNextLine()){
            //System.out.println("HI");
            currline = inf.nextLine();
            for(int i=0;i<currline.length();i++){
              ch=currline.charAt(i);
              n[l][i]=ch;
              //System.out.println(ch);
              if(ch=='S'){
                s++;
              }
              if(ch=='E'){
                e++;
              }
            }
              l++;
        }
        maze=n;
        //System.out.println(s);
        //System.out.println(e);
        if(s!=1 || e !=1){
          throw new IllegalStateException();
      }
    }
    catch(FileNotFoundException e){
      System.out.println("File not found: " + filename);
    }
  }


    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){



        System.out.println("\033[2J\033[1;1H");
      }




    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */


    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */


    public String toString(){
      String str="";
      for(int i=0;i<maze.length;i++){
        for(int l=0;l<maze[0].length;l++){
          str+=maze[i][l];
          if(l==maze[0].length-1){
            str+="\n";
          }
        }
      }
      return str;
    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){
      int srow=0;
      int scol=0;

      for(int i=0;i<maze.length;i++){
        for(int l=0;l<maze[0].length;l++){
          if(maze[i][l]=='S'){
            srow=i;
            scol=l;
            maze[i][l]=' ';
          }
        }
      }
      return solve(srow,scol,0);

    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col, int c){ //you can add more parameters since this is private
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }
        int nummoves=-1;
        if(maze[row][col]=='#'){
          return 0;
        }
        if(maze[row][col]=='.'){
          return 0;
        }
        if(maze[row][col]=='@'){
          return 0;
        }
        if(maze[row][col]=='E'){
          nummoves=c;
          return c;
        }
        if(maze[row][col]==' '){
          maze[row][col]='@';
        }
        int forward=solve(row, col + 1, c + 1);
        int backward=solve(row, col - 1, c + 1);
        int up=solve(row + 1, col, c + 1);
        int down=solve(row - 1, col, c + 1);
        if(forward>0){
          return forward;
        }
        if(backward>0){
          return backward;
        }
        if(up>0){
          return up;
        }
        if(down>0){
          return down;
        }
        maze[row][col]='.';

        return nummoves; //so it compiles
    }
    public static void main(String[] args){
      String filename=args[0];
      Maze f= new Maze(filename);
      f.setAnimate(true);
      System.out.println(f.solve());



    }

}
