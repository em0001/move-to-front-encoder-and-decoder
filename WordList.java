class WordList{
    private Node head = null;
    private int length = 0;
   
   /*Node class that contains a string value and points to the next and previous nodes in the list*/
    private class Node{
	String key;
	Node next;
	Node prev;
		
	//Constructor that sets the key to the provided string	
	public Node(String word){
	    key = word;
	}
    }

    /*
     * Counts down from the head of the list the number of times passed in returns the word at that node and moves the node to the front of the list 
     */
    public String decode(int index){
	//if an invalid index is passed in or the list is empty return an empty string
	if(index < 1 || head == null || index > length())
	    return "";

	//Create a reference to loop through the list
	Node cur = head;
	//loop through the list til we get to the node specified by the index    
	for(int i = 1; i < index; i++){
	    cur = cur.next;
	}
	//Move the node to the front of the list
	moveToFront(cur);	
	//Return the string in the node
	return cur.key;
    }
		
    /*
     * Adds a word to the front of the list 
     */
    public void add(String word){
    	//If the string passed in is null return
    	if(word == null)
    		return;
    
	//Create node to add to the list
	Node tmp = new Node(word);

	//increase the length of the list by 1
	length++;
		
	//If the list is empty make the node the head of the list
	if(head == null){
		head = tmp;
		return;
	}
		
	//Else update the list so tmp is the head of the list
	head.prev = tmp;
	tmp.next = head;
	head = tmp;
	return;
    }
    
    /*
     * Checks if the list contains the word or not. If it it does contain the word it returns the index of where the word is in relation to the top of the list
     * and moves that node to the front of the list, otherwise it returns 0
     */
    public int has(String word){
    	//if the list is empty or string passed in is null return 0
    	if(head == null || word == null)
    		return 0;    
    
	//iterates through the list and checks if any of the nodes contains the word
	for(Node cur = head; cur != null; cur = cur.next){	    
	    //if the word has been found in the list
	    if(word.equals(cur.key)){
	    	//get a reference to the node containing the word so it can be moved to the front of the after calculating it's index
	    	Node tmp = cur;	    	
	    	//calculate the index of where the node is in relation to the top of the list
		int index = 0;
		while(cur != null){
		    index++;
		    cur = cur.prev;
		}
		//move the node containing the word to the front of the list
		moveToFront(tmp);
		//return where the node is in relation to the top of the list		
		return index;
	    }		    		
	}    
	//otherwise the word hasn't been found so return 0
	return 0;    	
    }
    
    /*
     * Moves the passed in node to the front of the list
     */
    private void moveToFront(Node move){
	//if the passed in node is null return
	if(move == null)
		return;    
    	//if the node is already at the front of the list return
    	if(head == move)
    		return;	
    	
    	//make the node before the move node point to the node after move (make them 'skip over' move)
    	move.prev.next = move.next;
	//if move node is not the end of the list make the node after moves prev pointer point to the node before move
	if(move.next != null)
	    move.next.prev = move.prev; 
    	//update move to be the head of the list by its next pointer pointing to the current head of the list, having a null prev pointer
	//and making the previous head's prev pointer to point to move
    	head.prev = move;
    	move.next = head;
    	head = move;
	head.prev = null;	
    	return;    	
    }

    /*
     * Returns the length of the list 
     */
    public int length(){
	return length;
    } 
	
    /*
     * Prints out all the nodes in the list to the screen
     */
    public void print(){	
	for(Node cur = head; cur != null; cur = cur.next){
	    System.out.println(cur.key);
	}
	return;
    }	
}
	
