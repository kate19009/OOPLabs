import java.util.Scanner;

public class Parcel
{
    public boolean flag;
    public int numberOfParcel;
    public void getNumber()
    {
        try
        {
            Scanner in = new Scanner(System.in);
            numberOfParcel = Integer.parseInt(in.nextLine());
            System.out.println();
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error. Wrong input data. ");
        }
    }
}
