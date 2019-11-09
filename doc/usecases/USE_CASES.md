# Use Cases
## Basic Use Cases
### Graphical Authoring Environment (15)

#### Levels
1. Designer should be able to select an image of the background from a provided list.
2. Designer should be able to upload an image of the background from their computer.
3. Designer should be able to specify the enemies’ path through a map.
4. Designer should be able to specify enemy spawn points on the map
5. Designer should be able to specify the terrain of the game
6. Designer should be able to set how many waves of enemies will come in a level
7. Designer should be able to set how many enemies will appear in each wave.

#### Towers
1. Designer should be able to set and save the base stats for a new type of tower, including tower health, attack power, range and projectile type.
2. Designer should be able to set images for each tower and their projectiles. 
3. Designer should be able to set the cost of a tower.
4. Designer should be able to set the "attack mode" of each type of tower (firing in a fixed orientation or periodically rotating).
5. Designer should be able to load previously made towers from an XML file.

#### Enemies
1. Designer should be able to select an image of the enemy from a provided list.
2. Designer should be able to upload an image of the enemy from their computer.
3. Designer should be able to set the "health" of the enemy.
4. Designer should be able to set the "attack damage" of the enemy.
5. Designer should be able to set the speed of the enemy.



### Game Player (15)
1. Designer should be able to allow players to click and view tower information at some point in the game.
2. Designer should be able to allow players to view obstacle information at some point in the game.
3. User should be able to start the playing by clicking the start button
4. User should be able to pause the game by clicking the pause button
5. User should be able to see how many coins they have left
6. Use can drag and drop an obstacle from right pane onto the game screen before they start the game loop
7. Users can drag and drop a tower from right pane onto game map before staring the game
8. Users can drag and drop a tower from right pane onto game map while playing
9. Use can drag and drop an obstacle from right pane onto the game screen while the game loop is running (during enemy waves)
10. Users can select towers in the map and upgrade them individually with their in game coins
11. Users can choose the orientation of each tower (the direction in which it will shoot its projectiles/attack)
12. User will be able to purchase items before starting the game
13. User will be able to purchase items while the game is running 
14. User will be able to improve/repair towers by purchasing upgrades
15. User will be able to repair obstacles by spending coins to improve obstacle health
16. Users can access pause and access the game authoring environment to change and update the game at anypoint - for example, can change the background and resume game with all the towers and enemies in the same place as before pause


### Game Engine (15)
1. Sprites should recognize and react to collisions with other sprites.
1. Sprite objects should be able to recognize different kinds of collisions (attacks vs. powerups). 
1. Sprites should be able to keep track of and react to their own health.
1. GameManager should keep track of money.
1. GameManager should keep track of score.
1. Sprites should be able to walk along a configuration-defined path.
1. Player should be able to exchange in-game currency for sprites.
1. GameManager should be able to start and end levels after level-end conditions have been met.
1. GameManager should be able to start and end games after game-end conditions have been met.
1. AudioManager should be able to queue certain audio after certain conditions are met.
1. LevelManager should be able to keep track of all sprites involved in a scene.
2. GameEngine should manage game loop.
3. Player should be able to place towers (a kind of sprite) on valid spaces on screen.
4. GameEngine should export necessary information to GamePlayer.
5. Sprites should be able to keep track of and update own attributes.