// clock display
#include <TM1637Display.h>

// this prevents the Arduino from spamming "OCCUPIED"
bool wasOccupied1 = false;
bool wasOccupied2 = false;


// PINS clock
#define CLK 3
#define DIO 2

// GROUP 1 PINS
#define PRESSURE_PIN A0    // pressure Sensor
#define REFLECTION_PIN A1  // reflection sensor

// GROUP 2 PINS
#define PRESSURE_PIN_2 A2    // 2 pressure Sensor
#define REFLECTION_PIN_2 A3  // 2 reflection sensor

// LED PINS in groups
#define G1_RED 12
#define G1_GREEN 13
#define G2_RED 11
#define G2_GREEN 10

TM1637Display display(CLK, DIO);

// added: FULL in hex code
const uint8_t SEG_FULL[] = {
  0x71,  // F
  0x3E,  // U
  0x38,  // L
  0x38   // L
};
// dutch version
// const uint8_t SEG_FULL[] = {
//   0x3E,  // v = doesnt work but this is a u
//   0x3F,  // o
//   0x38,  // l
// };


void setup() {
  Serial.begin(9600);
  // notify that the USB connection is active for DB
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

  // read pressure Sensor
  int pressureValue = analogRead(PRESSURE_PIN);
  // if value is > 200, then reed pressed / car is on parking spot
  bool ifPressurePressed = (pressureValue > 200);

  // read reflection sensor
  int reflectionValue = digitalRead(REFLECTION_PIN);
  // the refelction sensor sends a LOW signal (0) when it when the car breaks the laser
  bool ifReflectionDetected = (reflectionValue == LOW);

  // LED Logic for Group 1
  if (ifPressurePressed == true && ifReflectionDetected == true) {
    //if both active: RED ON
    digitalWrite(G1_RED, HIGH);
    digitalWrite(G1_GREEN, LOW);


    // check if this was free before?)
    if (!wasOccupied1) {
      Serial.println("SENSOR_1:OCCUPIED"); // Send message via USB
      wasOccupied1 = true;                 // Remember that it is now occupied
    }

  } else {
    // if empty: GREEN ON
    digitalWrite(G1_RED, LOW);
    digitalWrite(G1_GREEN, HIGH);

    // check if this was occupied before?)
    if (wasOccupied1) {
    // send message via USB for DB
      Serial.println("SENSOR_1:FREE");
    // remember that it is now free
      wasOccupied1 = false;
    }
  }

  // repete but for group 2
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

  // start with 2 parking spots available
  int totalSpots = 2;

  // check group 1: Only count if BOTH sensors in Group 1 are active/dectecting input
  if (ifPressurePressed == true && ifReflectionDetected == true) {
    totalSpots = totalSpots - 1;
  }

  // check group 2: Only count if BOTH sensors in Group 2 are active/dectecting input
  if (ifPressurePressed2 == true && ifReflectionDetected2 == true) {
    totalSpots = totalSpots - 1;
  }

  // if all spots are filled
  if (totalSpots == 0) {
    display.setSegments(SEG_FULL);
  } else {
    display.showNumberDec(totalSpots);
  }
// Determine each spot status (1 = occupied, 0 = empty)
int spot1 = (ifPressurePressed && ifReflectionDetected) ? 1 : 0;
int spot2 = (ifPressurePressed2 && ifReflectionDetected2) ? 1 : 0;


// Send CSV format via Serial: spot1,spot2,totalSpots
Serial.print(spot1);
Serial.print(",");
Serial.print(spot2);
Serial.print(",");
Serial.println(totalSpots);



  delay(100);  // delay to stop flickring
}