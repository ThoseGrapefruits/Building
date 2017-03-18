Office Rumpus
=============
> A 2D office environment with people who interact with objects in the building (elevators, lights, doors, etc).

The initial goal of this project was to build an environment with simulated elevators that would come when called and could take people between floors. The end result is that, with some other little things thrown in (doors, lights, etc.) but without a fully fleshed out elevator interaction.

This was built on top of a `Graphics2D` window in `Swing`, and uses (I believe) nothing beyond that and Java builtins.

<br><hr><br>

## Controls

|     Key      |    Action    |
|--------------|--------------|
| <kbd>↑</kbd> |     Jump     |
| <kbd>←</kbd> |  Walk Left   |
| <kbd>→</kbd> |  Walk Right  |
| <kbd>space</kbd> | Interact |

<br><hr><br>

## Brainstorming
- <del>How should I implement walls & other permanent impassable objects?</del>
	- Using java.awt.Rectangle for boundary detection.
- </del>Coordinate system? Definitely need this in one way or another.</del>
	- Using pixel coordinates.
- Separate class for interaction that is extended by all the interactive objects. Specifications like `interactive` for every object and `inUse` for `Interactive` objects to specify interactivity and whether they are busy at the moment.
	- Did exactly this.
- The core problems are creating interactive objects and AI with intelligence enough to interact with objects they need to and choose random paths to travel on.
	- This is mostly done, but there will be a bit more complication when I add elevators, etc.
- <del>I want to make it scalable and somewhat randomly generated (as in, it will select a building size and the number of elevators, locations of doors, etc) but that will be a somewhat advanced feature.</del>
	- Not going to bother with this. Way too much complication for a nonessential feature.

<br><hr><br>

## Visual Preview
> This was the visual goal of the project.

<img src="https://docs.google.com/drawings/d/1zptTJuAx5-7astRzow8TUkD2LLrvAqHOmoRVIKBLf50/pub?w=827&amp;h=588">

> This is how it ended up looking.

![](http://i.imgur.com/Da2WD7l.png)
