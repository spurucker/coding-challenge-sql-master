![eBayK Logo](https://www.ebay-kleinanzeigen.de/static/img/common/logo/logo-ebayk-402x80.png)
# SQL Coding Challenge

Congratulations! You made it to the eBay Kleinanzeigen remote SQL Coding Challenge, where we want to see your hands-on coding skills.


## Steps

1. Check installation prerequisites: JDK version >=11, Maven latest version, your preferred IDE/editor. To see that everything basic is working, execute `mvn verify`, it should show you a `SUCCESS` build.
2. Read carefully through "Requirements" and "Out-of-scope" to focus on the right things.
3. IMPORTANT: After finishing coding, please add your comments and remarks inside this `README.md` file, see end of file. It helps us gaining context on how you reflect on the coding task.
4. Send back the result as mentioned inside the coding challenge mail

Coding guidance: Combine "clean, object-oriented, tested code" with "working software".


##  Requirements

* Read and parse data from [`purchases.csv`](/src/main/resources/purchases.csv) and [`users.csv`](/src/main/resources/users.csv) and initialize to [`Table.java`](/src/main/java/exercise/Table.java)
* [`Table.java`](/src/main/java/exercise/Table.java) should be generic, column-names are inside .csv files and parsing should be based on this source.
* Implement an ORDER BY DESC ([SQL reference](http://www.w3schools.com/sql/sql_orderby.asp))
  * Input: 
    * the name of the column to order the rows
  * Output: the ordered table
* Implement an INNER JOIN of two tables ([SQL reference](https://www.w3schools.com/sql/sql_join.asp))
  * Input:
    * the name of the column to use for the join from the right table
    * the name of the column to use for the join from the left table
  * Output: the joined table
* Structure and design [`Table.java`](/src/main/java/exercise/Table.java)
  * Think about an appropriated domain model. Hint: the domain should not be purchases and users.
* You are not restricted to implement everything in the existing file/folder structure. Feel free to add or alter files as you see fit.


## Out-of-scope

* NO "real" persistence necessary (do not integrate MySQL, HSQLDB, h2database, or JPA). Use your own internal storage model (see also [`Table.java`](/src/main/java/exercise/Table.java)).
* NO need to implement any SQL parser or anything, Java code interface is sufficient.
* NO need to be Maven expert or adapt build, project harness and build is setup already.
* NO Javadoc necessary. Tests and self explaining code are sufficient.


## Your comments / remarks

I am not sure if I understand the exercise correctly. So I am going to explain what I understand, some assumptions and some questions I would do in a regular working day

About Purchases table:
- Ad_Id is the foreign key? Why AD? What does it mean? Shouldn't it be PURCHASE_ID?
- Is Tittle the name of an item? So car 1 is a BMW and car 2 is a Ferrari? Or is it the same car, but bought twice?
- Why is there no Price or quantity if it is a Purchase?

It is said Table.java should be generic. Does it mean it has to implement Generics? Or does it mean it should work with any file? I inclined for this last scenario.

Sorting: I consider all values as string cause there is no way to know what data type are them.	

I consider that Table represents a table, and not the database. So when I am asked to load all files, it means that I have two instances of table and another entity (Database) is created to hold them.

Domain Design:
- I was not sure if I had to implement something that would use the Table and Database classes, or just to think and create a diagram of possible classes with the data that I had. I inclined for this last scenario.
- I only create the domain entities with the information that I had. Stock and Pricing would be something that would probably be needed.
- I create two entities InvoiceItem and Item cause if an Item change would probably be required to keep the old value. I didn't this with Customer as this might not be the requirement.  

I didn't create much structure as the exercise didn't specify how to access the application. If it was web or just a jar. 
I did however create a main class that would receive two filePath, the first one for user and the second for purchase and print the values as they come, the user table ordered by id, and an inner join between them.
The time is out, and I realized that CsvReader should validate if the file has at least one row. 

