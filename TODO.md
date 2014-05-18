#TODO
1. <del>Finish all basic paint functions</del>
2. <del>Put building as the first initializer parameter for BuildingObject and update all subclasses accordingly</del>
3. <del>Finish `Person` `say()` function</del>
	+ <del>Tie into drawing (draw a speech bubble for a certain amount of time<del>, might have to do on a thread</del></del>)
4. <del>Finish `Person` `interact()` function (requires finished `say()` function)</del>
5. Finish person AI 
	- Add interaction when interactive object is present
	- <del>Say hi to other passing people</del>
6. Make button pressing for interaction require holding, or at least interact once per press somehow.
	+ Call function on key down instead of doing it every tick when the key is pressed.
7. Make elevators functional
	+ Elevator movement when called
	+ Put characters inside elevator and turn off drawing for those characters until they exit
8. (optional) Implement sounds for the various objects
	+ Elevator ding
	+ Door open/shut
	+ Jumping
9. Background drawing
	+ Ground
	+ Seasons / transitions