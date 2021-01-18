package view;
import Model.*;
import controller.*;

//import Model.Fruit.fruits;
import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Mainn extends Application{

    public static Stage primaryStage;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

                objectFactory objectFactory=new objectFactory();
                

		GameObject fruit = objectFactory.getObject("fruit");
		GameObject bomba =objectFactory.getObject("bomb");
		Actions actions=Actions.getInstance();
                careTaker caretaker =new careTaker();
		originator originator=new originator();
		Reciever reciever=new Reciever(actions);

                Watermelon water = new Watermelon();
               banana banana =new banana();
               actions.setCaretaker(caretaker);
               actions.setOriginator(originator);
               actions.setReciever(reciever);
		bomb bomb=new bomb();
		bomb2 bomb2 = new bomb2();
               
                
                kiwi kiwi = new kiwi();
                mango mango = new mango ();
		dragon dragon = new dragon ();
		
                 Level level=new Level(stage,fruit,kiwi,mango,dragon);
                gamescene gamescene	= new gamescene(stage,fruit,bomba,bomb,bomb2,actions);
                arcade arcade=new arcade(stage,fruit,bomba,bomb,bomb2,actions);
                
		startmenu startmenu = new startmenu(stage, fruit, water, banana);
                startmenu.setArcade(arcade);
                startmenu.setActions(actions);
                level.setActions(actions);
                

	        startmenu.preparescene();
                actions.setGamescene(gamescene);
                
                startmenu.setLevels(level);

                level.setGamescene(gamescene);

	
	startmenu.setGamescene(gamescene);
	
	
	stage.setScene(startmenu.getScene());
    setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.show();
                primaryStage = stage;
		
   }
}