import java.util.List;


public class State {
	
	char[][] data;
	private State parent;
	private List<State> children;
	char parentMove;
	private int heuristic;
	
	public char[][] getData() {
		return data;
	}
	public void setData(char[][] data) {
		this.data = data;
	}
	
	public State getParent() {
		return parent;
	}
	public void setParent(State parent) {
		this.parent = parent;
	}
	
	public List<State> getChildren() {
		return children;
	}
	public void addChild(State child) {
		this.children.add(child);
	}
	
	public int getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
	

}
