package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 5
 *  returns their name and a
 *  modified string 
 *  
 *  @author Ben
 *  @version 1.1
 */
public class Person5 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */


  public Person5(String pname) {
	  name = pname;
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * push/commit test line
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *what what what what
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		//Person 5 put your implementation here
		if(input.length()>2){
			String firstTwo = input.substring(0,2);
			String theRest = input.substring(2,input.length());
			return theRest + firstTwo;

		}
		else return input;


	}
	
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {

		return name + calc(input);
	}

}
