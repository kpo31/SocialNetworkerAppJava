package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Filename: Graph.java Project: Social Network p4 Authors: Harrison Ian Eli
 * Mihir Hunter
 * 
 * Undirected and unweighted graph implementation Adapted from Harrison's p4
 * PackageMangager
 */

public class Graph implements GraphADT {
	private Map<Person, ArrayList<Person>> graph = new HashMap<>();

	/*
	 * Default no-argument constructor
	 */
	public Graph() {

	}

	/**
	 * Add new user to the graph.
	 *
	 * @param person - person to be added to graph
	 */
	public void addUser(String person) {
		if (person == null) { // if username is null, exit
			return;
		}
		// make sure person isn't already in graph
		for (int i = 0; i < this.getAllUsers().size(); i++) {
			if (this.getAllUsers().get(i).getUserName().equals(person)) {
				return;
			}
		}
		// put person in graph if neither of above conditions are true
		graph.put(new Person(person), new ArrayList<Person>());
	}

	/**
	 * Remove a user
	 * 
	 * @param user - the user to be removed
	 */
	public void removeUser(String user) {
		if (user == null) { // if user is null, exit method
			return;
		}
		// search for person and remove
		for (int i = 0; i < this.getAllUsers().size(); i++) {
			if (this.getAllUsers().get(i).getUserName().equals(user)) {
				Person person = this.getAllUsers().get(i);
				if (!graph.containsKey(person)) {
					return;
				}
				graph.values().stream().forEach(e -> e.remove(person));
				graph.remove(person);
			}
		}
	}

	/**
	 * Find a specific person in the graph
	 * 
	 * @param user - the user to be found
	 * @return - the Person object of that person
	 */
	public Person find(String user) {
		Person person = null;
		// search for person and return
		for (int i = 0; i < this.getAllUsers().size(); i++) {
			if (this.getAllUsers().get(i).getUserName().equals(user)) {
				person = this.getAllUsers().get(i);
			}
		}
		return person;
	}

	/**
	 * Add undirected edge from person1 to person2
	 * 
	 * @param person1 - the first person in the friendship
	 * @param person2 - the second person in the friendship
	 */
	public void addFriend(String person1, String person2) {
		if (person1 == null || person2 == null) { // if either string is null, exit method
			return;
		}
		// if either person doesn't exist in the graph, add them.
		if (!this.list().contains(person1))
			addUser(person1);

		if (!this.list().contains(person2))
			addUser(person2);

		// if there is already a friendship, exit method
		if (graph.get(this.find(person1)).contains(this.find(person2))) {
			return;
		}
		// add the friendship if none of the above conditions are met
		graph.get(this.find(person2)).add(this.find(person1));
		graph.get(this.find(person1)).add(this.find(person2));
	}

	/**
	 * Remove the edge from person1 to person2
	 * 
	 * @param person1 - the first person in the friendship
	 * @param person2 - the second person in the frinedship
	 */
	public void removeFriend(String person1, String person2) {
		// if either person is null, return
		if (person1 == null || person2 == null) {
			return;
		}
		// if person1 and person2 are the same person, exit method
		if (person1.equals(person2)) {
			return;
		}
		// if either person doesn't exist in the graph, exit method
		if (!graph.containsKey(this.find(person1))) {
			return;
		}
		if (!graph.containsKey(this.find(person2))) {
			return;
		}
		// if person1 has no friends or person2 isn't a friend of person1, exit
		if (this.getFriendsOf(person1) == null) {
			return;
		}
		if (!this.getFriendsOf(person1).contains(this.find(person2))) {
			return;
		}
		// if the graph has both people
		if (graph.containsKey(this.find(person1)) && graph.containsKey(this.find(person2))) {
			// and if they are friends, remove friendship
			if (graph.get(this.find(person1)).contains(this.find(person2))) {
				graph.get(this.find(person1)).remove(this.find(person2));
				graph.get(this.find(person2)).remove(this.find(person1));
			}
		}
	}

	/**
	 * Returns a ArrayList that contains all the vertices
	 * 
	 * @return a list of all users
	 */
	public ArrayList<Person> getAllUsers() {
		ArrayList<Person> aList = new ArrayList<Person>();
		for (Person x : this.graph.keySet()) {
			aList.add(x);
		}

		return aList;

	}

	/**
	 * Get all the friends of a user
	 *
	 * @param name - the name of the user who's friends are being found
	 * @return a list of the user's friends
	 */
	public ArrayList<Person> getFriendsOf(String name) {
		return graph.get(this.find(name));

	}

	/**
	 * Return list of strings of users in graph
	 * 
	 * @return a list of all users in the graph
	 */
	public ArrayList<String> list() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < this.getAllUsers().size(); i++) {
			list.add(this.getAllUsers().get(i).getUserName());
		}
		return list;
	}

	/**
	 * Clears the graph
	 */
	public void clear() {
		graph.clear();
	}
}
