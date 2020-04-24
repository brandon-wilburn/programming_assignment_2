
public class Prisoner {
	Prisoner(String name, int strategy) {
		this.name = name;
		this.strategy = strategy;
		this.score = 0;
		this.lastMove = 0;
		this.removeTag = 0;
		this.addTag = 0;
	}
	public String name;
	public int strategy;
	public int score;
	public int lastMove;
	public int removeTag;
	public int addTag;
	
}
