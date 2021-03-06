
## A Story of Passionate Software Engineer
You have an idea develop a website which allow users to store and share GPS track online (similar to http://www.trackprofiler.com/track/index). After discussion with your team, they helped you to came up with some mock-up files. 
Front-end side will be developed by another team member. You are the only one who is going to be in charge of the backend services development. 

Because you are so excited to show the idea to your CEO, you decided to reduce the scope and focus on three main tasks:

- Develop a WS API that allow users to upload "gpx" file and store mandatory information such as "metadata, waypoint, track" 
- Develop a WS API to return a list of "Latest track" from our users
- Develop a WS API to allow users to view details of their gpx file
  
Although this is a prototype version, but you are a "professional" software engineer. You don't allow yourself to produce "dirty-code" and code without "unit test".

This is all you have right now: 

- https://en.wikipedia.org/wiki/GPS_Exchange_Format
- Mock-up files
- A sample gpx file
- A passionate heart, if you don't like the given mock-up files, feel free to change and show your CEO a better version
- Your team is a big fan of "Spring IO" tech stack, so they prefers you use Sprint Boot as a starting point
- An in-memory database is enough for this moment (H2)