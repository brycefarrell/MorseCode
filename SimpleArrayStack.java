/**
 * @author Bryce Farrell
 *
 * @version Lab 3
 * @version Simple Array Stack Class
 */

import java.util.*;

public class SimpleArrayStack<T> implements SimpleStack<T>
{
    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArrayStack()
    {
        arr = (T[])new Object[10];
        size = 0;

    }

    public T peek()
    {
        if (size == 0)
        {
            throw new NoSuchElementException();
        }
        return arr[size - 1];
    }
    public T pop()
    {
        
        T rv;
        if (size == 0)
        {
            throw new NoSuchElementException();
        }

        rv = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return rv;


    }

    @SuppressWarnings("unchecked")
    public void push(T element)
    {
        if (size == arr.length)
        {
            T[] temp = (T[])new Object[size * 2];

            for (int x = 0; x < arr.length; x++)
            {
                temp[x] = arr[x];
            }
            arr = temp;
        }

        arr[size] = element;
        size++;
    }

    public int size()
    {
        return size;
    }

    public int capacity()
    {
        return arr.length;
    }
}

