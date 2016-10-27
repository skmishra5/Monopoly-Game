package org.cs414.a4source;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.*;

public class BiddingWindow extends JFrame {
	private final JFrame frame;
	Map<String, Integer> playerBids = new ConcurrentHashMap<>();
	private int counter;
	Timer timer;
	private Player winner_final;

	public BiddingWindow(ArrayList<Player> playerList, Property biddingProperty) {
		frame = new JFrame("Bidding");
		frame.setSize(700, 700);
		frame.setLocation(700, 200);
		// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		addButtonstoFrame(mainPanel, playerList);
		frame.add(mainPanel, "West");
		JPanel timePane = new JPanel();
		JLabel timerLabel = new JLabel("");
		timePane.add(timerLabel);
		frame.add(timePane);
		counter = 30;
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerLabel.setText("Time Remaining: " + counter--);
				if (counter < 0) {
					JOptionPane.showMessageDialog(frame, "Time up");
					timer.removeActionListener(this);
					int maxBid = (int) Double.MIN_VALUE;
					String winner = "";
					if (!playerBids.isEmpty()) {
						for (String temp : playerBids.keySet()) {
							if (playerBids.get(temp) > maxBid) {
								maxBid = playerBids.get(temp);
								winner = temp;
							}
						}
						JOptionPane.showMessageDialog(frame,
								"Winner: " + winner + "  Bid Amount: $" + String.valueOf(maxBid));
						if (winner.equals("Player1")) {
							playerList.get(0).deductCash(maxBid);
							playerList.get(0).addProperty(biddingProperty);
							biddingProperty.setPlayer(playerList.get(0));
							biddingProperty.setPropertyOwned(true);
							winner_final = playerList.get(0);
						} else if (winner.equals("Player2")) {
							playerList.get(1).deductCash(maxBid);
							playerList.get(1).addProperty(biddingProperty);
							biddingProperty.setPlayer(playerList.get(1));
							biddingProperty.setPropertyOwned(true);
							winner_final = playerList.get(1);
						} else if (winner.equals("Player3")) {
							playerList.get(2).deductCash(maxBid);
							playerList.get(2).addProperty(biddingProperty);
							biddingProperty.setPlayer(playerList.get(2));
							biddingProperty.setPropertyOwned(true);
							winner_final = playerList.get(2);
						} else {
							playerList.get(3).deductCash(maxBid);
							playerList.get(3).addProperty(biddingProperty);
							biddingProperty.setPlayer(playerList.get(3));
							biddingProperty.setPropertyOwned(true);
							winner_final = playerList.get(3);
						}
						frame.dispose();

					} else {
						JOptionPane.showMessageDialog(frame, "Bidding unsuccessful");
						 frame.dispose();
					}
				}
			}
		};

		timer = new Timer(1000, taskPerformer);
		timer.start();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void addButtonstoFrame(JPanel playerButtons, ArrayList<Player> playerList) {
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(8, 8, 100, 100));
		playerButtons.add(firstPanel, "West");

		JButton buttonPlayer1 = new JButton("Player1");
		JButton buttonPlayer2 = new JButton("Player2");
		JButton buttonPlayer3 = new JButton("Player3");
		JButton buttonPlayer4 = new JButton("Player4");

		buttonPlayer1.setPreferredSize(new Dimension(100, 50));
		buttonPlayer2.setPreferredSize(new Dimension(100, 50));
		buttonPlayer3.setPreferredSize(new Dimension(100, 50));
		buttonPlayer4.setPreferredSize(new Dimension(100, 50));

		firstPanel.add(buttonPlayer1);
		firstPanel.add(buttonPlayer2);
		firstPanel.add(buttonPlayer3);
		firstPanel.add(buttonPlayer4);

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(8, 8, 100, 100));
		JLabel player1Bid = new JLabel("Did not bid");
		JLabel player2Bid = new JLabel("Did not bid");
		JLabel player3Bid = new JLabel("Did not bid");
		JLabel player4Bid = new JLabel("Did not bid");

		player1Bid.setPreferredSize(new Dimension(100, 50));
		player2Bid.setPreferredSize(new Dimension(100, 50));
		player3Bid.setPreferredSize(new Dimension(100, 50));
		player4Bid.setPreferredSize(new Dimension(100, 50));
		labelPanel.add(player1Bid);
		labelPanel.add(player2Bid);
		labelPanel.add(player3Bid);
		labelPanel.add(player4Bid);
		playerButtons.add(labelPanel, "East");

		if (playerList.size() == 3) {
			buttonPlayer4.setVisible(false);
			player4Bid.setVisible(false);
		} else if (playerList.size() == 2) {
			buttonPlayer3.setVisible(false);
			buttonPlayer4.setVisible(false);
			player3Bid.setVisible(false);
			player4Bid.setVisible(false);
		}

		buttonPlayer1.addActionListener(new ActionListener() {
			int click = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				click++;
				if (click >= 4) {
					JOptionPane.showMessageDialog(frame, "Only 3 attempts to bid allowed");
				} else {
					if (confirmAuction("Player1")) {
						int biddingPrice = getPrice("Player1", playerList.get(0), 0);
						if (biddingPrice != -1) {
							// bidPrice.add(biddingPrice);
							playerBids.put("Player1", biddingPrice);
							player1Bid.setText("$" + String.valueOf(biddingPrice));
						}

					}
				}
			}
		});

		buttonPlayer2.addActionListener(new ActionListener() {
			int click = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				click++;
				if (click >= 4) {
					JOptionPane.showMessageDialog(frame, "Only 3 attempts to bid allowed");
				} else {
					if (confirmAuction("Player2")) {
						int biddingPrice = getPrice("Player2", playerList.get(1), 0);
						if (biddingPrice != -1) {
							// bidPrice.add(biddingPrice);
							playerBids.put("Player2", biddingPrice);
							player2Bid.setText("$" + String.valueOf(biddingPrice));
						}

					}
				}
			}
		});

		buttonPlayer3.addActionListener(new ActionListener() {
			int click = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				click++;
				if (click >= 4) {
					JOptionPane.showMessageDialog(frame, "Only 3 attempts to bid allowed");
				} else {
					if (confirmAuction("Player3")) {
						int biddingPrice = getPrice("Player3", playerList.get(2), 0);
						if (biddingPrice != -1) {
							// bidPrice.add(biddingPrice);
							playerBids.put("Player3", biddingPrice);
							player3Bid.setText("$" + String.valueOf(biddingPrice));
						}

					}
				}
			}
		});

		buttonPlayer4.addActionListener(new ActionListener() {
			int click = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				click++;
				if (click >= 4) {
					JOptionPane.showMessageDialog(frame, "Only 3 attempts to bid allowed");
				} else {
					if (confirmAuction("Player4")) {
						int biddingPrice = getPrice("Player4", playerList.get(3), 0);
						if (biddingPrice != -1) {
							// bidPrice.add(biddingPrice);
							playerBids.put("Player4", biddingPrice);
							player4Bid.setText("$" + String.valueOf(biddingPrice));
						}

					}
				}
			}
		});
	}

	public boolean confirmAuction(String player) {
		Object[] options = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(frame, "Would you like to bid for the Property ?",
				player + " Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, // the
																													// titles
																													// of
																													// buttons
				options[0]); // default button title
		if (n == 0)
			return true;
		else
			return false;
	}

	public int getPrice(String player, Player mainplayer, int attempts) {
		if (attempts == 3) {
			return -1;
		} else {
			Object result = JOptionPane.showInputDialog(frame, "Enter price to bid:");
			int price = Integer.parseInt((String) result);
			if (mainplayer.getAvailCash() < price) {
				JOptionPane.showMessageDialog(frame, "Insufficient Balance. Please bid again");
				return -1;
			}
			int maxPrice = (int) Double.MIN_VALUE;
			for (String temp : playerBids.keySet()) {
				if (maxPrice < playerBids.get(temp))
					maxPrice = playerBids.get(temp);
			}
			if (price > maxPrice)
				return price;
			else {
				JOptionPane.showMessageDialog(frame,
						"Enter valid bid. Bidding amount should be higher than $" + maxPrice);
				return getPrice(player, mainplayer, ++attempts);
			}

		}
	}

	public Player getWinner() {
		return winner_final;
	}

}