"# TravelApp" 

## Overview
### Description
Provides the user with travel information
App Evaluation
Category: Travel
Mobile: This app would be primarily developed for mobile.
Story: Allows users to get dates and prices for flights. 
Market: This is for people who are planning for their trips.
Habit: This app is mostly seasonal for each individual, and more so for those who travel a lot. 
Scope: We want to launch this for the leisure travel individuals first, we would like to create other features to allows users to plan a travel all within the same app
API: This will utilize the Skyscanner API
Product Specs
### 1. User Stories (Required and Optional)
Required Must-have Stories

#### Search Activity
	[x] Find flight information by typing in destination
	[x] Enter inbound information with dates

 
#### Result Activity
	
	[x] Planning Activities
	[x] Calendar, planning, organization 
 
### Optional Nice-to-have Stories
Details on the destination location
Pictures, available hotel, to-do recommendations
Utilize hotels API

### 2. Screen Archetypes
Search Activity
Find flight information by typing in destination
This could be 
Enter inbound information with dates
Flight Suggestions
Result Activity
Quotes of the 
Provide filter for prices, dates, and routes
Details Screen
Information about the city weather, popular destination
Hotel information

### 3. Navigation
Tab Navigation (Tab to Screen)
Search
 
Flow Navigation (Screen to Screen)
Search -> Results upon request
Result -> Details upon request

## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | user          | String   | unique id for the user|   
   | cities        | String   | name of the cities |
   | departDate    | Long     | date of depareture for flight  |
   | arriveDate    | long     | date of arrival for flight   |
   | toDo          | toDoItem | a toDoItem that contains the information of what happens on the day   |

### Networking
#### List of network requests by screen
   - Flight Search Screen
      - (Read/GET) Query all flights according to dates 
         ```java
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/US/USD/en-US/SFO-sky/LAX-sky/2019-09-01?inboundpartialdate=2019-12-01")
	.setHeader("x-rapidapi-key", "7b4dfa76b8mshbfa8c1a8e2cadf6p143008jsnd94aafc4c7a7")
	.setHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
	.execute()
	.toCompletableFuture()
	.thenAccept(System.out::println)
	.join();

client.close();
         ```java
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Get List of places
   AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/UK/GBP/en-GB/?query=Stockholm")
	.setHeader("x-rapidapi-key", "7b4dfa76b8mshbfa8c1a8e2cadf6p143008jsnd94aafc4c7a7")
	.setHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
	.execute()
	.toCompletableFuture()
	.thenAccept(System.out::println)
	.join();

client.close();


##### Skyscanner API
- Base URL - https://rapidapi.com/skyscanner/api

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /cities | gets all cities
    `GET`    | /cities/byId/:id | gets specific city by :id
    `GET`    | /continents | gets all continents
    `GET`    | /continents/byId/:id | gets specific continent by :id
    `GET`    | /regions | gets all regions
    `GET`    | /regions/byId/:id | gets specific region by :id
    
    
### Walk through of the app
 
    https://imgur.com/a/VtIK7Dw

### icons provided by Flation
<div>Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
<div>Icons made by <a href="https://www.flaticon.com/free-icon/world_761505" title="Good Ware">Good Ware</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
