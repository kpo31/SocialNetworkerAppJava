package application;

import java.util.ArrayList;

/**
 * Outline of the Person class. Used as a node in the SocialNetwork class
 * 
 * @author Eli Asher, Mihir Khatri, Harrison Bell, Hunter Abraham, Ian Jameson
 *
 */
public class Person {
	private final String userName;// name of this node
	private ArrayList<Person> friends;

	/**
	 * Constuctor
	 * 
	 * @param name - name of Person
	 */
	public Person(String name) {
		userName = name;
	}

	/**
	 * Accessor method for Person's name field
	 * 
	 * @return the user's name
	 */
	public String getUserName() {// name getter
		return userName;
	}

	/**
	 * toString() method for the Person class
	 */
	@Override
	public String toString() {
		return userName;
	}

	/**
	 * adds a friendship for this person
	 * 
	 * @param person - person who will have a friend added
	 */
	public void friends(Person person) {
		friends.add(person);
	}

	/**
	 * Accessor method for this person's friends
	 * 
	 * @return A list of this person's friends
	 */
	public ArrayList<Person> friend() {
		return friends;
	}
}