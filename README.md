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




  