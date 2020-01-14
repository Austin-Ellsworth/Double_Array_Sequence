/* Author: Austin Ellsworth
 * 
 * File: Sequence Test
 * This is a class that will declare two sequence objects, sequenceA and sequenceB.  
 * Use each of the constructors once, capacity of second one is 5.  
 * Then the program will have a menu output to the screen with 16 options.
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class SequenceTest {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Welcome to my Sequence Test program!");
		
		DoubleArraySeq sequenceA = new DoubleArraySeq();
		DoubleArraySeq sequenceB = new DoubleArraySeq(5);
		DoubleArraySeq active = sequenceA;
		
		int choice = 0;
		while(choice != 16) {
			choice = menu();
			switch(choice) {
				case 1: 
					System.out.println("Display Sequences");
					try {
						System.out.println("Sequence A : " + sequenceA.toString());
					} catch(IllegalStateException e) {
						System.out.println("Sequence A: " + e.getMessage());
					}
					try {
						System.out.println("Sequence B : " + sequenceB.toString());
						System.out.println();
					} catch(IllegalStateException e) {
						System.out.println("Sequence B: " + e.getMessage());
					}
					System.out.println();
					break;
				case 2:
					System.out.println("Display capacity of A and B");
					System.out.println(sequenceA.getCapacity());
					System.out.println(sequenceB.getCapacity());
					System.out.println();
					break;
				case 3:
					System.out.println("Report if A and B are equal");
					if(sequenceA.equals(sequenceB)) {
						System.out.println("The two sequences are equal!");
					} else {
						System.out.println("The two sequences are NOT equal.");
					} 
					System.out.println();
					break;
				case 4:
					System.out.println("Change active Sequence");
					if(active == sequenceA) {
						active = sequenceB;
						System.out.println("Active sequence changed to sequence B");
					} else {
						active = sequenceA;
						System.out.println("Active sequence changed to sequence A");
					}
					System.out.println();
					break;
				case 5:
					System.out.println("Add a number to the front of a sequence");
					System.out.print("Please enter your number: ");
					double entry = keyboard.nextDouble();
					active.addFront(entry);
					System.out.println("You've added " + entry + " to the front of the active sequence");
					System.out.println();
					break;
				case 6:
					try {
						System.out.println("Set the current element location");
						System.out.print("What would you like the current element to be? ");
						int current = keyboard.nextInt();
						active.setCurrent(current);
						System.out.println("\nYou've set " + current + " as the current element location!");
						System.out.println();
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Index of out bounds for array!");
					}
					System.out.println();
					break;
				case 7: 
					try {
						System.out.println("Add a number before current element");
						System.out.print("Please enter your number: ");
						double element = keyboard.nextDouble();
						active.addBefore(element);
						System.out.println("You've added " + element + " before the current element!");
						System.out.println();
					} catch (OutOfMemoryError e) {
						System.out.println("System out of memory!");
					}
					System.out.println();
					break;
				case 8:
					try {
						System.out.println("Add a number after current element");
						System.out.print("Please enter your number: ");
						double element = keyboard.nextDouble();
						active.addAfter(element);
						System.out.println("You've added " + element + " after the current element!");
						System.out.println();
					} catch (OutOfMemoryError e) {
						System.out.println("System out of memory!");
					}
					System.out.println();
					break;
				case 9: 
					try {
						System.out.println("Add a number to the end of the active sequence");
						System.out.print("Please enter your number: ");
						double element = keyboard.nextDouble();
						active.addEnd(element);
						System.out.println("You've added " + element + " to the end of the active sequence!");
						System.out.println();
					} catch (OutOfMemoryError e) {
						System.out.println("System out of memory!");
					}
					System.out.println();
					break;
				case 10:
					try {
						System.out.println("Add sequence B to the end of sequence A");
						sequenceA.addAll(sequenceB);
						System.out.println("You've added Sequence B to the end of Sequence A!");
						System.out.println();
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
					} catch (OutOfMemoryError e) {
						System.out.println("System out of memory!");
					}
					System.out.println();
					break;
				case 11:
					try {
						System.out.println("Delete a number at a certain index");
						System.out.print("What is the index of the number you'd like to delete? ");
						int index = keyboard.nextInt();
						active.setCurrent(index);
						active.removeCurrent();
						System.out.println("You've removed a number from the index: " + index);
						System.out.println();
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					System.out.println();
					break;
				case 12:
					try {
						System.out.println("Delete the first number from the active sequence");
						active.removeFront();
						System.out.println("You've deleted the first number from the active sequence!");
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Index of out bounds for array!");
					}
					System.out.println();
					break;
				case 13:
					try {
						System.out.println("Display a number at a certain location");
						int index = keyboard.nextInt();
						System.out.print("The number at the current index of the active sequence is: " + active.getElement(index));
						System.out.println();
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Index of out bounds for array!");
					}
					System.out.println();
					break;
				case 14:
					try {
						System.out.println("Display the last element in the active sequence");
						active.setCurrentLast();
						System.out.print("The last element of the active sequence is: " + active.getCurrent());
						System.out.println();
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					}
					System.out.println();
					break;
				case 15: 
					try {
						System.out.println("Create a new sequence using concatenate of B and A and show");
						DoubleArraySeq sequenceC = new DoubleArraySeq(sequenceA.getCapacity() + sequenceB.getCapacity());
						sequenceC = DoubleArraySeq.concatenation(sequenceB, sequenceA);
						System.out.println("The new sequence you've created is: " + sequenceC.toString());
						System.out.println();
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
					} catch (OutOfMemoryError e) {
						System.out.println("System out of memory!");
					}
					System.out.println();
					break;
				case 16:
					System.out.println("Thank you for using my program.");
			}//end switch
		}//end while
		
		keyboard.close();
	}//end main
	
	public static int menu() {
	      int choice = 0;
	      Scanner keyboard = new Scanner(System.in);
	      while(choice < 1 || choice > 16) {
	         System.out.println("Please choose from the following: ");
	         System.out.println("\t#1: Print all sequences of A and B");
	         System.out.println("\t#2: Report the capacity of A and B");
	         System.out.println("\t#3: Report if A and B are equal");
	         System.out.println("\t#4: Change active sequence");
	         System.out.println("\t#5: Add a number to the front of the sequence");
	         System.out.println("\t#6: Set the current element location");
	         System.out.println("\t#7: Add a number before the current element");
	         System.out.println("\t#8: Add a number after the current element");
	         System.out.println("\t#9: Add a number to the end of a sequence");
	         System.out.println("\t#10: Add sequence B to the end of A");
	         System.out.println("\t#11: Delete a number at a certain index");
	         System.out.println("\t#12: Delete the first number from the sequence");
	         System.out.println("\t#13: Display the first number at a certain location");
	         System.out.println("\t#14: Display the last element in the sequence");
	         System.out.println("\t#15: Create a new sequence using concatenate of B and A and show");
	         System.out.println("\t#16: Quit");
	         System.out.print("Choice : ");
	         try {
	            choice = keyboard.nextInt();
	         }
	         catch(InputMismatchException e) {
	            System.out.println("Please enter a number!");
	         }
	         keyboard.nextLine(); //clear buffer
	         System.out.println();
	      }
	      return choice;
	   }//end menu

}
