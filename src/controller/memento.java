package controller;

public class memento {

	 private int score;
	 
	 public memento(int score)
	 {
		 this.score=score;
	 }

	public int getScore() {
		return score;
	}

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
	 
}