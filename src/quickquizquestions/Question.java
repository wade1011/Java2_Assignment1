package quickquizquestions;


import javafx.collections.ObservableList;

public class Question
{
    private Integer questionNumber;
    private String topic;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Integer getQuestionNumber()
    {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber)
    {
        this.questionNumber = questionNumber;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getOptionA()
    {
        return optionA;
    }

    public void setOptionA(String optionA)
    {
        this.optionA = optionA;
    }

    public String getOptionB()
    {
        return optionB;
    }

    public void setOptionB(String optionB)
    {
        this.optionB = optionB;
    }

    public String getOptionC()
    {
        return optionC;
    }

    public void setOptionC(String optionC)
    {
        this.optionC = optionC;
    }

    public String getOptionD()
    {
        return optionD;
    }

    public void setOptionD(String optionD)
    {
        this.optionD = optionD;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }
}