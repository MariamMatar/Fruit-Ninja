package controller;

public class originator {

	 private int score;
	 private Actions action;
	public void setScore(int score)
	{
		this.score=score;
	}
	public memento save() {
		return new memento(getScore());
	}
	public void restore(memento m) {
		setScore(m.getScore());
		System.out.println(getScore());
		
	}

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the action
     */
    public Actions getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Actions action) {
        this.action = action;
    }
	
}