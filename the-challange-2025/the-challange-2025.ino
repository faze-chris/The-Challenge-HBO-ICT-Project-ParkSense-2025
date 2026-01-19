#include <TM1637Display.h> // clock lib


bool wasOccupied1 = false;
bool wasOccupied2 = false;



#define CLK 3
#define DIO 2

// GROUP 1 PINS
#define PRESSURE_PIN A0    
#define REFLECTION_PIN A1  

// GROUP 2 PINS
#define PRESSURE_PIN_2 A2    
#define REFLECTION_PIN_2 A3  

// LED PINS in groups
#define G1_RED 12
#define G1_GREEN 13
#define G2_RED 11
#define G2_GREEN 10

TM1637Display display(CLK, DIO);

// FULL in hex code
const uint8_t SEG_FULL[] = {
  0x71,  // F
  0x3E,  // U
  0x38,  // L
  0x38   // L
};


void setup() {
  Serial.begin(9600);
  Serial.println("ARDUINO STARTED - READY FOR USB DATA");

  display.setBrightness(7);

  // set the reflection sensor pins as input
  pinMode(REFLECTION_PIN, INPUT);
  pinMode(REFLECTION_PIN_2, INPUT);

  // set LED pins as output
  pinMode(G1_RED, OUTPUT);
  pinMode(G1_GREEN, OUTPUT);
  pinMode(G2_RED, OUTPUT);
  pinMode(G2_GREEN, OUTPUT);

  // start by displaying 2 parking spots
  display.showNumberDec(2);
}

void loop() {

  int pressureValue = analogRead(PRESSURE_PIN);
  bool ifPressurePressed = (pressureValue > 200);

  int reflectionValue = digitalRead(REFLECTION_PIN);
  bool ifReflectionDetected = (reflectionValue == LOW);

  if (ifPressurePressed == true && ifReflectionDetected == true) {
    digitalWrite(G1_RED, HIGH);
    digitalWrite(G1_GREEN, LOW);


    // check previous state
    if (!wasOccupied1) {
      Serial.println("SENSOR_1:OCCUPIED"); 
      wasOccupied1 = true;                 
    }

  } else {
    digitalWrite(G1_RED, LOW);
    digitalWrite(G1_GREEN, HIGH);

    if (wasOccupied1) {
      Serial.println("SENSOR_1:FREE");
      wasOccupied1 = false;
    }
  }

//repeat for group 2
  int pressureValue2 = analogRead(PRESSURE_PIN_2);
  bool ifPressurePressed2 = (pressureValue2 > 200);

  int reflectionValue2 = digitalRead(REFLECTION_PIN_2);
  bool ifReflectionDetected2 = (reflectionValue2 == LOW);

  if (ifPressurePressed2 == true && ifReflectionDetected2 == true) {
    digitalWrite(G2_RED, HIGH);
    digitalWrite(G2_GREEN, LOW);

    if (!wasOccupied2) {
      Serial.println("SENSOR_2:OCCUPIED");
      wasOccupied2 = true;
    }

  } else {
    digitalWrite(G2_RED, LOW);
    digitalWrite(G2_GREEN, HIGH);

    if (wasOccupied2) {
      Serial.println("SENSOR_2:FREE");
      wasOccupied2 = false;
    }
  }

  int totalSpots = 2;

  if (ifPressurePressed == true && ifReflectionDetected == true) {
    totalSpots = totalSpots - 1;
  }

  if (ifPressurePressed2 == true && ifReflectionDetected2 == true) {
    totalSpots = totalSpots - 1;
  }

  if (totalSpots == 0) {
    display.setSegments(SEG_FULL);
  } else {
    display.showNumberDec(totalSpots);
  }
int spot1 = (ifPressurePressed && ifReflectionDetected) ? 1 : 0;
int spot2 = (ifPressurePressed2 && ifReflectionDetected2) ? 1 : 0;

Serial.print(spot1);
Serial.print(",");
Serial.print(spot2);
Serial.print(",");
Serial.println(totalSpots);



  delay(100);  // delay to stop flickring
}
