/* How to use the DHT-22 sensor with Arduino uno
   Temperature and humidity sensor
   More info: http://www.ardumotive.com/how-to-use-dht-22-sensor-en.html
   Dev: Michalis Vasilakis // Date: 1/7/2015 // www.ardumotive.com */

//Libraries
#include "dht.h"

//Constants
dht DHT;
const int dhtPin = 2; //Data pin of DHT-22 to Arduino digital pin 2

//Variables
int chk;
float hum;  //Stores humidity value
float temp; //Stores temperature value
unsigned long time;

void setup()
{
    Serial.begin(9600);
}

void loop()
{
	chk = DHT.read22(dhtPin); //Check data pin and read values
    //Read data and store it to variables hum and temp
    //hum = DHT.humidity;
    //temp= DHT.temperature;
    //Print temp and humidity values to serial monitor
    Serial.print(String(DHT.humidity) + " " + String(DHT.temperature) + "\n\r");
    delay(60000); //Delay 60 sec.
}

   
