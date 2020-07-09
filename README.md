# BlockHero


### Description
The objective of the BlockHero game is to survive as long as possible by dodging the falling blocks.  The score is based on the seconds alive and the game detects and saves the highest score automatically with a data(DAT) file. You can play the game by downloading the project files in a zip and running the GameFrame class on a Java IDE. My personal highscore is 122 seconds. You can move through the arrow keys.

### Resources Used
I used Java's AWT package for event handling, image processing, and collision detection. Swing was used to preload my file images as image icons and develop the GUI interface for the game. I also used Swing's timer class to produce a timer instance that runs every second to animate the falling blocks. I created a TimerListener class that implemented an ActionListener to trigger the score and block movement as action events. Java's IO package was used to automatically make a DAT file when the game is first run, and to read and write out to that file with a buffered reader and writer to update the highscore and name of the player.


![Alt text](/assets/Screenshot.png?raw=true "BLOCK HERO")
