package controller;

import java.util.ArrayList;

public class careTaker {

	private ArrayList<memento> mementos=new ArrayList<memento>();
	public void addMemento(memento m) {
		getMementos().add(m);
	}
	public memento getMemento() {
		return getMementos().get(0);
	}

    /**
     * @return the mementos
     */
    public ArrayList<memento> getMementos() {
        return mementos;
    }

    /**
     * @param mementos the mementos to set
     */
    public void setMementos(ArrayList<memento> mementos) {
        this.mementos = mementos;
    }
	
	
}