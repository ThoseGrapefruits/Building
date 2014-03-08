#Building
> A virtual 2D building simulation and modelling program with random "people" who interact with various things in the building, such as elevators, lights, doors, etc.

<br><hr><br>

##Notes
- I would have used OpenGL, but JOGL is weird and since the rendering will be 2D and very simple, I'm just using `java.awt.Graphics2D` to render.
- Some objects (like doors and elevators) can be directly interacted with in order to use/activate them, but others will require special methods of activation (like light switches for lights).

<br><hr><br>

##Class & Function Outline
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
- `Elevator`
- `Door`
- `Light`
	- `LightSwitch`
- `Person`

###View
- `View`
- `Surface`

###Controller
- `Building`

<br><hr><br>

##Plan
1. Build `Test` class and continuously test the system throughout.
2. Complete model classes, keeping in mind the bigger picture and making sure it will be easy to make visuals with them later.
	1. `Elevator`
	2. `Door`
	3. `Light` & `LightSwitch`
	4. `Building`
	5. `Person`
		1. Some sort of interaction detection system
		2. Random movement & path-finding system
3. Create view classes to visualize the models.
	1. `View` ✓
	2. `Surface`
4. Build controller to get everything going.

<br><hr><br>

##Brainstorming
- How should I implement walls & other permanent impassable objects?
- Coordinate system? Definitely need this in one way or another. I guess I could just stick to pixels, as I would otherwise just have a scaled-up equivalent of a per-pixel coordinate system. I'm not sure how easy it will be to associate the pixel locations of objects to the objects themselves. _Actually nevermind, I can just instantiate all objects with coordinates._
	- I'm not going to even bother with scaling. Really isn't necessary for this project and it makes everything way more complicated.
- Separate class for interaction that is extended by all the interactive objects. Specifications like `interactive` for every object and `inUse` for `Interactive` objects to specify interactivity and whether they are busy at the moment.




