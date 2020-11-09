# Week 2

# merging the upstream code with git

To synchronise your repository with the upstream examples use the following commands

if you have not set up the upstream repo
```
git remote add upstream https://github.com/gallenc/solent2Public.git
``
Once the upstream is set use
```
git fetch upstream
git checkout master
git merge upstream/master
git push
```
copy week2 to myPracticeCourseWork and do your work there.

# Maven Test Exercise - Logging and Testing

Last week you were asked to import you simple MyTestClass and MyTestClassLog4J into a maven project.
This project contains the answer and also some extra work to consolidate knowledge of logs and testing.

1. Compare your answer to the example here
See how the pom.xml file imports the log4j and junit dependency jar

2. Look at the log4j.xml file in the resources folder. What is this doing for us?

3. Look at the SimpleMathClass.java and it's corresponding SimpleMathClassTest.java. 
Can you complete the class and add tests which prove it works and also test the edge cases where wrong input is entered.

# Jsp exercises

# Finally
You have created a new project in  
```
myPracticeCourseWork/example1
```
You should check this in to your local git repository and push to your github account.

IMPORTANT NOTE - make sure that the generated target directory is ignored by git (.gitignore). 
(You can also do this in Netbeans by right clicking the file/folder you wish to ignore and selecting git/ignore)

```
git status ( should show 'unstaged' commits)
git add --all (to 'stage' all the changes or new files you are going to commit'
git status (just to check what you are checking in)
git commit -m 'checking in my first example1' ( add a meaningful message so that people can see the history of your work)
git status ( whould show your repository is ahead of origin by one commit)
git push
```



