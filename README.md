Introduction:
=================================
This is a RESTful, CRUD web application that stores places of interest in a database and displays them either using a grid or a list layout back to the user.

The user can add, edit or delete places from the database. It uses the [Play framework](https://www.playframework.com/) and the MVC pattern, and is currently configured to run locally on one's machine.

There is also a Scala implementation of this project [here](https://github.com/muhsinali/picture-gallery-scala) (rewrote it in Scala so I could learn Scala :grinning:).

###How to run the web app:
To run the web app locally, start the MongoDB database using `mongod` and then go to the root directory of this project and run `activator run`. Once ready, go to [http://localhost:9000](http://localhost:9000) (if running for the first time, will need to wait a bit for the source code to compile).

###Tech stack:
- Java 8
- Play framework (version 2.3.10)
- MongoDB (Morphia 3.2)
- HTML, CSS, Bootstrap 3, [Twirl template engine](https://www.playframework.com/documentation/2.5.x/JavaTemplates)

If you have any suggestions, please submit a feature request/issue/bug. Always looking for feedback! 
