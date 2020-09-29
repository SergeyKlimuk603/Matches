import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Computer {
	
	private int memory[][];
	private final int depthOfMemory = 10;
	private final int correctMemory[];
	private int maxMatches;
	
	Random rand = new Random();
	
	private int compDel;
	
	Computer(int maxMatches){
		this.maxMatches = maxMatches;
		memory = new int[maxMatches][depthOfMemory];
		correctMemory = new int[maxMatches];
		createMemory();
		//saveMemory();
		
	}
	
	void createMemory() {
		
		try{
			FileReader fileReader = new FileReader("memory.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			char ch;
			for(int i = 0; i < maxMatches; i++){
				for(int j = 0; j < depthOfMemory; j++){
					ch = (char) reader.read();
					if(ch != '1' && ch != '2'){
						ch = '1';
					}
					memory[i][j] = Character.getNumericValue(ch);
				}
				reader.read();	
			}
			reader.close();
			
		} catch(IOException ioe) {
			System.out.println("Ничего не вышло, создаем новую память");
			for(int i = 0; i < maxMatches; i++){
				for(int j = 0; j < depthOfMemory; j++){
					memory[i][j] = 1;
				}
			System.out.println(" ");
			}
		}
		
		resetCorrectMemory();
	}
	
	void resetCorrectMemory() {
		for(int j = 0; j < maxMatches; j++) {
			correctMemory[j] = -1;
		}
	}
	
	void saveMemory() {
		
		try{
			
			FileWriter fileWriter = new FileWriter("memory.txt");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for(int i = 0; i < maxMatches; i++){
				for(int j = 0; j < depthOfMemory; j++){
					writer.write("" + memory[i][j]);
				}
				writer.write("\n");
			}
			writer.close();
			
		} catch(IOException ioe) {
			System.out.println("Сохранить память не удалось");
		}
		
	}
	
	int movie(int numOfMatches) {
		
		int depth = rand.nextInt(depthOfMemory);
		System.out.println("depth = " + depth);
		correctMemory[numOfMatches - 1] = depth;
		compDel = memory[numOfMatches - 1][depth];
		//numOfMatches -= compDel;
		return compDel;
	
	}
	
	
	
	
	void corMemory(boolean humanWin) {
		
		if(humanWin == true){
			for(int i = 0; i < maxMatches; i++){
				if(correctMemory[i] != -1){
					if(memory[i][correctMemory[i]] < 2){
						memory[i][correctMemory[i]] += 1;
					} else {
						memory[i][correctMemory[i]] = 1;
					}
				}
				System.out.println("" + correctMemory[i]);
			}
		saveMemory();
		
		}
		
		
		if(humanWin == false){
			for(int i = 0; i < maxMatches; i++){
				if(correctMemory[i] != -1){
					//if(memory[i][correctMemory[i]] < 2){
					//	memory[i][correctMemory[i]] += 1;
					//} else {
					//	memory[i][correctMemory[i]] = 1;
					//}
					int cellValue = memory[i][correctMemory[i]];
					int count = 0;
					for(int j = 0; j < depthOfMemory; j++){
						if(memory[i][j] != cellValue){
							memory[i][j] = cellValue;
							count++;
						}
						
						if(count > 1){
							break;
						}
						
					}
				}
				System.out.println("" + correctMemory[i]);
			}
		saveMemory();
			
		}
		resetCorrectMemory();
	}
	
	
	
	

}
