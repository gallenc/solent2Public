
# Feature list and Test Plan

![alt text](../drawio/farmUseCase_draw_io.png "Figure farmUseCase_draw_io.png" )



## 1. Use UI to modify the farm list

The user should be able to select which animal to add to the farm from a list of available animal types

## Tests


### 1.1 Test User can add animal


#### preconditions
Selected view animals from start page.

No animals in list.


#### test actions

a) Select 'add Cat' to see add animal page

b) Enter cat name on page and select add animal


#### expected outcome

Cat added to list of animals


### 1.2 Test User can delete animal


#### preconditions
Selected view animals from start page.

Animals are already enered in the list


#### test actions

a) Select delete button beside animal to be deleted


#### expected outcome

Animal is no longer on list of animals


## 1. Use ReST interface to modify the farm list

The user should be able to use the rest interface to modify the farm list

## Tests


### 1.1 Test ReST User can add animal


#### preconditions
Rester is already configured as a test client for the ReST interface

No animals in list.


#### test actions

etc...

#### expected outcome

etc...

