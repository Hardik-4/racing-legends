# Racing Legends

Local two-player top-down racing game built in Java Swing.

Race around the track, hit every checkpoint, then cross the finish line first. Cars bounce off each other and stay inside the track bounds.

## Features

- Two-player local multiplayer (WASD vs Arrow keys)
- Image-based track and cars with colored fallbacks
- Checkpoint + finish-line win condition
- Car-to-car bounce collision
- Diagonal movement speed normalization
- Restart with **R**
- Unit tests for core classes

## Controls

| Player | Move |
|---|---|
| Player 1 (Red) | `W` `A` `S` `D` |
| Player 2 (Blue) | Arrow keys |
| Either | `R` restart |

## Run

From the project root (so `images/` resolves correctly):

```bash
javac -d out $(find src -name "*.java" ! -path "*/test/*")
java -cp out com.racinglegends.Main
```

## Tests

JUnit 5 tests live under `src/com/racinglegends/test/`. Open them in Eclipse/IntelliJ with JUnit 5 on the classpath.

## Project structure

```
src/com/racinglegends/
  Main.java
  GamePanel.java
  Car.java
  Track.java
  CollisionDetector.java
  KeyHandler.java
  GameUI.java
  GameConstants.java
  test/ ...
images/
  track.png
  car_red.png
  car_blue.png
```

## Team

Originally developed as a Griffith College SD2 group project (Group J), with contributions from Hardik Rathee, John Jose Pullan, Akshay Jolly, and Syril Sinkencherian.

## Author (this GitHub mirror)

Hardik Rathee ([Hardik-4](https://github.com/Hardik-4))
