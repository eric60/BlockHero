# BlockHero
BlockHero is the first java game I've made by using a swing timer to simulate the animations of the falling blocks. The objective of the game is to survive as long as possible by dodging the falling blocks. The score is based on the seconds alive and the game saves the highest score automatically with a DAT file. You can play the game by downloading the project files and running it on any Java IDE. My personal highscore is 122 seconds. You can move through the arrow keys. 

I used Java's awt package for event handling, image processing, and collision detection. Java swing was used to preload my file images as image icons and develop the GUI interface for the game. I also used swing's timer class to produce a timer instance that ran every 1 second. I created a TimerListener class that implemented an ActionListener to trigger the score and block movement as action events. Java's io package was used to automatically make a DAT file when the game is first run, and to read and write out to that file with a buffered reader and buffered writer to update the highscore and name of the player.

UML Class Diagram
![Alt text](/assets/Screenshot.png?raw=true "BLOCK HERO")
