package quickquizquestions;

import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerMain implements Initializable
{
    //region <Golbal variables & FXML imports>
    @FXML public TableView<Question> tableView;
    @FXML public TableColumn<Question, Integer> questionNumberCol;
    @FXML public TableColumn<Question, String> topicCol;
    @FXML public TableColumn<Question, String> questionCol;

    @FXML public TextField topicText;
    @FXML public TextArea questionText;
    @FXML public TextArea aText;
    @FXML public TextArea bText;
    @FXML public TextArea cText;
    @FXML public TextArea dText;
    @FXML public TextField corrText;

    ObservableList<Question> quiz = FXCollections.observableArrayList();

    //endregion

    //region <initialization: setup Table View, import data from ObservableList<Question>>
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        questionNumberCol.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        topicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
        questionCol.setCellValueFactory(new PropertyValueFactory<>("question"));

        questionNumberCol.setSortable(false);
        topicCol.setSortable(false);
        questionCol.setSortable(false);

        tableView.setItems(getQuiz());
    }
    //endregion

    //region <Read data file, return ObservableList<Question>>
    public ObservableList<Question> getQuiz()
    {
        FileInputStream fis = null;

        try
        {
            fis = new FileInputStream("questions.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String str;
            ArrayList<String> lines = new ArrayList<>();

            while ((str = reader.readLine()) != null)
            {
                lines.add(str);
            }

            int index = 0;
            for (int i = 0; i < (lines.size() / 8); i++)
            {
                Question question = new Question();

                question.setQuestionNumber(Integer.parseInt(lines.get(index)));
                index++;
                question.setTopic(lines.get(index));
                index++;
                question.setQuestion(lines.get(index));
                index++;
                question.setOptionA(lines.get(index));
                index++;
                question.setOptionB(lines.get(index));
                index++;
                question.setOptionC(lines.get(index));
                index++;
                question.setOptionD(lines.get(index));
                index++;
                question.setCorrectAnswer(lines.get(index));
                index++;

                quiz.add(question);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fis != null) fis.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return quiz;
    }
    //endregion

    //region <Send selected row data to Text Fields>
    public void tableRowSelect()
    {
        Question selected = quiz.get(tableView.getSelectionModel().getSelectedIndex());

        topicText.setText(selected.getTopic());
        questionText.setText(selected.getQuestion());
        aText.setText(selected.getOptionA());
        bText.setText(selected.getOptionB());
        cText.setText(selected.getOptionC());
        dText.setText(selected.getOptionD());
        corrText.setText(selected.getCorrectAnswer());
    }
    //endregion

    //region <Sort algorithms>

    //region <Selection sort>
    public void selectionSort()
    {
        int i, j, first;
        Question temp;

        //if not already, sort ascending
        if (quiz.get(0).getQuestionNumber() > quiz.get(1).getQuestionNumber())
        {
            for (i = quiz.size() - 1; i > 0; i--)
            {
                first = 0; //initialize to subscript of first element
                for (j = 1; j <= i; j++) //locate smallest element between positions 1 and i.
                {
                    if (quiz.get(j).getQuestionNumber() > quiz.get(first).getQuestionNumber())
                    {
                        first = j;
                    }
                }
                temp = quiz.get(first); //swap smallest found with element in position i.
                quiz.set(first, quiz.get(i));
                quiz.set(i, temp);
            }
        }

        //if not already, sort descending
        else if (quiz.get(0).getQuestionNumber() < quiz.get(1).getQuestionNumber())
        {
            for (i = quiz.size() - 1; i > 0; i--)
            {
                first = 0; //initialize to subscript of first element
                for (j = 1; j <= i; j++) //locate smallest element between positions 1 and i.
                {
                    if (quiz.get(j).getQuestionNumber() < quiz.get(first).getQuestionNumber())
                    {
                        first = j;
                    }
                }
                temp = quiz.get(first); //swap smallest found with element in position i.
                quiz.set(first, quiz.get(i));
                quiz.set(i, temp);
            }
        }
    }
    //endregion

    //region <Bubble sort>
    public void bubbleSort()
    {
        Question temp;
        int i, j, c = 0;

        //find first instance of differing values of the element to be sorted
        while (quiz.get(c).getTopic().compareToIgnoreCase(quiz.get(c + 1).getTopic()) == 0)
        {
            c++;
        }

        //if not already, sort ascending
        if (quiz.get(c).getTopic().compareToIgnoreCase(quiz.get(c + 1).getTopic()) > 0)
        {
            for (i = 0; i < quiz.size() - 1; i++)
            {
                for (j = 0; j < quiz.size() - 1 - i; j++)
                {
                    String stringOne = quiz.get(j).getTopic();
                    String stringTwo = quiz.get(j + 1).getTopic();

                    if (stringOne.compareToIgnoreCase(stringTwo) > 0)
                    {
                        temp = quiz.get(j);
                        quiz.set(j, quiz.get(j+1));
                        quiz.set(j + 1, temp);
                    }
                }
            }
        }

        //if not already, sort descending
        else if (quiz.get(c).getTopic().compareToIgnoreCase(quiz.get(c + 1).getTopic()) < 0)
        {
            for (i = 0; i < quiz.size() - 1; i++)
            {
                for (j = 0; j < quiz.size() - 1 - i; j++)
                {
                    String stringOne = quiz.get(j).getTopic();
                    String stringTwo = quiz.get(j + 1).getTopic();

                    if (stringOne.compareToIgnoreCase(stringTwo) < 0)
                    {
                        temp = quiz.get(j);
                        quiz.set(j, quiz.get(j+1));
                        quiz.set(j + 1, temp);
                    }
                }
            }
        }
    }
    //endregion

    //region <Exchange sort>
    public void exchangeSort()
    {
        int i,j,c = 0;
        Question temp;

        while (quiz.get(c).getQuestion().compareToIgnoreCase(quiz.get(c+1).getQuestion()) == 0)
        {
            c++;
        }

        if (quiz.get(c).getQuestion().compareToIgnoreCase(quiz.get(c+1).getQuestion()) > 0)
        {
            for (i = 0; i < quiz.size() - 1; i++)
            {
                for (j = i + 1; j < quiz.size(); j++)
                {
                    if (quiz.get(i).getQuestion().compareToIgnoreCase(quiz.get(j).getQuestion()) > 0)
                    {
                        temp = quiz.get(i);
                        quiz.set(i, quiz.get(j));
                        quiz.set(j, temp);
                    }
                }
            }
        }
        else if (quiz.get(c).getQuestion().compareToIgnoreCase(quiz.get(c+1).getQuestion()) < 0)
        {
            for (i = 0; i < quiz.size() - 1; i++)
            {
                for (j = i + 1; j < quiz.size(); j++)
                {
                    if (quiz.get(i).getQuestion().compareToIgnoreCase(quiz.get(j).getQuestion()) < 0)
                    {
                        temp = quiz.get(i);
                        quiz.set(i, quiz.get(j));
                        quiz.set(j, temp);
                    }
                }
            }
        }
    }
    //endregion

    //endregion

    //region <Trivial methods>
    public void exit()
    {
        Platform.exit();
        System.exit(0);
    }
    //endregion
}