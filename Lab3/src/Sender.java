public class Sender extends Thread
{
    Parcel parcel;
    int number;
    public Sender(Parcel parcel_)
    {
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
                    System.out.println(Thread.currentThread().getName() + " sent parcel number " + (i + 1));
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
