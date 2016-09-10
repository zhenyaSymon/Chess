package Config;

import Figures.*;
import Main.Game;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by User on 21.06.2016.
 */
public class Utils {
    public static boolean checkCellExsisting(String x, int y) {

        if (y < 1 || y > 8) {
            System.out.println(Figure.Coordinate.valueOf(x).getIntValue());
            return false;
        }
        if(Game.getFigure(Figure.Coordinate.valueOf(x).getIntValue(), y)==null){
            return  true;
        }


//        if (!Game.turnSide().equals((Game.getFigure(Figure.Coordinate.valueOf(x).getIntValue(), y).getSide()))) { ДЛЯ ГЛУБОКОЙ ПРОВЕРКИ НА ШАХ ЧТО ЛИ
//
//            return false;
//        }
        for (Figure.Coordinate cord : Figure.Coordinate.values()) {
            if (x.equals(cord.name())) {
                return true;

            }
        }
        return false;
    }

    /*public static String parseInput(String move) {
        char[] seq = move.toCharArray();
        if (seq.length != 5) {
            return "Введите ход в соответствии шаблону";
        }

    }*/

    public static Figure parseFigure(String move) {



        if (move.length() != 5) {
            System.out.println("Введите ход в соответствии шаблону");
            return null; /////////////add exception??
        }

        if (Game.turnSide().equals(Game.getFigure(Figure.Coordinate.valueOf(String.valueOf(move.charAt(0))).getIntValue(), Character.getNumericValue(move.charAt(1))).getSide())&&checkCellExsisting(String.valueOf(move.charAt(0)), Character.getNumericValue(move.charAt(1)))) { //добавить обработку

            Figure.Coordinate x = Figure.Coordinate.valueOf(String.valueOf(move.charAt(0)));
       //     System.out.println("начальная пропарсилась"+(x)+" " +Character.getNumericValue(move.charAt(1)));
            return Game.getFigure(x.getIntValue(), Character.getNumericValue(move.charAt(1)));
        }
        return null;
    }

    public static Cell parseCellToMove(String move) {


        if (checkCellExsisting(String.valueOf(move.charAt(3)), Character.getNumericValue(move.charAt(4)))) {
        //    System.out.println("chlen");
            if(Game.getFigure(Figure.Coordinate.valueOf(String.valueOf(move.charAt(3))).getIntValue(), Character.getNumericValue(move.charAt(4)))!=null){

            if (Game.getFigure(Figure.Coordinate.valueOf(String.valueOf(move.charAt(3))).getIntValue(),Character.getNumericValue(move.charAt(4))).getSide().equals(Game.turnSide())) {
                System.out.println("Клетка " + Game.getFigure(Figure.Coordinate.valueOf(String.valueOf(move.charAt(3))).getIntValue(),Character.getNumericValue(move.charAt(4))).getC() + "занята");
                return  null;
            }
            }

       //     System.out.println("конечная пропарсилась"+Figure.Coordinate.valueOf(String.valueOf(move.charAt(3)))+" " +Character.getNumericValue(move.charAt(4)));
            return new Cell(Figure.Coordinate.valueOf(String.valueOf(move.charAt(3))), Character.getNumericValue(move.charAt(4)));
        }
        return null;
    }

    public static Figure kingFounding() {
        int k = 0;
        for (int i = 0; i < Game.getField().length - 1; i++) {
            for (int j = 0; j < Game.getField()[0].length; j++) {
                if (Game.getField()[i][j] instanceof King && Game.getField()[i][j].getSide().equals(Game.turnSide())) {
                    System.out.println(Game.getField()[i][j].getC());
                    return Game.getField()[i][j];
                }
            }
        }
        return null;///???????
    }

    public static boolean checkforCheck(Figure king) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Game.getField()[i][j] == null) { continue;}
                    if ((!Game.getField()[i][j].getSide().equals(Game.turnSide()))) {
                      //  System.out.println(king);
                  //      System.out.println("ПЕРВЫЙ УРОВЕНЬ");
                        if (Game.getField()[i][j].checkMovement(king.getC(), Game.getField())) {
                     //       System.out.println("ВТОРОЙ УРОВЕНЬ");
                            Game.setCheck(1);
                            return true;
                        } else {
                            Game.setCheck(0);
                        }
                    }
                    //return false;

            }
        }

        return false;
    }

  /*  public static boolean CheckTracker(Figure figure) { //переделать!
        if (Game.getCheck() == 1) {
            if (figure instanceof King) {
                return true;
            }
        }
        System.out.println("Вам обьявлен шах, защититесь!");
        return false;
    }*/

    public static boolean Mat() {
        if (checkMovesForCheck(kingFounding())) return false;
        else {
            if (numberOFCheckFigures(kingFounding()) > 1) return true;
            else {
                Figure a = findCheckFigure(kingFounding());
                ArrayList<Cell> criticalCells = a.findCriticalCells(kingFounding().getC());
                criticalCells.add(a.getC());
                for (int f = 0; f < criticalCells.size(); f++) {
                    for (int i = 0; i < Game.getField().length - 1; i++) {
                        for (int j = 0; j < Game.getField()[0].length; j++) {
                            if (String.valueOf(Game.getField()[i][j].getSide()).equals(Game.turnSide())) {
                                if (Game.getField()[i][j].checkMovement(criticalCells.get(f), Game.getField())) {
                                    return false;
                                }
                            }
                        }

                    }
                }
            }
        }
        return true;
    }

    public static Figure findCheckFigure(Figure king) {
        for (int i = 0; i < Game.getField().length - 1; i++) {
            for (int j = 0; j < Game.getField()[0].length; j++) {
                if (!Game.getField()[i][j].getSide().equals(Game.turnSide())) {
                    if (Game.getField()[i][j].checkMovement(king.getC(), Game.getField())) { //решить вопрос с тем, что когда вражеская ходит проверяется на свой шах
                        return Game.getField()[i][j];
                    }
                }
            }
        }
        return null;
    }

    public static int numberOFCheckFigures(Figure king) {     //если больше 1 то не надо проверять на перекрытие,сразу мат после отсутствия ходов короля
        int k = 0;
        for (int i = 0; i < Game.getField().length - 1; i++) {
            for (int j = 0; j < Game.getField()[0].length; j++) {
                if (!Game.getField()[i][j].getSide().equals(Game.turnSide())) {
                    if (Game.getField()[i][j].checkMovement(king.getC(), Game.getField())) {
                        k++;
                    }
                }
            }
        }
        return k;
    }

    public static boolean checkMovesForCheck(Figure king) {
        int k = 0;
        //   int m=0;
        for (int f = 0; f < King.movesForTheKing(king).length; f++) {
            if(King.movesForTheKing(king)[f].getIntx()!=0&&King.movesForTheKing(king)[f].getY()!=0) {
                if (Utils.checkCellExsisting(King.movesForTheKing(king)[f].getX().toString(), King.movesForTheKing(king)[f].getY())) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            System.out.println("che za xyita");
                            if (Game.getField()[i][j]!=null&&!Game.getField()[i][j].getSide().equals(Game.turnSide())) {
                                if (Game.getField()[i][j].checkMovement(King.movesForTheKing(king)[f], Game.getField())) {
                                    k++;
                                }
                            }
                        }
                    }
                    if (k == 0) return true;
                    //m++;
                    k = 0;
                }
            }
        }
        return false;
    }

    public static void printTheBoard(Figure[][] field) {
        String[] a = {"a", "b", "c", "d", "e", "f", "g", "h"};
        if (Game.turnSide().equals("white")) {
            for (int i = 0; i < 8; i++) {
                System.out.print("    " + a[i]);
            }
            System.out.print("\n8 ");
            for (int i = 0; i < 8; i++) {
                if (Game.getField()[0][i] == null) {
                    System.out.print("|___|");
                } else System.out.print(Game.getField()[0][i]);
            }
            for (int i = 1; i < 8; i++) {
                System.out.print("\n" + (8 - i) + " ");
                for (int j = 0; j < 8; j++) {
                    if (Game.getField()[i][j] == null) {
                        System.out.print("|___|");
                    } else System.out.print(Game.getField()[i][j]);
                }
            }
            System.out.println();
            for (int i = 0; i < 8; i++) {
                System.out.print("    " + a[i]);
            }
        } else{
            for (int i = 7; i >=0; i--) {
                System.out.print("    " + a[i]);
            }
            System.out.print("\n1 ");
            for (int i = 7; i >= 0; i--) {
                if (Game.getField()[7][i] == null) {
                    System.out.print("|___|");
                } else System.out.print(Game.getField()[7][i]);

            }
            for (int i = 6; i >=0; i--) {
                System.out.print("\n" + (8 - i) + " ");
                for (int j = 7; j >=0; j--) {
                    if (Game.getField()[i][j] == null) {
                        System.out.print("|___|");
                    } else System.out.print(Game.getField()[i][j]);
                }
            }
            System.out.println();
            for (int i = 7; i >=0; i--) {
                System.out.print("    " + a[i]);
            }
        }


    }
    public static void pushFigures(){
        Game.getField()[0][0] = new Rook(Figure.Side.black,new Cell(Figure.Coordinate.A,8));
        Game.getField()[0][1] = new Horse(Figure.Side.black,new Cell(Figure.Coordinate.B,8));
        Game.getField()[0][2] = new Elephant(Figure.Side.black,new Cell(Figure.Coordinate.C,8));
        Game.getField()[0][3] = new Queen(Figure.Side.black,new Cell(Figure.Coordinate.D,8));
        Game.getField()[0][4] = new King(Figure.Side.black,new Cell(Figure.Coordinate.E,8));
        Game.getField()[0][5] = new Elephant(Figure.Side.black,new Cell(Figure.Coordinate.F,8));
        Game.getField()[0][6] = new Horse(Figure.Side.black,new Cell(Figure.Coordinate.G,8));
        Game.getField()[0][7] = new Rook(Figure.Side.black,new Cell(Figure.Coordinate.H,8));
        Game.getField()[1][0] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.A,7));
        Game.getField()[1][1] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.B,7));
        Game.getField()[1][2] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.C,7));
        Game.getField()[1][3] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.D,7));
        Game.getField()[1][4] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.E,7));
        Game.getField()[1][5] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.F,7));
        Game.getField()[1][6] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.G,7));
        Game.getField()[1][7] = new Pawn(Figure.Side.black,new Cell(Figure.Coordinate.H,7));

        Game.getField()[6][0] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.A,2));
        Game.getField()[6][1] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.B,2));
        Game.getField()[6][2] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.C,2));
        Game.getField()[6][3] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.D,2));
        Game.getField()[6][4] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.E,2));
        Game.getField()[6][5] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.F,2));
        Game.getField()[6][6] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.G,2));
        Game.getField()[6][7] = new Pawn(Figure.Side.white,new Cell(Figure.Coordinate.H,2));

        Game.getField()[7][0] = new Rook(Figure.Side.white,new Cell(Figure.Coordinate.A,1));
        Game.getField()[7][1] = new Horse(Figure.Side.white,new Cell(Figure.Coordinate.B,1));
        Game.getField()[7][2] = new Elephant(Figure.Side.white,new Cell(Figure.Coordinate.C,1));
        Game.getField()[7][3] = new Queen(Figure.Side.white,new Cell(Figure.Coordinate.D,1));
        Game.getField()[7][4] = new King(Figure.Side.white,new Cell(Figure.Coordinate.E,1));
        Game.getField()[7][5] = new Elephant(Figure.Side.white,new Cell(Figure.Coordinate.F,1));
        Game.getField()[7][6] = new Horse(Figure.Side.white,new Cell(Figure.Coordinate.G,1));
        Game.getField()[7][7] = new Rook(Figure.Side.white,new Cell(Figure.Coordinate.G,1));
      //  System.out.println(Game.getField()[7][7].getSide());
    }
}