Feature: User Registration

Scenario Outline: User registration with given details

Given User is on registration page
When User enters <id>, <name>, <mobile>, <email>, <password>, <role>
Then User should be <status>

Examples:
|id|name|mobile|email|password|role|status|
|101|"Alex"|"9989102938"|"alex@gmail.com"|"alex@123"|"admin"|"Registered Successful"|
|101|"Alex"|"9989102938"|"alex@gmail.com"|"alex@123"|"admin"|"User already Exists"| 
