//package BankerAlgorithm;
import java.util.Random;


public class Bank 
    {
		public static int requests = 0;
	
		public static void main(String[] args) throws InterruptedException
        	{
			int numResources = 5;									
			int numCustomers = 5;
			int index = 0;											
		
			Random random = new Random();							
		
			int[] totalResources;
			totalResources = new int[numResources]; 				
			int[] availableResources;
			availableResources = new int[numResources];						
		
		
			System.out.print("Initial Resources Available:" + "\n[ ");  

			for (int i = 0; i < numResources; i++)	
			     {
				totalResources[i] = random.nextInt(9)+1;			
				availableResources[i] = totalResources[i];			
				System.out.print(totalResources[i] + " ");
		             }
		
		
			System.out.print("]\n");
		
			Customer[] customers;	
			customers = new Customer[numCustomers];
		
                 	System.out.print("Bank - Max: \n");

			for (int i = 0; i < numCustomers; i++)
                          {				
                          	customers[i] = new Customer(availableResources,i);
			        customers[i].start();
		          }
			
	                 do
                           {
			
			    for (int i = 0; i < availableResources.length; i++)
                             {
				   if(customers[index].adequate == false){
				customers[index].requestResources(availableResources, customers[index].max, customers[index].resourcesHeld);
				Thread.sleep(random.nextInt(500));	      
				index = (index + 1) % numCustomers;   
			     }
				
			  }
			
			do
                          {                                                  
				for (int i = 0; i < customers.length; i++)
                                  {
					if (customers[i].adequate)
                                        {
					      deallocateResources(customers[i].resourcesHeld, availableResources.length, availableResources);
					}
				
					else
                                          {
		                	      customers[i].adequate = allocateResources(customers[i].resourcesHeld, availableResources.length, availableResources);			    	
				          }
				}
				
			
			}while(customers[0].adequate != true && customers[1].adequate != true && customers[2].adequate != true && customers[3].adequate != true && customers[4].adequate != true);
			
			for (int i = 0; i < customers.length; i++)
                        {
				customers[i].adequate = false;
			}
		    
            
			requests++;
			
		}while (requests != 2);
		
				System.out.print("Final available vector: \n");
				System.out.print("["); 

		   for (int i = 0; i < numResources; i++)
			{
				System.out.print(availableResources[i] + " ");
		        }
				
                           System.out.print("]\n"); 
		
		}
	
	
       public synchronized static boolean allocateResources (int[] request, int length, int[] availableResources)
       {
		boolean adequateResources = true;
		
		for (int i = 0; i < length; i++)
                     {

			if (availableResources[i] < request[i])	
				adequateResources = false;
		     }
		
		
		if (adequateResources)
                        {

			for (int i = 0; i < length; i++){		
				availableResources[i] -= request[i];
			}
		
		return adequateResources;
      } 

     public synchronized static void deallocateResources (int[] resourcesHeld, int length, int[] availableResources)
        {
		System.out.print("Releasing resources: \n");
		System.out.print("[");

		for (int i = 0; i < length; i++)
                {
			System.out.print(resourcesHeld[i] + " ");            
		}
		         System.out.print("] \n");

		for (int i = 0; i < length; i++)
                {
	
			availableResources[i] += resourcesHeld[i];     
			
		}
		
	} 

	
} 



