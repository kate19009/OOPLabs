package sample.labs.Lab3.src;

import sample.Controller;
import sample.labs.Lab3.src.Parcel;

public class Sender extends Thread
{
    private Controller controller ;
    Parcel parcel;
    int number;
    String name = "Sender";
    public Sender(Controller controller, Parcel parcel_)
    {
        this.controller = controller ;
        number = parcel_.numberOfParcel;
        parcel = parcel_;
    }
    public void run()
    {
        synchronized (parcel)
        {
        for (int i = 0; i < number; i++)
        {

                parcel.notify();
                try
                {

                    controller.outputText.appendText(name + " sent parcel number " + (i + 1)+"\n");
                    parcel.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            parcel.notify();
         }

    }
}
