package Model;


public class objectFactory {

	
	 public GameObject getObject(String object){
	      if(object == null){
	         return null;
	      }		
	      if(object.equalsIgnoreCase("fruit")){
	         return new Fruit();       
	      } 
	      else if(object.equalsIgnoreCase("bomb")){
	         return new Bombs();
	         
	     
	      
	   }
		return null;
}
}