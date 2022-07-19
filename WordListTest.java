import static org.junit.Assert.*;
import org.junit.Test;

public class WordListTest{

    @Test
    public void check_add_one()
    {
	/*
	 * check that after adding one item to the list it reports the length as 1
	 */
	WordList list = new WordList();
	list.add("kitten");
	assertEquals("The list should contain one item", 1, list.length());
    }

    @Test
    public void check_add_multiple()
    {
	/*
	 * check that after adding multiple items to the list it reports the correct length
	 */
	WordList list = new WordList();
	list.add("Cat"); list.add("Otter"); list.add("Giraffe"); list.add("Zebra");
	assertEquals("The list should contain four items", 4, list.length());
    }

    @Test
    public void check_add_duplicates()
    {
	/*
	 * check that the same item can be added multiple times to the list (to show the add method purely adds a new node to the front of the list)
	 * has() is used to check if an item should be added to the list in the Encoder
	 */
	WordList list = new WordList();
	list.add("otter"); list.add("otter");
	assertEquals("The list should contain two items", 2, list.length());
    }

    @Test
    public void check_add_null()
    {
	/*
	 * check that attempting to add a null string to the list does not add anything to the list 
	 */
	WordList list = new WordList();
	String fish = null;
	list.add(fish); list.add("catfish");
	assertEquals("The list should contain one item", 1, list.length());
    }	

    @Test
    public void check_decode_emptyList()
    {
        /*
         * check that attempting to retrieve a word from an empty list returns an empty string
         */
        WordList list = new WordList();
        assertEquals("The list is empty so there are no values to retrieve", "", list.decode(1));
    }

    @Test
    public void check_decode_invalidIndex()
    {
        /*
         * check that attempting to retrieve a word using a negative index and index of 0 returns an empty string 
         */
        WordList list = new WordList();
	list.add("otter"); list.add("rhino"); list.add("panda"); list.add("kiwi");
        assertEquals("If the index is 0 it is the first instance of the word so the word isn't in the list", "", list.decode(0));
	assertEquals("Cannot retrieve a word with using a negative index", "", list.decode(-3));
    }

    @Test
    public void check_decode_indexLengthList()
    {
        /*
         * check that attempting to retrieve a word which at the length of the list returns the correct string
         */
        WordList list = new WordList();
	list.add("otter"); list.add("rhino"); list.add("panda"); list.add("kiwi");
        assertEquals("If the index is the length of the list it should return the first item in the list - otter", "otter", list.decode(4));
	//shuffle the list and retrieve the word at the length of the list (has internally moves the passed in word to the front of the list)
	//the list now looks like: panda, otter, kiwi, rhino, penguin
	list.has("otter"); list.has("kiwi"); list.has("rhino"); list.add("penguin");
	assertEquals("If the index is the length of the list it should return the first item in the list - panda", "panda", list.decode(list.length()));
	//the list now looks like: otter, kiwi, rhino, penguin, panda
	assertEquals("it should return panda", "panda", list.decode(1));	
    }

    
    @Test
    public void check_decode_simpleList()
    {
        /*
         * check that decode returns the correct word
	 * multiple lists are used because decode internally moves the node with the passed in node to the front of the list
         */
        WordList list = new WordList();
	WordList list1 = new WordList();
	WordList list2 = new WordList();
	WordList list3 = new WordList();
	//lists will look like: giraffe, goat, kitten, whale
	list.add("giraffe"); list.add("goat"); list.add("kitten"); list.add("whale");
	list1.add("giraffe"); list1.add("goat"); list1.add("kitten"); list1.add("whale");
	list2.add("giraffe"); list2.add("goat"); list2.add("kitten"); list2.add("whale");
	list3.add("giraffe"); list3.add("goat"); list3.add("kitten"); list3.add("whale");	
	assertEquals("Decode should return the word 'whale'", "whale", list.decode(1));
	assertEquals("Decode should return the word 'kitten'", "kitten", list1.decode(2));
	assertEquals("Decode should return the word 'goat'", "goat", list2.decode(3));	
        assertEquals("Decode should return the word 'giraffe'", "giraffe", list3.decode(4));        
    }
    
    @Test
    public void check_decode_complicatedList ()
    {
        /*
         * check that decode returns the correct word in a list that has been shuffled around internally by calling the has method   
	 * multiple lists are used because decode internally moves the node with the passed in node to the front of the list
         */
	WordList list = new WordList();
	WordList list1 = new WordList();
	WordList list2 = new WordList();
	WordList list3 = new WordList();
	//add some items to the list list looks like: giraffe, goat, kitten, whale
        list.add("giraffe"); list.add("goat"); list.add("kitten"); list.add("whale");
	list1.add("giraffe"); list1.add("goat"); list1.add("kitten"); list1.add("whale");
        list2.add("giraffe"); list2.add("goat"); list2.add("kitten"); list2.add("whale");
        list3.add("giraffe"); list3.add("goat"); list3.add("kitten"); list3.add("whale");
	//shuffle the lists around - the lists will now look like: goat, giraffe, kitten, whale
	//as has will move giraffe then kitten then whale to the top of the list
	list.has("giraffe"); list.has("kitten"); list.has("whale");
	list1.has("giraffe"); list1.has("kitten"); list1.has("whale");
	list2.has("giraffe"); list2.has("kitten"); list2.has("whale");
	list3.has("giraffe"); list3.has("kitten"); list3.has("whale");
	assertEquals("Decode should return the word 'whale'", "whale", list.decode(1));
	assertEquals("Decode should return the word 'kitten'", "kitten", list.decode(2));
	assertEquals("Decode should return the word 'giraffe'", "giraffe", list.decode(3));
	assertEquals("Decode should return the word 'goat'", "goat", list.decode(4));	
    }
    
    @Test
    public void check_decode_indexOutOfBounds()
    {
        /*
         * check that decode returns an empty string when the index passed in is greater than the length of the list (no word can be retrieved) 
         */
	WordList list = new WordList();
	list.add("giraffe"); list.add("goat"); list.add("kitten"); list.add("whale");
	assertEquals("The index is greater than the length of the list so cannot retrieve a word", "", list.decode(13));
	assertEquals("The index is greater than the length of the list so cannot retrieve a word", "", list.decode(5));
    }
    
    @Test
    public void check_has_singleItem()
    {
        /*
         * check that after adding an item to the list has reports it in the correct position 
         */
        WordList list = new WordList();
	list.add("Cat");         
	assertEquals("The list should have Cat and should return 1 as it is 1 position from the top of the list", 1, list.has("Cat"));
    }

    @Test
    public void check_has_multipleItems()
    {
	/*
	 * checks that after adding an item to the list has reports it in the the correct position
	 */
	WordList list = new WordList();
	list.add("Giraffe"); list.add("Otter"); list.add("Zebra"); list.add("Tiger");
	assertEquals("The list should have Otter and should return 3 as it is 3 positions from the top of the list", 3, list.has("Otter"));
    }

    @Test
    public void check_has_doesNotContain()
    {
        /*
         * checks that an item not in the list returns 0 (not being in the list) from the has method  
         */
	WordList list = new WordList();
	list.add("Icecream"); list.add("Apricots"); list.add("Tea");
	assertEquals("The list should not have Otter and should return 0 because it is not in the list", 0, list.has("Otter"));
    }

    @Test
    public void check_has_null()
    {
	/*
         * checks that when passed a null string it returns 0 (not being in the list) from the has method
         */
        WordList list = new WordList();
        list.add("Icecream"); list.add("Apricots"); list.add("Tea"); 
	String fish = null;
	list.add(fish); //will not add a null string to the list
	assertEquals("The list cannot contain a null string and should return 0", 0, list.has(fish));
    }
    
    @Test
    public void check_has_emptyList()
    {
        /*
         * checks that when the list is empty returns 0 (as nothing is in the list) from the has method
         */
        WordList list = new WordList();
        assertEquals("The list cannot contain fish as the list is empty so should return 0", 0, list.has("fish"));
    }

    @Test
    public void check_has_shuffledList()
    {
        /*
         * checks that when the list has been shuffled around (has() internally moves the passed in item to the front of the list)
	 * that the correct index has been returned
         */
        WordList list = new WordList();
	//add some items to the list
	list.add("Giraffe"); list.add("Otter"); list.add("Zebra"); list.add("Tiger"); list.add("Flamingo");
	//shuffle the list around will now look like this: giraffe, zebra, tiger, flamingo, otter
	list.has("Otter"); list.has("Zebra"); list.has(""); list.has("Tiger"); list.has("Flamingo"); list.has("Otter");
        assertEquals("Should return 4 as that is where Zebra is after shuffling the list", 4, list.has("Zebra"));
    }
	
}
