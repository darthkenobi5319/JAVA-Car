import java.util.*;
import java.io.*;

public class Car{
	public static boolean ignition = false;
	public static int xPosition;
	public static int yPosition;
	public static char color;
	
	public static int randomizePosition(){
		int x = (int) (20 * Math.random())+1;
		return x;
	}
	
	public static char assignColor() {
		char color[] = {'R','G','B','W','S'};
		int i = (int)(5 * Math.random());
		return color[i];
	}
	
	public static boolean ignitionSwitch(boolean ignitionStatus) {
		return !ignitionStatus;
	}
	
	public static int move(int x, boolean ignitionStatus, int d) {
		if (ignitionStatus == false) {
			System.out.println("Ignition is off!");
			return x;
		}
		if (x+d <= 0 || x+d > 20) {
			System.out.println("Out of bounds!");
			return x;
		}
		return x+d ;
	}
	

	public static void reportState(boolean ignition,int xPosition, int yPosition, char color) {
		System.out.println("Car Information");
		String colorS= null;
		if (color == 'R'){
			colorS = "Red"; 
		}
		if (color == 'G') {
			colorS = "Green";
		}
		if (color == 'B') {
			colorS = "Blue";
		}
		if (color == 'W') {
			colorS = "White";
		}
		if (color == 'S') {
			colorS = "Silver";
		}
		System.out.println("Color: " + colorS);
		System.out.println("Location: (" + Integer.toString(xPosition) + "," + Integer.toString(yPosition) + ")");
		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				if (i == yPosition && j == xPosition) {
					System.out.print(color);
				}
				else {
				System.out.print("-");
				}
			}
			System.out.print("\n");
		}
	}

	public static void main(String [] args) {
		
		Scanner userInput  = new Scanner (System.in); 
		String userValue = "";
		xPosition = randomizePosition();
		yPosition = randomizePosition();
		color = assignColor();
		
		do{
			System.out.print(" What would you like to do?\n1: turn the ignition on/off\n2: change the position of car\nQ: quit this program\nInput: ");			
			userValue = userInput.nextLine();
			if (!userValue.equalsIgnoreCase("q")) {
				if ((!userValue.equals("1")) && (!userValue.equals("2")) && (!userValue.equalsIgnoreCase("q"))){
					throw new IllegalArgumentException("Invalid entry. Should be 1, 2 or Q");
				}
				
				if (userValue.equals("1")){
					ignition = ignitionSwitch(ignition);
					reportState(ignition, xPosition, yPosition, color);
				}
				if (userValue.equals("2")) {
					reportState(ignition, xPosition, yPosition, color);
					System.out.println( "In which direction do you want to move the car?\nH: Horizontal\nV: Vertical\nDirection:");
					userValue = userInput.nextLine();
					if (userValue.equalsIgnoreCase("h")) {
						System.out.println("Enter a distance:");
						userValue = userInput.nextLine();
						xPosition = move(xPosition, ignition, Integer.parseInt(userValue));
						reportState(ignition, xPosition, yPosition, color);
					}
					else if (userValue.equalsIgnoreCase("v")) {
						System.out.println("Enter a distance:");
						userValue = userInput.nextLine();
						yPosition = move(yPosition, ignition, Integer.parseInt(userValue));
						reportState(ignition, xPosition, yPosition, color);
					}
					else{
						throw new IllegalArgumentException("Please enter H or V!");
					}					
					System.out.print("\n");
					}
			}
		}
		while (!userValue.equalsIgnoreCase("q"));
		userInput.close();	
	}
}