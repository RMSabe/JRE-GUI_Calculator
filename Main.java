import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Main
{
	public static JFrame appWindow = new JFrame();
	public static JPanel screen = new JPanel();
	public static JLabel text = new JLabel();
	public static JTextField textBox = new JTextField();
	public static JButton button1 = new JButton();
	public static JButton button2 = new JButton();
	public static JButton button3 = new JButton();
	public static JButton button4 = new JButton();
	public static JButton button5 = new JButton();
	public static JButton button6 = new JButton();
	public static ScreenLayout screenLayout = new ScreenLayout();
	public static RuntimeStatus runtimeStatus = RuntimeStatus.MAIN0;
	public static MathOperations op = MathOperations.SUM;
	public static boolean angUnit = false;

	public static ActionListener actionManager = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String cmd = event.getActionCommand();

			if(cmd.equals("BUTTON_1"))
			{
				if(runtimeStatus == RuntimeStatus.FIRST_NUM || runtimeStatus == RuntimeStatus.FINAL_NUM || runtimeStatus == RuntimeStatus.RESULT)
				{
					runtimeStatus = RuntimeStatus.MAIN0;
					paintMainScreen();
					return;
				}

				switch(runtimeStatus)
				{
					case MAIN0:
						op = MathOperations.SUM;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						break;

					case MAIN1:
						op = MathOperations.POWER;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						break;

					case MAIN2:
						op = MathOperations.COSINE;
						runtimeStatus = RuntimeStatus.FINAL_NUM;
						break;
				}

				paintNumberScreen();
			}
			else if(cmd.equals("BUTTON_2"))
			{
				switch(runtimeStatus)
				{
					case MAIN0:
						op = MathOperations.SUBTRACTION;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						paintNumberScreen();
						break;

					case MAIN1:
						op = MathOperations.ROOT;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						paintNumberScreen();
						break;

					case MAIN2:
						op = MathOperations.TANGENT;
						runtimeStatus = RuntimeStatus.FINAL_NUM;
						paintNumberScreen();
						break;

					case FIRST_NUM:
						MathHandler.setFirstNum(textBox.getText());
						runtimeStatus = RuntimeStatus.FINAL_NUM;
						paintNumberScreen();
						break;

					case FINAL_NUM:
						MathHandler.setFinalNum(textBox.getText());
						runtimeStatus = RuntimeStatus.RESULT;
						paintResultScreen(MathHandler.calculate(op, angUnit));
						break;
				}
			}
			else if(cmd.equals("BUTTON_3"))
			{
				switch(runtimeStatus)
				{
					case MAIN0:
						op = MathOperations.MULTIPLICATION;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						break;

					case MAIN1:
						op = MathOperations.LOGARITHM;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						break;

					case MAIN2:
						op = MathOperations.FACTORIAL;
						runtimeStatus = RuntimeStatus.FINAL_NUM;
						break;

					case FINAL_NUM:
						angUnit = !angUnit;
						break;

					default:
						return;
				}

				paintNumberScreen();
			}
			else if(cmd.equals("BUTTON_4"))
			{
				switch(runtimeStatus)
				{
					case MAIN0:
						op = MathOperations.DIVISION;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						break;

					case MAIN1:
						op = MathOperations.SINE;
						runtimeStatus = RuntimeStatus.FINAL_NUM;
						break;

					case MAIN2:
						op = MathOperations.BINOMIAL;
						runtimeStatus = RuntimeStatus.FIRST_NUM;
						break;

					default:
						return;
				}

				paintNumberScreen();
			}
			else if(cmd.equals("BUTTON_5"))
			{
				if(runtimeStatus == RuntimeStatus.MAIN1) runtimeStatus = RuntimeStatus.MAIN0;
				else if(runtimeStatus == RuntimeStatus.MAIN2) runtimeStatus = RuntimeStatus.MAIN1;
				else return;
				paintMainScreen();
			}
			else if(cmd.equals("BUTTON_6"))
			{	
				if(runtimeStatus == RuntimeStatus.MAIN0) runtimeStatus = RuntimeStatus.MAIN1;
				else if(runtimeStatus == RuntimeStatus.MAIN1) runtimeStatus = RuntimeStatus.MAIN2;
				else return;
				paintMainScreen();
			}
		}
	};

	public static void paintMainScreen()
	{
		text.setText("Choose an Operation");
		textBox.setVisible(false);

		switch(runtimeStatus)
		{
			case MAIN0:
				button1.setText("Sum");
				button2.setText("Subtraction");
				button3.setText("Multiplication");
				button4.setText("Division");
				button5.setVisible(false);
				button6.setVisible(true);
				button6.setLocation(260, 200);
				break;

			case MAIN1:
				button1.setText("Power");
				button2.setText("Root");
				button3.setText("Logarithm");
				button4.setText("Sine");
				button5.setVisible(true);
				button5.setLocation(150, 200);
				button6.setVisible(true);
				button6.setLocation(370, 200);
				break;

			case MAIN2:
				button1.setText("Cosine");
				button2.setText("Tangent");
				button3.setText("Factorial");
				button4.setText("Newton Binomial");
				button5.setVisible(true);
				button5.setLocation(260, 200);
				button6.setVisible(false);
				break;
		}

		button1.setLocation(10, 150);
		button1.setSize(160, 20);
		button1.setVisible(true);

		button2.setLocation(190, 150);
		button2.setSize(160, 20);
		button2.setVisible(true);

		button3.setLocation(370, 150);
		button3.setSize(160, 20);
		button3.setVisible(true);

		button4.setLocation(550, 150);
		button4.setSize(160, 20);
		button4.setVisible(true);
	}

	public static void paintNumberScreen()
	{
		textBox.setVisible(true);

		button1.setVisible(true);
		button1.setText("Cancel");
		button1.setLocation(250, 150);
		button1.setSize(100, 20);

		button2.setVisible(true);
		button2.setText("Next");
		button2.setLocation(370, 150);
		button2.setSize(100, 20);

		button3.setText("Toggle Ang. Unit");
		button3.setLocation(250, 180);
		button3.setSize(220, 20);

		button4.setVisible(false);
		button5.setVisible(false);
		button6.setVisible(false);

		switch(op)
		{
			case SUM:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Sum \"a + b\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Sum \"a + b\". Enter value of \"b\":");
				break;

			case SUBTRACTION:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Subtraction \"a - b\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Subtraction \"a - b\". Enter value of \"b\":");
				break;

			case MULTIPLICATION:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Multiplication \"a * b\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Multiplication \"a * b\". Enter value of \"b\":");
				break;

			case DIVISION:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Division \"a / b\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Division \"a / b\". Enter value of \"b\":");
				break;

			case POWER:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Power \"b\" of \"a\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Power \"b\" of \"a\". Enter value of \"b\":");
				break;

			case ROOT:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Root \"b\" of \"a\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Root \"b\" of \"a\". Enter value of \"b\":");
				break;

			case LOGARITHM:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Logarithm \"b\" of \"a\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Logarithm \"b\" of \"a\". Enter value of \"b\":");
				break;

			case SINE:
				button3.setVisible(true);
				if(angUnit) text.setText("Sine of \"a\". Enter value of \"a\" (Current unit: degrees)");
				else text.setText("Sine of \"a\". Enter value of \"a\" (Current unit: radians)");
				break;

			case COSINE:
				button3.setVisible(true);
				if(angUnit) text.setText("Cosine of \"a\". Enter value of \"a\" (Current unit: degrees)");
				else text.setText("Cosine of \"a\". Enter value of \"a\" (Current unit: radians)");
				break;

			case TANGENT:
				button3.setVisible(true);
				if(angUnit) text.setText("Tangent of \"a\". Enter value of \"a\" (Current unit: degrees)");
				else text.setText("Tangent of \"a\". Enter value of \"a\" (Current unit: radians)");
				break;

			case FACTORIAL:
				button3.setVisible(false);
				text.setText("Factorial \"a!\". Enter value of \"a\":");
				break;

			case BINOMIAL:
				button3.setVisible(false);
				if(runtimeStatus == RuntimeStatus.FIRST_NUM) text.setText("Newton Binomial \"a\" to \"b\". Enter value of \"a\":");
				else if(runtimeStatus == RuntimeStatus.FINAL_NUM) text.setText("Newton Binomial \"a\" to \"b\". Enter value of \"b\":");
				break;
		}
	}

	public static void paintResultScreen(String result)
	{
		text.setText("Result: " + result);

		button1.setVisible(true);
		button1.setText("Return");
		button1.setLocation(310, 200);
		button1.setSize(100, 20);

		textBox.setVisible(false);
		button2.setVisible(false);
		button3.setVisible(false);
		button4.setVisible(false);
		button5.setVisible(false);
		button6.setVisible(false);
	}

	public static void main(String[] args)
	{
		text.setVisible(true);
		text.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		text.setForeground(Color.BLACK);
		text.setLayout(screenLayout);
		text.setLocation(60, 50);
		text.setSize(600, 30);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setText("");

		textBox.setVisible(false);
		textBox.setBackground(Color.WHITE);
		textBox.setLayout(screenLayout);
		textBox.setLocation(60, 100);
		textBox.setSize(600, 30);

		button1.setVisible(false);
		button1.setBackground(Color.WHITE);
		button1.setForeground(Color.BLACK);
		button1.setText("");
		button1.addActionListener(actionManager);
		button1.setActionCommand("BUTTON_1");
		button1.setLayout(screenLayout);

		button2.setVisible(false);
		button2.setBackground(Color.WHITE);
		button2.setForeground(Color.BLACK);
		button2.setText("");
		button2.addActionListener(actionManager);
		button2.setActionCommand("BUTTON_2");
		button2.setLayout(screenLayout);
		
		button3.setVisible(false);
		button3.setBackground(Color.WHITE);
		button3.setForeground(Color.BLACK);
		button3.setText("");
		button3.addActionListener(actionManager);
		button3.setActionCommand("BUTTON_3");
		button3.setLayout(screenLayout);

		button4.setVisible(false);
		button4.setBackground(Color.WHITE);
		button4.setForeground(Color.BLACK);
		button4.setText("");
		button4.addActionListener(actionManager);
		button4.setActionCommand("BUTTON_4");
		button4.setLayout(screenLayout);

		button5.setVisible(false);
		button5.setBackground(Color.WHITE);
		button5.setForeground(Color.BLACK);
		button5.setText("<-- Prev Page");
		button5.addActionListener(actionManager);
		button5.setActionCommand("BUTTON_5");
		button5.setLayout(screenLayout);
		button5.setSize(200, 20);

		button6.setVisible(false);
		button6.setBackground(Color.WHITE);
		button6.setForeground(Color.BLACK);
		button6.setText("Next Page -->");
		button6.addActionListener(actionManager);
		button6.setActionCommand("BUTTON_6");
		button6.setLayout(screenLayout);
		button6.setSize(200, 20);

		screen.setSize(720, 360);
		screen.setBackground(Color.LIGHT_GRAY);
		screen.setLayout(screenLayout);
		screen.add(text);
		screen.add(textBox);
		screen.add(button1);
		screen.add(button2);
		screen.add(button3);
		screen.add(button4);
		screen.add(button5);
		screen.add(button6);
		screen.setVisible(true);

		appWindow.setTitle("JRE Calculator");
		appWindow.setSize(720, 360);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.add(screen);
		appWindow.setVisible(true);
		runtimeStatus = RuntimeStatus.MAIN0;
		paintMainScreen();
	}
}
