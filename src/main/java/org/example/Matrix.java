package org.example;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    public static void main(String[] args) {
        char[][] matrix = {
                {'>', '-', '-', '-', 'A', '-', '-', '-', '+'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'s', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
                {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'}
        };
        char [][] matrix2={
                {'>', '-', '-', '-', 'A', '-', '@', '-', '+'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'+', '-', 'U', '-', '+', ' ', ' ', ' ', 'C'},
                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                {'s', ' ', ' ', ' ', 'C', '-', '-', '-', '+'}
        };
        walkingRoute(matrix);
        walkingRoute(matrix2);

    }
    public static void walkingRoute(char [][] matrix){
        List<Character> path = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        char direction = 'R';
        char prevdirection = 'R';
        int i = 0, j = 0;
        for (int startRow = 0; startRow < matrix.length; startRow++) {
            for (int startCol = 0; startCol < matrix[startRow].length;startCol++) {
                if(matrix[startRow][startCol] == '>') {
                    i = startRow;
                    j = startCol;
                }
            }

        }
        while(true){
            if(matrix[i][j]=='s'){
                path.add('s');
                break;}
            else if(matrix[i][j]=='>'){
                path.add('@');}
            else if ( matrix[i][j]=='+')
                path.add('+');
            else if(Character.isLetter(matrix[i][j]) && Character.isUpperCase(matrix[i][j])) {
                path.add(matrix[i][j]);
                letters.add(matrix[i][j]);
            }
            else if(matrix[i][j]!=' '){
                if (direction == 'R' || direction == 'L' )
                    path.add('-');
                if (direction == 'U' || direction == 'D' )
                    path.add('|');
            }
            if(Character.isLetter(matrix[i][j]) && Character.isUpperCase(matrix[i][j]) || matrix[i][j]=='+' ) {
                switch (prevdirection) {
                    case 'R':
                        if (j + 1 < matrix[i].length && matrix[i][j + 1] != ' ') {
                            direction = 'R';
                            prevdirection = 'R';
                        }else if (i - 1 >= 0 && matrix[i - 1][j] != ' ') {
                            direction = 'U';
                            prevdirection = 'R';
                        }else if (i + 1 < matrix.length && matrix[i + 1 ][j] != ' ') {
                            direction = 'D';
                            prevdirection = 'R';
                        }
                        break;
                    case 'L':
                        if (j - 1 >= 0 && matrix[i][j - 1] != ' ') {
                            direction = 'L';
                            prevdirection = 'L';
                        }else if (i - 1 >= 0 && matrix[i - 1][j] != ' ') {
                            direction = 'U';
                            prevdirection = 'L';
                        }else if (i + 1 < matrix.length && matrix[i + 1][j] != ' ') {
                            direction = 'D';
                            prevdirection = 'L';
                        }
                        break;
                    case 'D':
                        if (i + 1 < matrix.length  && matrix[i + 1][j] != ' ') {
                            direction = 'D';
                            prevdirection = 'D';
                        }else if (j + 1 < matrix[i].length && matrix[i][j + 1] != ' ') {
                            direction = 'R';
                            prevdirection = 'D';
                        }else if (j - 1 >= 0 && matrix[i][j - 1] != ' ') {
                            direction = 'L';
                            prevdirection = 'D';
                        }
                        break;
                    case 'U':
                        if (i - 1 >= 0 && matrix[i - 1][j] != ' ') {
                            direction = 'U';
                            prevdirection = 'U';
                        }else if (j + 1 < matrix[i].length && matrix[i][j + 1] != ' ') {
                            direction = 'R';
                            prevdirection = 'U';
                        }else if (j - 1 >= 0 && matrix[i][j - 1] != ' ') {
                            direction = 'L';
                            prevdirection = 'U';
                        }
                        break;
                }


            }
            switch (direction) {
                case 'R':
                    if (j < matrix[i].length) {
                        j++;
                        if (matrix[i][j]=='-')
                            prevdirection = 'R';
                    }
                    break;
                case 'L':
                    if (j>0) {
                        j--;
                        if (matrix[i][j]=='-')
                            prevdirection = 'L';
                    }
                    break;
                case 'D':
                    if (i<matrix.length){
                        i++;
                        if (matrix[i][j]=='|')
                            prevdirection = 'D';
                    }
                    break;
                case 'U':
                    if (i>0){
                        i--;
                        if (matrix[i][j]=='|')
                            prevdirection = 'U';
                    }
                    break;
            }
        }
        System.out.println("Path: " + path);
        System.out.println("Letters: " + letters);
    }
}