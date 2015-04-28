package huji.ac.il.todoapplistex4;

import java.util.Date;

/**
 * Created by Omer on 26/04/2015.
 */
public class TodoItem
{
    String m_strTodoContent;
    Date m_dtExpirationDate;
    public TodoItem(String p_strTodoContent,Date p_dtExpiration)
    {
        m_strTodoContent = p_strTodoContent;
        m_dtExpirationDate = p_dtExpiration;
    }
}
