import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Введите количество параметров");
            Scanner in = new Scanner(System.in);
            int numberPar = Integer.parseInt(in.nextLine());
            String[] array = new String[numberPar];
            System.out.println("Введите параметры");
            for (int i=0; i<numberPar;i++)
            {
                array[i] = in.nextLine();
            }
            String[] arrayNew = new String[numberPar];
            int  sumFirst, sumSecond, numb1, numb2,lengN,k;
            String str1, str2;
            boolean flag = false;
            System.out.println("Результат");
            for (int i=0; i<numberPar;i++)
            {
                System.out.println("Результат");
                lengN = array[i].length()/2;
                k=lengN;
                sumFirst=0;
                sumSecond=0;
                if (array[i].length()%2==0)
                {
                    if (array[i].matches("^-?\\d+$"))
                    {
                        if (array[i].length()!=2)
                        {
                            numb1 = Integer.parseInt(array[i].substring(0, lengN));
                            numb2 = Integer.parseInt(array[i].substring(lengN ));

                        while ( numb1!= 0 )
                        {
                            sumFirst +=numb1%10;
                            numb1 /=10;
                            sumSecond +=numb2%10;
                            numb2 /=10;
                        }
                        if (sumFirst==sumSecond)
                            System.out.println(array[i]);
                        }
                        else if (array[i].charAt(array[i].length()-1)==array[i].charAt(0))
                            System.out.println(array[i]);
                    }
                    else
                    {
                        if (array[i].length()!=2)
                        {
                            str1 = array[i].substring(0, lengN);
                            str2 = array[i].substring(lengN);
                            for (int j=0;j<lengN;j++)
                            {
                               if (str1.charAt(j) != str2.charAt(k-1))
                                   break;
                               if (j==lengN-1) flag=true;
                               k--;
                            }
                            if (flag==true)
                            {
                                System.out.println(array[i]);
                                flag=false;
                            }
                        }
                    }

                    //System.out.println(arrayNew[j]);


                }
            }
        }catch (NumberFormatException e)
        {
            System.out.println("Error. Wrong input data.");
        }
    }
}
