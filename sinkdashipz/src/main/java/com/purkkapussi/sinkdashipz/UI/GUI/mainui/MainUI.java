
package com.purkkapussi.sinkdashipz.UI.GUI.mainui;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class provides the main game functionality for the GUI including aiming and
 * showing the players ships
 *
 * @author ile
 */
public class MainUI extends JPanel {

    private final int gameBoardSize;
    protected JPanel mainHolder;
    protected JPanel aimHolder;
    protected JPanel playerShipHolder;

    private final int buttonWidth = 40;
    private final int buttonHeight = 40;
    private final int fontSize = 7;

    public MainUI(GUI gui) {
        gameBoardSize = gui.getGameBoardSideLenght();
        playerShipHolder = new JPanel(new GridLayout(gameBoardSize, gameBoardSize));
        aimHolder = new JPanel(new GridLayout(gameBoardSize, gameBoardSize));
        mainHolder = new JPanel(new GridLayout(1, 2));
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(810, 400));
    }

    /**
     * method creates a new MainUI on the given graphical user interface
     *
     * @param gui GUI to be used
     */
    public void createMainUI(GUI gui) {
        updateAimButtons(gui);
        updatePlayerShipLabels(gui);
        mainHolder.add(aimHolder);
        mainHolder.add(playerShipHolder);
        this.add(mainHolder);
    }

    //MAIN UPDATE METHOD
    /**
     * Method updates the buttons and labels on the mainUI.
     *
     * @param gui GUI to be used
     */
    public void updateMainUI(GUI gui) {
        aimHolder.removeAll();
        playerShipHolder.removeAll();
        updateAimButtons(gui);
        updatePlayerShipLabels(gui);
    }

    //SECONDARY UPDATE METHODS
    /**
     * Method to update the aimButtons on the main UI
     *
     * @param gui main graphical user interface
     */
    public void updateAimButtons(GUI gui) {
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                //pelaajan ampumisnappulat
                JButton aimButton = createAimButton();
                Location loc = new Location(i, j);
                AimListener aimListener = new AimListener(gui, loc);
                if (loc.equals(gui.targetLocation())) {
                    markAimLocation(aimButton);
                }
                if (gui.getPlayerHits().contains(new Location(i, j))) {
                    changeButtonToMissed(aimButton);
                    if (gui.initialAIShipLocs().contains(new Location(i, j))) {
                        changeButtonToHit(aimButton);
                    }
                }
                aimButton.addActionListener(aimListener);
                aimHolder.add(aimButton);
            }
        }
    }

    /**
     * Method to update the container which contains the players ships.
     *
     * @param gui main graphical user interface
     */
    public void updatePlayerShipLabels(GUI gui) {
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                JLabel playerShipLabel = createPlayerShipLabel();

                if (gui.initialPlayerShipLocs().contains(new Location(i, j))) {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        setPlayerShipLabelToHit(playerShipLabel);
                    } else {
                        setPlayerShipLabelToShip(playerShipLabel);
                    }
                } else {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        setPlayerShipLabelToAIMiss(playerShipLabel);
                    } else {
                        setPlayerShipLabelToSea(playerShipLabel);
                    }
                }
                playerShipHolder.add(playerShipLabel);
            }
        }
    }

    /**
     * Method to update the aimButtons when the game has ended
     *
     * @param gui main graphical user interface
     */
    public void updateAimButtonsEndGame(GUI gui) {
        aimHolder.removeAll();
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                //pelaajan ampumisnappulat
                JButton aimButton = createAimButton();
                Location loc = new Location(i, j);
                if (gui.getPlayerHits().contains(new Location(i, j))) {
                    changeButtonToMissed(aimButton);
                    if (gui.initialAIShipLocs().contains(new Location(i, j))) {
                        changeButtonToHit(aimButton);
                    }
                } else if (gui.initialAIShipLocs().contains(new Location(i, j))) {
                    changeButtonToShip(aimButton);
                } else {
                    changeButtonToSea(aimButton);
                }
                aimButton.setEnabled(false);
                aimHolder.add(aimButton);
            }
        }
    }

    //END UPDATE METHODS
    //PLAYERSHIPLABEL CREATION AND MODIFICATION:
    /**
     * Method returns a new JLabel used indicate players ships, sea and AI's
     * hits and misses. Labels will be updated with icons in the future
     *
     * @return new Label with the defined appearance
     */
    public JLabel createPlayerShipLabel() {
        JLabel playerShipLabel = new JLabel();
        playerShipLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
        playerShipLabel.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        return playerShipLabel;
    }

    /**
     * Method changes the given Label to indicate AI hit on a players ship.
     *
     * @param playerShipLabel label to change
     */
    public void setPlayerShipLabelToHit(JLabel playerShipLabel) {
        URL imgURL = this.getClass().getResource("/img/playershiphitpic.png");
        playerShipLabel.setIcon(new ImageIcon(imgURL));
        playerShipLabel.setBackground(Color.ORANGE);
        playerShipLabel.setOpaque(true);
    }

    /**
     * Method updates the icon of the given label to indicate (undamaged) player ship
     *
     * @param playerShipLabel label to change
     */
    public void setPlayerShipLabelToShip(JLabel playerShipLabel) {
        URL imgURL = this.getClass().getResource("/img/playershippic.png");
        playerShipLabel.setIcon(new ImageIcon(imgURL));
        playerShipLabel.setBackground(Color.BLACK);
        playerShipLabel.setOpaque(true);
    }

    /**
     * Method updates the icon of the given label to indicate location on which the AI tried
     * to shoot but missed.
     *
     * @param playerShipLabel label to change
     */
    public void setPlayerShipLabelToAIMiss(JLabel playerShipLabel) {
        URL imgURL = this.getClass().getResource("/img/aimisspic.png");
        playerShipLabel.setIcon(new ImageIcon(imgURL));
        playerShipLabel.setForeground(Color.BLACK);
        playerShipLabel.setBackground(Color.red);
        playerShipLabel.setOpaque(true);
    }

    /**
     * Method updates the icon of the given label to indicate "sea" (no ships or AI misses)
     *
     * @param playerShipLabel label to change
     */
    public void setPlayerShipLabelToSea(JLabel playerShipLabel) {
        URL imgURL = this.getClass().getResource("/img/seapic.png");
        playerShipLabel.setIcon(new ImageIcon(imgURL));
        playerShipLabel.setForeground(Color.WHITE);
        playerShipLabel.setBackground(Color.BLUE);
        playerShipLabel.setOpaque(true);
    }
    //END PLAYERSHIPLABEL CREATION AND MODIFICATION

    // AIMBUTTON CREATION AND MODIFICATION:
    /**
     * Method returns a new JButton to be used in the aim grid.
     *
     * @return new JButton
     */
    public JButton createAimButton() {
        JButton aimButton = new JButton();
        aimButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        aimButton.setFont(new Font("Arial", Font.PLAIN, fontSize));
        aimButton.setOpaque(true);
        aimButton.setBackground(Color.DARK_GRAY);
        aimButton.setForeground(Color.red);
        aimButton.setToolTipText("Use these buttons to mark a target for your shot.");
        return aimButton;
    }

    /**
     * Method marks the selected button with and X to indicate current target.
     *
     * @param aimButton button to change
     */
    public void markAimLocation(JButton aimButton) {
        aimButton.setText("X");
    }

    /**
     * Method marks the selected button as missed shot.
     *
     * @param aimButton button to change
     */
    public void changeButtonToMissed(JButton aimButton) {
        aimButton.setEnabled(false);

        URL imgURL = this.getClass().getResource("/img/seamiss.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));

    }
        public void changeButtonToSea(JButton aimButton) {
        aimButton.setEnabled(false);

        URL imgURL = this.getClass().getResource("/img/seapic2.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));

    }

    /**
     * Method marks the selected button as successful shot
     *
     * @param aimButton button to change
     */
    public void changeButtonToHit(JButton aimButton) {
        URL imgURL = this.getClass().getResource("/img/playershiphitpic.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
        aimButton.setOpaque(true);
    }
    //END AIMBUTTON CREATION AND MODIFICATION

    //METHOD NOT YET IMPLEMENTED
    /*
     public void createImages() {
     shipUrl = getClass().getResource("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/ship.png");
     seaUrl = getClass().getResource("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/ship.png");
     try {
     shipImage = ImageIO.read(new File("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/ship.png"));

     } catch (IOException e) {
     }
     try {
     seaImage = ImageIO.read(new File("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/sea.png"));
     } catch (IOException e) {
     }
     }
     */
    private void changeButtonToShip(JButton aimButton) {
        URL imgURL = this.getClass().getResource("/img/playershippic.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
    }
}
