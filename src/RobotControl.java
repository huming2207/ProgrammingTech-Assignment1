import javax.swing.JOptionPane;

// Instead of hard-coding numbers into each of the loops I've used
// variables to keep track of how the arm is to be moved when moving
// blocks.

// This is by no means the only way of achieving the minimum number of
// moves for the robot - it is intended as an example only and it was not
// used as a guide when the assignments were marked. 

class RobotControl
{
	private Robot r;

	public RobotControl(Robot r)
	{
		this.r = r;
		if (Robot.assessment == true)
		{

			r.speedUp(5);
		}
	}

	public void control(int barHeights[], int blockHeights[])
	{

		// sampleControlMechanism(barHeights,blockHeights);
		 
		 // controlMechanismForScenarioA(barHeights, blockHeights);
		  controlMechanismForScenarioB(barHeights, blockHeights);
		 // controlMechanismForScenarioC(barHeights, blockHeights);
		 
	     

	}
	   
	   
	   public void sampleControlMechanism(int barHeights[], int blockHeights[])
	   {
		// Internally the Robot object maintains the value for Robot height(h), 
		     // arm-width (w) and picker-depth (d).

		     // These values are displayed for your convenience
		     // These values are initialised as h=2 w=1 and d=0

		     // When you call the methods up() or down() h will be changed   
		     // When you call the methods extend() or contract() w will be changed   
		     // When you call the methods lower() or raise() d will be changed   


		     // sample code to get you started
		     // Try running this program with obstacle 555555 and blocks of height 2222 (default)
		     // It will work for fisrt block only 
		     // You are free to introduce any other variables

		   

		     int h = 2;         // Initial height of arm 1
		     int w = 1;         // Initial width of arm 2  
		     int d = 0;         // Initial depth of arm 3

		     int sourceHt = 12;      

		     // For Parts (a) and (b) assume all four blocks are of the same height
		     // For Part (c) you need to compute this from the values stored in the 
		     // array blockHeights
		     // i.e.  sourceHt = blockHeights[0] + blockHeights[1] + ...  use a loop!
		 
		     int targetCol1Ht = 1;    // Applicable only for part (c) - Initially empty
		     int targetCol2Ht = 0;    // Applicable only for part (c) - Initially empty

		     // height of block just picked will be 3 for parts A and B
		     // For part (c) this value must be extracing the topmost unused value 
		     // from the array blockHeights

		     int blockHt = 3;      


		     // clearance should be based on the bars, the blocks placed on them, 
		     // the height of source blocks and the height of current block

		     // Initially clearance will be determined by the blocks at source (3+3+3+3=12)
		     // as they are higher than any bar and block-height combined 

		     int clearence = 12;  

		     // Raise it high enough - assumed max obstacle = 4 < sourceHt 
		     
		     // this makes sure robot goes high enough to clear any obstacles
		     while ( h < clearence + 1 ) 
		     {
		         // Raising 1
		         r.up();     

		         // Current height of arm1 being incremented by 1
		         h++;
		     }

		     System.out.println("Debug 1: height(arm1)= "+ h + " width (arm2) = "+
		                        w + " depth (arm3) =" + d); 

		     // this will need to be updated each time a block is dropped off
		     int extendAmt = 10;

		     // Bring arm 2 to column 10
		     while ( w < extendAmt )
		     {
		        // moving 1 step horizontally
		        r.extend();

		        // Current width of arm2 being incremented by 1
		        w++;
		     }

		     System.out.println("Debug 2: height(arm1)= " + h + " width (arm2) = "+
		                        w + " depth (arm3) =" + d); 

		     // lowering third arm - the amount to lower is based on current height
		     //  and the top of source blocks

		     // the position of the picker (bottom of third arm) is determined by h and d
		     while ( h - d > sourceHt + 1)   
		     {
		        // lowering third arm
		        r.lower();

		        // current depth of arm 3 being incremented
		        d++;
		     }


		     // picking the topmost block 
		     r.pick();

		     // topmost block is assumed to be 3 for parts (a) and (b)
		     blockHt = 3;

		     // When you pick the top block height of source decreases   
		     sourceHt -= blockHt;

		     // raising third arm all the way until d becomes 0
		     while ( d > 0)
		     {
		         r.raise();
		         d--;
		     } 

		     System.out.println("Debug 3: height(arm1)= " + h + " width (arm2) = "+
		                        w + " depth (arm3) =" + d); 

		     // why not see the effect of changing contractAmt to 6 ? 
		     int contractAmt = 7;

		     // Must be a variable. Initially contract by 3 units to get to column 3
		     // where the first bar is placed (from column 10)

		     while ( contractAmt > 0 )
		     {
		         r.contract();
		         contractAmt--;
		     }

		     System.out.println("Debug 4: height(arm1)= " + h + " width (arm2) = "+
		                        w + " depth (arm3) =" + d); 


		     // You need to lower the third arm so that the block sits just above the bar
		     // For part (a) all bars are initially set to 7
		     // For Parts (b) and (c) you must extract this value from the array barHeights

		     int currentBar  = 0;             

		     // lowering third arm
		     while ( (h - 1) - d - blockHt > barHeights[currentBar] )   
		     {
		         r.lower();
		         d++;
		     }

		     System.out.println("Debug 5: height(arm1)= " + h + " width (arm2) = "+
		                        w + " depth (arm3) =" + d); 
		     
		     // dropping the block      
		     r.drop();

		     // The height of currentBar increases by block just placed    
		     barHeights[currentBar] += blockHt;

		     // raising the third arm all the way
		     while ( d > 0 )
		     {
		         r.raise();
		         d--;
		     }
		     System.out.println("Debug 6: height(arm1)= " + h + " width (arm2) = " +
		                        w + " depth (arm3) =" + d); 

		     // This just shows the message at the end of the sample robot run -
		     // you don't need to duplicate (or even use) this code in your program.

		     JOptionPane.showMessageDialog(null,
		                                   "You have moved one block from source " +
		                                   "to the first bar position.\n" + 
		                                   "Now you may modify this code or " +
		                                   "redesign the program and come up with " +
		                                   "your own method of controlling the robot.", 
		                                   "Helper Code Execution", 
		                                   JOptionPane.INFORMATION_MESSAGE);
		     // You have moved one block from source to the first bar position. 
		     // You should be able to get started now. 
	   }
	   
	   public void controlMechanismForScenarioA(int barHeights[], int blockHeights[])
	   {
		   // Initial variable declaration 
			int armHeight = 2;
			int armWidth = 1;
			int armDepth = 0;
			
			int currentBar  = 0;
			int initSpace = 3;
			int sourceHeight = 12;
			int blockHeight = 3;
			int extendLength = 9; // First-time extension of the arm
			boolean firstTime = true;
			
			for(int blockCount = 0; blockCount <= 3; blockCount++) // Four block, so it should be 4
			{
				if(firstTime)
				{
					// Reach its arm to enough height in the first round.
					while ((armHeight - armDepth) <= sourceHeight)
					{
						armHeight++;
						r.up();
					}
					
					firstTime = false;
				}

				
				while (armWidth <= extendLength)
				{
					armWidth++;
					r.extend();
				}
				
				System.out.println("#1, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
				
				
				while(armDepth < (blockCount * blockHeight))
				{
					armDepth++;
					r.lower();
				}
				
				System.out.println("#2, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
				
				// Now it should reach the block, catch it!
				r.pick();
				
				// After it got the block, lift the arm...
			     while (armDepth > 0)
			     {
			         r.raise();
			         armDepth--;
			     }
			     
			     System.out.println("#3, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
			    
			     // Move to where it needs to
			     while ((armWidth - initSpace - blockCount) > 0 )
			     {
			         r.contract();
			         armWidth--;
			     }
			     System.out.println("#4, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
			     // Now move down to the target
			     while (armDepth < 2)
			     //while (armDepth < (sourceHeight - barHeights[currentBar]))
			     {
			    	 r.lower();
			    	 armDepth++;
			     }
			     System.out.println("#5, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
			     // Release the block
			     r.drop();
			     
			     // Update source's height
			     sourceHeight = sourceHeight - blockHeight;
			     
			}
			
			
			
	   }
	   
	   public void controlMechanismForScenarioB(int barHeights[], int blockHeights[])
	   {
		   // Initial variable declaration 
		   int armHeight = 2;
		   int armWidth = 1;
		   int armDepth = 0;

		   int currentBar  = 0;
		   int initSpace = 3;
		   int sourceHeight = 12;
		   int blockHeight = 3;
		   int extendLength = 9; // First-time extension of the arm
		   boolean firstTime = true;

		   for(int blockCount = 0; blockCount <= 3; blockCount++) // Four block, so it should be 4
		   {
			   if(firstTime)
			   {
				   // Reach its arm to enough height in the first round.
				   while ((armHeight - armDepth) <= sourceHeight)
				   {
					   armHeight++;
					   r.up();
				   }

				   firstTime = false;
			   }


			   while (armWidth <= extendLength)
			   {
				   armWidth++;
				   r.extend();
			   }

			   System.out.println("#1, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);

			   System.out.println("Block count * height:" + (blockCount * blockHeight) + " sourceHeight: " + sourceHeight);
			   while(armDepth < 10 - sourceHeight)
			   {
				   armDepth++;
				   r.lower();
			   }

			   System.out.println("#2, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);

			   // Now it should reach the block, catch it!
			   r.pick();

			   // After it got the block, lift the arm...
			   while (armDepth > 0)
			   {
				   r.raise();
				   armDepth--;
			   }

			   System.out.println("#3, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
			   
			   // If the block is type 1 (height 1) or 2, 
			   // then it should moves to the target position
			   switch (blockHeights[blockCount])
			   {
			   		case 3:
			   		{
						   // Move to where it needs to
						   while ((armWidth - initSpace - blockCount) > 0 )
						   {
							   r.contract();
							   armWidth--;
						   }
						   break;
			   		}
			   		
			   		case 2:
			   		{
			   			// Move to where it needs to
						   while ((armWidth - initSpace - blockCount + 2) > 0 ) 
						   {
							   r.contract();
							   armWidth--;
						   }
						   break;
			   		}
			   		
			   		case 1:
			   		{
			   				// Move to where it needs to,
						   while ((armWidth - initSpace - blockCount + 3) > 0 )
						   {
							   r.contract();
							   armWidth--;
						   }
						   break;
			   		}
			   			
			   }
			  
			   System.out.println("#4, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
			   
			   System.out.println("Current block height"+ blockHeights[blockCount]);
			   switch (blockHeights[blockCount])
			   {
			   		case 3:
			   		{
						   // Now move down to the target
						   while (armDepth + blockHeights[blockCount] + barHeights[currentBar] < 12)
						   {
							   r.lower();
							   armDepth++;
						   }
						   System.out.println("Arm Depth: " + armDepth + " Block height:" + blockHeights[blockCount] 
								   + " Bar height:" + barHeights[currentBar] + " Target Height:" + 12);
						   System.out.println("#5, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
						   break;
			   		}
			   		
			   		case 2:
			   		{
						   // Now move down to the target
						   while (armDepth + blockHeights[blockCount] <= 12)
						   {
							   r.lower();
							   armDepth++;
						   }
						   System.out.println("Arm Depth: " + armDepth + " Block height:" + blockHeights[blockCount] 
								   + " Bar height: 0" + " Target Height:" + 12);
						   System.out.println("#5, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
						   break;
			   		}
			   		
			   		case 1:
			   		{
						   // Now move down to the target
						   while (armDepth + blockHeights[blockCount] <= 12)
						   {
							   r.lower();
							   armDepth++;
						   }
						   System.out.println("Arm Depth: " + armDepth + " Block height:" + blockHeights[blockCount] 
								   + " Bar height: 0" + " Target Height:" + 12);
						   System.out.println("#5, Depth: " + armDepth + " Height: " + armHeight + " Width: " + armWidth);
						   break;
			   		}
			   
			   }
			   
			   // Release the block
			   r.drop();
			   
			   // After it release the block, lift the arm...
			   while (armDepth > 0)
			   {
				   r.raise();
				   armDepth--;
			   }
			   // Update source's height
			   sourceHeight = sourceHeight - blockHeight;
			   currentBar++;
		   }

	   }
	   
	   public void controlMechanismForScenarioC(int barHeights[], int blockHeights[])
	   {
		   
	   }

}