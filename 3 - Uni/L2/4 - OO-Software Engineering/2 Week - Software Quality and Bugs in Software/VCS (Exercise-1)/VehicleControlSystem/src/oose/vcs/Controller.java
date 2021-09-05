package oose.vcs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.UIManager;

import vehicle.types.Airplane;
import vehicle.types.Bicycle;
import vehicle.types.Boat;
import vehicle.types.Bus;
import vehicle.types.Car;
import vehicle.types.Helicopter;
import vehicle.types.Motorcycle;
import vehicle.types.Ship;
import vehicle.types.Train;
import vehicle.types.Tram;
import vehicle.types.Truck;
import vehicle.types.Vehicle;


public class Controller {

	private Vehicle vehicle;
	private String[] vehicles = { "Boat", "Ship", "Truck", "Motorcycle", "Bus", "Car", "Bicycle", "Helicopter", "Airplane", "Tram", "Train"};
	private Simulator simulationPane;
	private JLabel speedlabel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JComboBox<String> combobox;
	private JFrame frame;

	private boolean accelerate, decelerate, cruise,stop;
	int currentvelocity = 1;
	int maximumvelocity = 300;

	public static void main(String args[]) {
		new Controller();
	}

	public Controller() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame = new JFrame("Vehicle Control System");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());

				combobox = new JComboBox<String>(vehicles);
				combobox.setSelectedIndex(6);
				combobox.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						int selectedIndex = combobox.getSelectedIndex();
						String vehicleName = vehicles[selectedIndex];
						initialiseVehicle(vehicleName);							
					}
				});

				speedlabel = new JLabel("          ");
				
				configStart();
				configAccelerate();
				configDecelerate();
				configCruise();
				configStop();
				
				JToolBar toolBar =new JToolBar();
				toolBar.setRollover(true);

				toolBar.add(combobox);
				toolBar.add(speedlabel);
				toolBar.add(button1);
				toolBar.add(button2);
				toolBar.add(button3);
				toolBar.add(button4);
				toolBar.add(button5);

				frame.add(toolBar,BorderLayout.NORTH);
				frame.setPreferredSize(new Dimension(800,200));
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});
	}
	
	/*
	 * The method created for the third refactoring:
	 * Numerous repeating code blocks are visible in the the class’s methods which set the background colour of the 
	 * five buttons. Rather than repeat the same five lines multiple times over, I displaced the lines into a new method and 
	 * set all buttons to be a single common colour. The parameter of the method was used to supply the button whose colour
	 * should be set to green. In the GUi, whenever the user selects from the vehicle motion choice types provided,
	 * this method would execute. 
	 * Thus overall one line of code of the method would need to be placed for each of the configuratiohs that apply the
	 * button colour changes instead of having to rewrite the same five lines over and over again.
	 */
	private void changeButtonBGC(JButton buttonColour) {
		button1.setBackground(Color.lightGray);
		button2.setBackground(Color.lightGray);
		button3.setBackground(Color.lightGray);
		button4.setBackground(Color.lightGray);
		button5.setBackground(Color.lightGray);
		buttonColour.setBackground(Color.GREEN);
	}
	
	private void configStart() {
		button1 = new JButton("start");
		button1.setBackground(Color.lightGray);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(vehicle == null) {
					int selectedIndex = combobox.getSelectedIndex();
					String vehicleName = vehicles[selectedIndex];
					initialiseVehicle(vehicleName);
					speedlabel.setText(vehicle.printSpeed());
				}
				if(simulationPane !=null) {
					frame.remove(simulationPane);
				}
				accelerate = false;
				decelerate = false;
				cruise = false;
				stop = false;
				changeButtonBGC(button1);

				simulationPane = new Simulator();
				frame.add(simulationPane,BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}

		});
	}

	private void configAccelerate() {
		button2 = new JButton("accelerate");
		button2.setBackground(Color.lightGray);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = true;
				decelerate = false;
				cruise = false;
				stop = false;
				changeButtonBGC(button2);

				Thread thread = new Thread(){
					public void run(){
						try {
							while(accelerate) {
								Thread.sleep(2 * 1000);

								if(currentvelocity<=maximumvelocity) {
									currentvelocity = currentvelocity +1;
									vehicle.setCurrentSpeed(currentvelocity);
									speedlabel.setText(vehicle.printSpeed());
									simulationPane.updateTimer();
								}									    
							}
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						} 
					}
				};

				thread.start();
			}				    	
		});

	}
	
	private void configCruise() {
		button3 = new JButton("cruise");
		button3.setBackground(Color.lightGray);
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = false;
				decelerate = false;
				cruise = true;
				stop = false;
				changeButtonBGC(button3);

			}				    	
		});
	}
	private void configDecelerate() {
		button4 = new JButton("decelerate");
		button4.setBackground(Color.lightGray);
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = false;
				decelerate = true;
				cruise = false;
				stop = false;
				changeButtonBGC(button4);
				
				Thread thread = new Thread(){
					public void run(){
						try {
							while(decelerate) {
								Thread.sleep(2 * 1000);

								if(currentvelocity >1) {
									currentvelocity = currentvelocity -1;
									vehicle.setCurrentSpeed(currentvelocity);
									speedlabel.setText(vehicle.printSpeed());
									simulationPane.updateTimer();
								}									    
							}
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						} 
					}
				};

				thread.start();
			}				    	
		});
	}
	
	private void configStop() {
		button5 = new JButton("stop");
		button5.setBackground(Color.lightGray);
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = false;
				decelerate = false;
				cruise = false;
				stop = true;
				changeButtonBGC(button5);
				
				currentvelocity = 1;
				vehicle.setCurrentSpeed(currentvelocity);
				speedlabel.setText(vehicle.printSpeed());
				simulationPane.updateTimer();
			}				    	
		});
	}
	
	/*
	 * THe fourth refactoring:
	 * The if-else statements could be replaced with switch statements instead, however the result
	 * of such a refactoring is between little to none.
	 * Instead the method makes use of a HashMap that maps a string to a vehicle object.
	 * Via this method, the vehicle variable requires one assignment only and the the vehicle value
	 * is mapped to the string key it is linked to. So when the user selects a a vehicle string from
	 * the GUi, the appropriate vehicle object is retrieved and initialised as requested.
	 */
	private void initialiseVehicle(String vehicleName) {
		Map<String, Vehicle> myMap = new HashMap<>();
		myMap.put("Boat", new Boat("Apollo"));
		myMap.put("Ship", new Ship("Cruizz"));
		myMap.put("Truck", new Truck("Ford F-650"));
		myMap.put("Motorcycle", new Motorcycle("Suzuki"));
		myMap.put("Bus", new Bus("Aero"));
		myMap.put("Car", new Car("BMW"));
		myMap.put("Bicycle", new Bicycle("A-bike"));
		myMap.put("Helicopter", new Helicopter("Eurocopter"));
		myMap.put("Airplane", new Airplane("BA"));
		myMap.put("Tram", new Tram("EdinburghTram"));
		myMap.put("Train", new Train("Virgin", 4));
		vehicle = myMap.get(vehicleName);
	}


	public class Simulator extends JPanel {

		private BufferedImage boat;
		private int xPos = 0;
		private int direction = 1;
		private File file; 
		private Timer timer;
		
		/*
		 * For the second refactoring, opening the image files could be done in a single line of code as shown below.
		 * By adding a few more methods to retrieve the class name, and some fine-tuning at the end,
		 * vehicle image files can be retrieved more simply.
		 * What the substring(14) method does it it trims down the section: vehicle.types. part which is 14 characters in
		 * length. So when the user clicks on a vehicle in the GUI, making sure to convert to lower case, ".png" is appended
		 * to the end. Ultimately, it opens the image of the vehicle which is clicked on from the menu options in the GUI.
		 */
		public Simulator() {
			file = new File(System.getProperty("user.dir")+"/img/"+vehicle.getClass().getName().substring(14).toLowerCase()+".png");
			try {	
				boat = ImageIO.read(file);
				timer = new Timer(maximumvelocity/currentvelocity, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						xPos += direction;
						if (xPos + boat.getWidth() > getWidth()) {
							xPos = 0;
							direction *= -1;

						} else if (xPos < 0) { 
							xPos = 0;
							direction *= -1;
						}
						repaint();
					}

				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		public void updateTimer() {
			timer.setDelay(maximumvelocity/currentvelocity);
		}
		
		/* 
		 * For Refactorings 1 and 2:
		 * Notice that methods getPreferredSize() and setDisplayObject() have been removed
		 */

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			int y = getHeight() - boat.getHeight();
			g.drawImage(boat, xPos, y, this);

		}

	}

}
