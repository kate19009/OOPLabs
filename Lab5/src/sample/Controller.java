package sample;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import sample.labs.Lab3.src.Parcel;
import sample.labs.Lab3.src.Receiver;
import sample.labs.Lab3.src.Sender;
import sample.labs.Lab4.src.Threads;


public class Controller
{

    @FXML
    public TextArea inputText;
    public TextArea outputText;
    public Button exitBut;

    @FXML

    public void initialize()
    {
        loadData();
    }

    void doTask1(int numberPar, String[] array)
    {
        int sumFirst, sumSecond, numb1, numb2, lengN, k;
        String str1, str2;
        boolean flag = false;
        boolean done = false;
        outputText.appendText("Результат:\n");
        for (int i = 0; i < numberPar; i++)
        {
            lengN = array[i].length() / 2;
            k = lengN;
            sumFirst = 0;
            sumSecond = 0;
            if (array[i].length() % 2 == 0)
            {
                if (array[i].matches("^-?\\d+$"))
                {
                    if (array[i].length() != 2)
                    {
                        numb1 = Integer.parseInt(array[i].substring(0, lengN));
                        numb2 = Integer.parseInt(array[i].substring(lengN));

                        while (numb1 != 0)
                        {
                            sumFirst += numb1 % 10;
                            numb1 /= 10;
                            sumSecond += numb2 % 10;
                            numb2 /= 10;
                        }
                        if (sumFirst == sumSecond)
                        {
                            outputText.appendText(array[i] + "\n");
                            done = true;
                        }
                    } else if (array[i].charAt(array[i].length() - 1) == array[i].charAt(0))
                    {
                        outputText.appendText(array[i] + "\n");
                        done = true;
                    }
                } else
                {
                    if (array[i].length() != 2)
                    {
                        str1 = array[i].substring(0, lengN);
                        str2 = array[i].substring(lengN);
                        for (int j = 0; j < lengN; j++)
                        {
                            if (str1.charAt(j) != str2.charAt(k - 1))
                                break;
                            if (j == lengN - 1) flag = true;
                            k--;
                        }
                        if (flag)
                        {
                            outputText.appendText(array[i] + "\n");
                            flag = false;
                            done = true;
                        }
                    }
                }
            }
        }
        if (!done)
        {
            outputText.appendText("Таких строк нет\n");
        }
    }

    void doTask3(int numberPar)
    {
        try
        {
            Parcel parcel = new Parcel();
            parcel.getNumber(numberPar);
            Sender sender = new Sender(this,parcel);
            Receiver receiver= new Receiver(this,parcel);
            sender.start();
            receiver.start();
        }
        catch(NumberFormatException e)
        {
            System.out.println("Wrong input data");
        }
    }

    void doTask4(int numberOfThreads, int numberOfStrings)
    {
        try
        {
            Threads[] threads = new Threads[numberOfThreads];
            threads[0] = new Threads("Thread-0",this,null, numberOfThreads, numberOfStrings, 0);
            for (int i = 1; i < numberOfThreads; i++)
            {
                threads[i] = new Threads("Thread-"+i,this,threads[i - 1], numberOfThreads, numberOfStrings, i);

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

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> choices;

    private void loadData()
    {
        list.removeAll(list);
        list.addAll("Задание 1", "Задание 3", "Задание 4");
        choices.getItems().addAll(list);

    }

    public void doTask(ActionEvent actionEvent)
    {
        if (choices.getValue() != null)
        {
            Integer str = choices.getSelectionModel().getSelectedIndex();
            switch (str)
            {
                case 0:
                    try
                    {
                        String[] rows = inputText.getText().split("\n");
                        String[] dataArr = new String[rows.length];
                        System.arraycopy(rows, 1, dataArr, 0, rows.length-1);
                        doTask1(dataArr.length-1, dataArr);
                    } catch (Exception e)
                    {
                        System.out.println("Error. Wrong input data.");
                    }
                    break;
                case 1:
                    try
                    {
                        String[] rows = inputText.getText().split("\n");
                        doTask3(Integer.parseInt(rows[1]));
                    } catch (Exception e)
                    {
                        System.out.println("Error. Wrong input data.");
                    }
                    break;
                case 2:
                    try
                    {
                        String[] rows = inputText.getText().split("\n");
                      doTask4(Integer.parseInt(rows[1]), Integer.parseInt(rows[3]));
                    } catch (Exception e)
                    {
                        System.out.println("Error. Wrong input data.");
                    }
                    break;
                default:
                    System.out.println("no match");
            }
        }
    }


    public void folderByCategory(ActionEvent actionEvent)
    {
        if (choices.getValue() != null)
        {
            String str = choices.getSelectionModel().getSelectedItem();

            switch (str)
            {
                case "Задание 1":
                    try
                    {
                        inputText.clear();
                        outputText.clear();
                        choices.setValue("");
                        inputText.getScrollTop();
                        String firstStr = "Введите параметры:\n";

                        inputText.setText(firstStr);
                        inputText.appendText("\n");


                        inputText.appendText("\n");
                        inputText.setTextFormatter(new TextFormatter<String>((TextFormatter.Change c) ->
                        {
                            String proposed = c.getControlNewText();
                            if ((proposed.startsWith(firstStr)) && proposed.endsWith("\n"))
                            {
                                return c;
                            } else
                            {
                                return null;
                            }
                        }));
                    } catch (Exception e)
                    {
                        System.out.println("Error. Wrong input data.");
                    }
                    break;
                case "Задание 3":
                    try
                    {
                        inputText.clear();
                        outputText.clear();
                        choices.setValue("");
                        inputText.getScrollTop();
                        String firstStr = "Введите количество посылок:\n";
                        inputText.setText(firstStr);
                        inputText.appendText("\n");
                        inputText.setTextFormatter(new TextFormatter<String>((TextFormatter.Change c) ->
                        {
                            String proposed = c.getControlNewText();
                            if ((proposed.startsWith(firstStr)) && proposed.endsWith("\n"))
                            {
                                return c;
                            } else
                            {
                                return null;
                            }
                        }));
                    } catch (Exception e)
                    {
                        System.out.println("Error. Wrong input data.");
                    }
                    break;
                case "Задание 4":
                    try
                    {
                        inputText.clear();
                        outputText.clear();
                        choices.setValue("");
                        inputText.getScrollTop();
                        String firstStr = "Введите количество потоков:\n";
                        String secondStr = "Введите количество строк:\n";
                        inputText.setText(firstStr);
                        inputText.appendText("\n");
                        inputText.appendText(secondStr);
                        inputText.appendText("\n");
                        inputText.setTextFormatter(new TextFormatter<String>((TextFormatter.Change c) ->
                        {
                            String proposed = c.getControlNewText();
                            if ((proposed.startsWith(firstStr)) && proposed.endsWith("\n"))
                            {
                                return c;
                            } else
                            {
                                return null;
                            }
                        }));
                    } catch (Exception e)
                    {
                        System.out.println("Error. Wrong input data.");
                    }
                    break;

            }
        }
    }

    public void exit(ActionEvent actionEvent)
    {

        Stage stage = (Stage) exitBut.getScene().getWindow();
        stage.close();
    }

    public void clearField(ActionEvent actionEvent)
    {
        try
        {
            inputText.clear();
            outputText.clear();
            inputText.setText("\n");

            choices.getSelectionModel().select(choices.getSelectionModel().getSelectedIndex());
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
