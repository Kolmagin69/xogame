package learning.java.game;

public class WinnerController {
    
    public static String checkLineWinner(final String[][] field) {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (("X").equals(field[i][j])) counterX ++;
                if (("O").equals(field[i][j])) counterO ++;
                if (counterX == field.length) return  "X";
                if (counterO == field.length) return "O";
            }
            counterO = 0;
            counterX = 0;
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (("X").equals(field[j][i])) counterX ++;
                if (("O").equals(field[j][i])) counterO ++;
                if (counterX == field.length) return  "X";
                if (counterO == field.length) return "O";
            }
            counterO = 0;
            counterX = 0;
        }
        for (int i = 0; i < field.length; i++) {
            if (("X").equals(field[i][i])) counterX++;
            if (("O").equals(field[i][i])) counterO++;
            if (counterX == field.length) return  "X";
            if (counterO == field.length) return "O";
        }
        counterO = 0;
        counterX = 0;
        int pointX = field.length;
        for (int i = 0; i < field.length; i++) {
            pointX--;
            if (("X").equals(field[pointX][i])) counterX++;
            if (("O").equals(field[pointX][i])) counterO++;
            if (counterX == field.length) return  "X";
            if (counterO == field.length) return "O";
        }

        return  null;
    }



}
