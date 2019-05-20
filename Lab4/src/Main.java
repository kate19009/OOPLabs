import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        int numberOfThreads, numberOfStrings;
        try
        {
            System.out.println("Введите количество потоков");
            Scanner in = new Scanner(System.in);
            numberOfThreads = Integer.parseInt(in.nextLine());
            System.out.println("Введите количество строк");
            numberOfStrings = Integer.parseInt(in.nextLine());

            Threads[] threads = new Threads[numberOfThreads];
            threads[0] = new Threads(null, numberOfThreads, numberOfStrings, 0);
            for (int i = 1; i < numberOfThreads; i++)
            {
                threads[i] = new Threads(threads[i - 1], numberOfThreads, numberOfStrings, i);

            }
            threads[0].wait = threads[numberOfThreads - 1];
            threads[numberOfThreads - 1].lastEl = true;

            for (int i = numberOfThreads; i >0; i--)
            {

                new Thread(threads[i-1]).start();
            }
        } catch (NumberFormatException e)
        {
            System.out.println("Error. Wrong input data.");
        }

    }
}