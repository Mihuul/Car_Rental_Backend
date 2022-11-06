Hi! This is my first independently made REST API-based application. This is a simple car rental service.
The app allows users to create an account, log in, search for a car and rent it.
The app runs on local server 8080.
In order to launch the application, it is necessary to perform a few preparation steps:

1. Please create a MySQL database named car_rental_db and user with the below credentials:
   
   USERNAME = car_admin

   PASSWORD = car_password


2. Generate an API key for Email Verification API:

   The API key can be found on this site: https://main.whoisxmlapi.com/ after creating an account.

---------------
Features

Test coverage > 73%
---------------
Two external API's:
VinApi (for decode vin numbers)
EmailVerification (verifying correctness of email used during registration)
------------------------------------------------------------------------------------------
Email sender scheduler (for admin daily information about actual rentals and cars)
------------------------------------------------------------------------------------------
Design patterns:
Facade
Builder
------------------------------------------------------------------------------------------
To get frontend head to: https://github.com/Mihuul/Car_Rental_Frontend
------------------------------------------------------------------------------------------
