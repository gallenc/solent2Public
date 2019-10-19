# solent2Public
Git Repository of java examples for Solent Students for COM504 Object oriented design and development.

## Contents
The repository contains a number of example projects, weekly exercises and an area for you to create and store your own work during the course 'myPracticeCourseWork'. Please read and follow the Getting Started instructions before doing anything else.

[myPracticeCourseWork](../master/myPracticeCourseWork) This is where you should create and save your own practice projects.

[maven-setup](../master/maven-setup) This contains scripts to help you set the java class path and install maven if necessary.

[week1](../master/week1/) Initial exercises for the course.

PLEASE NOTE that the class PC's should now have maven pre-installed so these steps in [maven-setup](../master/maven-setup) not necessary on all machines. 


## Getting Started with GIT
Before doing anything else you will need to follow these getting started instructions.
This will teach you a little bit about git and how to fork a copy of this repository into your own github account.

You will then be able to clone your fork locally and then follow the instructions in the [maven-setup](../master/week1/maven-setup) project to get javac and maven working. 
After this you can then proceed to doing the exercises in [week1](../master/week1/).

### Introduction to GIT
To be a professional developer, you will need to become proficient at using version control systems.
Many version control systems (each with their own benefits and drawbacks e.g CVS, Subversion, git) have been popular over the years. 
Presently one of the most popular, git, was developed by Linus Torvalds to help with the collaborative development of the Linux Kernel.

In recent years many open source projects have migrated their code base to github (https://github.com) which supports a collaborative workflow for team development and sharing of source code using git. 

The principle advantage of git over other version control systems is that it is completely distributed. When you use git you clone a complete local copy of the repository you are cloning (usually referred to as the origin). You can develop code and save your changes in your local repository completely off line. At a later stage you may wish to push your changes to the on line repository or pull changes others have made into your local copy. 

There are many on line tutorials on using git and it will be worth while spending some time on these to get proficient. e.g.
* https://try.github.io/ Resources to learn Git
* https://guides.github.com/introduction/git-handbook/ Git Handbook 
* https://learngitbranching.js.org/ provides a very useful interactive tutorial on branching and merging.
 
Many IDE's including Netbeans support git natively, but it is very important to learn to use the git command line independently of the IDE so that you have more control over what is happening.

Using git successfully with a team can be complex but fortunately you only need to master a few commands to work on your own project
```
git clone  (a command to clone your local copy of the on line repository)
git status (a command to tell you what is the state of your local repository e.g are their any changes to commit and push)
git pull  (a command to pull the latest changes from the remote repository into your local copy)
git add --all (a command to stage all of the current changes ready for a commit)
git commit -m 'my commit message' (a command to commit changes to your local repository)
git push (a command to push your latest commits up to the remote repository)
```
### Forking the solent2Public repo
You could just clone the master solent2Public repo and work on the clone locally. 
However you do not have write permissions to this repo and so you couldn't save (or push) any changes or work you have added.
Therefore, to be able to use the examples and save your own work, it will be better to create a copy (called a fork) of this repository in your own github account where you will be able to push and save your changes on line.

You should open your own personal github account and FORK this repository into your own account. 
This will give you your own copy to work with and a backup of your work on github.
PLEASE NOTE, while github is very reliable, you should also keep a backup copy of your repo in case anything goes wrong.

To create a fork of this repository
1. sign in to your own github account
2. navigate to this repository https://github.com/gallenc/solent2Public/
3. click the FORK icon

![alt text](../master/docs/images/ForkingARepo.png "Figure ForkingARepo.png")

you can understand the process by reading these documentation examples.
https://help.github.com/articles/fork-a-repo/

### checking out your own fork

Having forked the repository, go to your own github account and clone the repository into your own workspace on your machine. 
Rather than just clone the repository into a workspace on your IDE, it is good practice to create a separate folder on your local machine where you will clone your remote repositories. 
You can import separate projects from this clone into your IDE workspace as you need to work on them.

Create a git repo folder on your local drive and clone your fork of solent2Public into it.
```
mkdir gitrepos
cd gitrepos
git clone https://github.com/ {your github id }/solent2Public.git
```

You should now have a clone of your fork in your gitrepos directory
gitrepos/solent2Public

You can see how to open projects in this repo using gthe Netbeans IDE in the exercises under

[maven-test-exercise](../master/week1/maven-test-exercise)

### hidden files .git and .gitignore
You should set the view on your windows file explorer to show hidden files and file extensions. 
This will allow you to see git and IDE specific files which are otherwise hidden.

In particular, you will see that the top level folder solent2Public contains a .git folder.
This is where git stores all of the branches versions and changes to your repository. 
The rest of the files under solent2Public are the currently checked out versions of your code.

You will also notice that many of the projects in this repo have a .gitignore file.
This tells git to ignore certain directories or files when committing changes.
.gitignore files inherit from .gitignore files further up the class path. This allows you to have a generic .gitignore and a more specific one in a nested folder to specify project specific files you don't want to check in in. 

It is VERY important to ensure that, in particular 'target', directories are NOT checked into git as this would fill your repository up with unnecessary class and jar files.

You should also ensure that in most cases IDE specific sub folders and files are not checked in to git as this will cause confusion if you change or upgrade your IDE. Your ide will read the maven pom.xml file and recreate these folders locally if necessary.

The example [.gitignore](../master/.gitignore)  should be suitable for most purposes and should be copied into the top level of your git repository.



### Syncing with the upstream repo
I will be adding stuff to the upstream repo each week and you should be able to pull these into your local repo using the procedure described below.

PLEASE DO NOT CHANGE ANYTHING IN YOUR LOCAL REPO EXCEPT IN myPracticeCourseWork.
 this will allow merges to go smoothly.

you can see which remote repositories are referenced in your local repo using
```
$ git remote -v
origin  https://github.com/{ your github id}/solent2Public.git (fetch)
origin  https://github.com/{ your github id}/solent2Public.git (push)
```

To sync with the upstream repo you need to add another remote repository
```
$ git remote add upstream https://github.com/gallenc/solent2Public.git

$ git remote -v
origin  https://github.com/{ your github id}/solent2Public.git (fetch)
origin  https://github.com/{ your github id}/solent2Public.git (push)
upstream        https://github.com/gallenc/solent2Public.git (fetch)
upstream        https://github.com/gallenc/solent2Public.git (push)

```
Now, you can keep your own fork of solent2Public synced with the upstream repository with a few Git commands.

1. Fetch the branches and their respective commits from the upstream repository. 
Commits to master will be stored in a local branch, upstream/master.
```
git fetch upstream
From https://github.com/gallenc/solent2Public
 * [new branch]      master     -> upstream/master
remote: Enumerating objects: 18, done.
remote: Counting objects: 100% (18/18), done.
remote: Compressing objects: 100% (7/7), done.
remote: Total 12 (delta 3), reused 12 (delta 3), pack-reused 0
Unpacking objects: 100% (12/12), done.
From https://github.com/gallenc/solent2Public
   ddd9643..bd85c0d  master     -> upstream/master
```

2. Check out your fork's local master branch.
```
git checkout master
Switched to branch 'master'
```
3. Merge the changes from upstream/master into your local master branch. This brings your fork's master branch into sync with the upstream repository, without losing your local changes.
```
git merge upstream/master
Updating ddd9643..bd85c0d
Fast-forward
 README.md                           |  89 +++++++++++++++++++++++++++++++++++-
 docs/images/ForkingARepo.png        | Bin 0 -> 44529 bytes
 maven-setup/README.md               |  44 ++++++++++++++++++
 week1/README.md                     |   7 +++
 week1/maven-test-exercise/README.md |  12 ++++-
 5 files changed, 149 insertions(+), 3 deletions(-)
 create mode 100644 docs/images/ForkingARepo.png
 create mode 100644 maven-setup/README.md
 create mode 100644 week1/README.md
```
Your local master branch should now contain all the changes from the upstream repository.

4. You should check in these changes to your own repository

```
git status
On branch master
Your branch is ahead of 'origin/master' by 1 commit.
  (use "git push" to publish your local commits)
nothing to commit, working directory clean

git push
Counting objects: 12, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (10/10), done.
Writing objects: 100% (12/12), 44.69 KiB | 0 bytes/s, done.
Total 12 (delta 3), reused 0 (delta 0)
remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
To https://github.com/gallenc-test/solent2Public.git
   ddd9643..bd85c0d  master -> master

git status
On branch master
Your branch is up-to-date with 'origin/master'.
nothing to commit, working directory clean

```

## Summary
to synchronise your repository with the upstream use the following commands

if you have not set up the upstream repo
```
git remote add upstream https://github.com/gallenc/solent2Public.git
```
Once the upstream is set use

```
git fetch upstream
git checkout master
git merge upstream/master
git push

```


## further reading

you should get familiar with the process for syncing a fork which means pulling down changes or updates from my master repository into your own fork.

https://help.github.com/articles/syncing-a-fork/

https://help.github.com/articles/configuring-a-remote-for-a-fork/




  
