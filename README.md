&#x20;**Road Distance Calculator**



A Java desktop application that calculates the road distance between two locations and estimates travel time, fuel requirements, and petrol cost.



**Features:**

Enter an origin and destination location

Calculate road distance between locations

Calculate estimated travel time

Calculate fuel required and estimated petrol cost

Reset the input fields and results

Input validation and error handling



**Technologies Used**



\- Java

\- JavaFX

\- NetBeans

\- REST APIs

\- OpenStreetMap Nominatim API

\- OSRM Routing API



**How It Works:**



The application takes two locations from the user.



1\. The **Nominatim API** is used to find the latitude and longitude of each location.

2\. The **OSRM Routing API** calculates the driving distance between the two locations.

3\. The application uses the distance together with: Petrol price ,Fuel consumption and Average speed.

4\. The application displays the calculated results.



**Calculations:**



Travel Time = Distance / Average Speed

Fuel Required = (Distance × Fuel Consumption) / 100

Petrol Cost = Fuel Required × Petrol Price



Eg. A user can enter following:

Origin: Eastern Cape

Destination: Cape Town



Petrol Cost: 24

Fuel Consumption: 8 L/100 km

Average Speed: 90 km/h



Then the application will display : distance , time to travel , fuel required and fuel cost.



**APIs:**



This project uses:

OpenStreetMap Nominatim for location search

OSRM for road routing and distance calculation



**Author:**

Siphelele Tolibadi

