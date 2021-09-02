class pc
{
	public static int x;
	synchronized void produce(int n)
	{ while(x!=NULL){try {wait();} catch(Exception e){System.out.println("STORAGE FULL!");}}
	  System.out.println("PRODUCING "+n);
	  x=n;
	  notify();
	  try{Thread.sleep(500);} catch(Exception e){System.out.println(e);}
	}
	synchronized void consume()
	{ while(x==NULL) {try {wait();} catch(Exception e){System.out.println("STORAGE EMPTY!");}}
	  System.out.println("Consuming"+x);
	  x=NULL;
	  notify();
	  try{Thread.sleep(500);} catch(Exception e){System.out.println(e);}
	}

}
class thread1 extends Thread
{
	pc obj;   //reference of table class
	thread1(pc p)
	{
		obj=p;
	}
	public void run()
	{ obj.produce(5); }
}
class thread2 extends Thread
{
	pc obj;   //reference of table class
	thread2(pc c)
	{
		obj=c;
	}
	public void run()
	{ obj.consume(); }
}
class main
{
	public static void main(String args[])
	{ table obj=new pc();          //obj name reference for table class
	  thread1 t1=new thread1(obj);
	  thread2 t2=new thread2(obj);
	  t1.start();
	  t2.start();
	}
}

			