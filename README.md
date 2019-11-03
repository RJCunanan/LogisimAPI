# Team API: LogiSim Group Project

## Vision
To create a simple and easy-to-use Logic Simulator application that doesn't
compromise on usability nor on the number of features available to the user.

## Team Members
* Jaime Rivera
* RJ Cunanan
* Theodora Fernandez
* Yong Yang 

## New Features
* Additional Logic Gates
* Redo and Undo Buttons
* Pinch to zoom in and out of grid


# LogiSim Design Document

## Introduction:
LogiSim is an Android logic simulator application created for Computer Science students moving from Discrete Mathematics (CSC 28) to Computer Organization (CSC 137) that allows users to create and test simple logic circuits in real time in order to validate their knowledge of logic expressions (Posnett). From a single screen interface, users are presented with a taskbar from which they can select a variety of circuit components, including toggle switches, wires, LEDs, AND gates, OR gates, and NOT gates, and place them on a grid to construct their circuits. Several function buttons are also available, such as move, delete, clear, toggle, run, and save that perform specific functions on existing circuit components. After a circuit is built, the user can then proceed to test the circuit by toggling the switches on to see of the circuit performs as the user anticipated.

## User Interface:
When the user first opens the application, a single screen interface is loaded up and displayed to the user that is composed to two main sections:

### Grid:
The first section is a grid which covers most of the screen and is used for the placement of circuit components. The grid is drawn cell by cell with vertical and horizontal lines being drawn for the top and left side of each cell so that when each cell is placed adjacent each other, a grid is formed. The code and implementation for creating this grid was influenced by the SubHunter code provided to us in class (Posnett). This design change was based on the consideration that each individual cell will be changed depending on the user’s actions, and so it would be much easier to draw a grid of empty cells so that each individual cell could be referenced later on in the program so as to add a circuit component or process some function to it. It is on each individual cell of the grid that the user can place a single circuit component by tapping the appropriate component’s button and then tapping on the square where the user wishes to place that component. As the user lifts his or her finger after tapping, the circuit component will be displayed within the selected grid cell.

### Taskbar:
The second section of the user interface that is loaded and displayed to the user is a taskbar that is located at the bottom of the screen and loads into the place of the las row of the grid. Within this taskbar are 15 different circuit components and function buttons that the user can select simply by tapping the button on the screen. More information is discussed in the sections below. What are included in the taskbar are switches, AND gates, OR gates, NOT gates, LEDs, wires, a move button, a delete button, a clear button, a toggle button, a run button, and a save button along with three additional buttons next to it labeled A, B, and C that store the saved circuits. When the user lifts his or her finger after selecting and tapping on a button, the icon for the selected component or function should be highlighted. This indicates that the selected component is ready to be placed onto the grid or the selected function is read to perform its task. After the user has chosen a component or function, either task is done simply by tapping on a square in the grid, and, using the coordinates of that tap, either the placement of a component or execution of a task will be done. However, this is not always the case. If there is already a circuit component within the grid box that the user wishes to place a new component, then when the user taps on this box, the new component will not be placed. Each new circuit component must be placed only within an empty grid box. This is to prevent accidental placement of circuit components that could drastically change the output of the circuit. Likewise, if the user has selected a function to be performed, such as delete or toggle, and then taps on an empty box on the grid, nothing will happen. Functions can only be performed on squares already containing circuit components. Should the user wish to no longer place the selected component or perform the selected action, then he or she just needs to tap on a different button to deselect the current action to be performed. By doing so, the previously selected icon will no longer be highlighted, and the newly selected icon will. Let us now look at each individual icon on the taskbar and their specific functions.

### Toggle Switches:
The very first item found on the taskbar is the toggle switch. Users can place as many switches as they like by simply tapping on the toggle switch icon and then tapping on an empty box on the grid. These switches are used to power the circuit by connecting a wire to a switch. When a switch is placed onto the grid, a toggle switch image is drawn in that grid cell to visually represent a switch to the user. Take note that a single switch can send power to multiple circuit components via several wires. As long as a wire is connected to a switch and another circuit component, the switch will send an output to that component. Also, because switches only send power, they do not take in any input from the circuit but only produce an output. When a switch is initially placed onto the grid, its initial “state” is off. In order to turn the switch on to produce power for the circuit, the user must first select the toggle button icon in the taskbar and then tap on the desired switch which will also change the image to a toggle switch that is turned on. Otherwise, simply tapping on the toggle switch will not produce power. More information on the toggle button will be discussed later. When these conditions are met, the switch will begin sending power to the circuit via a change in its “state” which is indicated using a boolean variable that keeps track of whether the switch is turned on or not. The switch’s state (on or off), will then have an effect on the states of the rest of the circuit components attached to it.

### AND Gate:
The AND gate is one of three logic gates the user can use to control the flow of power in a circuit. Logic gates are circuit components that perform basic logical functions by taking in an input, often a value of 1 (True) or 0 (False) and using that input to produce an output (“Logic Gate”). The AND gate icon can be selected from the taskbar and then placed onto a single box on the grid. AND gates take in input in the form of power from two separate sources and only two sources via two separate wires that connect to it. These two source components are saved during the wiring process. However, this gate will only produce an output only if it receives power from both wires connected to it. Otherwise, no power will be let through the gate. In order to accomplish this, the AND gate keeps track of its own state. The conditions to change its state is to ensure that the AND gate is receiving and input from the two wires it is connected to. When these conditions are met, the AND gate changes its state to indicate that an output is being sent out of this gate. When placed on the grid, an AND gate is visually represented by an AND gate symbol.

### OR Gate:
An OR gate works very similar to an AND gate in that it can receive power from two separate sources via the wires that connect to it and that these sources are saved during the wiring process. The difference is that an OR gate will only allow power through if it receives power from only one source and not the other. If both wires connected to an OR gate are sending power through, then the OR gate will not produce an output. Thus, the state of an OR gate will only be changed if it receives an input from one wire but not the other. When this is met, this component’s state will be changed to indicate to other components that it is sending an output. When placed on the grid, an OR gate is represented by an OR gate symbol.

### NOT Gate:
A NOT gate works differently from the previous two gates. Unlike an AND gate or an OR gate that can receive power from two separate sources, a NOT gate only receives one input source, which is also saved during the wiring process. Furthermore, the NOT gate takes that input and inverts the output such that if power were flowing into a NOT gate, no power would be flowing out. Therefore, regarding its state, when a NOT gate is receiving input, the state of this gate is “false” to indicate that it has inversed the input and so is not sending an output. Likewise, if there is no input being sent into the NOT gate, the state of this gate is “true” to indicate that it has inversed the input and is now sending an output. When placed on the grid, a NOT gate is represented by a NOT gate symbol.

### LEDs:
LEDs are the circuit components that indicate whether or not the circuit is working as expected by lighting up when powered. The button itself is represented by the text “LED” instead of an actual LED icon. When placed into the grid and connected to a circuit, a single LED receives input from only 1 power source. However, an LED does not send an output. Instead, the LED lights up to indicate that it has successfully received input/power. In the case of this program, the LED visually represented by the image of a light bulb. When connected with a wire and receiving input, the LED’s grid cell will be replaced with the image of a yellow light bulb to indicate the LED is on and is in fact receiving input from the circuit.

### Wires:
The next component on the taskbar is the wire. Originally, the plan was to have the user manually place wires on the grid. However, because this could introduce potential problems in regard to the proper connection of components and their orientation in relation to the wire, the decision was made to simply auto-connect the wire by selecting the wire icon and tapping on two circuit components to connect them. This would be done by keeping track of whether or not the user has tapped on two circuit components and their grid locations. As such, the wire button is more performing a task rather than placing a component, and so it has been grouped together with the other function buttons on the taskbar. When it is true that the user has tapped on two circuit components with the wire button selected, the program proceeds to take the two coordinates and connect them with a straight blue line to represent a wire. Because wires are essential in circulating power, each wire component receives an input and produces an output. As the user taps two components, the program remembers that the first component the user taps is the source of input for the second component. Thus, in order to ensure that a circuit is wired correctly, the user must ensure that the first component that is tapped with the wire button selected must be the source of input and the second component tapped is the destination or the one receiving the input from the first component.

### Move Button:
The move button is a feature that allows the user to move any single circuit component on the grid from one location to another. When this button is selected, the user first taps on the existing component that he or she wishes to be moved. The user then taps on a different location on the grid where he or she wants the circuit component to be moved to. As soon as the user lifts his or her finger, the selected component is removed from its original position on the grid and placed in its new position.

### Delete Button:
The delete button allows for the user to delete individual circuit components on the grid. With this button selected, users tap on an already existing circuit component on the grid and that component will be removed from the circuit. Note that if the component to be deleted is connected to other components via wires, then all the wires on the circuit will also be deleted to prevent unconnected wires from remaining after the deletion is completed.

### Clear Button:
Next is the Clear button which is similar to the delete button previously discussed above. When the user taps on this button to select it, the entire grid is then cleared of all circuit components and the user is presented with an empty grid. This is not to be confused with the delete button’s function which only deletes a single circuit component at a time. The way in which the clear button accomplishes its task of clearing the grid is that, starting from the top left corner of the grid and moving across and down, each cell in the grid is replaced with a clean empty cell that does not contain any sort of circuit component. By doing so, any already existing circuits on the grid are replaced and the user is left with a clean grid to create new circuits.

### Toggle and Run Buttons:
These next two buttons, the toggle and run buttons, are located next to each other on the taskbar and perform tasks that are vital to each other that we will discuss the functions of both in this section. When the toggle button is selected from the taskbar, the user can now tap any existing toggle switch on the grid to either turn them on and send power throughout the circuit or turn them off. If the user taps on any circuit component other than a toggle switch, then no action takes place. When the user is finished toggling on any desired switches, he or she then proceed to select the run button. As soon as this button is selected, all existing LEDs on the grid run an evaluation in order to determine if they are receiving an input from the circuit they are connected to. If this is true, then the connected LED will light up to visually confirm to the user that the LED is receiving an input. Any LEDs that do not receive an input will remain off with the run button is selected.

### Save Buttons:
A recent update to this application is the addition of the Save function which introduces four new taskbar buttons. What these buttons do is allow the user to save the current circuit on the grid into either button A, B, or C so that at any time the user can load that circuit back onto the grid. In order to save a circuit, the user first selects the Save button to indicate to the application that the current circuit is to be saved. With the Save button selected, the user then proceeds to tap on either button A, B, or C to assign and save that circuit into that button. When, at any time, the user decides to reload that saved circuit, he or she simply needs to tap on the button where the saved circuit is located with no other button selected, and the circuit will be loaded back onto the grid. The user may also overwrite any saved circuit by taping the save button again and then tapping on the button that already has a circuit saved in it.

## Software Procedure:
The following is a step by step set of instructions for how the user can interact with the LogiSim application to build and test various simple circuits. Note that circuit components do not have to be placed on the grid in this specific order.

1. The user taps on the application’s icon. This opens the app and loads both the grid and the taskbar onto the screen for the user to interact with.

2. Then, the user taps on the toggle switch button in the taskbar to select it. When releasing their finger after the tap, the toggle switch icon will be highlighted to visually confirm the selection. The user can then proceed to tap on an empty grid square to place a toggle switch on that square.

3. Next, the user taps on the LED button. Just like the toggle switch button, the LED button will be highlighted to display confirmation of the selection and the user can place lamps onto any empty grid square simply by tapping that square.

4. The user can now select from among the 3 different gates available (AND, OR, or NOT gate).  Once again, the user can place any gate on any open space on the grid.

5. With the toggle switches, lamps, and desired gates placed on the grid, the user can now start placing wires by selecting the wire icon. After the selection, users can start connecting wires between circuit components to connect all the components into a complete and working circuit.

Note: Throughout this circuit-building process, any of the function buttons could be used to edit the circuit as it is being built onto the grid.

6. When the circuit is complete, the user can now tap the toggle and run buttons to begin testing to see if the circuit behaves as expected. With the toggle button selected and highlighted, each individual toggle switch on the grid can now be tapped to turn them on and to simulate sending power throughout the circuit. The user then proceeds to tap on the run button, and any LEDs that receive power during this phase will light up to display to the user that they have received input from the circuit.


## Works Cited:
“Logic Gate.” Logic Gate Definition, https://techterms.com/definition/logicgate. Accessed 12 Oct. 2019.

Posnett, Daryl. “LogiSim Problem Statement.” 2019. PDF file.

Posnett, Daryl. “SubHunterRefactoredCSC131FA19.” 2019. PDF file.

