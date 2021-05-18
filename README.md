# szoft-beadando-rockgame

A simple rock games game played by 2 players.

## About the game

Exercise 2.9
There are thirty-two compartments placed next to each other.
Players have to take turns alternately.
In every turn they have to place a gravel to an empty compartment, which is neighboring with empty compartments as well.
The next player in the circle will lose if there is no compartment where they can put a gravel.

### Run the project
    mvn clean compile exec:java

###Generate docs
    mvn site

###Add Clover coverage report
    mvn -P clover site

###Generatig package
    mvn package

### Running the generated package
    java -jar rockgame-1.0.jar

    