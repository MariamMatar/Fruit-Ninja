package controller;

import java.util.Formatter;

public class File {

	private static Formatter x;
	public static  void openFile() {
		try 
		{
		x=new Formatter("scores.txt");
		
		}
		catch( Exception e) {
			System.out.println("you have an error");
		}
	}
	
	public static void closefile() {
		x.close();
	}


}