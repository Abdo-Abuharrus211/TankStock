
#*SWIM ALONG?*

###The Aquarium Fish Compatibility Program

- ##What Is It?
It's my 2021 CPSC210 term project and my first ever personal JAVA 
project!

- ##What Does It Do?
It's a simple program that takes in certain values entered by the user such as:
- fish type(species)
- tank type (cold-water or tropic).
- tank size (volume in Liters)
<br>
Then it checks whether these fish species are compatible with each other before adding them.<br>
Hopefully in the future I'll be able to connect it to a database, if such a database exists, 
and it'll use that to reference more variables.

- ## Why Fish?
Growing up, one of my favorite hobbies was *Fish-keeping and Aquaria*. To me there's something truly fascinating about
creating an environment for the fish that either mimics their natural habitat or has similar living 
conditions such that the fish can thrive but is like nothing in the wild!
And although aquariums can be hard to maintain and demand a lot of work the reward is always wonderful to observe and 
enjoy.

- ## How'd I Get The Idea?
When I first started my hobby there was a steep learning curve,one that has yet to flatten out to be honest,
and I often had to spend hours researching which fish species are compatible with which, and what
tank size is suitable, and all the other parameters needed to keep the fish healthy.
It would have been a **LOT** easier if a program like this existed.<br>
So I figured I'd give it a shot since this combines to things I'm passionate about. Starting with the most important
parameters for a beginner, aggression level, water temp, and whether a tank is big enough to house x number of fish.

***
###USER STORIES:

- As a user, I want to be able to create a list of Fish.
- As a user, I want to be able to assign the volume of the tank and calculate maximum capacity.
- As a user, I want to be able to add Fish to that list.
- As a user, I want to be able to remove a Fish from a Tank.
- As a user, I want to be able to view the fish in a tank (prints list).
 ####PHASE 2

- As a user, I want to be able to save a tank with its fish.
- As a user, I want to be able to load a tank with its fish.

 ####PHASE 3:

- As a user, I want to be prompted to choose whether to load OR make<br>
a tank when staring the program.
- As a user, I then want options for things I can do to my tank such as add or remove fish.<br>
- As a user I want to be prompted to save my tank before exiting the program.

####PHASE 4: Task 2 (sample of Event log)

-"Thu Nov 25 12:39:34 PST 2021
A new 500L tank has been made.

Thu Nov 25 12:39:45 PST 2021
"Fish1" has been added to your tank.

Thu Nov 25 12:39:56 PST 2021
"Fish2" is not compatible with the fish already in the tank!

Thu Nov 25 12:40:10 PST 2021
"Fish3" has been added to your tank.

Thu Nov 25 12:40:38 PST 2021
"Balrog of Moria" is not compatible with the fish already in the tank!

Thu Nov 25 12:40:50 PST 2021
"imposter" has been added to your tank.

Thu Nov 25 12:41:11 PST 2021
"Ancient Koi of wisdom" has been added to your tank.

Thu Nov 25 12:41:17 PST 2021
imposter has been removed

Thu Nov 25 12:41:56 PST 2021
"fish4" has been added to your tank.

Thu Nov 25 12:42:01 PST 2021
Tank Tank-1 has been saved
".

####PHASE 4 :Task 3: (Further refactoring given more time)

- I'd start with MainPanel in the ui package, that's responsible for the main window that the user interacts with
when running the program.<br>
MainPanel is similar to the original SwimAlongApp class that ran the console version, therefore lacks cohesion 
and is responsible for too many tasks despite delegating some functionality to other classes. 
- MainPanel could also benefit from some helper methods in regard to constructing
the panel's elements.<br>
- Same applies for all the GUI related classes.
- I'd refactor the button's actionListener methods, IntelliJ recommends replacing with lambda. So might need to look 
into that. 
- After examining the UML diagram, I notice that there are many associations between classes and surely many can be 
factored out using recently learned design patterns.




