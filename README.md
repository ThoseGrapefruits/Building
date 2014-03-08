Building
--------
A virtual 2D building simulation and modelling program with random "people" who interact with various things in the building, such as elevators, lights, doors, etc.

#Notes
- I would have used OpenGL, but JOGL is weird and since the rendering will be 2D and very simple, I'm just using `java.awt.Graphics2D` to render.
- Some objects (like doors and elevators) can be directly interacted with in order to use/activate them, but others will require special methods of activation (like light switches for lights).

#Class & Function Outline
##`Building`
> Contains the main function and serves as the "controller" in MVC.

##`View`
> JFrame

####

<br>
##`Surface`
> JPanel 2D rendering panel



<br>
##`Light`



<br>
##`LightSwitch`


<br><hr><br>
#MVC Outline
- `Building` serves as both a model and a controller.

##Model
##View
##Controller
####`Building`