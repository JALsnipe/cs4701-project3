import java.util.List;


public class State {
	
	char[][] data;
	private State parent;
	private List<State> children;
	char parentMove;
	private double heuristic;
	double alpha;
	double beta;
	
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
	
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	
	public double getAlpha() {
		return alpha;
	}
	
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	public double getBeta() {
		return beta;
	}
	
	public void setBeta(double beta) {
		this.beta = beta;
	}

}
