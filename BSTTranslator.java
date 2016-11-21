/**
 * BST Translator Interface
 *
 * @author Bryce Farrell
 *
 * @version Program 4
 * @version Fall 2016
 */

import java.util.*;

public interface BSTTranslator<T extends Comparable<? super T>>
{
    public BST<T> getBST();
    public String translate(String s);
}
