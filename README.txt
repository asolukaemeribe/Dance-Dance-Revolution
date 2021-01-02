*** DDR V2 Code Explained **************************************
****************************************************************

GameObj Class **************************************************

- Abstract class
- Represents the .png files for arrows to be printed on the grid
- Takes in integer width, integer height, and arrow enumeration 
  arrowType that indicates which .png file to draw
- Contains two methods that must be overriden in subclasses
- Its contains two getter methods for width and height
  and has four subclasses

ArrowDirection Class *******************************************

- Four enumeration values that each represent a direction
- Contains a getter method that returns an ArrayList of 
	all enumerations

Left, Right, Up, and Down Arrow Classes ************************

- Implements draw method that prints out .png files respective
  to direction at a specific x and y coordinate on a given
  graphics context
- Implements getString method that returns a String 
  representation of the given class's arrow type
 
DDR Class ******************************************************

- Creates a 2D Array of enumerations to store game state
- If an element in the 2D array is empty, their is no .png file
  that needs to be printed in that area of the grid
- resetEntireGame initializes the grid, shuffles the elements with
  the ArrayList returned from ArrowDirection's getter method,
  and shuffles+creates a LinkedList of orders to be printed
  at the top of the screen
- A LinkedList is used for the state of orders because dequeing
  is required to know which parts of the grid are left to press
- The reset method does the same exact thing as resetEntireGame
  but does not re-initialize the score integer to zero; this is
  because after each time a player scores, we want to re-randomize
  the state of the grid but do not want to reset the score
- getsArrowObject returns a GameObj given an enumeration; useful
  for translating game state stored in 2-D arrow to front end
- getOrdersString returns a string of all elements in orders
  so they may be printed on the screen
- clickGrid checks if clicked part on grid is not null and
  is in the correct order of elements to be removed; if it is,
  then the element in the 2D array will become null and unclickable
  and the first element of orders will be dequed
- awardPoints says if each element in the 2D array is now null,
  the user's score will be incremented by 10 and the method will
  return true
- There are three other getter methods that return score, 
  ordersSting, and the 2D array


GameGrid Class *************************************************

- The constructor initializes the DDR object, status, timers, 
  gameIsOver, and mouseListener
- One timer is for checking if points should be awarded each 35 
  milliseconds, the other time for stopping the game after 60 
  seconds
- paintComponent paints the background across the canvas, paints 
  the border of the grid, and paints .png files by traversing 
  the 2D array, creating objects from enumerations
- If the 60 sec timer has ran out and gameIsOver has been set
  to true, the game over screen is painted
- resetEntire game re-initializes everything the constructor did 
  and restarts the timer
- Writes final score and username into Highscores.txt once game
  is over

Main Class *****************************************************

- Creates canvas and control panel that houses two buttons: reset
  and instructions; reset re-initializes game state, restarts timers,
  and repaints canvas; instructions prints out directions
- Creates list of highscores needed to be passed through 
  Highscores.java, printing controlled by the highscores button


Instructions Class *********************************************

- Prints out instructions
- Action listener tied to instructions button in Main runs this

Highscores Class ***********************************************

- Prints out highscores
- Action listener tied to highscores button in Main runs this

FileLineIterator Class *****************************************

- Creates iterator object useful for iterating through files files
  for printing out highscores
