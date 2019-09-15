# paystationtdd-02-kieser-bilger-its-in-python
Project repository for the T.U. CIS 3296 course - Software Design
Team Members: Curtis Kieser and Chris Bilger

Requirements: This assignment required us to create our own repository of the PayStation project, and make a list of specified modifications to the code. These modifications had to be made by using the github repository to exchange each other’s work. This gave us practice coding in a collaborative setting and with test driven development. We fulfilled the collaborative requirements by uploading a new commit after each new test has successfully passed. The specific modifications requirements were to change the requirements for cancel, to add a method to int empty() that returns the total amount of money accumulated from purchases since the last time empty() was called, and to create the following test cases listed below:
- Empty() successfully returns the total amount accumulated.
- A canceled transaction does not add to the amount returned by empty.
- Empty() successfully resets the total amount accumulated to zero.
- Cancel() successfully returns a map when one coin is entered.
- Cancel() successfully returns a map when multiple coins are entered. The map must return the same specific mixture of coins entered
- Cancel() successfully retuns a map when no coins are entered.
- Cancel() successfully clears the map
- Buy() successfully clears the map

Team Contributions:

Curtis Kieser: <FILL IN LATER>

Chris Bilger: We worked physically side-by-side for the majority of this project. I personally wrote the code for the bullet line items 1,3,4,5, and 7. I have the commit history for line item number 8; however, Curtis wrote that on my laptop. I don't like this showing up this way, so for all future projects I will only commit what I personally wrote. I contributed code, but in my opinion, more ideas than anything. I think that I found fairly simplistic ways of approaching each test case so that the tests would pass and that they would correctly test for each bullet item. I learned that communication is necessary when working on programs because it is very easy to work on the same part at the same time and it's also very easy to forget to merge newly updated repository code into your local code before adding a new feature.
