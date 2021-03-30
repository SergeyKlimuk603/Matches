import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Computer {
	
	private final int DEPTH_OF_MEMORY = 10;
	
	private int memory[][];
	private final int correctMemory[];
	private int maxMatches;
	private Random rand;
	private int compDel;
	
	public Computer(int maxMatches){
		rand = new Random();
		this.maxMatches = maxMatches;
		memory = new int[maxMatches][DEPTH_OF_MEMORY];
		correctMemory = new int[maxMatches];
		createMemory();	
	}
	
	public void createMemory() {
		try{
			FileReader fileReader = new FileReader("memory.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			char ch;
			for(int i = 0; i < maxMatches; i++){
				for(int j = 0; j < DEPTH_OF_MEMORY; j++){
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
				for(int j = 0; j < DEPTH_OF_MEMORY; j++){
					memory[i][j] = 1;
				}
			System.out.println(" ");
			}
		}
		resetCorrectMemory();
	}
	
	public void resetCorrectMemory() {
		for(int j = 0; j < maxMatches; j++) {
			correctMemory[j] = -1;
		}
	}
	
	public void saveMemory() {	
		try{
			FileWriter fileWriter = new FileWriter("memory.txt");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for(int i = 0; i < maxMatches; i++){
				for(int j = 0; j < DEPTH_OF_MEMORY; j++){
					writer.write("" + memory[i][j]);
				}
				writer.write("\n");
			}
			writer.close();	
		} catch(IOException ioe) {
			System.out.println("Сохранить память не удалось");
		}	
	}
	
	public int movie(int numOfMatches) {
		int depth = rand.nextInt(DEPTH_OF_MEMORY);
		correctMemory[numOfMatches - 1] = depth;
		compDel = memory[numOfMatches - 1][depth];
		return compDel;
	}
	
	public void corMemory(boolean humanWin) {
		
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
					int cellValue = memory[i][correctMemory[i]];
					int count = 0;
					for(int j = 0; j < DEPTH_OF_MEMORY; j++){
						
						if(memory[i][j] != cellValue){
							memory[i][j] = cellValue;
							count++;
						}
						
						if(count > 1){
							break;
						}
					}
				}
			}
		saveMemory();	
		}
		resetCorrectMemory();
	}
}
