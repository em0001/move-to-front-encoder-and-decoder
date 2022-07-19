import java.util.*;

class MTFDecoder{
    public static void main(String [] args){	
	WordList list = new WordList(); //Create the MRU list
	
	try{
	    Scanner s = new Scanner(System.in);
			    
	    boolean lastWord = false; //flag to check if the word is the last word in the line			    
	    String endOfWord = ""; //string to use to check if a word is the last word in the line (the last two chars ae "\n")	    
	    String lastWordOfLine = ""; //Variable to hold the last word of the line without the "\n" when it is reached

	    //Ensure the output of the MTFEncoder is provided
	    if(!s.hasNextLine()){
		System.out.println("Pleases provide pipeline the output from the MTFEncoder into this program so the original text can be reconstructed");
	    }
	    else{
                while(s.hasNextLine()){
		    
                    //read a line in from standard input and split the line of the file into individual words on instances of white space
                    String line = s.nextLine();		    
                    String[] tokens = line.split(" ");
                    
                    //Check if it is the first instance of the word or space
                    if(Integer.parseInt(tokens[0]) == 0){
			//if this is the first instance of a word (NOT a space)
			if(tokens.length == 2){			    	
			    //check if the string could be the end of a line (a line that is the end of a line contains "\n" so length must be >=2)
			    if(tokens[1].length() >= 2){
				//Check if it is a blank line if so print one and move to the next line to process in the file
				if(tokens[1].equals("\n")){
				    System.out.println();			    
				    continue;
				}			
				//Retrieve the end of the word to see if it is the last word in the line 
				endOfWord = tokens[1].substring(tokens[1].length() - 2);		    
				//check if it is the last word in the line (last characters are "\n") is "\\n" due to needing to escape it for the comparison
				if(endOfWord.compareTo("\\n") == 0){
				    lastWord = true;				    
				    lastWordOfLine = tokens[1].substring(0, tokens[1].length() - 2); //retrieve the word without the "\n" so the word can be printed by itself 		    
				}
			    }			    
			    list.add(tokens[1]); //add the word to the list
			       
			    //if it is the last word in the line print out the word and a new line and we are moving to a new line so reset the flag
			    if(lastWord){
				System.out.println(lastWordOfLine);				
				lastWord = false;
			    }		                
			    else{				
				System.out.print(tokens[1] + " "); //print out the word followed by a space
			    }
		        }
	                //Otherwise the character is a space (a space is store as an empty string in the file): "0 " is split and the empty string caused by the split to "0" "" 
	                //is disregarded so we can't tell it's a space by comparing tokens[1]. Add an empty string to represent a space to the list and print a space	                
	                else{
			    list.add("");
			    System.out.print(" ");	
			}
		    }	
		    else{
			//retrieve the word the the index points to by counting down (index) times down our MRU list and then move that word to the front
			//of the list (done internally by the decode method)
			String word = list.decode(Integer.parseInt(tokens[0]));

			//check if the word is the last word of the line (will contain "\n" if it is so the length must be >= 2)
			if(word.length() >= 2){
			    //if the word is "\n" it is a blank line so print one and move to the next line in the file to process
			    if(word.equals("\n")){
				System.out.println();
				continue;
			    }
			    //retreive the end of the word
			    endOfWord = word.substring(word.length() - 2);		    
			    //check if it is the last word in the line (last characters of the word are "\n") - if says "\\n" due to needing to escape it to do the comparison
			    if(endOfWord.compareTo("\\n") == 0){
                                lastWord = true;                                
                                lastWordOfLine = word.substring(0, word.length() - 2); //retrieve the word without the "\n" so the word can be printed by itself
			    }
			}			
			//if it is the last word in the line print out the word and a new line and reset the flag
                        if(lastWord){
                            System.out.println(lastWordOfLine);
			    lastWord = false;
                        }                        
			else{ 			    
			    //print out the word with a space after it (if the character is a space it will print an empty string then a space - equivalent to a space)
			    System.out.print(word + " ");
			}
		    }			
		}		
		s.close(); //close the scanner		
	    }
	}
	catch(Exception e){
	    System.err.println("Exception: " + e);
	}
	    
    }
    
}	

