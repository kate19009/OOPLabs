public class Threads extends Thread
{
    private int numberOfThreads;
    private int numberOfStrings;
    public Object wait;
    private int count;
    boolean lastEl = false;

    public Threads(Object waitE, int numberThreads, int numberStrings, int k)
    {
        numberOfThreads = numberThreads;
        numberOfStrings = numberStrings;
        count = k;
        wait = waitE;
    }

    public void run()
    {
        for (int i = 0; i < numberOfStrings; i++)
        {

            if (wait!=null && (count != 0 || i != 0))
            {
                synchronized (wait)
                {
                    try
                    {
                        if (numberOfThreads != 1)
                        {

                            wait.wait();
                        }

                    } catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    wait.notify();
                }
            }
            System.out.print(this.getName() + " ");
            if (lastEl)
            {
                System.out.println("");
            }
            synchronized (this)
            {
                this.notify();
            }
        }
    }
}