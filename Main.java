import java.util.Scanner;
/**
 * This is the main class where all the other classes will be initialised and generated
 */
public class Main {
    public static void main(String[] args){
    	messages();
    	
    	Scanner in = new Scanner(System.in);
    	int difficulty = 0;
    	
    	System.out.println("Please enter your chosen difficulty 0 - 5 (0 is easy 5 is hard): ");
    	difficulty = in.nextInt();
    	in.close();
    	
        GenerateSudoku sudoku = new GenerateSudoku(difficulty);
        sudoku.genGame();
        

    }
    
    public static int messages() {
    	System.out.println("###########################################################");
    	System.out.println("##                   Welcome To Sudoku                   ##");
    	System.out.println("##                   By Patrick Yates                    ##");
    	System.out.println("###########################################################");
    	System.out.println();
    	
    	return 0;
    }
}
