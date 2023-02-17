import java.util.Random;

public class GenerateSudoku {

    private String[] letters;
    private int[] nums;
    private int random;
    private Random rnd = new Random();
    private int difficulty;

    // constructor for the sudoku generator
    public GenerateSudoku(int difficulty){
        this.letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        this.nums = new int[]{1,2,3,4,5,6,7,8,9};
        this.random = 0;
        this.difficulty = 5;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // generate the array of numbers for the game.
    
    public int[][] genNumbers(int[][] numbers, int count){
    	int[] row = this.nums;
    	int tmp;
    	
    	for(int i = 0; i < row.length; i++) {
    		this.random = 1 + rnd.nextInt(8);
    		tmp = row[i];
    		row[i] = row[this.random];
    		row[this.random] = tmp;
    	}
    	
    	for(int j = 0; j < row.length; j++) {
    		numbers[count][j] = row[j];
    	}
    	
    	if(count < 8) {
    		count++;
    		genNumbers(numbers, count);
    	}
    	else {
    		numbers = this.removeNumbers(numbers);
    		return numbers;
    	}
    	
        return numbers;
    }
    
    // remove the doubles from the array of numbers to make the grid playable
    public int[][] removeNumbers(int[][] numbers){
    	int[] tmp = new int[9];
    	boolean notReady = true;
    	int column = 0;
    	int box = 0;
    	int boxCount = 0;
    	
    	while (notReady) {
    		
    		for (int i = 0; i < tmp.length; i++) {
				tmp[i] = numbers[i][column];
			}
    		
    		tmp = removeDuplicates(tmp);
    		
    		// write something here to remove the duplicates in the boxes.
    		
    		for (int i = 0; i < this.difficulty; i++) {
    			numbers[(1 + rnd.nextInt(8))][column] = 0;
			}
    		
    		if (column == numbers.length - 1) {
				notReady = false;
			}
    		
    		column++;
		}
    	
    	return numbers;
    }
    
    public int[] removeDuplicates(int[] arr) {
    	/*System.out.print("Before -- " );
    	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}*/
    	
    	for (int i = 0; i < arr.length; i++) {
    		for (int j = 0; j < arr.length; j++) {
				if (arr[i] == arr[j] && i != j) {
					arr[i] = 0;
					arr[j] = 0;
				}
			}
		}
    	
    	/*System.out.print(" After -- ");
    	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
    	System.out.println();*/
    	
    	return arr;
    }
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    // generate the grid to at first to help wit generating the game board
    
    public String[][] genGrid(int[][] gamegrid) {
        String[][] grid = new String[9][9];
        
        if (gamegrid.length < 1) {
			grid = this.genStrGrid(grid);
		}
        else {
        	grid = this.genNumGrid(grid, gamegrid);
        }
        
        
        return grid;
    }
    
    public String[][] genStrGrid(String[][] grid) {
    	String letter = "";
        String[] letters = this.letters;

        for (int i = 0; i < 9; i++) {
            letter = letters[i];
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5){
                    grid[i][j] = letter + Integer.toString(j+1) + "| ";
                }
                else {
                    grid[i][j] = letter + Integer.toString(j+1) + " ";
                }
            }
        }
        return grid;
    }
    
    public String[][] genNumGrid(String[][] grid, int[][] numbers){
    	int number;
    	for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
            	number = numbers[i][j];
                if (j == 2 || j == 5){
                    grid[i][j] = Integer.toString(number) + "| ";
                }
                else {
                    grid[i][j] = Integer.toString(number) + " ";
                }
            }
        }
    	
        return grid;
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public int[][] genGame(){
    	
    	int[][] numbers = new int[9][9];

        for (int i = 0; i < numbers.length; i++) {
            int[] row = numbers[i];
            for (int j = 0; j < row.length; j++) {
                numbers[i][j] = 0;
            }
        }

        int count = 0; // counter for how many numbers have been set
    	
    	numbers = this.genNumbers(numbers, count);
    	
    	int[] tmp = new int[9];
    	for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers[i].length; j++) {
				tmp[i] = numbers[j][i];
			}
		}
    	
    	// output the generated numbers for testing 
    	/*for(int g = 0; g < numbers.length; g++) {
        	for(int h = 0; h < numbers[g].length; h++) {
        		System.out.print(numbers[g][h]);
        	}
        	System.out.println();
        }*/
    	
    	
    	// get the grid layout and print the grid // 
    	int[][] emptyGrid = new int[0][0];
    	String[][] strGrid = this.genGrid(emptyGrid);
    	System.out.println();
    	
    	String board = "";
        String seperator = "--------+---------+---------";

        System.out.println("");
        for (int i = 0; i < strGrid.length; i++) {
            if (i == 3 || i == 6){
                System.out.println(seperator);
            }

            String[] row = strGrid[i];
            for (int j = 0; j < row.length; j++) {
                board = board + row[j];
            }

            System.out.println(board);
            board = "";
        }
    	
        String[][] numGrid = this.genGrid(numbers);
        seperator = "-----+------+------";
        System.out.println();
        
        System.out.println("");
        for (int i = 0; i < numGrid.length; i++) {
            if (i == 3 || i == 6){
                System.out.println(seperator);
            }

            String[] row = numGrid[i];
            for (int j = 0; j < row.length; j++) {
                board = board + row[j];
            }

            System.out.println(board);
            board = "";
        }
        
    	return numbers;
    }
     
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}

