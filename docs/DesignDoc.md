---
geometry: margin=1in
---
# PROJECT Design Documentation

## Team Information
* Team name: TEAM D - CodeLords
* Team members
  * Bao Nguyen
  * Sidney Mitchell
  * Liam Obrochta
  * John Licitra
  * Angela Hudak

## Executive Summary

A web application to play a checkers game.

The application allows players to play checkers with other players who are currently signed-in. The game user interface (UI)
will support a game experience using drag-and-drop browser capabilities for making moves.

The application allows players to save their games in Replay Mode so that they could watch later on.

The application allows other players to view on-going games that they are not playing through Spectator Mode.

### Purpose
> _Provide a very brief statement about the project and the most
> important user group and user goals._


### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| VO | Value Object |


## Requirements

This section describes the features of the application.

### Definition of MVP
> _Provide a simple description of the Minimum Viable Product._

- Every player must sign-in before playing a game, and be able to sign-out when finished playing.
- Two players must be able to play a game of checkers based upon the American rules.
- Either player of a game may choose to resign, at any point, which ends the game.

### MVP Features
> _Provide a list of top-level Epics and/or Stories of the MVP._

- Sign In: Allow the player to sign in the page.
- Sign Out: Allow the player to sign out the page.
- Resign: Allow the player to resign a game.
- Play mode: Allow the players to play checkers games.

### Roadmap of Enhancements
> _Provide a list of top-level features in the order you plan to consider them._

- Sign In/ Sign Out.
- Play Mode.
- Resign.
- Spectator Mode.
- Replay Mode.

## Application Domain

This following Domain-model describes the application domain.

![The WebCheckers Domain Model](domain-model.png)

## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system using a
browser.  The client-side of the UI is composed of HTML pages with
some minimal CSS for styling the page.  There is also some JavaScript
that has been provided to the team by the architect.

The server-side tiers include the UI Tier that is composed of UI Controllers and Views.
Controllers are built using the Spark framework and View are built using the FreeMarker framework.  The Application and Model tiers are built using plain-old Java objects (POJOs).

Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the WebCheckers application.

![The WebCheckers Web Interface Statechart](web-interface.png)

### UI Tier

- Sign In/ Sign Out:
    + When a user first search for the web page, a GET "/" HTTP response is called to render the home page.
    Then if the user is not signed in, he could only see the numbers of players online. When the user clicked sign in, the
    GET HTTP "/signin" response is called to take him to the sign in page. If the sign in username already exists, a GET "/signin" HTTP response is called and he will be taken to the sign in page
    again. Otherwise, he would be taken into the home page and see all available players. After that, he could decide to stay or log out the game.
    If he chooses to log out, the player will be redirect to the sign in page again.
- Resign:
    + When a player wants to leave a game, he could clicked the RESIGN button on the controller bar. A POST "/resign" HTTP response is called and he will be taken to the home page.
- Spectator Mode:
    + A user is given the option to watch a game happening between two players. They cannot change or interfere with the game. 
- Replay Mode:
    + After each move is made in game, the move is saved to an arraylist which contains all board views for the given game. Replay mode allows a player to iterate through this arraylist using the replay mode UI and the next/previous replay routes. 
- Kinging Pieces 
    + Allows pieces to be king pieces and complete actions that only king pieces can perform such as jumping in any diagonal direction.



### Application Tier

- GameServer:
    + Store all the in-progress games and current active players.
- PlayerLobby:
    + When a player sign in the game, the PlayerLobby is called to add the player to the game server. 
    + When a player sign out the game, the PlayerLobby is called to remove the player from the game server.
    + When a player joins a game, the PlayerLobby is called to add the game to the game server.
    + When a player resign from a game, the PlayerLobby is called to remove the game from the game server.
### Model Tier

- Contains all the Checkers game logic.
- Contains Game and Player objects and their functions.

### Design Improvements

- Create some helpers function in as utils to handle HTTP request.
- The Controller Principle
    + PlayerLobby and GameServer are now independent of one another and control their own specific attributes. Prior to changes, there was poor adherence to the controller principle. 
- Increased Cohesion, Lowered Coupling
    + Prior to sprint three, our code exhibited many attributes of high coupling between the UI and Model tiers. This was largely due to incorrectly designed model data. These issues have been solved in Sprint 3.
- Polymorphism
    + Our endgame handling code has been designed to follow a polymorphic nature so that there is a clear distinguishing between a player resignation and natural game winning. This helped to lower the amount of lines of code in our product.

## Testing

- Run the website as normal to check if it is working.
- Write unit testings to test UI, Model and Application components and see whether they are fully functional.
- The game is fully functional and the player can spectate and replay games.

### Acceptance Testing

- Sign In/ Sign Out and Resign have passed all their acceptance criteria. 
- Spectator passed their acceptance criteria
- Replay passed their acceptance criteria
- Kinging passed their acceptance criteria

### Unit Testing and Code Coverage

- Write uniting test for each class. 
- Test the model before testing the UI since the logic needs to be working before being reflected through the UI.