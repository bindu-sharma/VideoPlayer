Following are files are added to the blank project provided:
1. NetworkingService - To connect to the URL and fetch the JSON data.
2. JsonService - To parse the JSON data and get the required attributes.
3. MyApp - To create the objects of the classes at App level.
4. VedioItem - Contains the blueprint of the reqired data.

NOTE - The URL provide did not work in my system so I created the project assuming that JSON data will be array of objects containing video link and decsription only and created the app accordingly.

IDEA BEHIND THE CODE - 
Step 1 - Networking service class connects to a URL and get the JSON data.
Step 2 - JSON data is then passed to JsonService class which parses the JSON data and extract video link and decsription from the it ( Assuming that JSON data contains these as URL did not work so could not have a look at it). 
Step 3 - After retrieving video link and description, these are added to an array of objects containing video link ad description as elements.
step 4 - Finally, we have array of objects containg video links and their descripton.
Step 5 - Media controllers and video path is set for the video view which is first element in the array we got in the previous step. Till the last element in the array, the path can be set using for loop.