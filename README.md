# TITLE: CUSTOM ACTION REPOSITORY


# Description
The custom action repositories contain various custom actions that a person 
might need to resolve their scenario while automating.


# Motivation
The websites differ from one project to another. Also, they differ in their 
implementation. Thus, it is not always possible that the scenarios specific to 
the project would be solved by Qualitia actions.

This is where the custom actions come in. Custom actions are a small snippet 
of automated code used to acheive a scenario. It is more often that the scenarios 
are common to different projects. Taking that into consideration, you will find 
the the custom actions that suite your scenario in this repository.


# Structure of the Repository
- Each custom action will have a separate .java file.
- They would be placed in the respective folders e.g. If the action is for Web 
you will find it in Web folder, if it is for Mobile project then it would be 
present in the mobile folder.
- There is sub-division within the respective folder on whether it is a general
action or object based action.
- For each action, you will find a JavaDoc written on top of it that will tell 
what the action does and in what scenarios it can be used.


# How to use the Repository?
- You can navigate and search for the custom action that satisfy your scenario.
- If the action is exactly how you need it you can add it to your projects 
through Qualitia and paste the code snippet in eclipse and execute it.
- If the action is not an exact copy of what you need then you can copy it in 
your project and do the minor changes needed for it to fit your need.


# How to contribute?
- If you have developed some custom action that you feel is generic and can be 
used by others then you can create a pull request for that custom action.
- Internal team will look into it and make the necessary modifications and add 
it to the repository.
