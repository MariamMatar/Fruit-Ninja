package controller;

import Model.GameObject;
import Model.IStrategy;
import view.gamescene;
import Model.objectFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import view.Fruits;
import view.Watermelon;
import view.kiwi;



public class Actions implements GameActions{
    private int score;
    private int Live=3;
	private static Actions actions;	
	   private IStrategy strategy;
        private kiwi kiwi;
        private Watermelon watermelon;
        private boolean sliced=false;
         private gamescene gamescene;
         private String name;
         private originator originator;
        private careTaker caretaker;
     	private Scanner s;
    	private String word;
     	
    	private double a;

    	private double bestscore;
        private ArrayList<Double> highScore = new ArrayList<Double>();
    private Invoker invoke = new Invoker();
    private Reciever reciever;

	 
    public static Actions getInstance() {
    	if(getActions()==null) {
        		setActions(new Actions());
        	}
       return getActions();
     }
 
	@Override
	public void ResetGame() {
         ResetOnCommand reset=new ResetOnCommand(getReciever());
   	 getInvoke().setCommand(reset);
   	 getInvoke().pressButton();
	}

	@Override
	public int getScore() {
		return score;
	}
     
	@Override
	public Boolean hasMovedOffScreen() {
     		    return false;
	}

	@Override
	public void slice(boolean isSliced,boolean bonus) {
            
            if(bonus==true){
                updatescore(50);
            }
            else if(isSliced==true ) {
	    	 updatescore(10);
	     }
	     else {
	    	 updateLives(-1);
                 if(getScore()!=0)
                 updatescore(-10);
	     }
	           System.out.println(getLive());	
	}
	@Override
	public void updateLives(int live) {
            System.out.println(getStrategy());
            if(getLive()>=1 && (getName().equals("easy")||getName().equals("medium")||getName().equals("hard"))){
                this.setLive(this.getLive() + live);
            
                
            }
	}

	@Override
	public int levelvelocityX() {
       
        	return getStrategy().velocityX();
               
        
		
	}
        @Override
	public int levelvelocityY() {
       
        	
             return   getStrategy().velocityY();
        
		
	}
	@Override
        public void updatescore(int score) {
		this.setScore(this.getScore() + score);
	}
        @Override
	public void save() throws IOException {

		FileWriter fw = new FileWriter("scores.txt",true);
	    BufferedWriter writer = new BufferedWriter(fw);  
	    getOriginator().setScore(getScore());
   	    getCaretaker().addMemento(getOriginator().save());
   	    getOriginator().restore(getCaretaker().getMemento());
   
//	    writer.write(originator.score);  
   	    writer.write(String.valueOf(getOriginator().getScore())); 
   	    writer.write("\n");
   	    writer.close();
		
	}
	
	public void load() throws IOException {
		
		setS(new Scanner(new File("scores.txt")));
        getS().useDelimiter("\n");
        int i=0;
        while (getS().hasNext()) {
       	    setWord(getS().next());
     	    setA(Double.parseDouble(getWord()));
       	    getHighScore().add(i, getA());
       	    i++;
        }
        setBestscore((double) getHighScore().get(0));
        for(int j=0;j<getHighScore().size();j++) {
        	
        	if(getHighScore().get(j)>getBestscore())
        		setBestscore((double) getHighScore().get(j));
        }
        System.out.println(getBestscore()+"linaaaaaaaaa");
	}

	public int getLive() {
		return Live;
	}

	public void setLive(int live) {
		this.Live = live;
	}

	public static Actions getActions() {
		return actions;
	}

	public static void setActions(Actions actions) {
		Actions.actions = actions;
	}


    public gamescene getGamescene() {
        return gamescene;
    }

    public void setGamescene(gamescene gamescene) {
        this.gamescene = gamescene;
    }

    public boolean getSliced() {
        return isSliced();
    }

    public void setSliced(boolean sliced) {
        this.sliced = sliced;
    }
   public Watermelon getWatermelon() {
        return watermelon;
    }

    public void setWatermelon(Watermelon watermelon) {
        this.watermelon = watermelon;
    }
    public kiwi getKiwi() {
        return kiwi;
    }

    public void setKiwi(kiwi kiwi) {
        this.kiwi = kiwi;
    }

    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public originator getOriginator() {
        return originator;
    }

    public void setOriginator(originator originator) {
        this.originator = originator;
    }

    public careTaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(careTaker caretaker) {
        this.caretaker = caretaker;
    }

    public Reciever getReciever() {
        return reciever;
    }

    public void setReciever(Reciever reciever) {
        this.reciever = reciever;
    }

    public double getBestscore() {
        return bestscore;
    }

    public void setBestscore(double bestscore) {
        this.bestscore = bestscore;
    }

    /**
     * @return the sliced
     */
    public boolean isSliced() {
        return sliced;
    }

    /**
     * @return the s
     */
    public Scanner getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(Scanner s) {
        this.s = s;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * @return the highScore
     */
    public ArrayList<Double> getHighScore() {
        return highScore;
    }

    /**
     * @param highScore the highScore to set
     */
    public void setHighScore(ArrayList<Double> highScore) {
        this.highScore = highScore;
    }

    /**
     * @return the invoke
     */
    public Invoker getInvoke() {
        return invoke;
    }

    /**
     * @param invoke the invoke to set
     */
    public void setInvoke(Invoker invoke) {
        this.invoke = invoke;
    }

    
    
    

   

    
    
        
}