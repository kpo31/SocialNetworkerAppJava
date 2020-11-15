package application;

import java.util.ArrayList;
import java.util.List;
import application.DuplicateNameException;
import application.Graph;
import application.SocialNetworkADT;

/**
 * Implementation of SocialNetworkADT
 * 
 * @author Eli Asher, Mihir Khatri, Harrison Bell, Hunter Abraham, Ian Jameson
 *
 */
public class SocialNetwork implements SocialNetworkADT {
	private Graph graph;

	/**
	 * default constructor for SocialNetwork
	 */
	public SocialNetwork() {
		graph = new Graph();
	}

	/**
	 * Method to create a new account
	 * 
	 * @param userName - the username of the new account
	 */
	@Override
	public void createNewAccount(String userName) throws DuplicateNameException {
		if (userName == null) { // if username is null, exit method
			return;
		}
		if (userName.equals("")) { // if username is empty string, throw exception
			throw new DuplicateNameException();
		}
		userName = userName.replaceAll("[^a-zA-Z0-9]", ""); // remove inappropriate characters
		if (this.string().contains(userName)) { // if the network already has the user, throw exception
			throw new DuplicateNameException();
		}
		if (userName.contains("")) // if username has an empty string,
			graph.addUser(userName); // add userName
	}

	/**
	 * Removes a user from the network
	 * 
	 * @param userName - username of user to be removed
	 */
	public void removeUser(String userName) throws DuplicateNameException {
		if (!this.string().contains(userName)) { // if the network doesn't have the user, throw exception
			throw new DuplicateNameException();
		}
		graph.removeUser(userName); // remove user
	}

	/**
	 * gets a list of the user's friends
	 * 
	 * @param user - user who's friends are being retrieved
	 */
	@Override
	public ArrayList<Person> getFriendList(String user) {
		return graph.getFriendsOf(user);
	}

	/**
	 * Add a friend connection
	 * 
	 * @param user1 - first friend in the friendship
	 * @param user2 - second friend in the friendship
	 */
	@Override
	public void addFriend(String user1, String user2) {
		graph.addFriend(user1, user2);
	}

	/**
	 * remove a friendship from the network
	 * 
	 * @param user1 - the first friend in the friendship
	 * @param user2 - the second frined in the friendship
	 */
	@Override
	public void removeFriend(String user1, String user2) {
		graph.removeFriend(user1, user2);
	}

	/**
	 * Person Arraylist of all users
	 * 
	 * @return
	 */
	public ArrayList<Person> get() {
		return graph.getAllUsers();
	}

	/**
	 * String Arraylist of all users
	 * 
	 * @return
	 */
	public ArrayList<String> string() {
		return graph.list();
	}

	/**
	 * Search for a specific person in the graph
	 * 
	 * @param person
	 * @return
	 */
	public Person search(String person) {
		return graph.find(person);
	}

	/**
	 * Clears the graph
	 */
	public void clear() {
		graph.clear();
	}

	/**
	 * Find mutual friends between users
	 */
	public ArrayList<String> mutuals(String user1, String user2) {
		ArrayList<Person> one = graph.getFriendsOf(user1);
		ArrayList<Person> two = graph.getFriendsOf(user2);
		ArrayList<String> three = new ArrayList<>();
		for (int i = 0; i < one.size(); i++) { // If both are friends add to list
			if (two.contains(one.get(i))) {
				three.add(one.get(i).getUserName());
			}
		}
		return three;
	}

}