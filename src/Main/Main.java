package Main;

import Config.Utils;
import Figures.Cell;
import Figures.Figure;
import Figures.Pawn;

/**
 * Created by User on 20.06.2016.
 */
public class Main {
    public static void main(String[] arg) {
        Figure[][] field = new Figure[9][8];
    //    System.out.println(field.length);
      //  System.out.println(field[0].length);
//        Figure pawn = new Pawn(Figure.Side.black, new Cell(Figure.Coordinate.C, 1));
//        Figure pawn1 = new Pawn(Figure.Side.black, new Cell(Figure.Coordinate.E, 1));
//        field[0][0]=pawn;
//        field[0][1]=pawn1;
//        field[0][2]=pawn;
//        field[2][0]=pawn;
//        for(int i=0;i<2;i++){
//            for (int j=0;j<8;j++){
//               Game.getField()[i][j]=pawn;
//           }
//       }

//        Pawn rwerg =new Pawn(Figure.Side.black, new Cell(Figure.Coordinate.C, 2));
//
//        System.out.println(rwerg instanceof Figure);
//        String[] a = new String[10] ;
//        a[0]=String.valueOf(pawn);
//        a[1]="1";
//        for(int i = 0 ; i< 2;i++){
//            System.out.println(a[i]);
//        }
//        String.valueOf(pawn);


        Game.start();
       // System.out.println(field[0][0]);
      //  System.out.println(" ___");
      //  System.out.println("|_K_|");

       // System.out.println(" ___");
        //  System.out.println(Figure.Coordinate.valueOf("H").getIntValue());
//        for(int i=0;i<field.length-1;i++){
//            for(int j=0;j<field[0].length;j++){
//                if(Game.getField()[i][j]==null){
//                    System.out.println("chlen");
//                }
//                else {
//                    System.out.println(Game.getField()[i][j]);
//                }
//            }
//        }
//        System.out.println("");
     //   Game.changeTheTurn();
        //Utils.printTheBoard(Game.getField());
        //
        //Utils.printTheBoard(Game.getField());
//Utils.pushFigures();
      //  System.out.println(Game.getField()[7][8]);
    }
}
