package sample.labs.Lab3.src;

import java.util.Scanner;

public class Parcel
{
    public boolean flag;
    public int numberOfParcel;
    public void getNumber(int number)
    {
        try
        {
            numberOfParcel = number;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error. Wrong input data. ");
        }
    }
}
