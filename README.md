# move-to-front-encoder-and-decoder
MTFEncoder.java - move to front encoder <br />
MTFDecoder.java - move to front decoder <br />
WordList.java - implementation of an MRU (most recently used) list <br /> 

To run the program after compilation: java MTFEncoder textfile.txt | java MTFDecoder <br />
where textfile.txt is the text file to be encoded and decoded

The MTFEncoder takes in a text file encodes it with the assistance of an MRU (most recently used) list and prints the encoded file to standard output (when encountering a token in the file for the first time it prints the line “0 token” without the speech marks, when encountering a token subsequent times, it prints the index in the MRU list it was at), this is then piped into the MTFDecoder which with the assistance of an MRU list decodes the file and prints to terminal the contents of the original text file passed into the MTFEncoder.
