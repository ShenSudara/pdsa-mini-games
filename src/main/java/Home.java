import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.TravelingSalesManController;
import services.TravelingSalesManService;
import views.TravelingSalesManView;

import views.TowerOfHanoiView; 
import controllers.TowerOfHanoiController; 
import services.TowerOfHanoiService;

import services.EightQueensService;
import services.KnightTourService;
import views.EightQueensView;
import views.KnightTourView;
import controllers.EightQueensController;
import controllers.KnightTourController;

import controllers.TicTacToeController;
import services.TicTacToeService;
import services.TicTacToeAI;
import views.TicTacToeView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//dependencies of the TicTacToe Game
	TicTacToeService ticTacToeService;
	TicTacToeAI ticTacToeAI;
	TicTacToeView ticTacToeView;
	TicTacToeController ticTacToeController;
	
	//dependencies of the application initialization
	TravelingSalesManService salesManService;
	TravelingSalesManView salesManView;
	TravelingSalesManController salesManController;
	
    // dependencies of the Tower of Hanoi game
    TowerOfHanoiView hanoiView;
    TowerOfHanoiController hanoiController;
    TowerOfHanoiService hanoiService;
    
    //dependencies of Eight Queens Puzzle Game
     EightQueensService eightQueensService;
     EightQueensView eightQueensView;
     EightQueensController eightQueensController;
     
    // dependencies of knight tour game
     KnightTourService knightTourService;
     KnightTourView knightTourView;
     KnightTourController knightTourController;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		// JFrame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(720, 480);
		setPreferredSize(new Dimension(720, 480));

		// initialize game shortcuts to UI
		String[][] gameInfo = {
				{ "ticTacToeIcon", "<html><p style='text-align: center'>Tic-Tac-Toe<br />Game</p></html>",
						"gameicons/tic-tac-toe.png" },
				{ "salesManIcon", "<html><p style='text-align: center'>Traveling Salesman<br />Game</p></html>",
						"gameicons/city-map.png" },
				{ "hanoiIcon", "<html><p style='text-align: center'>Tower of Hanoi<br />Game</p></html>",
						"gameicons/tower-of-hanoi.png" },
				{ "queensIcon", "<html><p style='text-align: center'>Eight Queens<br />Game</p></html>",
						"gameicons/eight-queens.png" },
				{ "knightsIcon", "<html><p style='text-align: center'>knight's tour<br />Game</p></html>",
						"gameicons/knight-tour.png" } };
		JLabel[] gameIcons = new JLabel[gameInfo.length];
		for (int i = 0; i < gameInfo.length; i++) {
			// game icon initialization
			Image icon = new ImageIcon(this.getClass().getResource(gameInfo[i][2])).getImage().getScaledInstance(30, 30,
					Image.SCALE_FAST);

			JPanel iconPane = new JPanel();
			iconPane.setLayout(new BorderLayout());
			iconPane.setSize(100, 100);
			iconPane.setPreferredSize(new Dimension(200, 200));
			iconPane.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20),
							BorderFactory.createLineBorder(Color.BLACK, 1)),
					BorderFactory.createEmptyBorder(15, 15, 15, 15)));
			iconPane.setName(gameInfo[i][0]);

			// game shortcut initialization
			gameIcons[i] = new JLabel(new ImageIcon(icon), JLabel.CENTER);
			gameIcons[i].setName(gameInfo[i][0]);

			JLabel gameText = new JLabel(gameInfo[i][1], JLabel.CENTER);
			gameText.setBorder(new EmptyBorder(10, 0, 10, 0));
			gameText.setName(gameInfo[i][0]);
			
			iconPane.add(gameIcons[i], BorderLayout.CENTER);
			iconPane.add(gameText, BorderLayout.SOUTH);
			
			// add mouse click listener for panel
			iconPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					switch (e.getComponent().getName()) {
					case "ticTacToeIcon":
						initializeDependencies();
						ticTacToeController.showView();
						break;
					case "salesManIcon":
						initializeDependencies();
						salesManController.showView();
						break;
					case "hanoiIcon":
						initializeDependencies();
						hanoiController.showView();
						break;
					case "queensIcon":
						initializeDependencies();
						eightQueensController.showView();
						break;
					case "knightsIcon":
						initializeDependencies();
						knightTourController.showView();
						break;
					default:
						JOptionPane.showMessageDialog(null, "game is not defined");
						break;
					}
					
				}
			});
			getContentPane().add(iconPane);
		}
		
		//method to initialize dependencies
		this.initializeDependencies();
	}
	
	/**
	 *  method for initialize dependencies
	 */
	private void initializeDependencies() {
		
		//TicTacToe dependencies
		this.ticTacToeService = new TicTacToeService();
		this.ticTacToeAI = new TicTacToeAI();
		this.ticTacToeView = new TicTacToeView();
		this.ticTacToeController = new TicTacToeController(ticTacToeView,ticTacToeService);
		
		//Traveling salesman dependencies
		this.salesManService = new TravelingSalesManService();
		this.salesManView = new TravelingSalesManView();
		this.salesManController = new TravelingSalesManController(salesManService, salesManView);
		
		
	    // Tower of Hanoi dependencies
	    this.hanoiView = new TowerOfHanoiView();
	    this.hanoiService = new TowerOfHanoiService();
	    this.hanoiController = new TowerOfHanoiController(hanoiService, hanoiView);
	    
	    // Eight Queens Puzzle dependencies
        this.eightQueensService = new EightQueensService();
        this.eightQueensView = new EightQueensView();
        this.eightQueensController = new EightQueensController(eightQueensView, eightQueensService);
        
        //initialize the knight tour game dependencies
        this.knightTourService = new KnightTourService();
        this.knightTourView = new KnightTourView();
        this.knightTourController = new KnightTourController(knightTourService, knightTourView);
	
	}

}
