package controller;

public class Reciever {

	private Actions actions;
	
    public Reciever(Actions actions) {

    	this.actions=actions;
    }
	 
	public void reset() {
		
		getActions().setScore(0);
		getActions().setLive(3);     
	}

    /**
     * @return the actions
     */
    public Actions getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(Actions actions) {
        this.actions = actions;
    }
}