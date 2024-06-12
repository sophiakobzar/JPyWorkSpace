import java.util.concurrent.Semaphore;
import java.util.Random;

public class Customer  implements Runnable
{
	  private int TotalCustomers;
	  private int TotalPostalWorkers;
	  private int index,worker_index,customer_action,i;
	  private static Semaphore max_capacity ; 
	  private static Semaphore freeCounter; 
	  private static Semaphore counterAvailable; 	  
	  private static Semaphore worker; 
	  private static Semaphore cust_ready; 
	  private static Semaphore mutex; 
	  private static Semaphore mutex2;
	  private static Semaphore scaleInUse;
	  private static Semaphore customer_entered;
	  private static Semaphore serve_signal;
	  private static Semaphore order_finish;
	  private static Semaphore order;
	  private static Semaphore finished[] = new Semaphore[50];
	  
	 public Customer(int index,
		int TotalCustomers,
		int TotalPostalWorkers,
		Semaphore []finished,
		int customer_action,
		Semaphore max_capacity, 
		Semaphore freeCounter,
		Semaphore counterAvailable,
		Semaphore worker,
		Semaphore cust_ready,
		Semaphore mutex,
		Semaphore mutex2,
		Semaphore scaleInUse,
		Semaphore customer_entered,
		Semaphore serve_signal,
		Semaphore order_finish,
		Semaphore order)
	{
		 this.index= index;
		 this.TotalCustomers = TotalCustomers;
		 this.TotalPostalWorkers=TotalPostalWorkers;
		 this.finished=finished;
		 this.max_capacity=max_capacity;
		 this.freeCounter=freeCounter;
		 this.counterAvailable= counterAvailable;
		 this.cust_ready=cust_ready;
		 this.worker=worker;
		 this.mutex= mutex;
		 this.mutex2=mutex2;
		 this.scaleInUse=scaleInUse;
		 this.customer_entered= customer_entered;
		 this.serve_signal=serve_signal;
		 this.order_finish=order_finish;
		 this.order=order;
		 System.out.println("Customer "+ index +" created");
		 //to generate a random number between 1 and 3
		 // 1 -> buy stamp 
		 // 2 -> mail letter
		 // 3 -> mail package
		 Random ranNumber = new Random();
		 this.customer_action = ranNumber.nextInt(3) + 1;
		 
	}
	public void run() 
	{	
		//for letting only 10 customers at a time inside the office
		try{max_capacity.acquire();} //wait(max_capacity)
		catch (InterruptedException e){}
		
		System.out.println("Customer "+index+ " enters post office");
		try{serve_signal.acquire();} //wait(serve_signal)
		catch (InterruptedException e){}
	
		//it allows only 3 customers to go to counters (only 3 workers)
		try{mutex.acquire();}//wait(mutex)
		catch (InterruptedException e){}		
		
		//to signal the worker that the customer is ready to be served
		cust_ready.release();//signal(cust_ready)
		
		PostOffice.cust_index = this.index;
		PostOffice.customer_action=this.customer_action;
		
		//Signal the worker once customer enters the post office
		customer_entered.release();//signal(customer_entered)
		
		//to wait for the worker to be available
		try{counterAvailable.acquire();} //wait(counterAvailable)
		catch (InterruptedException e){}
		
		this.worker_index= PostOffice.worker_index;
		
		switch(customer_action)
		{
			case 1:
				System.out.println("Customer "+ index+ " asks postal worker "+this.worker_index+" to buy stamps");
				break;
			case 2:
				System.out.println("Customer "+ index+ " asks postal worker "+this.worker_index+" to mail a letter");
				 break;
			case 3:
				System.out.println("Customer "+ index+ " asks postal worker "+this.worker_index+" to mail a package");
				break;
		}
		
		//to signal the worker to work on the service ordered by the customer
		order.release();//signal(order)
		
		//waiting for customer to receive signal that the worker is done.
		try{finished[index].acquire();}//wait(finished[cust_index])
		catch (InterruptedException e){}
		
		//waiting for worker to finish the service ordered by the customer
		try{order_finish.acquire();} //wait(order_finish)
		catch (InterruptedException e){}		
		// print "Customer X finished Y"
		// Y is specific job
		// X is the customer's id or number 
		switch(this.customer_action)
		{
		case 1:			
			 System.out.println("Customer "+index+" finished buying stamps");
			  break;
		case 2:			
			 System.out.println("Customer "+index+" finished mailing a letter");
			  break;
		case 3:			
			 System.out.println("Customer "+index+" finished mailing a package");
			 break;			 
		}	
		
		//to signal that 1 counter has become free
		mutex.release();		//signal(mutex)
	    System.out.println("Customer "+ index+ " leaves post office");
	    
	    //to signal to the worker that 1 customer has left the office
		freeCounter.release();//signal(freeCounter)
		
		//to signal that 1 more customer can occupy the post office since 1 left the office
		 max_capacity.release();//signal(max_capacity)	 
	}
}