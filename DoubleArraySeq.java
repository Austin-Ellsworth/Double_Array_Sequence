// Author: Austin Ellsworth

//File: DoubleArraySeq.java 
 
// This is an assignment for students to complete after reading Chapter 3 of
// "Data Structures and Other Objects Using Java" by Michael Main.
 
 
/******************************************************************************
* This class is a homework assignment;
* A DoubleArraySeq is a collection of double numbers.
* The sequence can have a special "current element," which is specified and 
* accessed through four methods that are not available in the bag class 
* (start, getCurrent, advance and isCurrent).
*
* @note
*   (1) The capacity of one a sequence can change after it's created, but
*   the maximum capacity is limited by the amount of free memory on the 
*   machine. The constructor, addAfter, 
*   addBefore, clone, 
*   and concatenation will result in an
*   OutOfMemoryError when free memory is exhausted.
*   <p>
*   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
*   (Integer.MAX_VALUE). Any attempt to create a larger capacity
*   results in a failure due to an arithmetic overflow. 
*
* @note
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @see
*   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
*   </A>
*
* @version
*   March 5, 2002
******************************************************************************/
public class DoubleArraySeq implements Cloneable
{
   // Invariant of the DoubleArraySeq class:
   //   1. The number of elements in the sequences is in the instance variable 
   //      manyItems.
   //   2. For an empty sequence (with no elements), we do not care what is 
   //      stored in any of data; for a non-empty sequence, the elements of the
   //      sequence are stored in data[0] through data[manyItems-1], and we
   //      don’t care what’s in the rest of data.
   //   3. If there is a current element, then it lies in data[currentIndex];
   //      if there is no current element, then currentIndex equals manyItems. 
   private double [] data;
   private int manyItems;
   private int currentIndex; 
    
   /**
   * Initialize an empty sequence with an initial capacity of 10.  Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * @param none
   * @postcondition
   *   This sequence is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[10].
   **/  
   public DoubleArraySeq( )
   {
      final int SIZE = 10;
      data = new double[SIZE];
      manyItems = 0;
   }
      
 
   /**
   * Initialize an empty sequence with a specified initial capacity. Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this sequence
   * @precondition
   *   initialCapacity is non-negative.
   * @postcondition
   *   This sequence is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[initialCapacity].
   **/  
   public DoubleArraySeq(int initialCapacity)
   {
      if(initialCapacity < 0){
       
         throw new IllegalArgumentException
         ("initialCapacity is negative: " + initialCapacity);
      }
       
      manyItems = 0;
      data = new double[initialCapacity];
       
   }
         
  
   /**
   * Add a new element to this sequence, after the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed after the current
   *   element. If there was no current element, then the new element is placed
   *   at the end of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addAfter(double element)
   {
	   if(manyItems == data.length) {
		   ensureCapacity((data.length * 2) + 1);
	   }
	   
	   if(currentIndex < manyItems){
		   for(int i = manyItems - 1; i > currentIndex; i--) {
			   data[i + 1] = data[i];
		   }
		   data[currentIndex + 1] = element;
		   advance();
	   } else {
		   data[manyItems] = element;
		   currentIndex = manyItems;
	   }
	   manyItems++;
   }
 
 
   /**
   * Add a new element to this sequence, before the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addBefore(double element)
   {
	   if(manyItems == data.length) {
		   ensureCapacity((data.length * 2) + 1);
	   }
	   
	   if(currentIndex < manyItems){
		   for(int i = manyItems - 1; i > currentIndex - 1; i--) {
			   data[i + 1] = data[i];
		   }
		   data[currentIndex] = element;
		   currentIndex++;
	   } else {
		   data[manyItems] = element;
		   currentIndex = manyItems;
	   }
	   manyItems++;
   }
    
    
   /**
   * Place the contents of another sequence at the end of this sequence.
   * @param addend
   *   a sequence whose contents will be placed at the end of this sequence
   * @precondition
   *   The parameter, addend, is not null. 
   * @postcondition
   *   The elements from addend have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the addend is also unchanged.
   * @exception NullPointerException
   *   Indicates that addend is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/
   public void addAll(DoubleArraySeq addend)
   {
      ensureCapacity(manyItems + addend.manyItems);
       
      System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
      manyItems += addend.manyItems; 
   }   
    
    
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * @param none
   * @precondition
   *   isCurrent() returns true. 
   * @postcondition
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   advance may not be called.
   **/
   public void advance( )
   {
      currentIndex++;
   }
    
    
   /**
   * Generate a copy of this sequence.
   * @param none
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/
   public DoubleArraySeq clone( )
   {  // Clone a DoubleArraySeq object.
      DoubleArraySeq answer;
       
      try
      {
         answer = (DoubleArraySeq) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
       
      answer.data = data.clone( );
       
      return answer;
   }
    
 
   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1
   *   the first of two sequences
   * @param s2
   *   the second of two sequences
   * @precondition
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of s1 followed by the
   *   elements of s2 (with no current element)
   * @exception NullPointerException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   * @note
   *   An attempt to create a sequence with a capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/  
   public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
   {
      DoubleArraySeq result = new DoubleArraySeq(s1.getCapacity() + s2.getCapacity());
      
      System.arraycopy(s1.data, 0, result.data, 0, s1.manyItems);
      System.arraycopy(s2.data, 0, result.data, s1.manyItems, s2.manyItems);
      result.manyItems = s1.manyItems + s2.manyItems;
      result.currentIndex = result.manyItems;
      
      return result;
   }
 
 
   /**
   * Change the current capacity of this sequence.
   * @param minimumCapacity
   *   the new capacity for this sequence
   * @postcondition
   *   This sequence's capacity has been changed to at least minimumCapacity.
   *   If the capacity was already at or greater than minimumCapacity,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new int[minimumCapacity].
   **/
   public void ensureCapacity(int minimumCapacity)
   {
      double[] biggerArray;  
         
      if(data.length < minimumCapacity)
      {
         biggerArray = new double[minimumCapacity];
         System.arraycopy(data, 0, biggerArray, 0, manyItems);
         data = biggerArray;
      }
   }
 
    
   /**
   * Accessor method to get the current capacity of this sequence. 
   * The add method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @param none
   * @return
   *   the current capacity of this sequence
   **/
   public int getCapacity( )
   {
      return data.length;
   }
 
 
   /**
   * Accessor method to get the current element of this sequence. 
   * @param none
   * @precondition
   *   isCurrent() returns true.
   * @return
   *   the current element of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   getCurrent may not be called.
   **/
   public double getCurrent( )
   {
      return data[currentIndex];
   }
 
 
   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent method. 
   * @param none
   * @return
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent( )
   {
	  boolean flag = false;
      if(currentIndex > 0 && currentIndex < manyItems) {
    	  flag = true;
      }
      return flag;
   }
               
   /**
   * Remove the current element from this sequence.
   * @param none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   removeCurrent may not be called. 
   **/
   public void removeCurrent( )
   {
	  if(currentIndex >= 0 && currentIndex < manyItems) {
	      for(int i = currentIndex; i < manyItems; i++) {
	    	  data[i] = data[i + 1];
	      }
	  } else { 
		  throw new IllegalStateException
		  ("There is no current element.");
	  }
	  manyItems--;
	  
	  if(manyItems == 0) {
		  currentIndex = data.length - 1; //This sets current index to an non-important index
	  }
   }
                  
    
   /**
   * Determine the number of elements in this sequence.
   * @param none
   * @return
   *   the number of elements in this sequence
   **/
   public int size( )
   {
      return manyItems - 1;
   }
    
    
   /**
   * Set the current element at the front of this sequence.
   * @param none
   * @postcondition
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/
   public void start( )
   {
	  if(manyItems != 0) { 
		  currentIndex = 0;
	  }
	  
   }
    
    
   /**
   * Reduce the current capacity of this sequence to its actual size (i.e., the
   * number of elements it contains).
   * @param none
   * @postcondition
   *   This sequence's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/
   public void trimToSize( )
   {
      double[ ] trimmedArray;
       
      if (data.length != manyItems)
      {
         trimmedArray = new double[manyItems];
         System.arraycopy(data, 0, trimmedArray, 0, manyItems);
         data = trimmedArray;
      }
   }
   
   /**
    * Add a new element at the front of the sequence and make it the current
    * element.
    * @param element
    * 	The new element that is being added
    * @exception OutOfMemoryError
    *   Indicates insufficient memory for addition of another element.
    * @postcondition
    * 	A new element has been added to the front of the sequence and 
    * 	the current element has been set to that element
    * @note
    *   An attempt to create a sequence with a capacity beyond
    *   Integer.MAX_VALUE will cause an arithmetic overflow
    *   that will cause the sequence to fail.
    **/
   public void addFront(double element)
   {
	   start();
	   addBefore(element);
   }
   
   /**
    * Remove the element at the front of the sequence and make the
    * new front element the current element. If there is no front element
    * then the current element is null.
    * @param none
    * @exception IllegalStateException
    *   Indicates that there is no current element, so 
    *   removeCurrent may not be called. 
    * @postcondition
    * 	The front element has been removed and the current element has 
    *	been set to the new front element.
    **/
   public void removeFront( )
   {
	   start();
	   removeCurrent();
	   //IllegalStateException will be called from removeCurrent

   }
   
   /**
    * Add a new element at the front of the sequence and make it the current
    * element.
    * @param element
    * 	The new element that is being added
    * @exception OutOfMemoryError
    *   Indicates insufficient memory for addition of another element.
    * @postcondition
    * 	A new element has been added to the end of the sequence and 
    * 	the current element has been set to that element
    * @note
    *   An attempt to create a sequence with a capacity beyond
    *   Integer.MAX_VALUE will cause an arithmetic overflow
    *   that will cause the sequence to fail.
    **/
   public void addEnd(double element)
   {
	   currentIndex = manyItems - 1;
	   addAfter(element);
   }
   
   /**
    * Set the last element of the sequence to the current element.
    * @exception IllegalStateException
    *   Indicates that the sequence is empty.
    * @postcondition
    * 	The last element in the sequence has been set to the current element.
    **/
   public void setCurrentLast()
   {
	   if (manyItems == 0) {
		   throw new IllegalArgumentException
			  ("The sequence is empty.");
	   } else {
		   currentIndex = manyItems - 1;
	   }
   }
   
   /**
    * Returns the n'th element of the sequence. The current element is set 
    * to this element. 
    * @param n
    * 	The n'th element in the sequence.
    * @return
    * 	The value stored in data[n - 1]
    * @exception IllegalStateException
    *   Indicates the sequence is empty.
    * @exception IllegalArgumentException
    * 	Indicates n is greater than the sequence size or is zero or negative.
    * @postcondition
    * 	The n'th element of the sequence is returned and is set to the current index. 
    * @note
    *   n is not the index value, if the value of n passes in as 4, the index
    *   value would be 3.
    **/
   public double getElement(int n)
   {
	   if (n > manyItems) {
		   throw new IllegalArgumentException
		   		("n is larger than the size of the sequence or is zero or negative.");
	   } else if (manyItems == 0) {
		   throw new IllegalStateException
		   		("The sequence is empty.");
	   } else {
		   setCurrent(n);
	   }
	   
	   return data[n - 1];
   }
   
   /**
    * The current element is set to the passed in value 'n'. 
    * @param n
    * 	The n'th element in the sequence.
    * @exception IllegalStateException
    *   Indicates the sequence is empty.
    * @exception IllegalArgumentException
    * 	Indicates n is greater than the sequence size or is zero or negative.
    * @postcondition
    * 	The n'th element is set to the current index. 
    * @note
    *   n is not the index value, if the value of n passes in as 4, the index
    *   value would be 3.
    **/
   public void setCurrent(int n) 
   {
	   if (n > manyItems || n < 1) {
			throw new IllegalArgumentException
		   		("n is larger than the size of the sequence or is zero or negative.");
	   } else if (manyItems == 0) {
   		    throw new IllegalStateException
			   	("The sequence is empty.");
	   } else {
		   currentIndex = n - 1;
	   }
   }
   
   /**
    * The current element is set to the passed in value 'n'. 
    * @param obj
    * 	The object to be compared.
    * @postcondition
    * 	A boolean value is returned based on the values of the two compared objects.
    * @return
    * 	A boolean value
    **/
   public boolean equals(Object obj)
   {
	   boolean flag = true;
	   if (obj instanceof DoubleArraySeq)
	   {
		   DoubleArraySeq candidate = (DoubleArraySeq) obj; 
		   if (candidate.manyItems != manyItems) { //if manyItems is not equal, will return false right away
			   flag = false;					   //this also protects from ArrayOutOfBounds
		   } else {
			   for(int i = 0; i < manyItems; i++) {
				   if (candidate.data[i] == data[i]) { //move through array and check each number
					   flag = true;
				   } else {
					   flag =  false;
				   }
			   }
		   }
	   }
	   return flag;
   }
   
   /**
    * Returns a String containing all values in data. 
    * @return
    * 	A String will all values of data
    * @exception IllegalStateException
    *   Indicates the sequence is empty.
    * @postcondition
    * 	A String containing all values of the sequence is output. 
    **/
   public String toString()
   {
	   int current = currentIndex;
	   String output = "";

	   if (manyItems == 0) {
		   throw new IllegalStateException 
		   	("This sequence is empty.");
	   } else {
		   start(); //move current index to start
		   for(int i = 0; i < manyItems; i++) {
			   output += getCurrent() + " ";
			   advance();
		   }
	   }
	   currentIndex = current;
	   return output;
   }
       
}