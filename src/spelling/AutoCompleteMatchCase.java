package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
    	root = new TrieNode();		
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.		
		if(word.equals("")|| word.equals(null)|| isWord(word)) return false;		
		String wordLCase=word.toLowerCase();
		TrieNode curr=new TrieNode(wordLCase);
		curr=root;
		char[] wordArray=wordLCase.toCharArray();
		
			for(int i=0;i<wordArray.length;i++)
			{				
				if(!curr.getValidNextCharacters().contains((Character)wordArray[i]))
					curr=curr.insert((Character)wordArray[i]);
				else
					curr=curr.getChild((Character)wordArray[i]);			
				if(i==wordArray.length-1)
				{
					curr.setEndsWord(true);					
				}				
			}
			if(!curr.equals(null))
			{
				size++;
				return true;				
			}				
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method			
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s.equals("")|| s.equals(null)|| root.getValidNextCharacters().size()==0) return false;
		String sLCase=s.toLowerCase();
		TrieNode curr=root;
		char[] sArray=sLCase.toCharArray();
		for(int i=0;i<sArray.length;i++)
			{
				if(curr!=null && !curr.getValidNextCharacters().isEmpty())
				{
					curr=curr.getChild((Character)sArray[i]);					
					if(curr!=null && i==sArray.length-1)
					{
						return curr.endsWord();
					}
				}				
			}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List <String> completions=new LinkedList<String>();
    	 Queue<TrieNode> queue=new LinkedList<TrieNode>();
    	 String prefixLCase=prefix.toLowerCase();
 		 TrieNode curr=root;
 		 char[] prefixArray=prefixLCase.toCharArray();
 		 for(int i=0;i<prefixArray.length;i++)
 		 {
 			 if(curr!=null && !curr.getValidNextCharacters().isEmpty())
 			 {
 				 curr=curr.getChild((Character)prefixArray[i]);					
 			 }				
 		 }
 		 if(curr==null)
 		 {
 			 return completions;
 		 }
 		 queue.add(curr);
 		 while(!queue.isEmpty() && completions.size()<numCompletions)
 		 {
 			 curr=queue.remove(); 			 
 			 if(isWord(curr.getText()))
 			 {
 				 completions.add(curr.getText());
 			 }
 			TrieNode next = null;
 			for (Character c : curr.getValidNextCharacters()) 
			 {
			 	next = curr.getChild(c);
			 	queue.add(next);			 
		 	 } 			
 		 }
         return completions;
     }
     
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

}