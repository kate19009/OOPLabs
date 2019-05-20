public class Receiver extends Thread
{
    Parcel parcel;
    int number;
    boolean flag;
    public Receiver(Parcel parcel_)
    {
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
                System.out.println(Thread.currentThread().getName() + " received parcel number " + (i + 1));
                System.out.println();
                parcel.wait();
            }
            catch (InterruptedException e)
            {
                 e.printStackTrace();
            }
        }
        //parcel.notify();
        }

    }
}
