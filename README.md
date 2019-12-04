# Team API: LogiSim Group Project

## About Our Team and Our Vision:

### Team Members
* Jaime Rivera
* RJ Cunanan
* Theodora Fernandez
* Yong Yang

### Vision
**To create a clean, simple, and easy-to-use Logic Simulator application that doesn't compromise on usability nor on the number of features available to the user.** What our team desires is to create an application that has a clean and simple User Interface that includes a grid and a single taskbar with button icons that doesn't leave the user guessing as to what each button does. The main goal for us when it comes to the UI is simplicity. The user should be able to pick up his or her phone, open the LogiSim application, and just go on building and testing circuits. However, simplicity doesn't mean taking away from the User Experience. When users get to building circuits using our app, they should be given all the tools and functionality necessary to accomplish that goal. The application should also be as bug free as possible so that the user can create, test, and run circuits without fail. Thus, our end goals for this group project is to have a Logic Simulator app with a clean and simple design but with a dynamic taskbar, added functionality such as the ability to redo and undo past actions, and additional logic gates for more diversity in circuit building while also ensuring that the app works as the user expects it should.

### New Features
* Additional Logic Gates (NAND, NOR, XOR)
* Redo and Undo functionality to allow the user to go back and forth between current and previously created circuits
* Dynamic taskbar that switches between different menus and allows for the addition of new buttons without running out of space
* Sound feedback for user taps and background music during application startup
* Realtime evaluation of circuits
* Color-coded feedback for user to visually see the inputs and outputs from one point of a circuit to another


# LogiSim Design Document

## Introduction:
LogiSim is an Android logic simulator application created for Computer Science students moving from Discrete Mathematics (CSC 28) to Computer Organization (CSC 137) that allows users to create and test simple logic circuits in real time in order to validate their knowledge of logic expressions (Posnett). From a single screen interface, users are presented with a taskbar from which they can select a variety of circuit components, including toggle switches, wires, lamps, AND gates, OR gates, NOT gates, NAND gates, NOR gates, and XOR gates, and place them on a grid to construct their circuits. Several function buttons are also available, such as move, delete, run, and save that perform specific functions on existing circuit components. After a circuit is built, the user can then proceed to test the circuit by toggling the switches on and then selecting the run button to see of the circuit performs as the user anticipated.

## User Interface:
When the user first opens the application, a single screen interface is loaded up and displayed to the user. Background music has been added during the startup of the application, and will continue to loop through as long as the screen is open. Sound feedback has also been configured for every tap that the user makes. The single screen interface that is loaded up is composed of two main sections:

### Grid:
The first section is a grid which covers most of the screen and is used for the placement of circuit components. The grid is drawn cell by cell with vertical and horizontal lines being drawn for the top and left side of each cell so that when each cell is placed adjacent each other, a grid is formed. The application continuously draws grid cells all the way to the bottom of the user's screen so that no matter the resolution, there will not be any whitespace appearing at the bottom of the grid, as was the case in our original design. The code and implementation for creating this grid was influenced by the SubHunter code provided to us in class (Posnett). This design change was based on the consideration that each individual cell will be changed depending on the user’s actions, and so it would be much easier to draw a grid of empty cells so that each individual cell could be referenced later on in the program so as to add a circuit component or process some function to it. It is on each individual cell of the grid that the user can place a single circuit component by tapping the appropriate component’s button and then tapping on the square where the user wishes to place that component. As the user lifts his or her finger after tapping, the circuit component will be displayed within the selected grid cell.

### Taskbar:
The second section of the user interface that is loaded and displayed to the user is a taskbar that is located at the left of the screen and loads into the place of the first column of the grid. Within this taskbar are several different circuit components and function buttons that the user can select simply by tapping the button on the screen. More information is discussed in the sections below. What are included in the taskbar are switches, AND gates, OR gates, NOT gates, NAND gates, NOR gates, XOR gates, lamps, wires, a move button, a delete button, a redo button, an undo, a save button, and a load button, all of which are divided into different menus that the user can switch between.

When the application first opens up, the user is presented with the "main menu" that holds the build, move, delete, undo, redo, save, and load buttons. When the user decides to start building circuits, he or she taps on the build button, which then switches to the build menu. This new menu now contains new buttons, namely the switch, lamp, gates, and link button, all of which are related to building circuits. Should the user tap on the gates button, the application will then switch to the gates menu that contains different buttons for all of the logic gates. Back at the main menu, there are also save and load buttons, each of which has their own menu of save slots. While these two menus may look identical, they function differently. The save menu allows the user to save the current circuit on the grid by tapping on any of several save slot buttons. The load menu, on the other hand, allows the user to bring back a circuit that has been previously saved by tapping on its corresponding save slot button. If a save slot has nothing saved in it, then tapping that button simply replaces the current grid with empty grid cells.

As the user lifts his or her finger after selecting and tapping on a button, the icon for the selected component or function should be highlighted. This indicates that the selected component is ready to be placed onto the grid or the selected function is read to perform its task. After the user has chosen a component or function, either task is done simply by tapping on a square in the grid, and, using the coordinates of that tap, either the placement of a component or execution of a task will be done. However, this is not always the case. If there is already a circuit component within the grid box that the user wishes to place a new component, then when the user taps on this box, the new component will replace the old one. Furthermore, if the user has selected a function to be performed, such as delete or move, and then taps on an empty box on the grid, nothing will happen. Functions can only be performed on squares already containing circuit components. Should the user wish to no longer place the selected component or perform the selected action, then he or she just needs to tap on a different button to deselect the current action to be performed. By doing so, the previously selected icon will no longer be highlighted, and the newly selected icon will. Let us now look at each individual button on the taskbar and their specific functions.

### Toggle Switches:
One of the items found on the taskbar that is used to build circuits is the toggle switch. Users can place as many switches as they like by simply tapping on the toggle switch icon and then tapping on an empty box on the grid. These switches are used to power the circuit by connecting a wire to a switch. When a switch is placed onto the grid, a toggle switch image is drawn in that grid cell to visually represent a switch to the user. Take note that a single switch can send power to multiple circuit components via several wires. As long as a wire is connected to a switch and another circuit component, the switch will send an output to that component. Also, because switches only send power, they do not take in any input from the circuit but only produce an output. When a switch is initially placed onto the grid, its initial “state” is off. In order to turn the switch on to produce power for the circuit, the user simple needs to tap on a switch while no button on the taskbar is selected, and the image of the switch will be replaced with the image of an on switch. When these conditions are met, the switch will begin sending power to the circuit via a change in its “state” which is indicated using a boolean variable that keeps track of whether the switch is turned on or not. The switch’s state (on or off), will then have an effect on the states of the rest of the circuit components attached to it.

### AND Gate:
The AND gate is one of three logic gates the user can use to control the flow of power in a circuit. Logic gates are circuit components that perform basic logical functions by taking in an input, often a value of 1 (True) or 0 (False) and using that input to produce an output (“Logic Gate”). The AND gate icon can be selected from the taskbar and then placed onto a single box on the grid. AND gates take in input in the form of power from two separate sources and only two sources via two separate wires that connect to it. These two source components are saved during the wiring process. However, this gate will only produce an output only if it receives power from both wires connected to it. Otherwise, no power will be let through the gate. In order to accomplish this, the AND gate keeps track of its own state. The conditions to change its state is to ensure that the AND gate is receiving an input from the two wires it is connected to. When these conditions are met, the AND gate changes its state to indicate that an output is being sent out of this gate. When placed on the grid, an AND gate is visually represented by an AND gate symbol.

### NAND Gate:
Similar and yet different from the AND gate is the NAND gate (NOT-AND gate). Like the AND gate, the NAND gate takes in an input from two separate sources and sends only a single output. However, unlike the AND gate, the NAND gate produces an inverse output. What this means is that when the NAND gate receives an input from both connected sources, it does not send out an output. Otherwise, the NAND gate produces an output. When placed on the grid, a NAND gate symbol is used to represent this logic gate.

### OR Gate:
An OR gate works very similar to an AND gate in that it can receive power from two separate sources via the wires that connect to it and that these sources are saved during the wiring process. The difference is that an OR gate will only allow power through if it receives power from only one source and not the other. If both wires connected to an OR gate are sending power through, then the OR gate will not produce an output. Thus, the state of an OR gate will only be changed if it receives an input from one wire but not the other. When this is met, this component’s state will be changed to indicate to other components that it is sending an output. When placed on the grid, an OR gate is represented by an OR gate symbol.

### NOR Gate:
Like the relationship between an AND gate and a NAND gate, the NOR gate works similarly and yet differently from the OR gate. Like the OR gate, the NOR gate also receives input from two separate sources and produces a single output. However, the only time a NOR gate will produce this out is when both sources of input are off or are not sending any input to the gate. Otherwise, when an input is received from either source or both, the NOR gate will not produce an output. When placed on the grid, a NOR gate symbol is used to represent this logic gate.

### XOR Gate:
Unlike the NOR gate, the XOR functions almost exactly like a normal OR gate. The only difference between the two is in the case when a XOR gate receives inputs from both connected sources, it does not produce an output. When placed on the grid, a XOR gate symbol is used to represent this logic gate.

### NOT Gate:
A NOT gate works differently from the previous two gates. Unlike an AND gate or an OR gate that can receive power from two separate sources, a NOT gate only receives one input source, which is also saved during the wiring process. Furthermore, the NOT gate takes that input and inverts the output such that if power were flowing into a NOT gate, no power would be flowing out. Therefore, regarding its state, when a NOT gate is receiving input, the state of this gate is “false” to indicate that it has inversed the input and so is not sending an output. Likewise, if there is no input being sent into the NOT gate, the state of this gate is “true” to indicate that it has inversed the input and is now sending an output. When placed on the grid, a NOT gate is represented by a NOT gate symbol.

### Lamps:
Lamps are the circuit components that indicate whether or not the circuit is working as expected by lighting up when powered. The button itself is represented by the text “Lamp” instead of an actual lamp icon. When placed into the grid and connected to a circuit, a single lamp receives input from only 1 power source. However, a lamp does not send an output. Instead, the lamp lights up when user taps on a switch and the output produced finds its way to the lamp to indicate that it has successfully received input/power. In the case of this program, the lamp is visually represented by the image of a light bulb. When connected with a wire and receiving input, the LED’s grid cell will be replaced with the image of a yellow light bulb to indicate the LED is on and is in fact receiving input from the circuit.

### Wires:
The next component on the taskbar is the wire that is created using the "Link" button. Originally, the plan was to have the user manually place wires on the grid. However, because this could introduce potential problems in regard to the proper connection of components and their orientation in relation to the wire, the decision was made to simply auto-connect the wire by selecting the Link button and tapping on two circuit components to connect them. This would be done by keeping track of whether or not the user has tapped on two circuit components and their grid locations. As such, the wire button is, in a way, both performing a function and placing a circuit component. When it is true that the user has tapped on two circuit components with the Link button selected, the program proceeds to take the two coordinates and connect them with a blue line to represent a wire. However, unlike in our original design where the wire is just a single straight line, now the application uses taxicab wiring to create three separate but connected lines to represent a wire. This new design offers a cleaner interface to the user so that wires won't get in the way of other components when circuits are being built on the grid. Because wires are essential in circulating power, each wire component receives an input and produces an output. As the user taps two components, the program remembers that the first component the user taps is the source of input for the second component. Thus, in order to ensure that a circuit is wired correctly, the user must ensure that the first component that is tapped with the wire button selected must be the source of input and the second component tapped is the destination or the one receiving the input from the first component. In relation to wire input and output, a recent update to our app now allows us to implement color differentiation. When a wire is sending an output to a circuit component, the app will color that wire yellow to display to the user the path of any output.

### Move Button:
The move button is a feature that allows the user to move any single circuit component on the grid from one location to another. When this button is selected, the user first taps on the existing component that he or she wishes to be moved. The user then taps on a different location on the grid where he or she wants the circuit component to be moved to. As soon as the user lifts his or her finger, the selected component is removed from its original position on the grid and placed in its new position. If the component to be moved is currently connected to other components via wires, then when the move function is performed the component will be moved while still remaining connected.

### Delete Button:
The delete button allows for the user to delete individual circuit components on the grid. With this button selected, users tap on an already existing circuit component on the grid and that component will be removed from the circuit. Note that if the component to be deleted is connected to other components via wires, then all the wires on the circuit will also be deleted to prevent unconnected wires from remaining after the deletion is completed.

### Clear Button (under redevelopment):
Next is the Clear button which was an original feature of the application but, because of the recent changes and restructuring made to the programming, ended up being removed due to incompatibility and is currently under redevelopment. The clear button is similar to the delete button previously discussed above. When the user taps on this button to select it, the entire grid is then cleared of all circuit components and the user is presented with an empty grid. This is not to be confused with the delete button’s function which only deletes a single circuit component at a time. The way in which the clear button accomplishes its task of clearing the grid is that, starting from the top left corner of the grid and moving across and down, each cell in the grid is replaced with a clean empty cell that does not contain any sort of circuit component. By doing so, any already existing circuits on the grid are replaced and the user is left with a clean grid to create new circuits.

### Undo Button (Under Development):




### Redo Button (Under Development):




### Save Button:
A recent update to this application is the implementation of a Save menu rather that a single save button with three save spots next to it. When the user presses the save button in the main menu, the taskbar switches over to the save menu which contains several save slot buttons labeled from slots A to F. What these buttons do is allow the user to save the current circuit on the grid into either of the slot buttons so that at any time the user can load that circuit back onto the grid. In order to save a circuit, the user simply needs to just tap on one of the slot buttons to select it, and that button will be highlighted not only to indicate it has been selected, but also to show that the circuit has been saved into that slot. The user may also overwrite any saved circuit by taping the same button again.

### Load Button:
In addition to the save button is the load button that switches the taskbar into a load menu. Although the load menu might look exactly the same as the save menu, its function is to load back a previously saved circuit back onto the grid when the user taps on its corresponding save slot button. The save slot will then be highlighted after the user's tap to indicate selection and the successful loading of a circuit onto the grid.


## Software Procedure:
The following is a step by step set of instructions for how the user can interact with the LogiSim application to build and test various simple circuits. Note that circuit components do not have to be placed on the grid in this specific order.

1. The user taps on the application’s icon. This opens the application and loads both the grid and the taskbar onto the screen for the user to interact with.

2. Then, the user taps on the build button to enter into the build menu with all the buttons associated with creating circuits. The user then proceeds to tap on the toggle switch button in the taskbar to select it. When releasing their finger after the tap, the toggle switch icon will be highlighted to visually confirm the selection. The user can then tap on an empty grid square to place a toggle switch onto that square.

3. Next, the user taps on the Lamp button. Just like the toggle switch button, the Lamp button will be highlighted to display confirmation of the selection and the user can place lamps onto any empty grid square simply by tapping that square.

4. The user can now select from among several different logic gates available by tapping and selecting the gates button in the build menu. The build menu now switches to the gates menu with all the buttons that are used to place logic gates onto the grid. Once again, the user can place any gate on any open space on the grid by taping on a button and then tapping on a grid square.

5. With the toggle switches, lamps, and desired gates placed on the grid, the user can now start placing wires by pressing the back button on the gates menu to go back to the build menu and selecting the Link button. After the selection, users can start connecting wires between circuit components to connect all the components into a complete and working circuit.

Note: Throughout this circuit-building process, any of the function buttons, such as move or delete, could be used to edit the circuit as it is being built onto the grid.

6. When the circuit is complete, the user can now tap on any switches to toggle them on or off to begin testing and evaluating the circuit in real-time to see if the circuit behaves as expected. With no button on the taskbar currently selected and highlighted, each individual toggle switch on the grid can now be tapped to turn them on and to simulate sending power throughout the circuit. Any lamps as well as any wires that receive power during this phase will light up to display to the user that they have received input from the circuit.


## Works Cited:
“Logic Gate.” Logic Gate Definition, https://techterms.com/definition/logicgate. Accessed 12 Oct. 2019.

Posnett, Daryl. “LogiSim Problem Statement.” 2019. PDF file.

Posnett, Daryl. “SubHunterRefactoredCSC131FA19.” 2019. PDF file.
