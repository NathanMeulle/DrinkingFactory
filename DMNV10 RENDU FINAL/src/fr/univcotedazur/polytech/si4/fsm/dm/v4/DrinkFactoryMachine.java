package fr.univcotedazur.polytech.si4.fsm.dm.v4;


import dmnv10.TimerService;
import dmnv10.defaultsm.DefaultSMStatemachine;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Timer;
import java.util.*;


public class DrinkFactoryMachine extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPanel;
	private JLabel messagesToUser;
	JTextField id;

	JProgressBar progressBar = new JProgressBar();
	protected DefaultSMStatemachine theFSM; // Declaration de la stateMAchine
	private List<Person> persons = new ArrayList<>();

	private int cagnote = 0;
	private int coffePrice = 35;
	private int expressoPrice = 50;
	private int teaPrice = 40;
	private int soupPrice = 75;
	private int icedTeaPrice = 50;
	private int montant;

	private int stockCoffe = 15; // nombre de dosette de cafee
	private int stockTea = 3; // nombre de sachet de the
	private int stockExpresso = 3; // nombre de packet de grain pour l expresso
	private int stockSoup = 10; // nombre de dose de soupe
	private int stockSugar = 25; // nombre de dose de sucre
	private int stockSpices = 25; // nombre de dose d epice
	private int stockErable = 10; // nombre de dose de sirop d erable
	private int stockMilk = 10 ; // nombre de dose de lait
	private int stockGlaceVanille = 10; // nombre de dose de glace vanille
	private int stockCrouton = 3; // nombre de dose de crouton

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
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		messagesToUser = new JLabel("<html>Hello ! La Drinking Machine est prête !");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 165, 175);
		contentPanel.add(messagesToUser);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPanel.add(lblCoins);

		coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.DARK_GRAY);
		coffeeButton.setBackground(Color.WHITE);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPanel.add(coffeeButton);

		expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.DARK_GRAY);
		expressoButton.setBackground(Color.WHITE);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPanel.add(expressoButton);

		teaButton = new JButton("Tea");
		teaButton.setForeground(Color.DARK_GRAY);
		teaButton.setBackground(Color.WHITE);
		teaButton.setBounds(12, 108, 96, 25);
		contentPanel.add(teaButton);

		soupButton = new JButton("Soup");
		soupButton.setForeground(Color.DARK_GRAY);
		soupButton.setBackground(Color.WHITE);
		soupButton.setBounds(12, 145, 96, 25);
		contentPanel.add(soupButton);

		icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.DARK_GRAY);
		icedTeaButton.setBackground(Color.WHITE);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPanel.add(icedTeaButton);


		JLabel optionLabel = new JLabel("<html>Options proposées :");
		optionLabel.setForeground(Color.WHITE);
		optionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		optionLabel.setVerticalAlignment(SwingConstants.TOP);
		optionLabel.setBackground(Color.WHITE);
		optionLabel.setBounds(12, 300, 165, 175);
		contentPanel.add(optionLabel);

		milkButton = new JCheckBox("Nuage de lait");
		milkButton.setForeground(Color.WHITE);
		milkButton.setBackground(Color.DARK_GRAY);
		milkButton.setBounds(45, 330, 120, 25);
		milkButton.setEnabled(false);
		contentPanel.add(milkButton);

		siropErableButton = new JCheckBox("Sirop d'érable");
		siropErableButton.setForeground(Color.WHITE);
		siropErableButton.setBackground(Color.DARK_GRAY);
		siropErableButton.setBounds(45, 367, 120, 25);
		siropErableButton.setEnabled(false);
		contentPanel.add(siropErableButton);

		glaceVanilleButton = new JCheckBox("Glace Vanille");
		glaceVanilleButton.setForeground(Color.WHITE);
		glaceVanilleButton.setBackground(Color.DARK_GRAY);
		glaceVanilleButton.setBounds(45, 404, 120, 25);
		glaceVanilleButton.setEnabled(false);
		contentPanel.add(glaceVanilleButton);

		croutonButton = new JCheckBox("Croutons");
		croutonButton.setForeground(Color.WHITE);
		croutonButton.setBackground(Color.DARK_GRAY);
		croutonButton.setBounds(45, 441, 120, 25);
		croutonButton.setEnabled(false);
		contentPanel.add(croutonButton);


		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setForeground(Color.blue);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		contentPanel.add(progressBar);

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
		contentPanel.add(sugarSlider);

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
		contentPanel.add(sizeSlider);

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

		contentPanel.add(temperatureSlider);

		lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(380, 34, 44, 15);
		contentPanel.add(lblSugar);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setBackground(Color.DARK_GRAY);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(380, 113, 44, 15);
		contentPanel.add(lblSize);

		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBackground(Color.DARK_GRAY);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(363, 173, 96, 15);
		contentPanel.add(lblTemperature);

		JPanel moneyPanel = new JPanel();
		moneyPanel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(moneyPanel);
		moneyPanel.setBounds(538, 25, 96, 97);
		contentPanel.add(moneyPanel);

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
		contentPanel.add(panel_1);

		nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.BLACK);
		nfcBiiiipButton.setBackground(Color.WHITE);
		panel_1.add(nfcBiiiipButton);

		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPanel.add(lblNfc);

		id = new JTextField(20);
		id.setFont(new Font("Cantarell", Font.LAYOUT_LEFT_TO_RIGHT, 10));
		id.setDocument(new JTextFieldLimit(16));
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(531, 195, 110, 28);
		contentPanel.add(id, BorderLayout.SOUTH);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPanel.add(separator);

		addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.BLACK);
		addCupButton.setBackground(Color.WHITE);
		addCupButton.setBounds(505, 336, 96, 25);
		contentPanel.add(addCupButton);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/picts/vide1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures = new JButton(new ImageIcon(myPicture));
		labelForPictures.setBounds(175, 319, 286, 260);
		contentPanel.add(labelForPictures);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 227, 96, 33);
		contentPanel.add(panel_2);

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
			if (id.getText().equals("")) {
				setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
				addMessageToUser("<b>Numéro CB Manquant<b>");
				id.setBackground(Color.ORANGE);
			} else if (id.getText().length() < 16) {
				setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
				addMessageToUser("<b>Numéro Incorrect<b>,<br> Renseignez les 16 chiffres de la carte");
				id.setBackground(Color.ORANGE);
			} else {
				id.setBackground(Color.WHITE);
				doNFC();
				theFSM.raiseBip();
				theFSM.raiseAnyButton();
			}

		});
		temperatureSlider.addChangeListener(e -> {
			sugarSlider.setEnabled(true);
			theFSM.raiseAnyButton();
		});
		sugarSlider.addChangeListener(e -> theFSM.raiseAnyButton());
		sizeSlider.addChangeListener(e -> theFSM.raiseAnyButton());
		milkButton.addActionListener(e -> theFSM.raiseAnyButton());
		siropErableButton.addActionListener(e -> theFSM.raiseAnyButton());
		glaceVanilleButton.addActionListener(e -> theFSM.raiseAnyButton());
		croutonButton.addActionListener(e -> theFSM.raiseAnyButton());


		// initialisation de la stateMachine
		disableButtonCauseOfStock();
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
		montant = getMontant();
		Person person = getPerson(id.getText());
		return montant - ((person != null && person.getAchats().size() >= 10) ? person.remise() : 0) <= cagnote;
	}

	private int getMontant() {
		int montant = 0;
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
				montant = icedTeaPrice + (siropErableButton.isSelected() ? 10 : 0) + sizeSlider.getValue() * 25;
				break;
		}
		return montant;
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


	public void doNFC() {
		int remise = 0;
		Person person = getPerson(id.getText());
		if (person != null && person.getAchats().size() >= 10) {
			remise = person.remise();
			person.clearAchats();
			System.out.println(String.format("Remise : %.2f€", remise / 100.0));
		}
		cagnote += getMontant();
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + (cagnote - remise) / 100.0);
		if (remise > 0) addMessageToUser(String.format("%.2f€ de remise !", remise / 100.0));
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
		unselectCheckbox();
		milkButton.setEnabled(true);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(true);
		reinitialiseSugarSlider();
		reinitialiseTemperatureSlider();
		disableOptionButtonCauseOfStock();
		disbleSugarSliderCauseOfStock();
		selection = "Coffee";
		System.out.println("Coffee selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doExpresso() {
		unselectCheckbox();
		milkButton.setEnabled(true);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(true);
		reinitialiseSugarSlider();
		reinitialiseTemperatureSlider();
		disableOptionButtonCauseOfStock();
		disbleSugarSliderCauseOfStock();
		selection = "Expresso";
		System.out.println("Expresso selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doTea() {
		unselectCheckbox();
		milkButton.setEnabled(true);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(false);
		reinitialiseSugarSlider();
		reinitialiseTemperatureSlider();
		disableOptionButtonCauseOfStock();
		disbleSugarSliderCauseOfStock();
		selection = "Tea";
		System.out.println("Tea selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}


	public void doIcedTea() {
		unselectCheckbox();
		milkButton.setEnabled(false);
		croutonButton.setEnabled(false);
		siropErableButton.setEnabled(true);
		glaceVanilleButton.setEnabled(false);
		reinitialiseSugarSlider();
		changeTemperatureSliderForIcedTea();
		disableOptionButtonCauseOfStock();
		disbleSugarSliderCauseOfStock();
		selection = "IcedTea";
		System.out.println("Iced Tea selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doSoup() {
		unselectCheckbox();
		milkButton.setEnabled(false);
		croutonButton.setEnabled(true);
		siropErableButton.setEnabled(false);
		glaceVanilleButton.setEnabled(false);
		disableOptionButtonCauseOfStock();
		createSpicesSlider();
		reinitialiseTemperatureSlider();
		if (stockSpices <= 0) {
			sugarSlider.setEnabled(false);
			sugarSlider.setValue(0);
		}
		else {
			sugarSlider.setEnabled(true);
		}
		selection = "Soup";
		System.out.println("Soup selected");
		setMessageToUser("Selection : " + selection + "<br>" + "Montant inséré : " + cagnote());
		repaint();
	}

	public void doSugar() {
		addMessageToUser(String.format("Ajout de %d sucre", sugarSlider.getValue()) + (sugarSlider.getValue() > 1 ? "s" : ""));
		System.out.println("do sugar : " + sugarSlider.getValue());
		stockSugar -= sugarSlider.getValue();
	}

	public void doSpices() {
		addMessageToUser(String.format("Ajout de %d dose%s d'épice", sugarSlider.getValue(), sugarSlider.getValue() > 1 ? "s" : ""));
		System.out.println("do spices : " + sugarSlider.getValue());
		stockSpices -= sugarSlider.getValue();
	}


	public void doHeat() {
		int wantedTemperature = Integer.parseInt(temperatureTable.get(temperatureSlider.getValue()).getText().substring(0, 2));
		//System.out.println("temperature position : " + temperatureSlider.getValue() + " ie " + wantedTemperature + "°C");

		if (!currentTemperatureChange) {
			delay = (long) (1000 * (wantedTemperature - currentTemperature) / 20);
			startProgressBar(delay, wantedTemperature);
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

		if (!currentTemperatureChange) {
			delay = (long) (1000 * (currentTemperature - wantedTemperature) / 5);
			startProgressBar(delay, wantedTemperature);
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
		if (!id.getText().equals("")) {
			if (getPerson(id.getText()) == null) {
				try {
					persons.add(new Person(Encryption.toHexString(Encryption.getSHA(id.getText())), montant));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			} else {
				Person person = getPerson(id.getText());
				person.addAchat(montant);
			}
		}
		disableButtons();
		System.out.println("Receipt created");
		if (montant < 0) montant = 0;
		int rendu = doRendu();
		if (rendu > 0)
			addMessageToUser("Transaction effectuée, récupérez votre monnaie <br> Rendu : " + rendu / 100.0 + "€");
		else addMessageToUser("Transaction effectuée");
		cagnote = 0;
		int size = sizeSlider.getValue();
		System.out.println("size = " + sizeSlider.getValue());
		defineDelayPoor(size);
		System.out.println("poor delay = " + poorDelay);
		id.setText("");
	}

	private void startProgressBar(double delay, double wantedTemperature) {
		long duree = 0;
		if (selection.contains("Tea")) duree += 10;

		double totalTimeHeating = Math.abs((wantedTemperature - currentTemperature)) / (delay / 600.0);
		double totalTimePooring = (poorDelay * 0.5) / 500;
		totalTimePooring += (milkButton.isSelected())? 1 : 0;
		totalTimePooring += (glaceVanilleButton.isSelected())? 1 : 0;
		totalTimePooring = (croutonButton.isSelected() && totalTimePooring < 3) ? 3 : totalTimePooring;


		duree += totalTimeHeating;
		duree += totalTimePooring;

		long finalDuree = duree * 1000;
		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				progressBarValue += 1.0;
				progressBar.setValue(progressBarValue);
				repaint();
				if (progressBarValue >= 100) {
					cancel();
				}
			}
		};
		Timer timer = new Timer("TimerProgressBar");
		timer.scheduleAtFixedRate(repeatedTask, 0, finalDuree / 100);
		repaint();

	}

	private int doRendu() {
		return cagnote - montant;
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
		montant = 0;
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
		disableButtonCauseOfStock();
		disbleSugarSliderCauseOfStock();
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
		stockTea -= 1;
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
		stockMilk -= 1;
	}

	public void doGlace() {
		System.out.println("glace");
		addMessageToUser("Ajout glace");
		stockGlaceVanille -= 1;
	}

	public void doErable() {
		addMessageToUser(String.format("Ajout de %d dose%s de sirop d'erable", sugarSlider.getValue(), (sugarSlider.getValue() > 1 ? "s" : "")));
		System.out.println("do erable : " + sugarSlider.getValue());
		stockErable -= sugarSlider.getValue();

	}

	public void doCrouton() {
		if (croutonButton.isSelected()) {
			System.out.println("croutons");
			addMessageToUser("Ajout croutons");
			stockCrouton -= 1;
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

		if (currentTemperatureChange) {
			System.out.println("début versage");
			addMessageToUser("Début du versage de la boisson");
			repaint();
			labelForPictures.setIcon(new ImageIcon(pooringPicture));
			currentTemperatureChange = false;
		}

		currentPoorDelay += 500;

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
		long delay = 5000;
		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				progressBarValue-= 1.0;
				progressBar.setValue(progressBarValue);
				repaint();
				if(progressBarValue<=0){
					cancel();
				}
			}
		};
		Timer timer = new Timer("TimerProgressBar");
		timer.scheduleAtFixedRate(repeatedTask, 0, delay/100);
		repaint();



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
		id.setEnabled(false);
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
		id.setEnabled(true);
		unselectCheckbox();
		disableOptionButtonCauseOfStock();
		disableButtonCauseOfStock();
	}

	private void unselectCheckbox() {
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
			poorDelay = 6000;
		if (size == 1)
			poorDelay = 10000;
		if (size == 2)
			poorDelay = 16000;
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


	public void disableButtonCauseOfStock() {
		if (stockSoup <= 0) {
			soupButton.setEnabled(false);
		}
		if (stockCoffe <= 0) {
			coffeeButton.setEnabled(false);
		}
		if (stockExpresso <= 0) {
			expressoButton.setEnabled(false);
		}
		if (stockTea <= 0) {
			teaButton.setEnabled(false);
			icedTeaButton.setEnabled(false);
		}
	}

	public void disableOptionButtonCauseOfStock() {
		if (stockCrouton <= 0) {
			croutonButton.setSelected(false);
			croutonButton.setEnabled(false);
		}
		if (stockGlaceVanille <= 0) {
			glaceVanilleButton.setSelected(false);
			glaceVanilleButton.setEnabled(false);
		}
		if (stockMilk <= 0) {
			milkButton.setSelected(false);
			milkButton.setEnabled(false);
		}
		if (stockErable <= 0) {
			siropErableButton.setSelected(false);
			siropErableButton.setEnabled(false);
		}
	}

	public void disbleSugarSliderCauseOfStock(){
		if (stockSugar <= 0) {
			sugarSlider.setValue(0);
			sugarSlider.setEnabled(false);
		}
		else sugarSlider.setEnabled(true);

	}

	public Person getPerson(String id) {
		if (id.equals("")) return null;
		for (Person person : persons) {
			try {
				if (person.getId().equals(Encryption.toHexString(Encryption.getSHA(id))))
					return person;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}


	//---------------------------------------------------OTHERS----------------------------------------------------------------//

	//Optionnel
	// TODO: 06/11/2020 ajout du popcorn

}
