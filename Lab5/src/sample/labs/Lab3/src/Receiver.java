package sample.labs.Lab3.src;

import sample.Controller;

public class Receiver extends Thread
{
    Parcel parcel;
    private Controller controller ;
    int number;
    boolean flag;
    String name = "Receiver";
    public Receiver(Controller controller,Parcel parcel_)
    {
        this.controller = controller ;
        number = parcel_.numberOfParcel;
        parcel = parcel_;
        flag = parcel.flag;
    }
    public void run()
    {
        synchronized (parcel)
        {
        for (int i=0; i < number; i++ )
        {
            parcel.notify();
            try
            {
                controller.outputText.appendText(name + " received parcel number " + (i + 1)+"\n");
                parcel.wait();
            }
            catch (InterruptedException e)
            {
                 e.printStackTrace();
            }
        }
        }

    }
}
