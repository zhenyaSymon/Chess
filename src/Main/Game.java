package Main;

import Config.Utils;
import Figures.Figure;

import java.util.Scanner;

/**
 * Created by User on 26.06.2016.
 */
public class Game {
    private static int turn = 0;
    private static int check = 0;
    private static Figure[][] field= new Figure[9][8];


    public static int getCheck() {
        return check;
    }

    public static void setCheck(int check) {
        Game.check = check;
    }

    public static Figure[][] getField() {
        return field;
    }

    public static void changeTheTurn() {
        turn = (turn + 1) % 2;
    }

    public static String turnSide() {
        String side;
        if (turn == 0) {
            side = "white";
        } else side = "black";
        return side;
    }

    public static Figure getFigure(int x, int y) {
      //  System.out.println("координаты фигуры "+(8-y)+" "+(x));
        return field[8-y][x];
    }

    public static void start() {
        Utils.pushFigures();
        System.out.println("Здравствуйте! шаблон ввода хода такой : H1-H2");
        String s;
       while(true){
           Utils.printTheBoard(Game.getField());
            if (Utils.checkforCheck(Utils.kingFounding())) {
                System.out.println("ну не всегда ж в шах заходим");
                if (Utils.Mat()) {
                    System.out.println(turnSide() + "Проиграли");
                    break;
                }
                    System.out.println("Ход делают " + turnSide());
                    System.out.println("Вам обьявлен шах, защититесь!");

            }
            System.out.println("Ход делают " + turnSide());
            while (true) {
                Scanner sc = new Scanner(System.in);
                s = sc.nextLine();
                if(Utils.parseFigure(s).move(Utils.parseCellToMove(s),Game.getField())){
                    Game.changeTheTurn();
                    break;
                }
                else {
                    System.out.println("Ваш ход невозможен введите другой");
                }
//
            }

        }
        Game.changeTheTurn();
        System.out.println("Победители "+turnSide());
    }
}
