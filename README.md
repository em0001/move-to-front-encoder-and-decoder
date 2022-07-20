# move-to-front-encoder-and-decoder
MTFEncoder.java - move to front encoder <br />
MTFDecoder.java - move to front decoder <br />
WordList.java - implementation of an MRU (most recently used) list <br /> 
WordListTest.java - JUnit tests for the functionality of the WordList class <br /> 

To run the encoding and decoding programs after compilation: <br />
java MTFEncoder textfile.txt | java MTFDecoder <br />
where textfile.txt is the text file to be encoded and decoded

The MTFEncoder takes in a text file encodes it with the assistance of an MRU (most recently used) list and prints the encoded file to standard output (when encountering a token in the file for the first time it prints the line “0 token” without the speech marks, when encountering a token subsequent times, it prints the index in the MRU list it was at), this is then piped into the MTFDecoder which with the assistance of an MRU list decodes the file and prints to terminal the contents of the original text file passed into the MTFEncoder.

The JUnit tests written in WordListTest.java work for JUnit 4 <br /> 
To get the files required to be able to run the test file: <br /> 
wget -O junit-4.13.2.jar "https://search.maven.org/remotecontent?filepath=junit/junit/4.13.2/junit-4.13.2.jar" <br /> 
wget -O hamcrest-core-1.3.jar "https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar" <br /> 

To run the WordListTest file:<br /> 
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar WordListTest.java <br /> 
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore WordListTest

