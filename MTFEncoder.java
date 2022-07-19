import java.io.*;
class MTFEncoder {
    public static void main (String [] args){
	//check that a text file has been provided
	if(args.length < 1 ){
	    System.out.println("Please provide a text file to be encoded and decoded");
	}
	else{	    
	    WordList list = new WordList(); //Create the MRU list
	    
	    try{		
		BufferedReader in = new BufferedReader(new FileReader(args[0])); //create a buffered reader to read from the provided file				 
				
		while(in.ready() == true){
		    //read a line from the file split the line of the file into individual words on instances of a white space
		    String line = in.readLine();		
		    String[] tokens = line.split(" ");

		    //flags for processing the line
		    boolean emptyLine = false;
		    boolean lastWord = false;
		    
		    //check if tokens only contains an empty string (a blank line in the file)
		    if(tokens.length == 1 && tokens[0].compareTo("") == 0)
			emptyLine = true;		    
		    
		    int index = 0;
		    //loop through all the strings in the tokens array and add them to the MRU list
		    for(int i = 0; i < tokens.length; i++){			
			//check if this is the last word of the line we are adding to the file
			if(i == tokens.length - 1)			    
			    lastWord = true;

			//if it is the last word need to check if it exists in the list with a new line character appended to it
			if(lastWord){
			    index = list.has(tokens[i].concat("\\n"));			    
			}
			//otherwise see if the token by itself exists in the list
			else{
			    index = list.has(tokens[i]);
			}
			
			//if the list does not contain the word add it to the front of the list (a space will be stored as an empty string in the list)
			if(index == 0){
			    //if tokens contains an empty string (a blank line in the file) add a new line character to the MRU List, print to the screen with its index and reset the flag  
			    if(emptyLine){
				list.add("\\n");
   			        System.out.println(index + " " + "\\n");				
				emptyLine = false;
			    }			    
			    //If it is the last word of the line add the word to the MRU List with a new line character at the end to signify it is the end of the line
			    //print the word with a new line character and its index and reset the flag
			    else if(lastWord){
				list.add(tokens[i].concat("\\n"));
				System.out.println(index + " " + tokens[i].concat("\\n"));
				lastWord = false;
			    }
			    //add the word to the MRU list and print the index number and the word 			    
			    else{				
				list.add(tokens[i]);
				System.out.println(index + " " + tokens[i]);				
			    }
			}
			//else the list contains the word, so output the index. It was internally moved to the front of the list in the has() method call when the index was calculated
			else{
			    System.out.println(index);
			}			
		    }
		}
		in.close(); //close the file
	    }
	    catch(Exception e){
		System.err.println("Exception: " + e);
	    }    
	}  
    }
}
	
