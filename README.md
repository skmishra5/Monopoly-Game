# cs414_Project
Monopoly

Section 1:
1. Import the project in Eclipse. 

2. The StartMonopoly class contains the main method. Select this file and start running the application.

3. In the first frame, select the number of players for the game and click on the start game button.

4. In the next window, the monopoly board will be visible with all the players.

5. Now, start rolling the dice to make the player movement. The player's turn will be done as a clockwise rotation.

6. If a player rolls double in one turn, then one more chance will be given.

7. If a player lands on a property, then a dialog box will be opened to whether buy or auction the property.

8. If the player selects the auction button, then a new bidding window will be opened. 
   Here, each of the players can raise money for 30 seconds. Then, a winner of the bidding will be decided.

9. If the players run out of balance, then they can sell, mortgage and unmortage the property.

10. The game will continue for 60 minutes and at the end the player who has the maximum cash will win the game.


The use cases which have been covered in the imlementation are listed below.

1. Start a game - Setting up the board and the game is started with the given players with $1500 cash as an inital amount.

2. Roll the dice: Take turns

3. Roll the dice: Goto Jail

4. Roll the dice: Pay player for every complete turn

5. Roll the dice: Rolling same number on Dice

6. Landing on a property: Pay rent if you land on someone else’s property

7. Landing on a property: Pay rent if you landed on someone else’s property: Don’t pay if landed on mortgaged

8. Landing on a property: Pay rent if you landed on someone else’s property: Pay Rent for landing on a Hotel, House

9. Landing on a property: Buy property if it is unowned

10. Landing on a property: Request banker to start auction

11. Landing on a property: Landing on a Luxury Place

12. Landing on a property: Landing on income tax place

13. Landing on a property: Landing on parking place

14. Mortgaging: Un-mortgaging a property from the bank

15. Mortgaging: Mortgage the property with the bank

16. Buying: Buying houses

17. Buying: Upgrading to Hotels

18. Buying: Buying Railroads

19. Selling: Sell the property

20. Jail: Land on jail

21. Jail: Coming out of jail

22. End of Game: Time limit  

Section 2:
# Instructions to the run the game in Distributed way:

1. Login to the server and set the server and mention the time limit. Give the command as rmiregistry on the server machine.
2. Run the server from the class StartServer and provide the arguments as the hostname , port number which the clients should connect to,  the total number of players(By default 4) and the time duration in minutes.
3. Once the server starts running, connect the clients one by one. These can be on different machines. 
4. To run the clients, run MonopolyClient class with the arguments as the server's hostname and the port number.
5. Once all the 4 clients have connected start playing the game as mentioned in points in Section 1.

-- No external jar dependencies are required.
-- Handles auction, monopoly, sell condition , Community and Chance cards, and all the conditions mentioned above.
-- We have used Broker and Grasp Patterns. 
-- All the features work for non-distributed applications as well.
