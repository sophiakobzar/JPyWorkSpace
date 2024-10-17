//written by Sophia Kobzar
// project 2
// the driver code for the simulation
// semaphores are set and ready for the customer and worker to use
// we have 50 customer in total but only 3 allowed in the store at a time
// we also only have three postal workers wokring in the simulation 

import java.util.concurrent.Semaphore;
public class PostOffice
{
	
  public int TotalCustomers, TotalPostalWorkers;
  public static int cust_index, worker_index, customer_action;
  //set the semaphores
  public static Semaphore freeCounter = new Semaphore( 0, true );
  public static Semaphore max_capacity = new Semaphore( 10, true ); //hit max capacity 
  public static Semaphore counterAvailable = new Semaphore( 0, true ); 
  public static Semaphore worker= new Semaphore( 3, true ); 
  public static Semaphore cust_ready= new Semaphore( 0, true ); 
  public static Semaphore mutex= new Semaphore( 3, true ); 
  public static Semaphore mutex2= new Semaphore( 1, true );
  public static Semaphore scaleInUse= new Semaphore( 1, true );
  public static Semaphore customer_entered= new Semaphore( 0, true ); //there is a new customer in the store
  public static Semaphore serve_signal= new Semaphore( 1, true );// the customer knows what they want 
  public static Semaphore order_finish= new Semaphore( 0, true );
  public static Semaphore order= new Semaphore( 0, true );
  public static Semaphore finished[] = new Semaphore[50]; // order was completed the customer can leave
  // main creates all the threads for the 3 workers and 50 customer
  // we use Semaphore to let the people in the simulation know when something is ready 
  
	public static void main(String args[])
    { 
    	System.out.println("Starting Simulation");
		int TotalCustomers = 50;
    	int TotalPostalWorkers = 3;
    	//Initiate array of semaphores
    	  for(int n =0; n <TotalCustomers ; n++)
    	  {
    	          finished[n]= new Semaphore(0, true); //Initialize each with 0
    	  }
    	
    	// object and thread for 50 customers
    	Thread Customerthread[] = new Thread[TotalCustomers];
		Customer CustomerObj[] = new Customer[TotalCustomers];
        
        // object and thread for the 3 postal workers
        Thread Workerthread[] = new Thread[TotalPostalWorkers];
		PostalWorker WorkerObj[] = new PostalWorker[TotalPostalWorkers];
        
        
        //thread initialized for customers
        for(int i = 0; i < TotalCustomers ; ++i ) 
        {
           CustomerObj[i] = new Customer(i,TotalPostalWorkers, TotalCustomers,finished,customer_action,max_capacity,freeCounter,counterAvailable,worker,cust_ready,mutex,mutex2,scaleInUse,customer_entered,serve_signal,order_finish,order);
           Customerthread[i] = new Thread( CustomerObj[i]);
           Customerthread[i].start();
        }
        
        //thread initialized for workers        
        for(int j = 0; j < TotalPostalWorkers; ++j ) 
        {
        	WorkerObj[j] = new PostalWorker(j,TotalPostalWorkers, TotalCustomers,finished,customer_action,max_capacity,freeCounter,counterAvailable,worker,cust_ready,mutex,mutex2,scaleInUse,customer_entered,serve_signal,order_finish,order);
        	Workerthread[j] = new Thread(WorkerObj[j]);
        	Workerthread[j].setDaemon(true);
			Workerthread[j].start(); 
        }
        for(int k = 0; k < TotalCustomers; ++k ) 
        {
	  	 try
	  	 {
			 Customerthread[k].join();
			 System.out.println("Joined customer "+ k);
	  	 }
	  	 catch (InterruptedException e){}
        }
		System.out.println("Ending Simulation");
		return;
     }	
}