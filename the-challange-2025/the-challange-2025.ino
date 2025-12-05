// clock display
#include <TM1637Display.h>

// PINS
#define CLK 3
#define DIO 2

// GROUP 1 PINS
#define PRESSURE_PIN A0    // pressure Sensor
#define REFLECTION_PIN A1  // reflection sensor

// GROUP 2 PINS
#define PRESSURE_PIN_2 A2    // 2 pressure Sensor
#define REFLECTION_PIN_2 A3  // 2 reflection sensor

// LED PINS
#define G1_RED 12
#define G1_GREEN 13
#define G2_RED 11
#define G2_GREEN 10

TM1637Display display(CLK, DIO);

void setup() {
  Serial.begin(9600);
  display.setBrightness(7);

  // set the reflection sensor pins as input
  pinMode(REFLECTION_PIN, INPUT);
  pinMode(REFLECTION_PIN_2, INPUT);

  // Set LED pins as output
  pinMode(G1_RED, OUTPUT);
  pinMode(G1_GREEN, OUTPUT);
  pinMode(G2_RED, OUTPUT);
  pinMode(G2_GREEN, OUTPUT);

  // start by displaying 2 parking spots
  display.showNumberDec(2);
}

void loop() {

  // read pressure Sensor
  // if value is > 200, then reed pressed / car is on parking spot
  int pressureValue = analogRead(PRESSURE_PIN);
  bool ifPressurePressed = (pressureValue > 200);

  // read reflection sensor
  // The refelction sensor sends a LOW signal (0) when it when the car breaks the laser
  int reflectionValue = digitalRead(REFLECTION_PIN);
  bool ifReflectionDetected = (reflectionValue == LOW);

  // LED Logic for Group 1
  if (ifPressurePressed == true && ifReflectionDetected == true) {
    // Both active -> RED ON
    digitalWrite(G1_RED, HIGH);
    digitalWrite(G1_GREEN, LOW);
  } else {
    // Empty -> GREEN ON
    digitalWrite(G1_RED, LOW);
    digitalWrite(G1_GREEN, HIGH);
  }

  // repete but for group 2
  int pressureValue2 = analogRead(PRESSURE_PIN_2);
  bool ifPressurePressed2 = (pressureValue2 > 200);

  int reflectionValue2 = digitalRead(REFLECTION_PIN_2);
  bool ifReflectionDetected2 = (reflectionValue2 == LOW);

  // LED Logic for Group 2
  if (ifPressurePressed2 == true && ifReflectionDetected2 == true) {
    // Both active -> RED ON
    digitalWrite(G2_RED, HIGH);
    digitalWrite(G2_GREEN, LOW);
  } else {
    // Empty -> GREEN ON
    digitalWrite(G2_RED, LOW);
    digitalWrite(G2_GREEN, HIGH);
  }


  int totalSpots = 2;  // Start with 2 parking spots available

  // Check Group 1: Only count if BOTH sensors in Group 1 are active
  if (ifPressurePressed == true && ifReflectionDetected == true) {
    totalSpots = totalSpots - 1;
  }

  // Check Group 2: Only count if BOTH sensors in Group 2 are active
  if (ifPressurePressed2 == true && ifReflectionDetected2 == true) {
    totalSpots = totalSpots - 1;
  }

  // If BOTH are active
  display.showNumberDec(totalSpots);

  delay(100);  // delay to stop flic
}