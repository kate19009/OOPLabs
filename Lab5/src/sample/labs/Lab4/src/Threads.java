package sample.labs.Lab4.src;

import sample.Controller;

public class Threads extends Thread
{
    private int numberOfThreads;
    private int numberOfStrings;
    String name;
    public Object wait;
    private int count;
    public boolean lastEl = false;
    private Controller controller ;
    public Threads(String nameTh, Controller controller,Object waitE, int numberThreads, int numberStrings, int k)
    {
        this.controller = controller;
        numberOfThreads = numberThreads;
        numberOfStrings = numberStrings;
        count = k;
        wait = waitE;
        name = nameTh;
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
            controller.outputText.appendText(name + " ");
            if (lastEl)
            {
                controller.outputText.appendText("\n");
            }
            synchronized (this)
            {
                this.notify();
            }
        }
    }
}