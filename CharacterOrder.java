/**
 * CharacterOrder Class
 *
 * @author Bryce Farrell
 *
 * @version Program 4
 * @version Fall 2016
 */

import java.util.*;

public class CharacterOrder extends MorseCode implements Comparable<CharacterOrder>
{
    public CharacterOrder(Character character, String code)
    {
        super(character, code);
    }
    
    public CharacterOrder(CharacterOrder co)
    {
        super(co);
    }
    
    public int compareTo(CharacterOrder other)
    {
        return this.getCharacter().compareTo(other.getCharacter());
    }
}
