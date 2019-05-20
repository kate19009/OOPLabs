
public class Main
{
    public static void main(String[] args) throws Exception
    {
        try
        {
        System.out.print("Please enter the number of parcels ");
        Parcel parcel = new Parcel();
        parcel.getNumber();
        Sender sender = new Sender(parcel);
        Receiver receiver= new Receiver(parcel);

            sender.start();
            receiver.start();
        }
        catch(NumberFormatException e)
        {
            System.out.println("Wrong input data");
        }

    }
}