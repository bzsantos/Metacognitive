# Cognomy Project - Classifying and recommending knowledge-level metadata

It is necessary to download the software and data from this tool to test the software. This project was developed with Java EE and MySQL. The following steps must be taken to test the application:

1- Download the files from this directory and unzip them in an appropriate directory on your computer.

2- Load the unzipped software directory named MetaSocial in the Netbeans 8.2 IDE (It is a pre-requisite for you to be able to test the application). You must use JDK 8.u.X for it to work, both I'm making available in this directory if you need them:
link: https://drive.google.com/drive/folders/1zQaOOxhRA3-sxkgmhXRiI7JpFm9uPpA6?usp=sharing

3- It is necessary to use a DBMS to load the data, in this case, we use MySQL 5.5 community version, this software is freely licensed, as well as Netbeans, if you need it, I'm leaving the link, you can use MySQL version 5.5 or newer:
link: https://dev.mysql.com/downloads/installer/

4- To manipulate the procedures we use MySQL Workbench, which is a free IDE, if you need it, follow the:
link: https://downloads.mysql.com/archives/workbench/

5- Run the metalearn.sql file in MySQL to create the metalearn database and the structure of the tables used in the prototype.

6- Load the data contained in the document data-example1.sql table so that you can manipulate the data.

7- For each analysis, it is necessary to load the instance with the sample that you want to analyze, the previous procedure '6' needs to be updated in order to get a clean result.

8- To filter the data so that the software understands the data set and which reference of the 'term' to prepare the sample, follow the steps within the data-example1.sql procedure, after executing the steps go to the software prototype and load it on the tomcat Web server, there you must create a user and test the application.

9- Just use a term in the search and write the title and subtitle to create the post and the recommendation system will offer a set of metadata at the level of knowledge. Despite having several steps, it is not complicated to run the software.

I'll leave a link to my YouTube channel so you can see these previous steps and be able to test if you need to. (As soon as possible)

For the tests, it is necessary to load a set of data according to the objective, the loaded data were used in the research carried out with the title:

The metrics and explanations I'm leaving here in Git with the URL address: http://www.zolotareff.com.br/metadata and on the YouTube channel. (As soon as possible)

To understand how the classification algorithm works, it is necessary to observe the pseudocode, however, part of the expressions in the article happen automatically, however, at the request of advisors, I am putting the explanation.

This work is licensed under a CC BY 4.0 License
