#Office Simulator 2014
> A virtual 2D building simulation and modelling program with random "people" who interact with various things in the building, such as elevators, lights, doors, etc.

<br><hr><br>

##Notes
- I would have used OpenGL, but JOGL is weird and since the rendering will be 2D and very simple, I'm just using `java.awt.Graphics2D` to render.
- Some objects (like doors) can be directly interacted with in order to use/activate them, but others will require special methods of activation (like light switches for lights or elevator buttons for elevators).
- I put in the Visible class just in case I need to have nonvisible objects in the future (as I feel like I will) but I currently have no use for nonvisible objects.

<br><hr><br>

##Brainstorming
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

##Visual Preview
> This is a generalization of what I plan on having it look like

<img src="https://docs.google.com/drawings/d/1zptTJuAx5-7astRzow8TUkD2LLrvAqHOmoRVIKBLf50/pub?w=827&amp;h=588">

> This is how it actually looks, right now.

![](http://i.imgur.com/Da2WD7l.png)

<br><hr><br>

##Class & Function Outline
######start `package base`
###`BuildingObject`
> Base functionality of all objects in the building.

###`Interactive`
> An interface to be implemented by all interactive objects.

- Includes an `interact(BuildingObject)` function to be overridden by classes implementing it.
- Overrides `BuildingObject`'s `interactive` property to `true`.

###`InteractListener`
> The interaction listener in case I made interaction listener-based.

- Likely won't be used in practice and I will implement interaction in a simpler fashion.

###`Visible`
> Interface to be extended by all visible objects.

- Includes `paint(Graphics2D)` function, which is called on all visible objects by the controller (the `Building`).

######end `package base`

######start `package main`
###`Building`
> Contains the main function and serves as the "controller" in MVC.

`// TODO documentation on Building`

###`Test`
> Class to test all of the program's classes, with functions inside for each class.

######end `package main`

<br>

######start `package boundaries`
###`Boundary`
> Base functionality for all impassable objects.

####`Floor`
> Object that `Person`s will be held above and which will constitute the makeup of the `Building`'s height.

####`Wall`
> Impassable object that exists on the outside of and within floors.

######end `package boundaries`

<br>

######start `package view`
###`View`
> JFrame window

#####`contentPane`
> Content pane area on which everything will be drawn.

#####`View()`
> Initializes a new `JPanel` window.

<br>

###`Surface`
> `JPanel` 2D rendering panel.

#####`paintComponent(Graphics g)`
> Performs all of the drawing.

- Creates a `Graphics2D` object to be used for rendering and passes it to various drawing functions which draw the specific object types.

######end `package view`

<br>

######start `package interactive`
###`Interactive`
> Includes standard properties of interactive objects.

#####`interactive` & `isInteractive`
> The interactivity of the current object

#####`inUse`
> The current state of the given `Interactive` object.

- Any interaction with an Interactive object will set this to true, and it will be reset to false when that interaction stops.

####`Light`
> Supplies light to a room.

- Activated only through a `LightSwitch`.
- Will probably have an `emittedLight` property that gives the corner coordinates of the area the light fills, or just a `lightStrength` property that indicates the maximum distance the light can travel.

`// TODO function layout on Light`

<br>

####`LightSwitch`
> Activates a light linked to it.

- `Person` will seek this out if there is no light.
- Is linked to a `LightSwitch` on instantiation and will send a signal to that `Light` on activation.

`// TODO function layout on LightSwitch`

<br>

####`Door`
> Entrance that can be opened or closed.

`// TODO documentation on Door`

<br>

####`Person`
> Virtual person that automatically pathfinds and interacts with objects that can be interacted with.

- Seek out a `LightSwitch` if a room is dark.
- Opens and closes `Door`s

`// TODO function layout on Person`
######end `package interactive`
<br>
######start `package constants`
###`Constants`
#####`FLOOR_HEIGHT`
#####`BUILDING_WIDTH`
#####Floors
- `GROUND_FLOOR`, `FIRST_FLOOR`, & `FLOOR_1` = 0
- `SECOND_FLOOR` & `FLOOR_2` = 1
- `THIRD_FLOOR` & `FLOOR_3` = 2
- `FOURTH_FLOOR` & `FLOOR_4` = 3

######end `package constants`


<br><hr><br>
##MVC Outline
###Model
- `Building`
- `BuildingObject`
	+ `Elevator`
	+ `Door`
	+ `Light`
	+ `LightSwitch`
	+ `Person`
		* `Me`
		* `Tai`

###View
- `View`
- `Surface`

###Controller
- `Building`
- `Main`

<br><hr><br>

##Plan
1. Complete model classes, keeping in mind the bigger picture and making sure it will be easy to make visuals with them later. ✓
	1. `Elevator` ✓
	2. `Door` ✓
	3. `Light` ✓ & `LightSwitch` ✓
	4. `Building` ✓
	5. `Person` ✓
		1. Some sort of interaction detection system ✓
		2. Random movement & path-finding system
2. Create view classes to visualize the models. ✓
	1. `View` ✓
	2. `Surface` ✓
3. Build controller to get everything going. ✓
4. Make everything look decent.

<br><hr><br>

##Controls
|     Key      |    Action    |
|--------------|--------------|
| <kbd>↑</kbd> |     Jump     |
| <kbd>←</kbd> |  Walk Left   |
| <kbd>→</kbd> |  Walk Right  |
| <kbd>space</kbd> | Interact |