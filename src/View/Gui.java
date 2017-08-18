package View;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.QuadCurve2D;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public  class Gui extends JFrame implements MouseListener {
	boolean node = false;
	private int[][] circles = new int[2400][3];// x coord+ y coord + deleted
	public static int startingNode;// nodes
	public int endingNode;
	public int noOfShape = 0;
	public  int noOfEdges = 0;
	public int noOfDeletedNode=0;
	public int deletedNode=-1;
	public static int[][] edges = new int[2400][10];// 3 points+from+to+value+deleted
	boolean edge = false;
	boolean delete = false;
	public static boolean calculateFlag=false;
	int click = 0;
	int tempnode1 = -1;
	int tempnode2 =-1;

	public Gui() {
		getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnNewButton_1 = new JButton("edge");
		splitPane.setRightComponent(btnNewButton_1);

		JButton btnNewButton = new JButton("node");
		splitPane.setLeftComponent(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				node = true;
				
				
				
			}
		});

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				edge = true;

			}
		});

		JSplitPane splitPane_2 = new JSplitPane();
		panel.add(splitPane_2);

		JButton btnNewButton_3 = new JButton("calculate");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				final JTextField textField;
				final JTextField textField_1;
				getContentPane().setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setForeground(Color.LIGHT_GRAY);
				panel.setBounds(550, 300, 293, 117);
				getContentPane().add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("enter starting node");
				lblNewLabel.setBounds(10, 20, 118, 23);
				panel.add(lblNewLabel);
				
				textField = new JTextField();
				textField.setBounds(10, 49, 106, 20);
				panel.add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("enter ending node");
				lblNewLabel_1.setBounds(156, 24, 118, 19);
				panel.add(lblNewLabel_1);
				
				textField_1 = new JTextField();
				textField_1.setBounds(156, 49, 106, 20);
				panel.add(textField_1);
				textField_1.setColumns(10);
				
				JButton btnNewButton = new JButton("calculate");
				btnNewButton.setBounds(102, 83, 89, 23);
				panel.add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String start=textField.getText();
						String end=textField_1.getText();
						if (isNumeric(start) && isNumeric(end)){
							int startToInt=Integer.valueOf(start);
							int endToInt=Integer.valueOf(end);
							if(startToInt < noOfShape && endToInt < noOfShape){
								startingNode=startToInt;
								endingNode=endToInt;
								calculateFlag=true;
								panel.removeAll();
								panel.setVisible(false);
								repaint();
								
								////////////////////////show result
							}
							else{
								JOptionPane.showMessageDialog(panel,
									    "enter a valid node number.",
									    "error",
									    JOptionPane.ERROR_MESSAGE);
										textField.setText("");
										textField_1.setText("");
							}
						
						
						}
						else{
							JOptionPane.showMessageDialog(panel,
								    "invalid paramenter.",
								    "Inane error",
								    JOptionPane.ERROR_MESSAGE);
									textField.setText("");
									textField_1.setText("");
						}
						
					}
					
					
				});
		
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.BLUE);
				panel_1.setBounds(0, 0, 293, 23);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JButton btnNewButton_1 = new JButton("New button");
				btnNewButton_1.setBackground(Color.BLUE);
				btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
				btnNewButton_1.setIcon(new ImageIcon(Gui.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
				btnNewButton_1.setBounds(240, 0, 53, 23);
				panel_1.add(btnNewButton_1);
				btnNewButton_1.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						 panel.removeAll();
						 panel.setVisible(false);
						 repaint();
						
					}
					
				});
			
				panel.setFocusable(true);
				panel.setVisible(true);
				revalidate();
				repaint();
				getContentPane().add(panel);
			
			}
		});

		JButton btnEndNode = new JButton("clear node");
		
		btnEndNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete = true;

			}
		});

		splitPane_2.setLeftComponent(btnEndNode);
		splitPane_2.setRightComponent(btnNewButton_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(0);
		panel_2.setBackground(Color.LIGHT_GRAY);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 10,
								GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE, 361,
												Short.MAX_VALUE)
										.addComponent(panel_1,
												GroupLayout.DEFAULT_SIZE, 363,
												Short.MAX_VALUE))
						.addGap(1)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										286, Short.MAX_VALUE)
								.addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_2, Alignment.TRAILING,
						GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 361,
						Short.MAX_VALUE));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 6));

		JLabel lblForwaredEdge = new JLabel("forwared edge :  ");
		lblForwaredEdge.setForeground(Color.BLUE);
		panel_1.add(lblForwaredEdge);

		JLabel lblNewLabel = new JLabel("black colered      ");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		panel_1.add(lblNewLabel);

		JLabel lblBackwaredEdge = new JLabel("backwared edge : ");
		lblBackwaredEdge.setForeground(Color.BLUE);
		panel_1.add(lblBackwaredEdge);

		JLabel lblNewLabel_1 = new JLabel("green colerd");
		lblNewLabel_1.setForeground(Color.GREEN);
		panel_1.add(lblNewLabel_1);
		getContentPane().setLayout(groupLayout);
		// -------------------------
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				
				PointerInfo a = MouseInfo.getPointerInfo();
				Point point = new Point(a.getLocation());
				SwingUtilities.convertPointFromScreen(point, me.getComponent());
				int x = (int) point.getX();
				int y = (int) point.getY();
				
				// ---------------------------------------------------------------
				if (edge == true) {
					
					if (click == 0) {
						edges[noOfEdges][0] = x;
						edges[noOfEdges][1] = y;
						click++;
						tempnode1=searchNode(x, y);
					} else if (click == 1) {
						
						edges[noOfEdges][2] = x;
						edges[noOfEdges][3] = y;
						click++;
						tempnode2=searchNode(x, y);

					} else {
						
						boolean endTouchfNode = false;
						boolean startTouchNode = false;
						click = 0;
						for (int i = 0; i < noOfShape; i++) {
							
						
							// check end pint 
							if(tempnode1 ==-1 || tempnode1==-1){
								edge=false;
							}
							if ((Math.abs(edges[noOfEdges][2] - circles[i][0]) < 70
									&& Math.abs(edges[noOfEdges][3]- circles[i][1]) < 70)  && circles[tempnode2][2]!=-1) {
								endTouchfNode = true;
								
								edge = false;
								// break;
							}
							if ((Math.abs(edges[noOfEdges][0] - circles[i][0]) < 70
									&& Math.abs(edges[noOfEdges][0]
											- circles[i][1]) < 70)  && circles[tempnode1][2]!=-1) {
								startTouchNode = true;
								
								edge = false;
								// break;
							}
							//
							
						}
						startTouchNode = true;
						if (endTouchfNode && startTouchNode) {
							edges[noOfEdges][4] = x;
							edges[noOfEdges][5] = y;
							noOfEdges++;
							click = 0;
							edge = false;
							endTouchfNode = false;
							startTouchNode = false;
							// wieght of edge
							int weight=getEdgeValue();
							if(weight==0){
								edges[noOfEdges - 1][9]=-1;
							}
							edges[noOfEdges - 1][8] = weight;
							if (edges[noOfEdges - 1][2] > edges[noOfEdges - 1][0]) {
								edges[noOfEdges - 1][6] = searchNode(
										edges[noOfEdges - 1][0],
										edges[noOfEdges - 1][1]);
								edges[noOfEdges - 1][7] = searchNode(
										edges[noOfEdges - 1][2],
										edges[noOfEdges - 1][3]);
							} else if (edges[noOfEdges - 1][2] < edges[noOfEdges - 1][0]) {
								edges[noOfEdges - 1][6] = searchNode(
										edges[noOfEdges - 1][0],
										edges[noOfEdges - 1][1]);
								edges[noOfEdges - 1][7] = searchNode(
										edges[noOfEdges - 1][2],
										edges[noOfEdges - 1][3]);
							}

						}

					}
				}
				// ----------------------------------------------------------------
				for (int i = 0; i < noOfShape; i++) {
					if ((Math.abs(x - circles[i][0]) < 50 
							&& Math.abs(y - circles[i][1]) < 50) && circles[i][2] !=-1) {
						node = false;
						break;
					}
				}

				if (node == true) {
					
					circles[noOfShape][0] = x;
					
					circles[noOfShape][1] = y;
					noOfShape++;
					repaint();
					node = false;

				}
				// ------
				// search for node to delete
				int deletedNode = searchNode(x, y);
			//	System.out.println("deleted node  "+deletedNode);
			//	System.out.println(edges[i][7]);
				// ------
				if (deletedNode == -1) {
					delete = false;
				} else if (delete == true) {
					circles[deletedNode][2] = -1;
					noOfDeletedNode++;
					
					for (int i = 0; i < noOfEdges; i++) {
						if ((edges[i][6] == deletedNode) || (edges[i][7] == deletedNode)) {
							edges[i][9] = -1;
						}
					}
					delete = false;

				}
				setFocusable(true);
				revalidate();
				repaint();

			}
		});

		// this.setFocusable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("file");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenu.setForeground(Color.BLUE);
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("new window");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				node = false;
				circles = new int[2400][3];
				noOfShape = 0;
				noOfEdges = 0;
				edges = new int[2400][10];
				edge = false;
				delete = false;
				click = 0;
				repaint();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
	}

	// -----------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------
	public void paint(Graphics g) {
		Graphics2D ga = (Graphics2D) g;
		// call superclass version of method paint
		super.paint(ga);
		// draw edges
		for (int i = 0; i < noOfEdges; i++) {
			if (edges[i][9] == -1) {
				continue;
			}
			if (edges[i][2] > edges[i][0]) {
				g.setColor(Color.BLACK);
				// edges[i][6]=searchNode(edges[i][0],edges[i][1]);
				// edges[i][7]=searchNode(edges[i][2],edges[i][3]);
			} else {
				g.setColor(Color.GREEN);
			}
			int x1 = edges[i][0];
			int x2 = edges[i][2];
			int y1 = edges[i][1];
			int y2 = edges[i][3];
			int x3 = edges[i][4];
			int y3 = edges[i][5];
		
			QuadCurve2D curve = new QuadCurve2D.Float(x1, y1, x2 - x1, y2 - y1,
					x2, y2);
			
			curve.setCurve(x1, y1, x3, y3, x2, y2);
			ga.draw(curve);
			ga.drawString(String.valueOf(edges[i][8]), x3, y3 + 20);
//			for (int z = 0; z < noOfEdges; z++) {
//				for (int j = 6; j < 10; j++) {
//					System.out.print(edges[z][j]);
//					System.out.print("  ");
//				}
//				System.out.println();
//			}
			// ------------------------------------------------------------------------------------------------------

		}
		// draw nodes
		for (int i = 0; i < noOfShape; i++) {

			if (circles[i][2] == -1) {
				continue;
			}
			ga.setColor(Color.red);
			ga.fillOval(circles[i][0], circles[i][1], 50, 50);
			ga.setColor(Color.blue);
			ga.drawString(String.valueOf(i), circles[i][0], circles[i][1]);
			
		}
	}

	// ----------------------------------------------------------------------------------------------------
	int searchNode(int x, int y) {
		

		for (int i = 0; i < noOfShape; i++) {
			if ((Math.abs(x - circles[i][0]) < 50
					&& Math.abs(y - circles[i][1]) < 50) && circles[i][2]!=-1) {
				return i;
			}
		}
		return -1;
	}

	// --------------------------------------------------------------------------------------------------------------
	int getEdgeValue() {
		String value = JOptionPane.showInputDialog(null,
				"enter the weight of this edge", JOptionPane.QUESTION_MESSAGE);
		int as;
		try{
		as=Integer.valueOf(value);
		}
		catch(Exception e){
			 as=0;
		}
		return as;

	}

	public  void runGui() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("unused")
				Gui my = new Gui();
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static boolean isNumeric(String str)
	{
		
	
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	
	
	}
	public void showresult(String str){
		JScrollPane scrollPane = new JScrollPane(new JLabel(str));
        scrollPane.setPreferredSize(new Dimension(500,200));
        Object message = scrollPane;
       
  
        JTextArea textArea = new JTextArea(str);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(5,5,5,5));
        scrollPane.getViewport().setView(textArea);
        message = scrollPane;
        JOptionPane.showConfirmDialog(null,
                                               message,
                                               "the transfere function",
                                               JOptionPane.INFORMATION_MESSAGE);
    
		//JOptionPane.showMessageDialog(null, str,   "result", JOptionPane.INFORMATION_MESSAGE);
	}
}

