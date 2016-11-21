import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;


public class BST<T extends Comparable<? super T>> implements Iterable<T>{
   private final BSTNode<T> EMPTY_NODE = new EmptyNode();
   private BSTNode<T> root = EMPTY_NODE;
   private int size;

  
   private interface BSTNode<T> {
      public BSTNode<T> insert(T element);
      public boolean contains(T element);
      public T minimum(T minimum);
      public T maximum(T maximum);
      public void toSortedList(List<T> list);
      public int treeHeight();
      public long internalPathLength(int depth);
      public BSTNode<T> remove(T element);
      public T get(T element);
   }

   ////////////////////////////////////////////////////////////////////////////
   // BST class methods...
   //

   
   public void insert(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }

      root = root.insert(element);
   }

   
   public boolean contains(T element) {
      if (element == null) {
         return false;
      }

      return root.contains(element);
   }

   public T minimum() {
      if (size == 0) {
         throw new NoSuchElementException();
      }

      return root.minimum(((Node)root).element);
   }

 
   public T maximum() {
      if (size == 0) {
         throw new NoSuchElementException();
      }

      return root.maximum(((Node)root).element);
   }

   /**
    * Takes the elements in the BST and places them in ascending order into the
    * list.
    *
    * @param list the list in which you will place elements
    */
   public void toSortedList(List<T> list) {
      root.toSortedList(list);
   }

   public int size() {
      return size;
   }
   
   public void remove(T element)
   {      
      root = root.remove(element);
      size--;
   }

   public int treeHeight()
   {
      return root.treeHeight();
   }

   public long internalPathLength()
   {
      return root.internalPathLength(0);
   }

   public T get(T element)
   {
      return root.get(element);
   }

   public Iterator<T> iterator()
   {
      return new BSTIterator();
   }

   //iterator!
   private class BSTIterator implements Iterator<T>
   {
      private SimpleArrayStack<Node> stack = new SimpleArrayStack<Node>();
      private Node current;
      
      public BSTIterator()
      {
        if (root != EMPTY_NODE)
        {
            stack.push((Node)root);
        
            current = stack.peek();
            while (current.left != EMPTY_NODE)
            {
                current = (Node)current.left;
                stack.push(current);
            }
        }
      }

      public boolean hasNext()
      {
        return !(stack.size() == 0);
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }

      public T next()
      {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        }

        current = stack.pop();
        T rv = current.element;

        if (current.right != EMPTY_NODE)
        {
            current = (Node)current.right;
            stack.push(current);
            while (current.left != EMPTY_NODE)
            {
                current = (Node)current.left;
                stack.push(current);
            }
        }
        return rv;
      }

   }
   ////////////////////////////////////////////////////////////////////////////
   // private EmptyNode class...
   //
   private class EmptyNode implements BSTNode<T> {

      public BSTNode<T> insert(T element) {
         size++;
         return new Node(EMPTY_NODE, EMPTY_NODE, element);
      }
      

      public boolean contains(T element) {
         return false;
      }

      public T minimum(T element) {
         return element;
      }

      public T maximum(T element) {
         return element;
      }
      
      public void toSortedList(List<T> list) {
		 //do nothing
      }

      public int treeHeight()
      {
        return -1;
      }

      public long internalPathLength(int depth)
      {
        if (size == 0)
        {
            return -1;
        }
        return 0;
      }
      
      public BSTNode<T> remove(T element)
      {
        throw new NoSuchElementException();
      }

      public T get(T element)
      {
        throw new NoSuchElementException();
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // private Node class...
   //
   private class Node implements BSTNode<T> {

      T element;
      BSTNode<T> left, right;

      
      
      public Node(BSTNode<T> left, BSTNode<T> right, T element)
      {
         this.left = left;
         this.right = right;
         this.element = element;

      }
      
      public BSTNode<T> insert(T element) {
         
         if (this.element.compareTo(element) > 0)
         {
            this.left = this.left.insert(element);
         }
         else if (this.element.compareTo(element) < 0)
         {                     
            this.right = this.right.insert(element);   
         }

         return this;
      }

      public boolean contains(T element) {

        if (this.element.compareTo(element) == 0)
        {
            return true;
        }
        
        if (this.element.compareTo(element) > 0)
        {
            return this.left.contains(element);
        }
        else if (this.element.compareTo(element) < 0)
        {
            return this.right.contains(element);
        }
        
        return false;

      }

      public T minimum(T element) {
         return this.left.minimum(this.element);

      } 

      public T maximum(T element) {
         return this.right.maximum(this.element);
      }

      public void toSortedList(List<T> list) {
       
        this.left.toSortedList(list);
        list.add(this.element);
        this.right.toSortedList(list);
      }

      public int treeHeight()
      {
        int heightLeft = this.left.treeHeight();
        int heightRight = this.right.treeHeight();

        if (heightLeft > heightRight)
        {
            return 1 + heightLeft;
        }
        else
        {
            return 1 + heightRight;
        }
      }
      
      public long internalPathLength(int depth)
      {
        return depth + this.left.internalPathLength(depth + 1) + this.right.internalPathLength(depth + 1);
      }

      public BSTNode<T> remove(T element)
      {
        
        if (this.element.compareTo(element) > 0)
        {
            this.left = this.left.remove(element);
        }
        else if (this.element.compareTo(element) < 0)
        {   
            this.right = this.right.remove(element);
        }
        else
        {
            if (this.left == EMPTY_NODE && this.right == EMPTY_NODE)
            {
                return EMPTY_NODE;
            }
            else if (this.right == EMPTY_NODE)
            {
                return this.left;
            }
            else if (this.left == EMPTY_NODE)
            {
                return this.right;
            }

            this.element = this.right.minimum(element);
            this.right = this.right.remove(this.element);
        }
        return this;
      }

      public T get(T element)
      {
        if (this.element.compareTo(element) == 0)
        {
            return this.element;
        }
        
        if (this.element.compareTo(element) > 0)
        {
            return this.left.get(element);
        }
        else if (this.element.compareTo(element) < 0)
        {
            return this.right.get(element);
        }
        return this.element;
      }
   }
}
