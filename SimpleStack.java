/**
 * @author Bryce Farrell
 *
 * @version Lab 3
 * @version Simple Stack Interface
 */

import java.util.*;

public interface SimpleStack<T>
{
    
    public T peek();
    public T pop();
    public void push(T element);
    public int size();

}

