package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * An interface that outlines a graphADT
 * 
 * @author Eli Asher, Mihir Khatri, Harrison Bell, Hunter Abraham, Ian Jameson
 *
 */
public interface GraphADT {
	/**
	 * Add new user to the graph.
	 *
	 * If user is null or already exists, method ends without adding a user or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. user is non-null 2. user is not already in the
	 * graph
	 * 
	 * @param user the user to be added
	 * @throws DuplicateNameException
	 */
	public void addUser(String userName) throws DuplicateNameException;

	/**
	 * Remove a user and all associated edges from the graph.
	 * 
	 * If user is null or does not exist, method ends without removing a user,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. user is non-null 2. user is not already in the
	 * graph
	 * 
	 * @param user the user to be removed
	 */
	public void removeUser(String user);

	/**
	 * Add the edge from user1 to user2 to this graph. (edge is directed and
	 * unweighted)
	 * 
	 * If either user does not exist, user IS ADDED and then edge is created. No
	 * exception is thrown.
	 *
	 * If the edge exists in the graph, no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither user is null 2. both vertices are in
	 * the graph 3. the edge is not in the graph
	 * 
	 * @param user1 the first user (src)
	 * @param user2 the second user (dst)
	 */
	public void addFriend(String user1, String user2);

	/**
	 * Remove the edge from user1 to user2 from this graph. (edge is directed and
	 * unweighted) If either user does not exist, or if an edge from user1 to user2
	 * does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither user is null 2. both vertices are in
	 * the graph 3. the edge from user1 to user2 is in the graph
	 * 
	 * @param user1 the first user
	 * @param user2 the second user
	 */
	public void removeFriend(String user1, String user2);

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return a Set<String> which contains all the vertices in the graph
	 */
	public ArrayList<Person> getAllUsers();

	/**
	 * Get all the neighbor (adjacent-dependencies) of a user
	 * 
	 * For the example graph, A->[B, C], D->[A, B] getAdjacentVerticesOf(A) should
	 * return [B, C].
	 * 
	 * In terms of packages, this list contains the immediate dependencies of A and
	 * depending on your graph structure, this could be either the predecessors or
	 * successors of A.
	 * 
	 * @param user the specified user
	 * @return an List<String> of all the adjacent vertices for specified user
	 */
	public ArrayList<Person> getFriendsOf(String user);

//
//  /**
//   * Returns the number of edges in this graph.
//   * 
//   * @return number of edges in the graph.
//   */
//  public int size();
//
//
//  /**
//   * Returns the number of vertices in this graph.
//   * 
//   * @return number of Friendships in graph.
//   */
//  public int order();

}