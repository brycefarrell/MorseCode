/**
 * MorseOrder Class
 *
 * @author Bryce Farrell
 *
 * @version Program 4
 * @version Fall 2016
 */

import java.util.*;

public class MorseOrder extends MorseCode implements Comparable<MorseOrder>
{   
    
    public MorseOrder(Character character, String code)
    {
        super(character, code);
    }
    
    public MorseOrder(MorseOrder mo)
    {
        super(mo);
    }
    
    public int compareTo(MorseOrder other)
    {
        return this.getCode().compareTo(other.getCode());
    }
}
