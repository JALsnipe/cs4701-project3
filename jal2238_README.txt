COMS W4701 - Artificial Intelligence 
Jonathan Voris

Josh Lieberman
jal2238
Assignment 3 - Battle of the Gomoku Agents

Programming Language Used: JavaSE-1.6
Development Environment Used: Eclipse Kepler (Build id: 20130614-0229)
Code tested on these environments: Eclipse, CLIC Lab Machines

Usage:
Compile all Java files and run GomokuTester.
---Example---
jal2238@prague:~/cs4701/assignment3$ ll
total 32
drwx------ 2 jal2238 student 4096 Nov 21 22:33 ./
drwx------ 5 jal2238 student 4096 Nov 15 00:35 ../
-rw------- 1 jal2238 student 8588 Nov 21 22:33 Actions.java
-rw------- 1 jal2238 student 7061 Nov 21 22:33 GomokuTester.java
-rw------- 1 jal2238 student  977 Nov 21 22:33 State.java
jal2238@prague:~/cs4701/assignment3$ javac *.java
jal2238@prague:~/cs4701/assignment3$ java GomokuTester
Welcome to Gomoku!
Please enter the game board dimension n:
15
Please enter the winning chain length m:
5
Please enter the move selection time limit s:
60
Please select your game mode:
1. Human (X) vs Human (O)
2. Human (X) vs. Computer (O)
3. Computer (X) vs. Human (O)
4. Randomly-moving player (X) vs. Computer (O)
5. Computer (X) vs. Computer (O)
4
Random Computer Move:
x: 9
y: 13
Player O Move:
O . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . . . . . . . . . .

Random Computer Move:
x: 3
y: 4
Player O Move:
O O . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . X . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . . . . . . . . . .

Random Computer Move:
x: 13
y: 11
Player O Move:
O O O . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . X . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . X .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . . . . . . . . . .

Random Computer Move:
x: 6
y: 14
Player O Move:
O O O O . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . X . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . X .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . X . . . . . . . .

Random Computer Move:
x: 13
y: 7
Player O Move:
O O O O O . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . X . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . X .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . X .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . X . . . . . . . .

Player O wins!
O O O O O . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . X . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . X .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . X .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . X . . . . . . . .
---End of Example---

Note: My count function for determining a winning game state builds on a
similar function from a programming exercise in "Programming: Introduction to
Programming Using JAVA, 3rd Edition" by David J. Eck.

My evaluation function attempts to give a higher score to pieces of greater
chain lengths.  In theory, try to increase one's own chains without blocking
or defending from another user from attempting the same.  Chains of longer 
pieces would be worth more: a chain of 1 piece would have a heuristic score
of 1, a 2 chain of 2, etc.  My heuristic search performs very well with
alpha-beta pruning.  It can return a move in a matter of seconds, sometimes
less.  The origin of this heuristic comes from a brainstorming session with
Professor Voris.  He told me that when he coded his algorithm, he scored
chains exponentially by length.  Hence, I try to give longer chains of a 
single piece a higher score.

Human vs. Heuristic:
Example game board:
X O O O O . . . . . . . . . .
X . . . . . . . . . . . . . .
X . . . . . . . . . . . . . .
X . . . . . . . . . . . . . .
X . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .

The way my algorithm runs, it is very easy for a human player to block the 
computer player from winning, while also working on one's own chain and 
nearly guaranteeing a win.  An option would have been to also write a 
heuristic that tries to block an opponent from lengthening their chain, but due
to time constraints, I opted to leave my heuristic as-is.

Random Computer Move vs. Heuristic
Example game board:
O O O O O . . . . . . . . . X
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . X . . . . .
. . . . . . . X . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . X X . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .
. . . . . . . . . . . . . . .

Although my heuristic does not function as intended, it can still almost 100%
of all games against a random-moving opponent.  The larger the board, the
smaller the chance a random-moving opponent will block my heuristic player's
chain.  Thus, most games are won like the board pictured above.


Agent vs. Agent
Games 1-5:
X O X O X O X O X O X O X O X 
O X O X O X O X O X O X O X O 
X O X O X O X O X O X O X O X 
O X O X O X O X O X O X O X O 
X O . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . 

Clearly, X is the winner here.  Unfortunately, a bug in my count function causes
a final O to be printed.  Because of the way my heuristic works, matching it up
against itself produces this result every time.  The player who moves first has
an advantage, and thus wins every time.
