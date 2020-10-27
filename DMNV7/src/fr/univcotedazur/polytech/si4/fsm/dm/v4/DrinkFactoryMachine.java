package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dmnv6.TimerService;
import dmnv6.defaultsm.DefaultSMStatemachine;


public class DrinkFactoryMachine extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private JLabel messagesToUser;
	protected DefaultSMStatemachine theFSM; // Declaration de la stateMAchine
    private int cagnote = 0;
    private int coffePrice = 35;
    private int expressoPrice = 50;
    private int teaPrice = 40;
	
	
	
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
		messagesToUser.setText("<html>"+str);
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

		messagesToUser = new JLabel("<html>Hello ! The Drinking Machine is ready !");
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

		JButton coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.DARK_GRAY);
		coffeeButton.setBackground(Color.WHITE);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPane.add(coffeeButton);

		JButton expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.DARK_GRAY);
		expressoButton.setBackground(Color.WHITE);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);

		JButton teaButton = new JButton("Tea");
		teaButton.setForeground(Color.DARK_GRAY);
		teaButton.setBackground(Color.WHITE);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);

		JButton soupButton = new JButton("Soup");
		soupButton.setForeground(Color.DARK_GRAY);
		soupButton.setBackground(Color.WHITE);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(70);               /// TODO: 27/10/2020  implementer ce set dans les autres methode pour montrer lavance de la preparation
		progressBar.setForeground(Color.blue);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		contentPane.add(progressBar);

		JSlider sugarSlider = new JSlider();
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

		JSlider sizeSlider = new JSlider();
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

		JSlider temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		temperatureSlider.setValue(2);
		temperatureSlider.setBackground(Color.DARK_GRAY);
		temperatureSlider.setForeground(Color.WHITE);
		temperatureSlider.setPaintTicks(true);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setMaximum(3);
		temperatureSlider.setBounds(301, 188, 200, 54);

		Hashtable<Integer, JLabel> temperatureTable = new Hashtable<Integer, JLabel>();
		temperatureTable.put(0, new JLabel("20°C"));
		temperatureTable.put(1, new JLabel("35°C"));
		temperatureTable.put(2, new JLabel("60°C"));
		temperatureTable.put(3, new JLabel("85°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);

		contentPane.add(temperatureSlider);

		JButton icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.DARK_GRAY);
		icedTeaButton.setBackground(Color.WHITE);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPane.add(icedTeaButton);

		JLabel lblSugar = new JLabel("Sugar");
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

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(panel);
		panel.setBounds(538, 25, 96, 97);
		contentPane.add(panel);

		JButton money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.BLACK);
		money50centsButton.setBackground(Color.WHITE);
		panel.add(money50centsButton);

		JButton money25centsButton = new JButton("0.25 €");
		money25centsButton.setForeground(Color.BLACK);
		money25centsButton.setBackground(Color.WHITE);
		panel.add(money25centsButton);

		JButton money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.BLACK);
		money10centsButton.setBackground(Color.WHITE);
		panel.add(money10centsButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 154, 96, 40);
		contentPane.add(panel_1);

		JButton nfcBiiiipButton = new JButton("biiip");
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

		JButton addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.BLACK);
		addCupButton.setBackground(Color.WHITE);
		addCupButton.setBounds(45, 336, 96, 25);
		contentPane.add(addCupButton);

		BufferedImage myPicture = null;
		try {
			System.out.println(System.getProperty("user.dir") );
			myPicture = ImageIO.read(new File(System.getProperty("user.dir") +"/src/picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel labelForPictures = new JLabel(new ImageIcon(myPicture));
		labelForPictures.setBounds(175, 319, 286, 260);
		contentPane.add(labelForPictures);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 217, 96, 33);
		contentPane.add(panel_2);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.WHITE);
		panel_2.add(cancelButton);

		// listeners
		addCupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BufferedImage myPicture = null;
				try {
					myPicture = ImageIO.read(new File(System.getProperty("user.dir") +"/src/picts/ownCup.jpg"));
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				labelForPictures.setIcon(new ImageIcon(myPicture));
			}
		});

		cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doCancel();
                theFSM.raiseCancel();
            }
        });

		coffeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doCoffee();
				theFSM.raiseCoffee();
				theFSM.raiseAnyButton();
			}
		});
		
		expressoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doExpresso();
				theFSM.raiseExpresso();
				theFSM.raiseAnyButton();

			}
		});
		teaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doTea();
				theFSM.raiseTea();
				theFSM.raiseAnyButton();

			}
		});
		soupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doSoup();
				theFSM.raiseSoup();
				theFSM.raiseAnyButton();

			}
		});
		icedTeaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doIcedTea();
				theFSM.raiseIcedTea();
				theFSM.raiseAnyButton();

			}
		});
		
		money50centsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    doCinqanteCents();
				theFSM.raiseCinquanteCents();
				theFSM.raiseAnyButton();

			}
		});
		
		money25centsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    doVingtCinqCents();
				theFSM.raiseVingtCinqCents();
				theFSM.raiseAnyButton();

			}
		});
		
		money10centsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    doDixCents();
				theFSM.raiseDixCents();
				theFSM.raiseAnyButton();

			}
		});		
		
		
		// initialisation de la stateMachine
				theFSM = new DefaultSMStatemachine(); 
				TimerService timer = new TimerService(); 
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

    private void doCancel() {
        System.out.println("doCancel");
        setMessageToUser("Commande annulé, veuillez récupérer vos " +cagnote());
        cagnote=0;
        repaint();
    }

    private void doCinqanteCents() {
        System.out.println("50 centimes ajoutés");
        cagnote+=50;
        setMessageToUser("50 centimes ajoutés, votre cagnote est de " +cagnote());
        repaint();
    }

    private void doVingtCinqCents() {
        System.out.println("20 centimes ajoutés");
        cagnote+=25;
        setMessageToUser("20 centimes ajoutés, votre cagnote est de " +cagnote());
        repaint();
    }

    private void doDixCents() {
        System.out.println("10 centimes ajoutés");
        cagnote+=10;
        setMessageToUser("10 centimes ajoutés, votre cagnote est de " +cagnote());
        repaint();
    }

    public void doCoffee() {
		System.out.println("Coffee selected");
		setMessageToUser("Coffee selected");
		repaint();
	}

	public void doExpresso() {
		System.out.println("Expresso selected");
		setMessageToUser("Expresso selected");
		repaint();		
	}

	public void doIcedTea() {
		System.out.println("Iced Tea selected");
		setMessageToUser("Iced Tea selected");
		repaint();			
	}
	
	public void doSoup() {
		System.out.println("Soup selected");
		setMessageToUser("Soup selected");
		repaint();			
	}

	public void doSugar() {
		// TODO Auto-generated method stub
		
	}

	public void doSelect() {
		// TODO Auto-generated method stub
		
	}

	public void doTea() {
		System.out.println("Tea selected");
		setMessageToUser("Tea selected");
		repaint();			
	}

	public void doPay() {
		// TODO Auto-generated method stub
		
	}

    public boolean isPay() {
        if (selection.equals("Coffee")&&(coffePrice<=cagnote)){return true;}
        if (selection.equals("Tea")&&(teaPrice<=cagnote)){return true;}
        if (selection.equals("Expresso")&&(expressoPrice<=cagnote)){return true;}
        return false;
    }

	public String cagnote(){
	    String tunes = cagnote/100.0 + "€";
	    return tunes;
    }


}
