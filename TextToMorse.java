/**
 * TextToMorse Class
 *
 * @author Bryce Farrell
 *
 * @version Program 4
 * @version Fall 2016
 */

import java.util.*;

public class TextToMorse implements BSTTranslator<CharacterOrder>
{
    private BST<CharacterOrder> bst;

    public TextToMorse()
    {
        BST<CharacterOrder> unsortedBst = new BST<CharacterOrder>();
        bst = new BST<CharacterOrder>();
        int size = MorseCode.size();
        
        for (int x = 0; x < size; x++)
        {
            MorseCode mc = MorseCode.get(x);
            CharacterOrder mo = new CharacterOrder(mc.getCharacter(), mc.getCode());
            unsortedBst.insert(mo);
        }

        //create a sortedArrayList
        ArrayList<CharacterOrder> sortedArrayList = new ArrayList<CharacterOrder>();
        unsortedBst.toSortedList(sortedArrayList);

        //System.out.println(sortedArrayList.size());
        //System.out.println(sortedArrayList.get(0).getCode());
        
        //convert sorted arraylist to balanced BST
        SALtoBST(sortedArrayList, 0, size - 1);
      
    }
    
    private void SALtoBST(ArrayList<CharacterOrder> AL, int start, int end)
    {
        if (start <= end)
        {
            int mid = (start + end) / 2;
            CharacterOrder t = AL.get(mid);
            bst.insert(t);
            SALtoBST(AL, start, mid - 1);
            SALtoBST(AL, mid + 1, end);
        }
    }

    public BST<CharacterOrder> getBST()
    {
        return bst;
    }
    public String translate(String s)
    {
        String rv = "";
        Scanner sc = new Scanner(s).useDelimiter("");

        while (sc.hasNext())
        {
            String character = sc.next();
            String text;
            if (character.equals(" "))
            {
                rv += " ";
            }
            else
            {
                CharacterOrder tempCo = new CharacterOrder(character.charAt(0), "-");
                
                if (bst.contains(tempCo))
                {
                    tempCo = bst.get(tempCo);
                    text = tempCo.getCode();
            
                    rv += text + " ";  
                }
            }
        }
        return rv.trim();

    }
}
