package fr.univcotedazur.polytech.si4.fsm.dm.v4;



import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dmnv10.TimerService;
import dmnv10.defaultsm.DefaultSMStatemachine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;


public class DrinkFactoryMachine extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private JLabel messagesToUser;
	JProgressBar progressBar = new JProgressBar();
	protected DefaultSMStatemachine theFSM; // Declaration de la stateMAchine

	private int cagnote = 0;
	private int coffePrice = 35;
	private int expressoPrice = 50;
	private int teaPrice = 40;
	private int soupPrice = 75;
	private int icedTeaPrice = 50;
	private int montant;

	private int stockCoffe = 10; // nombre de dosette de cafee
	private int stockTea = 2; // nombre de sachet de the
	private int stockExpresso = 2; // nombre de packet de grain pour lexpresso
	private int stockIcedTea = 1; // nombre de sachet pour l iced tea
	private int stockSoup = 10; // nombre de dose de soupe

	long poorDelay;
	long currentPoorDelay = 0;
	long delay;
	boolean poor;
	boolean taken;
	boolean cupAdded = false;
	boolean currentTemperatureChange = false;

	JButton cancelButton;
	JButton nfcBiiiipButton;
	JButton icedTeaButton;
	JButton money50centsButton;
	JButton money25centsButton;
	JButton money10centsButton;
	JSlider temperatureSlider;
	JSlider sugarSlider;
	JSlider sizeSlider;
	JButton coffeeButton;
	JButton expressoButton;
	JButton teaButton;
	JButton soupButton;
	JButton addCupButton;
	JCheckBox milkButton;
	JCheckBox croutonButton;
	JCheckBox siropErableButton;
	JCheckBox glaceVanilleButton;

	JButton labelForPictures;
	JLabel lblSugar;

	private final int displayTime = 1;

	Hashtable<Integer, JLabel> temperatureTable;
	private double currentTemperature = 15;
	private int progressBarValue = 0;
	TimerService timer;
	private String selection;

	/**
	 * @wbp.nonvisual location=311,475
	 */
	private final ImageIcon imageIcon = new ImageIcon();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinkFactoryMachine frame = new DrinkFactoryMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setMessageToUser(String str) {
		messagesToUser.setText("<html>" + str);
	}

	public void addMessageToUser(String str) {
		messagesToUser.setText(messagesToUser.getText() + "<br>" + str);
	}


	/**
	 * Create the frame.
	 */
	public DrinkFactoryMachine() {
		setForeground(Color.WHITE);
		setFont(new Font("Cantarell", Font.BOLD, 22));
		setBackground(Color.DARK_GRAY);
		setTitle("Drinking Factory Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messagesToUser = new JLabel("<html>Hello ! La Drinking Machine est prête !");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 165, 175);
		contentPane.add(messagesToUser);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);

		coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.DARK_GRAY);
		coffeeButton.setBackground(Color.WHITE);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPane.add(coffeeButton);

		expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.DARK_GRAY);
		expressoButton.setBackground(Color.WHITE);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);

		teaButton = new JButton("Tea");
		teaButton.setForeground(Color.DARK_GRAY);
		teaButton.setBackground(Color.WHITE);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);

		soupButton = new JButton("Soup");
		soupButton.setForeground(Color.DARK_GRAY);
		soupButton.setBackground(Color.WHITE);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);

		icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.DARK_GRAY);
		icedTeaButton.setBackground(Color.WHITE);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPane.add(icedTeaButton);


		JLabel optionLabel = new JLabel("<html>Options proposées :");
		optionLabel.setForeground(Color.WHITE);
		optionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		optionLabel.setVerticalAlignment(SwingConstants.TOP);
		optionLabel.setBackground(Color.WHITE);
		optionLabel.setBounds(12, 300, 165, 175);
		contentPane.add(optionLabel);

		milkButton = new JCheckBox("Nuage de lait");
		milkButton.setForeground(Color.WHITE);
		milkButton.setBackground(Color.DARK_GRAY);
		milkButton.setBounds(45, 330, 120, 25);
		milkButton.setEnabled(false);
		contentPane.add(milkButton);

		siropErableButton = new JCheckBox("Sirop d'érable");
		siropErableButton.setForeground(Color.WHITE);
		siropErableButton.setBackground(Color.DARK_GRAY);
		siropErableButton.setBounds(45, 367, 120, 25);
		siropErableButton.setEnabled(false);
		contentPane.add(siropErableButton);

		glaceVanilleButton = new JCheckBox("Glace Vanille");
		glaceVanilleButton.setForeground(Color.WHITE);
		glaceVanilleButton.setBackground(Color.DARK_GRAY);
		glaceVanilleButton.setBounds(45, 404, 120, 25);
		glaceVanilleButton.setEnabled(false);
		contentPane.add(glaceVanilleButton);

		croutonButton = new JCheckBox("Croutons");
		croutonButton.setForeground(Color.WHITE);
		croutonButton.setBackground(Color.DARK_GRAY);
		croutonButton.setBounds(45, 441, 120, 25);
		croutonButton.setEnabled(false);
		contentPane.add(croutonButton);


		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setForeground(Color.blue);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		contentPane.add(progressBar);

		sugarSlider = new JSlider();
		sugarSlider.setValue(1);
		sugarSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sugarSlider.setBackground(Color.DARK_GRAY);
		sugarSlider.setForeground(Color.WHITE);
		sugarSlider.setPaintTicks(true);
		sugarSlider.setMinorTickSpacing(1);
		sugarSlider.setMajorTickSpacing(1);
		sugarSlider.setMaximum(4);
		sugarSlider.setBounds(301, 51, 200, 36);
		contentPane.add(sugarSlider);

		sizeSlider = new JSlider();
		sizeSlider.setPaintTicks(true);
		sizeSlider.setValue(1);
		sizeSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sizeSlider.setBackground(Color.DARK_GRAY);
		sizeSlider.setForeground(Color.WHITE);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMaximum(2);
		sizeSlider.setMajorTickSpacing(1);
		sizeSlider.setBounds(301, 125, 200, 36);
		contentPane.add(sizeSlider);

		temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		temperatureSlider.setValue(2);
		temperatureSlider.setBackground(Color.DARK_GRAY);
		temperatureSlider.setForeground(Color.WHITE);
		temperatureSlider.setPaintTicks(true);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setMaximum(3);
		temperatureSlider.setBounds(301, 188, 200, 54);

		reinitialiseTemperatureSlider();

		contentPane.add(temperatureSlider);

		lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(380, 34, 44, 15);
		contentPane.add(lblSugar);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setBackground(Color.DARK_GRAY);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(380, 113, 44, 15);
		contentPane.add(lblSize);

		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBackground(Color.DARK_GRAY);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(363, 173, 96, 15);
		contentPane.add(lblTemperature);

		JPanel moneyPanel = new JPanel();
		moneyPanel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(moneyPanel);
		moneyPanel.setBounds(538, 25, 96, 97);
		contentPane.add(moneyPanel);

		money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.BLACK);
		money50centsButton.setBackground(Color.WHITE);
		moneyPanel.add(money50centsButton);

		money25centsButton = new JButton("0.25 €");
		money25centsButton.setForeground(Color.BLACK);
		money25centsButton.setBackground(Color.WHITE);
		moneyPanel.add(money25centsButton);

		money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.BLACK);
		money10centsButton.setBackground(Color.WHITE);
		moneyPanel.add(money10centsButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 154, 96, 40);
		contentPane.add(panel_1);

		nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.BLACK);
		nfcBiiiipButton.setBackground(Color.WHITE);
		panel_1.add(nfcBiiiipButton);

		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPane.add(lblNfc);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.BLACK);
		addCupButton.setBackground(Color.WHITE);
		addCupButton.setBounds(505, 336, 96, 25);
		contentPane.add(addCupButton);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/vide1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures = new JButton(new ImageIcon(myPicture));
		labelForPictures.setBounds(175, 319, 286, 260);
		contentPane.add(labelForPictures);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 217, 96, 33);
		contentPane.add(panel_2);

		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.WHITE);
		panel_2.add(cancelButton);
//------------------------------------------------------LISTENERS----------------------------------------------------------------//
		labelForPictures.addActionListener(e -> {
			BufferedImage myPicture1 = null;
			try {
				myPicture1 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/vide1.jpg"));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			labelForPictures.setIcon(new ImageIcon(myPicture1));
			expressoPrice = 50;
			coffePrice = 35;
			soupPrice = 75;
			icedTeaPrice = 50;
			teaPrice = 40;
			cupAdded = false;
			taken = true;
		});
		addCupButton.addActionListener(e -> {
			BufferedImage myPicture12 = null;
			try {
				myPicture12 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/ownCup1.jpg"));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			labelForPictures.setIcon(new ImageIcon(myPicture12));
			cupAdded = true;
			expressoPrice = 40;
			coffePrice = 25;
			soupPrice = 65;
			icedTeaPrice = 40;
			teaPrice = 30;
		});

		cancelButton.addActionListener(e -> {
			doCancel();
			theFSM.raiseCancel();
		});

		coffeeButton.addActionListener(e -> {
			doCoffee();
			theFSM.raiseCoffee();
			theFSM.raiseAnyButton();
		});

		expressoButton.addActionListener(e -> {
			doExpresso();
			theFSM.raiseExpresso();
			theFSM.raiseAnyButton();

		});
		teaButton.addActionListener(e -> {
			doTea();
			theFSM.raiseTea();
			theFSM.raiseAnyButton();

		});
		soupButton.addActionListener(e -> {
			doSoup();
			theFSM.raiseSoup();
			theFSM.raiseAnyButton();

		});
		icedTeaButton.addActionListener(e -> {
			doIcedTea();
			theFSM.raiseIcedTea();
			theFSM.raiseAnyButton();
		});
		money50centsButton.addActionListener(e -> {
			doCinqanteCents();
			theFSM.raiseCinquanteCents();
			theFSM.raiseAnyButton();

		});

		money25centsButton.addActionListener(e -> {
			doVingtCinqCents();
			theFSM.raiseVingtCinqCents();
			theFSM.raiseAnyButton();

		});

		money10centsButton.addActionListener(e -> {
			doDixCents();
			theFSM.raiseDixCents();
			theFSM.raiseAnyButton();

		});

		nfcBiiiipButton.addActionListener(e -> {
			switch (selection) {
				case "Coffee":
					cagnote += coffePrice;
					break;
				case "Expresso":
					cagnote += expressoPrice;
					break;
				case "Tea":
					cagnote += teaPrice;
					break;
				case "Soup":
					cagnote += soupPrice;
					break;
				case "Iced Tea":
					cagnote += icedTeaPrice;
					break;
			}
			theFSM.raiseBip();
			setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
			repaint();
			theFSM.raiseAnyButton();

		});
		temperatureSlider.addChangeListener(e -> theFSM.raiseAnyButton());
		sugarSlider.addChangeListener(e -> theFSM.raiseAnyButton());
		sizeSlider.addChangeListener(e -> theFSM.raiseAnyButton());
		milkButton.addActionListener(e -> theFSM.raiseAnyButton());
		siropErableButton.addActionListener(e -> theFSM.raiseAnyButton());
		glaceVanilleButton.addActionListener(e -> theFSM.raiseAnyButton());
		croutonButton.addActionListener(e -> theFSM.raiseAnyButton());


		// initialisation de la stateMachine
		enableButtonCauseOfStock();
		taken = false;
		poor = false;
		theFSM = new DefaultSMStatemachine();
		selection = "";
		timer = new TimerService();
		theFSM.setTimer(timer);
		// Implementation des méthodes de callBack !!
		DrinkingFactoryCallBackInterfaceImplementation callback = new DrinkingFactoryCallBackInterfaceImplementation(this);
		theFSM.getSCInterface().setSCInterfaceOperationCallback(callback);

		theFSM.init();
		theFSM.enter();
		theFSM.getSCInterface().getListeners().add(
				new DrinkingMachineInterfaceImplementation(this)
		);

	}

	//------------------------------------------------------METHOD IS----------------------------------------------------------------//
	public boolean isPay() {
		switch (selection) {
			case "Coffee":
				montant = coffePrice + (milkButton.isSelected() ? 10 : 0) + (siropErableButton.isSelected() ? 10 : 0) + (glaceVanilleButton.isSelected() ? 40 : 0);
				break;
			case "Tea":
				montant = teaPrice + (milkButton.isSelected() ? 10 : 0) + (siropErableButton.isSelected() ? 10 : 0);
				break;
			case "Expresso":
				montant = expressoPrice + (milkButton.isSelected() ? 10 : 0) + (siropErableButton.isSelected() ? 10 : 0) + (glaceVanilleButton.isSelected() ? 40 : 0);
				break;
			case "Soup":
				montant = soupPrice + (croutonButton.isSelected() ? 30 : 0);
				break;
			case "IcedTea":
				montant = icedTeaPrice + (milkButton.isSelected() ? 10 : 0) + (siropErableButton.isSelected() ? 10 : 0) + sizeSlider.getValue() * 25;
				break;
		}
		return montant <= cagnote;
	}

	public boolean isHot() {
		if (currentTemperature >= Integer.parseInt(temperatureTable.get(temperatureSlider.getValue()).getText().substring(0, 2))) {
			System.out.println("isHot");
			return true;
		}
		return false;
	}

	public boolean isCool() {
		if (currentTemperature <= Integer.parseInt(temperatureTable.get(temperatureSlider.getValue()).getText().substring(0, 2))) {
			System.out.println("isCool");
			return true;
		}
		return false;
	}

	public boolean isErable() {
		return siropErableButton.isSelected();
	}

	public boolean isCrouton() {
		return croutonButton.isSelected();
	}

	public boolean isMilk() {
		return milkButton.isSelected();
	}

	public boolean isGlace() {
		return glaceVanilleButton.isSelected();
	}

	public boolean isPoor() {
		if (currentPoorDelay >= poorDelay) {
			System.out.println("isPoor");
			return true;
		}
		return false;
	}


	public boolean isTaken() {
		return taken;
	}

	//------------------------------------------------------METHOD IS----------------------------------------------------------------//
	//------------------------------------------------------METHOD DO----------------------------------------------------------------//
	public void doCancel() {
		System.out.println("doCancel");
		setMessageToUser("Transaction annulée");
		if (cagnote > 0) {
			addMessageToUser("Rendue monaie : " + cagnote());
		}

		TimerTask task = new TimerTask() {
			public void run() {
				doRestart();
				setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L * displayTime;
		timer.schedule(task, delay);
		repaint();
	}

	private void doCinqanteCents() {
		System.out.println("50 centimes ajoutés");
		cagnote += 50;
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	private void doVingtCinqCents() {
		System.out.println("25 centimes ajoutés");
		cagnote += 25;
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	private void doDixCents() {
		System.out.println("10 centimes ajoutés");
		cagnote += 10;
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doCoffee() {
		milkButton.setEnabled(true);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(true);
		reinitialiseSugarSlider();
		reinitialiseTemperatureSlider();
		selection = "Coffee";
		System.out.println("Coffee selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doExpresso() {
		milkButton.setEnabled(true);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(true);
		reinitialiseSugarSlider();
		reinitialiseTemperatureSlider();
		selection = "Expresso";
		System.out.println("Expresso selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doTea() {
		milkButton.setEnabled(true);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(false);
		reinitialiseSugarSlider();
		reinitialiseTemperatureSlider();
		selection = "Tea";
		System.out.println("Tea selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}


	public void doIcedTea() {
		milkButton.setEnabled(false);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(false);
		reinitialiseSugarSlider();
		changeTemperatureSliderForIcedTea();
		selection = "IcedTea";
		System.out.println("Iced Tea selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doSoup() {
		milkButton.setEnabled(false);
		croutonButton.setEnabled(true);
		siropErableButton.setEnabled(false);
		glaceVanilleButton.setEnabled(false);
		createSpicesSlider();
		reinitialiseTemperatureSlider();
		selection = "Soup";
		System.out.println("Soup selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doSugar() {
		addMessageToUser(String.format("Ajout de %d sucre", sugarSlider.getValue()) + (sugarSlider.getValue() > 1 ? "s" : ""));
		System.out.println("do sugar : " + sugarSlider.getValue());
	}

	public void doSpices() {
		addMessageToUser(String.format("Ajout de %d doses d'épices", sugarSlider.getValue()) + (sugarSlider.getValue() > 1 ? "s" : ""));
		System.out.println("do spices : " + sugarSlider.getValue());
	}


	public void doHeat() {
		int wantedTemperature = Integer.parseInt(temperatureTable.get(temperatureSlider.getValue()).getText().substring(0, 2));
		//System.out.println("temperature position : " + temperatureSlider.getValue() + " ie " + wantedTemperature + "°C");

		if(!currentTemperatureChange){
			delay = (long) (1000 * (wantedTemperature - currentTemperature) / 5);
			System.out.println("début chauffage");
			setMessageToUser("Début du chauffage de l'eau");
			repaint();
			currentTemperatureChange = true;
		}
		currentTemperature += delay / 600.0;
		if (isHot()) {
			System.out.println("fin chauffage");
			addMessageToUser("chauffage terminé");
			repaint();
		}

	}

	public void doCool() {
		//System.out.println("Cooling");
		int wantedTemperature = Integer.parseInt(temperatureTable.get(temperatureSlider.getValue()).getText().substring(0, 2));
		//System.out.println("temperature position : " + temperatureSlider.getValue() + " ie " + wantedTemperature + "°C");

		if(!currentTemperatureChange){
			delay = (long) (1000 * (currentTemperature - wantedTemperature) / 5);
			System.out.println("début refroidissmenet");
			setMessageToUser("Début du refroidissement de l'eau");
			repaint();
			currentTemperatureChange = true;
		}
		currentTemperature -= delay / 600.0;

		if (isCool()) {
			System.out.println("fin refroidissage");
			addMessageToUser("refroidissement terminé");
			repaint();
		}

	}



	public void doReceipt() {
		disableButtons();
		System.out.println("Receipt created");
		int rendu = doRendu();
		if (rendu > 0)
			setMessageToUser("Transaction effectuée, récupérez votre monnaie <br> Rendu : " + rendu / 100.0 + "€");
		else setMessageToUser("Transaction effectuée");
		cagnote = 0;
		int size = sizeSlider.getValue();
		System.out.println("size = " + sizeSlider.getValue());
		defineDelayPoor(size);
		System.out.println("poor delay = " + poorDelay);
	}

	private int doRendu() {
		switch (selection) {
			case "Coffee":
				return cagnote - montant;
			case "Expresso":
				return cagnote - montant;
			case "Tea":
				return cagnote - montant;
			case "Soup":
				return cagnote - montant;
			case "Iced Tea":
				return cagnote - montant;
		}
		return cagnote;
	}

	public void doRestart() {
		theFSM.raiseAnyButton();
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/vide1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures.setIcon(new ImageIcon(myPicture));
		progressBarValue = 0;
		progressBar.setValue(progressBarValue);
		System.out.println("doRestart");
		cagnote = 0;
		selection = "";
		currentTemperature = 15;
		currentPoorDelay = 0;
		taken = false;
		poor = false;
		temperatureSlider.setValue(2);
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		reinitialiseSugarSlider();
		activateButtons();
		enableButtonCauseOfStock();
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doDosette() {
		stockCoffe -= 1;
		System.out.println("dosette");
		addMessageToUser("Ajout dosette");
	}

	public void doGrain() {
		stockExpresso -= 1;
		System.out.println("grain");
		addMessageToUser("Broyage des grains");
		TimerTask task = new TimerTask() {
			public void run() {
				addMessageToUser("Tassage des grains");
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L * displayTime;
		timer.schedule(task, delay);
		repaint();
	}

	public void doSachet() {
		stockTea -= 1;
		System.out.println("sachet");
		addMessageToUser("Préparation du thé");
		TimerTask task = new TimerTask() {
			public void run() {
				addMessageToUser("Immersion du thé");
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L * displayTime;
		timer.schedule(task, delay);
		repaint();
	}

	public void doSoupDose() {
		stockSoup -= 1;
		System.out.println("dosette");
		addMessageToUser("Ajout dosette");
	}


	public void doIcedTeaSachet() {
		stockIcedTea -= 1;
		System.out.println("IcedTea sachet");
		addMessageToUser("Préparation du thé glacé");
		TimerTask task = new TimerTask() {
			public void run() {
				addMessageToUser("Immersion du thé");
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L * displayTime;
		timer.schedule(task, delay);
		repaint();
	}

	public void doGobelet() {
		if (!cupAdded) {
			System.out.println("gobelet");
			addMessageToUser("Positionnement du gobelet");
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/gobelet1.jpg"));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			labelForPictures.setIcon(new ImageIcon(myPicture));
			taken = false;
		}

	}

	public void doMilk() {
		System.out.println("milk");
		addMessageToUser("Ajout lait");
	}

	public void doGlace() {
		System.out.println("glace");
		addMessageToUser("Ajout glace");
	}

	public void doErable() {
		addMessageToUser(String.format("Ajout de %d dose%s de sirop d'erable", sugarSlider.getValue(), (sugarSlider.getValue() > 1 ? "s" : "")));
		System.out.println("do erable : " + sugarSlider.getValue());
	}

	public void doCrouton() {
		//TODO controle à mettre dans la FSM... en attendant :
		if(croutonButton.isSelected()) {
			System.out.println("croutons");
			addMessageToUser("Ajout croutons");
		}
	}

	public void doPoor() {
		BufferedImage pooringPicture = null;
		try {
			if (!cupAdded)
				pooringPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/pooring1.jpg"));
			else
				pooringPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/pooringOwnCup1.jpg"));
		} catch (IOException ee) {
			ee.printStackTrace();
		}

		if(currentTemperatureChange){
			System.out.println("début versage");
			addMessageToUser("Début du versage de la boisson");
			repaint();
			labelForPictures.setIcon(new ImageIcon(pooringPicture));
			currentTemperatureChange = false;
		}

		currentPoorDelay +=  500;

		if (isPoor()) {
			BufferedImage finishPicture = null;
			try {
				if (!cupAdded)
					finishPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/gobelet1.jpg"));
				else
					finishPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/ownCup1.jpg"));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			labelForPictures.setIcon(new ImageIcon(finishPicture));
			System.out.println("fin de versage");
			setMessageToUser("Versage terminé");
			repaint();
		}
	}


	public void doRetake() {
		System.out.println("retrait du sachet");
	}

	public void doInfuse() {
		System.out.println("infusion");
		addMessageToUser("infusion en cours");
		repaint();
	}

	public void doFinish() {
		addMessageToUser("C'est prêt !");
		labelForPictures.setEnabled(true);
	}

	public void doWash() {
		setMessageToUser("Washing in progress");
		System.out.println("Washing");
	}
	//------------------------------------------------------METHOD DO----------------------------------------------------------------//
	//--------------------------------------------------------OTHERS----------------------------------------------------------------//


	public String cagnote() {
		return cagnote / 100.0 + "€";
	}

	/**
	 * désactive les boutons lors de la préparation
	 */
	private void disableButtons() {
		cancelButton.setEnabled(false);
		nfcBiiiipButton.setEnabled(false);
		icedTeaButton.setEnabled(false);
		money50centsButton.setEnabled(false);
		money25centsButton.setEnabled(false);
		money10centsButton.setEnabled(false);
		temperatureSlider.setEnabled(false);
		sugarSlider.setEnabled(false);
		sizeSlider.setEnabled(false);
		coffeeButton.setEnabled(false);
		expressoButton.setEnabled(false);
		teaButton.setEnabled(false);
		soupButton.setEnabled(false);
		addCupButton.setEnabled(false);
		temperatureSlider.setEnabled(false);
		sugarSlider.setEnabled(false);
		sizeSlider.setEnabled(false);
		labelForPictures.setEnabled(false);
		milkButton.setEnabled(false);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(false);
		glaceVanilleButton.setEnabled(false);
	}

	/**
	 * réactive les boutons après la préparation
	 */
	private void activateButtons() {
		cancelButton.setEnabled(true);
		nfcBiiiipButton.setEnabled(true);
		icedTeaButton.setEnabled(true);
		money50centsButton.setEnabled(true);
		money25centsButton.setEnabled(true);
		money10centsButton.setEnabled(true);
		temperatureSlider.setEnabled(true);
		sugarSlider.setEnabled(true);
		sizeSlider.setEnabled(true);
		coffeeButton.setEnabled(true);
		expressoButton.setEnabled(true);
		teaButton.setEnabled(true);
		soupButton.setEnabled(true);
		addCupButton.setEnabled(true);
		temperatureSlider.setEnabled(true);
		sugarSlider.setEnabled(true);
		sizeSlider.setEnabled(true);
		milkButton.setEnabled(false);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(false);
		glaceVanilleButton.setEnabled(false);
		milkButton.setSelected(false);
		croutonButton.setSelected(false);
		siropErableButton.setSelected(false);
		glaceVanilleButton.setSelected(false);
	}


	public String getSelection() {
		return selection;
	}


	private void defineDelayPoor(int size) {
		if (size == 0)
			poorDelay = 2000;
		if (size == 1)
			poorDelay = 2500;
		if (size == 2)
			poorDelay = 3000;
	}


	public void createSpicesSlider() {
		lblSugar.setText("Spices");
	}

	public void reinitialiseSugarSlider() {
		lblSugar.setText("Sugar");
	}

	public void changeTemperatureSliderForIcedTea() {
		temperatureSlider.setValue(3);
		sizeSlider.setMaximum(1);
		sizeSlider.setValue(0);
		temperatureTable = new Hashtable<>();
		temperatureTable.put(0, new JLabel("02°C"));
		temperatureTable.put(1, new JLabel("05°C"));
		temperatureTable.put(2, new JLabel("07°C"));
		temperatureTable.put(3, new JLabel("12°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);

	}

	public void reinitialiseTemperatureSlider() {
		temperatureSlider.setValue(2);
		sizeSlider.setMaximum(2);
		sizeSlider.setValue(1);
		temperatureTable = new Hashtable<>();
		temperatureTable.put(0, new JLabel("20°C"));
		temperatureTable.put(1, new JLabel("35°C"));
		temperatureTable.put(2, new JLabel("60°C"));
		temperatureTable.put(3, new JLabel("85°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);
	}


	public void enableButtonCauseOfStock() {
		if (stockSoup <= 0) {
			soupButton.setEnabled(false);
		}
		if (stockCoffe <= 0) {
			coffeeButton.setEnabled(false);
		}
		if (stockExpresso <= 0) {
			expressoButton.setEnabled(false);
		}
		if (stockIcedTea <= 0) {
			icedTeaButton.setEnabled(false);
		}
		if (stockTea <= 0) {
			teaButton.setEnabled(false);
		}
	}

//---------------------------------------------------OTHERS----------------------------------------------------------------//

	// TODO: 06/11/2020 Corriger Soup et Iced Tea Bugs
	// TODO: 06/11/2020 gerer stock
	// TODO: 06/11/2020 programme de fidelité (creer la classe client avec un id et une liste de ces achats)
	//Optionnel
	// TODO: 06/11/2020 nouvelle gestion de la progress bar
	// TODO: 11/11/2020 affichage prompteur dans sur la machine
	// TODO: 06/11/2020 ajout des sons et du popcorn

}
