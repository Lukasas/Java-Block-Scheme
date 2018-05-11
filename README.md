# Java Block Scheme
Application written in Java that allows easily create block schemes for calculations.

## Compiling
In order to compile the project you need to have:
- Java SDK 1.8.0 (161)
- Java JRE 1.8.0 (161)
- Ant 1.10.3

Compiling is done with one single command:
```shell
ant compile
```
Compiled program will be in folder *dest*. Application can be run by executing following command:
```shell
ant run
```
This command is designed to depend on *compile* command so it always starts the newest compiled source.

## Usage:
Everything is very simple to use. On the top panel, there are all blocks, that can be created. When a block is created, it appears on the light blue area called scene.


![Created Block](../assets/screenshots/00_creating_block.png)

Every block can be moved around the scene by holding down left click button on the block and moving the mouse.

![Moving Block Aroung](../assets/screenshots/01_moving_block.png)

Creating connection between blocks can be done by right clicking on a block that will be the start of the connection. This will show all output ports, that block have.

![Block outputs](../assets/screenshots/02_connecting_output_port.png)

When output port is selected, the next step is to select an input port in another block. This is also done by right clicking on block that will be the end of the connection. This will bring up all it's input ports.

![Block Inputs](../assets/screenshots/03_connecting_input_port.png)

After selecting the input port, the red line is drawn representing the connection.

Every block has it's own input ports and output ports. Values of these ports can be seen by simply pointing mouse cursor on the block. Then a small window will popup with these informations.

![Block Tooltip](../assets/screenshots/04_block_tooltip.png)

When everything is set to go, calculation can be started. It's done by clicking Start button on left bar.

![Start Calculation](../assets/screenshots/05_start_calc.png)

Before the calculation can start, every block must have it's input ports set or connected. If there are any blocks that has it's ports not connected, the small window will popup asking for a value for the port.

![Port Pin Value Input](../assets/screenshots/06_setting_block_values.png)

After setting all values for not connected ports the calculation can start by clicking Step button on left bar.

![Step Button Click](../assets/screenshots/07_all_set.png)

This will execute computing in every starting block, highlighting them green which means that those blocks were calculated.

![First Step Calculated](../assets/screenshots/08_step.png)

Values that were set and calculated result can be seen in tooltip with pointing on an block.

![Tooltip Calculated](../assets/screenshots/09_calculated_block_tooltip.png)

Every block, that has been currently calculated are highlighted green, block that has been already calculated is painted with light blue.

![Calculated and Currently Calculated](../assets/screenshots/10_calculating_next_step.png)

There can be done almost every calculations with these blocks and adding new blocks are really simple.
There is an example of blocks that do Runge Kutta 4 (RK4).

![RK4](../assets/screenshots/11_rk_scheme.png)

## Screenshots
![Application](../assets/screenshots/screenshot_1.png)
