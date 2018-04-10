import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;

/** 
 * @author Jacob Thomas
 */

public class Main {

	static ArrayList<Integer> nodeList= new ArrayList<Integer>();
	static ArrayList<Integer> visitingNodes= new ArrayList<Integer>();

	public static void main(String[] args) {

		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println("Step 1:");
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		//gets the number of bits from the user
		int B=0;
		Scanner user_input = new Scanner (System.in);
		System.out.print("Please enter a number from 5 to 10 for the number of bits: ");
		B=user_input.nextInt();
		while (B<5 || B>10)
	    {
			System.out.print("Please enter a number from 5 to 10 for the number of bits: ");
	        B = user_input.nextInt();
	    }
		
		//gets the number of nodes from the user
		int N;
		Scanner other_user_input = new Scanner (System.in);
		System.out.print("Please enter a number from 5 to 15 for the number of nodes: ");
		N=user_input.nextInt();
		while (N<5 || N>15) 
	    {
			System.out.print("Please enter a number from 5 to 15 for the number of nodes: ");
	        N = other_user_input.nextInt();
	    }
		
		System.out.println();
		System.out.println("Your B value is: " + B);
		System.out.println("Your N value is : " + N);

		
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println("Step 3 & 4:");
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println();

		int ID_Spaces= (int) Math.pow(2,B); //uses the value the user entered to generate 2^B
		int key_ID = 1 + (int)(Math.random() * ID_Spaces); //randomly creates the key
		System.out.println("The Key ID is: " + key_ID);				
		
		//constructs the node list with random values
		while (nodeList.size()<N)
		{
			int num= 1 + (int)(Math.random() * ID_Spaces); 

			if (nodeList.contains(num)==false)
			{
			nodeList.add(num);
			}
		}
		
		Collections.sort(nodeList); //sorts the node list
		
		System.out.println("The Node List is: " + nodeList);
		
		int startNode=nodeList.get(1); //finds the starting node by grabbing the second smallest value in the sorted list
		System.out.println("The value of the start node is (nStart): " + startNode + "\n");
		
		int endNode = 0;
		int key_swap=0;
		
		//figures out what the ending node should be before having to generate the finger tables 
		for (int x : nodeList)
		{
			if (key_ID > nodeList.get(nodeList.size()-1))
			{
				key_swap=ID_Spaces-nodeList.get(nodeList.size()-1);
				if (key_swap<x || key_swap==x)
					{
						endNode=x;
						break;
					}
				
			}
			else if (key_ID < x || key_ID==x)
			{
				endNode=x;
				break;
			}

			}
		System.out.println("The value of the end node is: " + endNode);
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println("Step 2:");
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println();

		
		//prints out the finger tables for the visiting order of the nodes
		int count=0;
		int nodeSwap;
		int closestNode = 0;
		for (int currentNode: nodeList)
		{
			for (int i=0; i<B; i++)
			{				
				count++;
				int bits= (int) Math.pow(2,i);
				int total=currentNode + bits;
				//this loop is for finding the closest node after adding the bits to the current node
				for (int existingNode: nodeList){
					if (total > nodeList.get(nodeList.size()-1))
					{		
						nodeSwap=ID_Spaces-nodeList.get(nodeList.size()-1);
						if (nodeSwap<existingNode ||nodeSwap==existingNode)
						{
							closestNode=existingNode;
							break;
						}
					}
					else if (total <= existingNode)
					{
						closestNode=existingNode;
						break;
						//The current existingNode is where the finger table should route to for this row
					}	
				}	
				//prints outs the node that is constructing its finger table
				if (count==1)
				{
					System.out.println("The current node finger table is Node #" + currentNode);

				}
				//print out the finger tables 
				System.out.print("N" +   + currentNode + " " + "+" + " " + bits + " " + " " + "=" + " " + total + " " + "Closest node is: "+ closestNode);
				if (count % B == 0 )
				{
					count=0;
					System.out.println();
				}
				
				//if you get to the end of the list and the existing node is still 
				//then add the last node to the list greater than the key_ID and not equal to it
				
				if (nodeList.get(nodeList.size()-1)==N && closestNode!=endNode)
				{
					visitingNodes.add((nodeList.get(nodeList.size()-1)));
				}
				
				System.out.println();
			}
		}		
				
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				visitingNodes.add(startNode);
				visitingNodes.add(endNode);
		if (startNode==endNode)
		{
			visitingNodes.remove(visitingNodes.get(1));
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println("Step 5:");
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println();

		//prints out the visiting order of the nodes
		System.out.println("The visiting nodes are: " + visitingNodes);
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println("Step 6:");
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println();
				//prints out the finger tables for the visited nodes
				int counter=0;
				for (int currentNode: visitingNodes)
				{
					for (int i=0; i<B; i++)
					{				
						counter++;
						int bits= (int) Math.pow(2,i);
						int total=currentNode + bits; 
						
						//this loop is for finding the closest node after adding the bits to the current node
						for (int existingNode: nodeList){
							if (total > nodeList.get(nodeList.size()-1))
							{		
								nodeSwap=ID_Spaces-nodeList.get(nodeList.size()-1);
								if (nodeSwap<existingNode ||nodeSwap==existingNode)
								{
									closestNode=existingNode;
									break;
								}
							}
							else if (total <= existingNode)
							{
								closestNode=existingNode;
								break;
								//The current existingNode is where the finger table should route to for this row
							}	
						}	
						//prints outs the node that is constructing its finger table
						if (counter==1)
						{
							System.out.println("The current node finger table is Node #" + currentNode);

						}
						//print out the finger tables 
						System.out.println("N" +   + currentNode + " " + "+" + " " + bits + " " + " " + "=" + " " + total + " " + "Closest node is: "+ closestNode);
						if (counter % B == 0 )
						{
							counter=0;
							System.out.println();
						}
					}
				}
			}
		}