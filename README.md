# solent2Public
Git Repository of java examples for Solent Students for COM504 Object orientated design and development.

## contents
The repository contains a number of example projects, weekly exercises and an area for you to create and store your own work during the course myPracticeCourseWork. 

## how to use

You should open your own github account and FORK this repository into your own account. 
This will give you your own copy to work with and a backup of your work on github.

### forking the repo

To create a fork of this repository
1. sign in to your own github account
2. navigate to this repository https://github.com/gallenc/solent2Public/
3. click the FORK icon

![alt text](https://github.com/gallenc/solent2Public/raw/master/docs/images/ForkingARepo.png "Figure NForkingARepo.png")

you can understand the process by reading these documentation examples.
https://help.github.com/articles/fork-a-repo/

### checking out your own fork

Having forked the repository, go to your own github account and clone the repository into your own workspace on your machine. create a git repo folder on your local drive and clone into in
```
mkdir gitrepos
cd gitrepos
git clone https://github.com/ {your github id }/solent2Public.git
```

You should now have a clone of your fork in your gitrepos directory
gitrepos/solent2Public

### syncing with the upstream repo

```
$ git remote -v
origin  https://github.com/{ your github id}/solent2Public.git (fetch)
origin  https://github.com/{ your github id}/solent2Public.git (push)

$ git remote add upstream https://github.com/gallenc/solent2Public.git

$ git remote -v
origin  https://github.com/{ your github id}/solent2Public.git (fetch)
origin  https://github.com/{ your github id}/solent2Public.git (push)
upstream        https://github.com/gallenc/solent2Public.git (fetch)
upstream        https://github.com/gallenc/solent2Public.git (push)

```
Now, you can keep your fork synced with the upstream repository with a few Git commands.

Fetch the branches and their respective commits from the upstream repository. Commits to master will be stored in a local branch, upstream/master.
```
git fetch upstream
From https://github.com/gallenc/solent2Public
 * [new branch]      master     -> upstream/master
```

Check out your fork's local master branch.
```
git checkout master
Switched to branch 'master'
```
Merge the changes from upstream/master into your local master branch. This brings your fork's master branch into sync with the upstream repository, without losing your local changes.
```
git merge upstream/master
```
Your local master branch should now contain all the changed from the upstream repository.
You should check in your changes to your own repository
```
git add --all
git commit -m 'merged upstream changes'
git push
```

you should get familiar with the process for syncing a fork which means pulling down changes or updates from my master repository into your own fork.

https://help.github.com/articles/syncing-a-fork/

https://help.github.com/articles/configuring-a-remote-for-a-fork/

I will be adding stuff to the upstream repo each week and you should be able to pull these into your local repo using the procedure described.

PLEASE DO NOT CHANGE ANYTHING IN YOUR LOCAL REPO EXCEPT IN myPracticeCourseWork.
 this will allow merges to go smoothly.



  