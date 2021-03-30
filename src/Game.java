
public class Game {
	
	private int numOfMatches;
	
	private Computer comp;
	private int compDel;
	
	private boolean humanWin = false;
	
	public Game(int maxMatches) {
		comp = new Computer(maxMatches);
	}
	
	public String getResult(int matchesDel) {
		numOfMatches -= matchesDel;
		
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
	
	public int getCompDel() {
		return compDel;
	}
	
	public int getNumOfMatches() {	
		return numOfMatches;	
	}
	
	public void setNumOfMatches(int numOfMatches) {	
		this.numOfMatches = numOfMatches;	
	}
}
