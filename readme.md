## Packaging Challenge
A solution to packaging problem where maximum price is required withing weight range.

## Getting Started
### Running tests
mvn clean install
## Assumptions
1.Index of Paket is Long and Price is Int.
###
2.No Rest Endpoints since it is a maven dependency! If microservices arch. was mentioned then it could still be Spring Boot with rest endpoints. 
###
3.All lines in the file are valid! There is a pre validate method to check all lines before processing.
## Algorithm/Strategy
Greedy algorithm after sorting Things by Price DESC and Weight ASC.
###
Reason for that is readability , correctness and simplicity.
## Design Patterns
Singleton design pattern is used as In memory Packages repository.
###
Spring context is used for DI (for easier maintainibility) except on the static method mentioned pack.
## Data Structures
IMPORTANT : all data strucures are List as we always either know the index or iterating over all items. 
as well as the max size of items is 15! otherwise HashMaps and Queues could be used.
## Other Notes
TDD has been applied!
###
Test coverage is 100% in used methods.
###
Parent is Spring boot excluding web starters.
###
Sample files added to resources for main as well as test.
###
In Packer class there is a commented main method , you can un comment to run file from resources.
