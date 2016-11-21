/**
 * MorseToText Class
 *
 * @author Bryce Farrell
 *
 * @version Program 4
 * @version Fall 2016
 */

import java.util.*;

public class MorseToText implements BSTTranslator<MorseOrder>
{
    private BST<MorseOrder> bst;

    public MorseToText()
    {
        //construct a temp BST, the actual BST, and get size of MorseCode array
        BST<MorseOrder> unsortedBst = new BST<MorseOrder>();
        bst = new BST<MorseOrder>();
        int size = MorseCode.size();
        
        //cast and add every element of array to BST
        for (int x = 0; x < size; x++)
        {
            MorseCode mc = MorseCode.get(x);
            MorseOrder mo = new MorseOrder(mc.getCharacter(), mc.getCode());
            unsortedBst.insert(mo);
        }

        //create a sortedArrayList
        ArrayList<MorseOrder> sortedArrayList = new ArrayList<MorseOrder>();
        unsortedBst.toSortedList(sortedArrayList);

        //System.out.println(sortedArrayList.size());
        //System.out.println(sortedArrayList.get(0).getCode());
        
        //convert sorted arraylist to balanced BST
        SALtoBST(sortedArrayList, 0, size - 1);
      
    }
    
    private void SALtoBST(ArrayList<MorseOrder> AL, int start, int end)
    {
        if (start <= end)
        {
            int mid = (start + end) / 2;
            MorseOrder t = AL.get(mid);
            bst.insert(t);
            SALtoBST(AL, start, mid - 1);
            SALtoBST(AL, mid + 1, end);
        }
    }

    public BST<MorseOrder> getBST()
    {
        return bst;
    }
    public String translate(String s)
    {
        String rv = "";
        Scanner sc = new Scanner(s).useDelimiter(" ");;

        while (sc.hasNext())
        {
            String morse = sc.next();
            Character text;
            
            if (morse.equals(""))
            {
                rv += " ";
            }
            else
            {
                //create temporary morseorder to compare with arbitrary char value
                MorseOrder tempMo = new MorseOrder('A', morse);
                
                if (bst.contains(tempMo))
                {
                    tempMo = bst.get(tempMo);
                    text = tempMo.getCharacter();
            
                    rv += text;  
                }
            }
        }
        return rv.trim();

    }
}
