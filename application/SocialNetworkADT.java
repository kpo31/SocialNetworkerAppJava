package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface outlining the social network ADT
 * 
 * @author Eli Asher, Mihir Khatri, Harrison Bell, Hunter Abraham, Ian Jameson
 *
 */
public interface SocialNetworkADT {

	/**
	 * 
	 * Creates a new account to be added to the social network
	 * 
	 * @param userName - String for the account
	 * @throws DuplicateNameException - if name already exists in network
	 */
	public void createNewAccount(String userName) throws DuplicateNameException;

	/**
	 * 
	 * Removes an account from the social network
	 * 
	 * @param userName - String for the account
	 * @throws DuplicateNameException - if name already exists in data structure
	 */
	public void removeUser(String userName) throws DuplicateNameException;

	/**
	 * gets a list of a user's friends
	 * 
	 * @param user - the user
	 */
	public ArrayList<Person> getFriendList(String user);

	/**
	 * Creates a friendship between two users
	 * 
	 * @param user1 - first user in friendship
	 * @param user2 - second user in friendship
	 */
	public void addFriend(String user1, String user2);

	/**
	 * Removes friend from graph
	 * 
	 * @param user1 - first user in friendship
	 * @param user2 - second user in friendship
	 */
	public void removeFriend(String user1, String user2);

	public ArrayList<String> mutuals(String user1, String user2); // arraylist holding mutual friends
}