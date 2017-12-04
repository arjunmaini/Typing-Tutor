package TypeTutor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {

	JLabel lblTimer;
	JLabel lblScore;
	JLabel lblWord;
	JTextArea txtarea;
	JButton btnStart;
	JButton btnStop;

	int score;
	int timeleft;
	boolean isRunning;
	Timer timer;
	String[] data;

	public TypingTutor(String feeder) {

		data = feeder.split(" ");

		super.setTitle("Typing Tutor");
		super.setSize(1000, 1000);

		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		lblTimer = new JLabel();
		this.addComponent(lblTimer);

		lblScore = new JLabel();
		this.addComponent(lblScore);

		lblWord = new JLabel();
		this.addComponent(lblWord);

		txtarea = new JTextArea();
		this.addComponent(txtarea);

		btnStart = new JButton();
		btnStart.addActionListener(this);
		btnStart.setForeground(Color.green);
		this.addComponent(btnStart);

		btnStop = new JButton();
		btnStop.addActionListener(this);
		btnStart.setForeground(Color.green);
		this.addComponent(btnStop);

		this.initGame();

		super.setVisible(true);
	}

	private void addComponent(JComponent comp) {
		Font font = new Font("Comic Sans MS", 1, 50);
		comp.setFont(font);
		super.add(comp);

	}

	private void initGame() {
		score = 0;
		timer = new Timer(3000, (ActionListener) this);
		timeleft = 50;
		isRunning = false;

		btnStop.setEnabled(false);
		lblScore.setText("Score : " + score);
		lblTimer.setText("Timer : " + timeleft);
		btnStart.setText("Start");
		btnStop.setText("Stop");
		lblWord.setText("");
		txtarea.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			this.handleStart();
		} else if (e.getSource() == btnStop) {
			this.handleStop();
		} else if (e.getSource() == timer) {
			this.handleTimer();
		}

	}

	private void handleStart() {
		if (isRunning) {

			timer.stop();
			isRunning = false;

			btnStart.setText("Resume");
		} else {
			timer.start();
			isRunning = true;

			btnStop.setEnabled(true);

			btnStart.setText("Pause");
		}
	}

	private void handleStop() {
		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "GameOver.Restart?");

		if (choice == JOptionPane.YES_OPTION) {
			initGame();
		} else {
			super.dispose();
		}
	}

	private void handleTimer() {
		if (timeleft > 0) {
			timeleft--;
			lblTimer.setText("Timer : " + timeleft);

			String word = lblWord.getText();
			String actual = txtarea.getText();
			if (word.equals(actual)) {
				score++;
				lblScore.setText("Score : " + score);
			}

			int idx = (int) (data.length * Math.random());
			lblWord.setText(data[idx]);

			txtarea.setText("");
			txtarea.setFocusable(true);

		} else {
			handleStop();
		}
	}

}
