package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JPanel;


public class GuiProgram {

	private JFrame frmAutopark;
	private JTextField textPlate;
	private JTextField textPlateEnters;
	private JTextField textPlateExits;
	
	private AutoPark autopark;
	private Date date;
	private Time time;
	
	private JTextField textBeginDay;
	private JTextField textBeginMonth;
	private JTextField textBeginYear;
	private JTextField textEndDay;
	private JTextField textEndMonth;
	private JTextField textEndYear;
	private JTextField textFieldEnterHour;
	private JTextField textFieldEnterMinute;
	private JTextField textFieldExitHour;
	private JTextField textFieldExitMinute;
	private JTextField textFieldIncomeDaily;
	private JTextField textFieldHourlyFee;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiProgram window = new GuiProgram();
					window.frmAutopark.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GuiProgram() {
		initialize();
		
		autopark = new AutoPark(10, 100);
		date = new Date();
		time = new Time();
		textFieldIncomeDaily.setText(Double.toString(autopark.getIncomeDaily()) + " €");
		textFieldHourlyFee.setText(Double.toString(autopark.getHourlyFee()) + " €");
	}


	private void initialize() {
		frmAutopark = new JFrame();
		frmAutopark.getContentPane().setBackground(new Color(51, 153, 153));
		frmAutopark.setTitle("AutoPark");
		frmAutopark.setBounds(100, 100, 1047, 654);
		frmAutopark.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutopark.getContentPane().setLayout(null);
		
		JLabel lblBeginDate = new JLabel("Begin Date");
		lblBeginDate.setBounds(12, 114, 84, 22);
		lblBeginDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBeginDate.setBackground(Color.WHITE);
		lblBeginDate.setForeground(Color.BLACK);
		lblBeginDate.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(lblBeginDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(12, 143, 84, 22);
		lblEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(lblEndDate);
		
		JLabel lblPlate = new JLabel("Plate");
		lblPlate.setBounds(12, 172, 84, 22);
		lblPlate.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(lblPlate);
		
		textPlate = new JTextField();
		textPlate.setBounds(108, 172, 147, 22);
		textPlate.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(textPlate);
		textPlate.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(502, 88, 515, 437);
		textArea.setFont(new Font("Segoe UI", Font.BOLD, 14));
		textArea.setEditable(false);
		frmAutopark.getContentPane().add(textArea);
		
		JComboBox comboBoxIsOfficial = new JComboBox();
		comboBoxIsOfficial.setBounds(108, 318, 147, 22);
		frmAutopark.getContentPane().add(comboBoxIsOfficial);
		comboBoxIsOfficial.addItem("Official");
		comboBoxIsOfficial.addItem("Not Official");
		
		
		JButton btnAddSubscription = new JButton("Add Subscription");
		btnAddSubscription.setBounds(267, 115, 157, 79);
		btnAddSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date begin = new Date(Integer.parseInt(textBeginDay.getText()), Integer.parseInt(textBeginMonth.getText()), Integer.parseInt(textBeginYear.getText()));
					Date end = new Date(Integer.parseInt(textEndDay.getText()), Integer.parseInt(textEndMonth.getText()), Integer.parseInt(textEndYear.getText()));
					
					Subscription subscription = new Subscription(begin, end, textPlate.getText());
					SubscribedVehicle subVehicle = new SubscribedVehicle(subscription);

					if (autopark.addVehicle(subVehicle))
						textArea.setText("The vehicle is successfully subscribed: " + subVehicle.getPlate());
					else
						textArea.setText("The vehicle is already a subscribed vehicle: " + subVehicle.getPlate());
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Please enter valid Begin Date, End Date or Plate!");
				}
					
			}
		});
		
		
		btnAddSubscription.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddSubscription.setForeground(new Color(0, 102, 102));
		frmAutopark.getContentPane().add(btnAddSubscription);
		
		JLabel labelPlateEnters = new JLabel("Plate");
		labelPlateEnters.setBounds(12, 253, 84, 22);
		labelPlateEnters.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(labelPlateEnters);
		
		textPlateEnters = new JTextField();
		textPlateEnters.setBounds(108, 253, 147, 22);
		textPlateEnters.setHorizontalAlignment(SwingConstants.CENTER);
		textPlateEnters.setColumns(10);
		frmAutopark.getContentPane().add(textPlateEnters);
		
		
		JButton btnVehicleEnters = new JButton("Vehicle Enters");
		btnVehicleEnters.setBounds(267, 253, 157, 87);
		btnVehicleEnters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isOfficial;
				if (comboBoxIsOfficial.getSelectedItem().equals("Official")) {
					isOfficial = true;
				}
				else
					isOfficial = false;
				
				try {
					Time enter = new Time(Integer.parseInt(textFieldEnterHour.getText()), Integer.parseInt(textFieldEnterMinute.getText()));
					if (autopark.vehicleEnters(textPlateEnters.getText(), enter, isOfficial)) {
						
						if ((autopark.searchVehicle(textPlateEnters.getText()) != null) && (isOfficial == false))
							textArea.setText("The subscribed vehicle successfully entered: " + textPlateEnters.getText());
						else if (isOfficial == false)
							textArea.setText("The regular vehicle successfully entered: " + textPlateEnters.getText());
					else if (isOfficial == true)
						textArea.setText("The official vehicle is successfully entered (but it can't be in parked vehicles): \n" + textPlateEnters.getText());
					}
					else
						textArea.setText("The vehicle is already in the park now: " + textPlateEnters.getText());
				}
				catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Please enter valid Plate or Enter Time!");
				}					
			}
		});
		
		
		btnVehicleEnters.setForeground(new Color(0, 102, 102));
		btnVehicleEnters.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmAutopark.getContentPane().add(btnVehicleEnters);
		
		JButton btnVehicleExits = new JButton("Vehicle Exits");
		btnVehicleExits.setBounds(267, 404, 157, 64);
		btnVehicleExits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Time exit = new Time(Integer.parseInt(textFieldExitHour.getText()), Integer.parseInt(textFieldExitMinute.getText()));
					if (autopark.vehicleExits(textPlateExits.getText(), exit)) {
						textArea.setText("The regular vehicle successfully exited: " + textPlateExits.getText());
					
						if (autopark.searchVehicle(textPlateExits.getText()) != null) {
							textArea.setText("The subscribed vehicle successfully exited: " + textPlateExits.getText());		
						}
						textFieldIncomeDaily.setText(Double.toString(autopark.getIncomeDaily()) + " €");
					}
					else
						textArea.setText("The vehicle is not in the park now: " + textPlateExits.getText());	
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Please enter valid Plate or Exit Time!");
				}
				
			}
		});
		
		
		btnVehicleExits.setForeground(new Color(0, 102, 102));
		btnVehicleExits.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmAutopark.getContentPane().add(btnVehicleExits);
		
		JLabel labelPlateExits = new JLabel("Plate");
		labelPlateExits.setBounds(12, 409, 84, 22);
		labelPlateExits.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(labelPlateExits);
		
		textPlateExits = new JTextField();
		textPlateExits.setBounds(108, 409, 147, 22);
		textPlateExits.setHorizontalAlignment(SwingConstants.CENTER);
		textPlateExits.setColumns(10);
		frmAutopark.getContentPane().add(textPlateExits);
		
		
		JButton btnShowParkedVehicles = new JButton("Show Parked Vehicles");
		btnShowParkedVehicles.setBounds(774, 28, 197, 47);
		btnShowParkedVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				String str = "PARKED VEHICLES\n\n";
				if (autopark.showParkedVehicles().size() == 0)
					str = str + "There is not any parked vehicle now!";
				for (i = 0; i < autopark.showParkedVehicles().size(); i++) {
					if (autopark.showParkedVehicles().get(i).getSubscription() != null)
						str = str + autopark.showParkedVehicles().get(i).getSubscription().toString() + "\n";	
					else
						str = str + "Plate: " + autopark.showParkedVehicles().get(i).getPlate() + "\n";
				}
				textArea.setText(str);
			}
		});
		
		
		btnShowParkedVehicles.setForeground(new Color(0, 102, 102));
		btnShowParkedVehicles.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmAutopark.getContentPane().add(btnShowParkedVehicles);
		
		
		JButton btnShowSubscribedVehicles = new JButton("Show Subscribed Vehicles");
		btnShowSubscribedVehicles.setBounds(534, 28, 211, 47);
		btnShowSubscribedVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int i;
				String str = "SUBSCRIBED VEHICLES\n\n";
				if (autopark.showSubscribedVehicles().size() == 0)
					str = str + "There is not any subscribed vehicle now!";
				for (i = 0; i < autopark.showSubscribedVehicles().size(); i++) {
					str = str + autopark.showSubscribedVehicles().get(i).getSubscription().toString() + "\n";	
				}
				textArea.setText(str);
			}
		});
		
		
		btnShowSubscribedVehicles.setForeground(new Color(0, 102, 102));
		btnShowSubscribedVehicles.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmAutopark.getContentPane().add(btnShowSubscribedVehicles);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(502, 538, 516, 35);
		panel_1.setBackground(new Color(0, 0, 0));
		frmAutopark.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(0, 0, 248, 35);
		panel_1.add(lblDate);
		lblDate.setForeground(new Color(0, 128, 128));
		lblDate.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setText(date.getToday().getDay() + "/" + date.getToday().getMonth() + "/" + date.getToday().getYear());
		if (date.getToday().getMonth() < 10) 
			lblDate.setText(date.getToday().getDay() + "/0" + date.getToday().getMonth() + "/" + date.getToday().getYear());
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBackground(new Color(0, 0, 0));
		lblTime.setBounds(260, 0, 256, 35);
		panel_1.add(lblTime);
		lblTime.setForeground(new Color(0, 128, 128));
		lblTime.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setText(time.getTime().getHour() + ":" + time.getTime().getMinute());
		if ((time.getTime().getMinute() >= 0) & (time.getTime().getMinute() < 10)) {
			lblTime.setText(time.getTime().getHour() + ":0" + time.getTime().getMinute());
		}
		else if ((time.getTime().getHour() >=0) && (time.getTime().getHour() < 10)) {
			lblTime.setText("0" + time.getTime().getHour() + ":" + time.getTime().getMinute());
		}
		else if ((time.getTime().getHour() >=0) && (time.getTime().getHour() < 10) && (time.getTime().getMinute() >= 0) & (time.getTime().getMinute() < 10)) {
			lblTime.setText("0" + time.getTime().getHour() + ":0" + time.getTime().getMinute());
		}
		
		textBeginDay = new JTextField();
		textBeginDay.setBounds(108, 114, 34, 22);
		textBeginDay.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(textBeginDay);
		textBeginDay.setColumns(10);
		
		textBeginMonth = new JTextField();
		textBeginMonth.setBounds(154, 114, 34, 22);
		textBeginMonth.setHorizontalAlignment(SwingConstants.CENTER);
		textBeginMonth.setColumns(10);
		frmAutopark.getContentPane().add(textBeginMonth);
		
		textBeginYear = new JTextField();
		textBeginYear.setBounds(200, 114, 55, 22);
		textBeginYear.setHorizontalAlignment(SwingConstants.CENTER);
		textBeginYear.setColumns(10);
		frmAutopark.getContentPane().add(textBeginYear);
		
		textEndDay = new JTextField();
		textEndDay.setBounds(108, 143, 34, 22);
		textEndDay.setHorizontalAlignment(SwingConstants.CENTER);
		textEndDay.setColumns(10);
		frmAutopark.getContentPane().add(textEndDay);
		
		textEndMonth = new JTextField();
		textEndMonth.setBounds(154, 143, 34, 22);
		textEndMonth.setHorizontalAlignment(SwingConstants.CENTER);
		textEndMonth.setColumns(10);
		frmAutopark.getContentPane().add(textEndMonth);
		
		textEndYear = new JTextField();
		textEndYear.setBounds(200, 143, 55, 22);
		textEndYear.setHorizontalAlignment(SwingConstants.CENTER);
		textEndYear.setColumns(10);
		frmAutopark.getContentPane().add(textEndYear);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 13, 401, 73);
		panel.setBackground(Color.DARK_GRAY);
		frmAutopark.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcomeToAutopark = new JLabel("AutoPark");
		lblWelcomeToAutopark.setBounds(0, 0, 401, 73);
		panel.add(lblWelcomeToAutopark);
		lblWelcomeToAutopark.setBackground(Color.WHITE);
		lblWelcomeToAutopark.setForeground(new Color(0, 128, 128));
		lblWelcomeToAutopark.setFont(new Font("MV Boli", Font.BOLD, 50));
		lblWelcomeToAutopark.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblEnterTime = new JLabel("Enter Time");
		lblEnterTime.setBounds(12, 285, 84, 22);
		lblEnterTime.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(lblEnterTime);
		
		textFieldEnterHour = new JTextField();
		textFieldEnterHour.setBounds(108, 283, 69, 22);
		textFieldEnterHour.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEnterHour.setColumns(10);
		frmAutopark.getContentPane().add(textFieldEnterHour);
		
		textFieldEnterMinute = new JTextField();
		textFieldEnterMinute.setBounds(186, 283, 69, 22);
		textFieldEnterMinute.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEnterMinute.setColumns(10);
		frmAutopark.getContentPane().add(textFieldEnterMinute);
		
		JLabel lblExitTime = new JLabel("Exit Time");
		lblExitTime.setBounds(12, 446, 84, 22);
		lblExitTime.setHorizontalAlignment(SwingConstants.CENTER);
		frmAutopark.getContentPane().add(lblExitTime);
		
		textFieldExitHour = new JTextField();
		textFieldExitHour.setBounds(108, 444, 69, 22);
		textFieldExitHour.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldExitHour.setColumns(10);
		frmAutopark.getContentPane().add(textFieldExitHour);
		
		textFieldExitMinute = new JTextField();
		textFieldExitMinute.setBounds(186, 444, 69, 22);
		textFieldExitMinute.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldExitMinute.setColumns(10);
		frmAutopark.getContentPane().add(textFieldExitMinute);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(23, 494, 401, 47);
		panel_2.setBackground(new Color(220, 20, 60));
		frmAutopark.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblIncomeDaily = new JLabel("Income Daily");
		lblIncomeDaily.setBounds(0, 0, 146, 46);
		panel_2.add(lblIncomeDaily);
		lblIncomeDaily.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIncomeDaily.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldIncomeDaily = new JTextField();
		textFieldIncomeDaily.setEditable(false);
		textFieldIncomeDaily.setBounds(158, 0, 243, 47);
		panel_2.add(textFieldIncomeDaily);
		textFieldIncomeDaily.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldIncomeDaily.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIncomeDaily.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(55, 545, 230, 28);
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(255, 215, 0));
		frmAutopark.getContentPane().add(panel_3);
		
		JLabel lblHourlyFee = new JLabel("Hourly Fee");
		lblHourlyFee.setHorizontalAlignment(SwingConstants.CENTER);
		lblHourlyFee.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHourlyFee.setBounds(0, 0, 114, 28);
		panel_3.add(lblHourlyFee);
		
		textFieldHourlyFee = new JTextField();
		textFieldHourlyFee.setText("0.0 \u20AC");
		textFieldHourlyFee.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldHourlyFee.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldHourlyFee.setEditable(false);
		textFieldHourlyFee.setColumns(10);
		textFieldHourlyFee.setBounds(126, 0, 104, 28);
		panel_3.add(textFieldHourlyFee);
		
	}
}
