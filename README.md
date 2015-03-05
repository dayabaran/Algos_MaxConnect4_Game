README
Description & Summary:

This program simulates the maxConnect4 game by fetching the board input from freshGame.txt  and also runs in two modes.
1)human-computer mode 
2)computer-computer mode

The project consists of 9 files

1)Main class -maxConnect4.java
2)BoardReader.java- to read the input txt file and board details
3)BoardWriter.java-to print the board results in console.
4)GameBoard.java - Simulates a gameboard play.
5)HumanPlayer.java - Simulates human player
6)AiPlayer.java - Simulates computer player
7)Player.java - interface
8)PlayerIdentifier.java -enum to identify the player , -human or computer
9)PlayMode.java - Enum which shows the number of playmodes

How to Run
The program operates in two modes :
1) Human- Computer mode 
2) computer-computer mode.

Human-Computer mode
The human computer mode accepts an input file to initialize the board which is given in freshGame.txt also the program must mention the mode as human-computer.

Usage: java [program name] human-computer [input_file] 
eg: java maxConnect4 human-computer freshGame.txt

Computer-Computer mode
The computer-computer mode accepts an input file and outputfile to initialize the board and to print the results to a file. Also the program must mention the mode as computer-computer.
The other computer program should give the output of the first program as its input file and vice versa for both the computers to communicate with each other.
Usage: java [program name] computer-computer [input_file] [output-file]
eg: java maxConnect4 computer-computer input1.txt input2.txt

Example:
Computer 1
java maxConnect4 computer-computer input_1.txt input_2.txt

Computer2
java maxConnect4 computer-computer input_2.txt input_1.txt



Compiler Used: 

Java Compiler 

Platform Used :
Java Development Kit 1.8
J2SE ( Java 2 Platform,Standard Edition) 1.8



Compiling & running the program
Compiling : javac *.java

To run human-computer mode:
java maxConnect4 human-computer freshGame.txt

To run computer-computer mode:
For Program 1:(in separate terminal)
java maxConnect4 computer-computer input_1.txt input_2.txt 
For Program 2: (in separate terminal)
java maxConnect4 computer-computer input_2.txt input_1.txt

DataStructures & Algo Used
 The DataStructure used here are Arraylist, HashMap. 
 Algorithm used is MinMax Algorithm to simulate Computer player.
 
 References Used:
http://vlm1.uta.edu/~athitsos/courses/cse4308_summer2012/assignments/programming2/
http://www.wieschoo.com/tutorials/optimization/connect4-ai-minimax/001189/

