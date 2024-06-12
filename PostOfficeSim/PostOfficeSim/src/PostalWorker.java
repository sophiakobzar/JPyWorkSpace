// written by Sophia Kobzar
import java.util.concurrent.Semaphore;
public class PostalWorker implements Runnable
{
	  private int TotalCustomers;
	  private int TotalPostalWorkers;
	  private int index,cust_index,worker_index,customer_action,i;
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
	  
	  public PostalWorker(int index,
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
		 this.TotalCustomers = TotalCustomers;
		 this.TotalPostalWorkers=TotalPostalWorkers;
		 this.index= index;
		 this.finished=finished;
		 this.max_capacity=max_capacity;
		 this.freeCounter=freeCounter;
		 this.counterAvailable= counterAvailable;
		 this.cust_ready=cust_ready;
		 this.worker=worker;
		 this.mutex=mutex;
		 this.mutex2=mutex2;
		 this.scaleInUse=scaleInUse;
		 this.customer_entered= customer_entered;
		 this.serve_signal=serve_signal;
		 this.order_finish=order_finish;
		 this.order=order;
	  }
	public void run() 
	{
		System.out.println("Postal Worker "+ index +" created");
		while(true)
		{
			//waiting for customer
			try{cust_ready.acquire();} //wait(cust_ready)
			catch (InterruptedException e){}
			
			try{worker.acquire();} //wait(worker)
			catch (InterruptedException e){}				
				
			//only after the customer 
			//comes to the counter the worker will work
			try{customer_entered.acquire();} //wait(customer_entered) 
			catch (InterruptedException e){}
			
			try{mutex2.acquire();}//wait(mutex2)
			catch (InterruptedException e){}
			// get the customer id that the worker just worked on 
			this.cust_index= PostOffice.cust_index;
			System.out.println("Postal Worker "+index+" serving Customer "+ this.cust_index);
			
			PostOffice.worker_index=this.index;
			
			//to let the customer know that 
			//he can ask for the service he wants
			serve_signal.release();//signal(serve_signal)
			
			//Signal that the counter is available
			counterAvailable.release();//signal(counterAvailable)
					
			this.customer_action= PostOffice.customer_action;
			
			//wait for customer to ask for the job
			try{order.acquire();} //wait(order)
			catch (InterruptedException e){}
			
			//mutual exclusion ends here
			mutex2.release();//signal(mutex2)
					
			switch(this.customer_action)
			{
			case 1:
				try{Thread.sleep(1000);}
				catch (InterruptedException e){ }
				break;
			case 2:
				try{Thread.sleep(1500);}
				catch (InterruptedException e){}
				break;
			case 3:
				//to ensure mutual exclusion to the scales
				try{scaleInUse.acquire();}//wait(scaleInUse)
				catch (InterruptedException e){}
				System.out.println("Scales in use by postal worker "+ index);
				try{Thread.sleep(2000);}
				catch (InterruptedException e) {}
				System.out.println("Scales released by postal worker "+ index);
				//mutual exclusion of the scale ends here
				scaleInUse.release();//signal(scaleInUse)
				break;
			}
			
			System.out.println("Postal worker " +index+ " finished serving customer "+ this.cust_index);
			finished[this.cust_index].release();//signal(finished[cust_index])
			order_finish.release();	//to signal that the customer has confirmed that customer is done with his work	
			try{freeCounter.acquire();}//waiting for customer to leave and the counter to be free
			catch (InterruptedException e){}
			worker.release(); //signal(worker)
		}
	}
}