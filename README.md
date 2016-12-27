# studies-socket-connections
Java socket based project (academic)

Project consists of two application - Monitor and Sensor.

Specification:

Monitor: 
- It is an app with GUI in which user can connect to Sensor and send commands to it. It is also capable of showing data received from sensor to the screen

Sensor:
- Console program which is listening to any commands on choosen port and then based on those commands sets some parameters and runs sensing thread which sends data to monitor (which adress is set with one of commands).

Communication protocol:
- S:{interval},{host},{port}      :     tells Sensor the adress of monitor where it will output data and also interval for sensing process
- P:      :     tells Sensor to start sensing and sending data
- K:      :     tells Sensor to stop sensing and sending data
