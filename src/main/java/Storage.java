import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;
    Storage()
    {
        quoteList = new ArrayList<>();
        quoteList.add("сообщение 1 ");
        quoteList.add("сообщение 2 ");
        quoteList.add("сообщение 3 ");
    }

    String getRandQuote()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int)(Math.random() * quoteList.size());
        //Из коллекции получаем сообщение со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }
}