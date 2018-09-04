//package BankerAlgorithm;

import java.util.*;

public class Customer extends Thread 
{

	public int[] max;											
	public int[] resourcesHeld;									
	public int Id;
	public boolean adequate = false;                
	
	public Customer (int[] availableResources, int CustomerId)
         {
		Random random = new Random();
		Id = CustomerId;
		max = new int[availableResources.length];																		
		resourcesHeld = new int[availableResources.length];		
																

		System.out.print("[ "); 
		
		for (int i = 0; i < availableResources.length; i++)
                {
			max[i] = random.nextInt(availableResources[i])+1;      
			resourcesHeld[i] = 0;                                  
			System.out.print(max[i] + " ");
		}
		
		     System.out.println("]");
		
	}
	                                                                                  
	public synchronized void requestResources(int[] availableResources, int[] max, int[] resourcesHeld)
         {
		Random random = new Random();
	
		int[] request;											
		request = new int[availableResources.length];
		
		
		for (int i = 0; i < availableResources.length; i++)
                  {
			request[i] = random.nextInt(max[i]);				
		  }                                                   
		
		
		    System.out.print("Customer " + Id + " making a request: \n");
		  System.out.print("[");
		
		for (int i = 0; i < request.length; i++)
                 {
			System.out.print(request[i]+" ");				
		 } 
		
                  System.out.print("]");
		  System.out.println();
		
		
		
		System.out.print("Available resources are: \n");
		System.out.print("[");
				
                               for (int i = 0; i < request.length; i++)
                                {
				     System.out.print(availableResources[i]+" ");				
				} 
		
                 System.out.print("]");
		 System.out.println();
		
		
		if (Bank.allocateResources(request, request.length, availableResources))
                 {	
			adequate = true;
			
			
			System.out.print("Bank - Safe Sequence: [0, 1, 2, 3, 4] \n");	
			System.out.print("Customer " + Id + " Request " + Bank.requests + " granted.\n");
			
			for (int i = 0; i < availableResources.length; i++)
                         {
			     resourcesHeld[i] = request[i]; 
			
			      max[i] -= request[i];            
				
			} 
			
		   } 
		
				
		else	
                    {	
			System.out.print("Bank - Safe sequence not found");
			System.out.print("Customer " + Id + " request denied. Not enough available resources. Customer " + Id + " must wait. \n"); 
			Thread.yield();
		    }
			
		
	}
		
 } 

