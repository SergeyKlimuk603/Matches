
public class Game {
	
	private int numOfMatches = 0;
	
	private Computer comp;
	
	private int compDel;
	
	private boolean humanWin = false;
	
	
	
	Game(int maxMatches) {
		comp = new Computer(maxMatches);
	}
	
	
	public int getNumOfMatches() {
		
		return numOfMatches;
		
	}
	
	public void setNumOfMatches(int numOfMatches) {
		
		this.numOfMatches = numOfMatches;
		
		
	}
	
	//void removeMatches(int matchesDel) {
	//	numOfMatches -= matchesDel;
	//	if(numOfMatches < 0){
			//numOfMatches = -1;
		//}
		
	//}
	
	String getResult(int matchesDel) {
		System.out.println("вызываем getResult numOfMatches = " + numOfMatches);
		numOfMatches -= matchesDel;
		System.out.println("Человек удалил " + matchesDel + " спички numOfMatches = " + numOfMatches);
		if(numOfMatches <= 0){
			numOfMatches = 0;
			humanWin = true;
			
			comp.corMemory(humanWin);
			comp.saveMemory();
			return "humanWin";
		}
		compDel = comp.movie(numOfMatches);
		numOfMatches -= compDel;
		if(numOfMatches <= 0){
			numOfMatches = 0;
			humanWin = false;
			
			comp.corMemory(humanWin);
			comp.saveMemory();
			
			return "compWin";
		} else {
			return "gameContinue";
		}
		
		
	}
	
	int getCompDel() {
		return compDel;
	}
	
	
	

}
