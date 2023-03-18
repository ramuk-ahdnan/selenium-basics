@Amazon
Feature: Amazon Automation

@scenario1
Scenario Outline: Product search using Examples
  Given I launch the application
  And login using my credentials
  When I search for the product "<product>"
  Then I logout of the application
  Examples:
    |product|
  	|SanDisk Ultra Flair 64GB USB 3.0 Pen Drive|   
   
@scenario2
Scenario: Product search using DataTable
  Given I launch the application
  And login using my credentials
  When I search for the product using data table
  	|SanDisk Ultra Flair 64GB USB 3.0 Pen Drive|  
  Then I logout of the application
  