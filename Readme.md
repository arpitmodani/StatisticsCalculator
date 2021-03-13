# Statistics Calculator

 1. This is a Spring Boot Application. 
 2. System Requirements : 
 
                            JAVA 11.0.5
                            MAVEN
                            
 3. Created Rest APIs for all the given requirements. 
 4. Created an in-memory database that will persist the data while application is running. When you stop the application , all data will be deleted.
 5. Application will run on 8000 port.
 

### Rest APIs

1. void event(int value) -: 

                            URL :- /event/{num} 
                            Method:- POST
                            
2. float mean() -: 

                            URL :- /mean 
                            Method:- GET
                            
3. float mean(int lastNMinutes) :- 
                             
                             URL :- /mean/{lastNMinutes}
                             Method:- GET
                             
4.  float variance() :- 
                             
                             URL :- /variance
                             Method:- GET
                             
5.   int minimum() :- 
                             
                             URL :- /min
                             Method:- GET
                             
5.   int maximum() :- 
                             
                             URL :- /max
                             Method:- GET