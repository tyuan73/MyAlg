class TestMultiThread implements Runnable {
	//Buffer mybuffer = new Buffer();
	class Buffer {
		private boolean empty = true;
		volatile boolean termed = false;
		private String message = null;
		public synchronized void put(String msg) {
		//System.out.println("in buffer.put"+msg);
			if(!empty) {
				try {
					wait();
					//Thread.sleep(100);
				} catch (InterruptedException e) {//System.out.println("wokenup");
				}
			}
			empty = false;
			this.message = msg;
			System.out.println("added : " + msg);
			notifyAll();	
		}
		
		public synchronized void get() {
			//System.out.println("in buffer.get");
			while(!termed) {
				if(empty) {
					try {
						wait();
						//Thread.sleep(100);
					} catch (InterruptedException e) {};
				}
				empty = true;
				System.out.println("got & removed: " + this.message);
				this.message = null;
				notifyAll();
			}
		}
	}
	
	class Producer implements Runnable {
		String[] allMsg = null;
		Buffer b;
		public Producer (Buffer bf, String[] msg) {this.b = bf; this.allMsg = msg;}
		
		public void run() {
			System.out.println("Producer started ");
			for(String s : allMsg)
				b.put(s);
			b.termed = true;
		}
	}
	
	class Consumer implements Runnable {
		Buffer b;
		public Consumer (Buffer bf) {this.b = bf;}
		public void run() {
			System.out.println("consumer started ");
			// infinite loop
			//while(true) {
				b.get();
			//}
		}
	}
	
	static public void main(String[] args) {
		TestMultiThread tt = new TestMultiThread();
		tt.demo();
		System.out.println("Exit main thread!");
	}
	
	public void demo() {		
		Buffer b = new Buffer();
		String[] msg = {"Goog job", "wonderfule job", "excellent", "continue working", "will stop soon"};
		Thread t1 = new Thread(new Producer(b, msg));
		Thread t2 = new Thread(new Consumer(b));
		t1.start();
		t2.start();		
		/*
		try {
		while(t1.isAlive()) {
			Thread.sleep(1000);
		}

		t2.stop();
		} catch (InterruptedException e) {}
		*/
	}
	
	public void run() {
		// nothing , since this main object will not be called to start.
	}
/*
	int total = 0;
	static public void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new TestMultiThread());
		//total++;
		t.start();
		Thread.sleep(500);
		System.out.println("stop sleeping");
		int count = 3;
		while(t.isAlive()) {
			t.join(500);
			count--;
			if(count > 0)
				System.out.println("Still wait");
			else {
				System.out.println("now kill...");
				t.interrupt();
				t.join();
			}
				
		}
		System.out.println("exit main thread");
	}
	
	public void run() {
		int num = total;
		System.out.println("enter sub thread" + num);
		if(total < 4) {
			Thread t = new Thread(this);
			total++;
			t.start();		
			try {
				Thread.sleep(4000);
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				t.interrupt();
			}

		}

		
		System.out.println("exit sub thread" + num);
	}
*/
}