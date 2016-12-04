import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.rooms.RoomUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;


public class formGET extends JFrame {

	
	private RoomUtils roomUtils;
	private JPanel contentPane;
	private JTextField room;
	private JTextField command;
	private JTextField gameState;
	private JButton btnSubmit;
	private JLabel lblRoom;
	private JLabel lblCommand;
	private JLabel lblGamestate;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formGET frame = new formGET();
					frame.setVisible(true);
					//frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public formGET() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRoom = new JLabel("Room: ");
		lblRoom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRoom.setBounds(10, 11, 46, 14);
		contentPane.add(lblRoom);
		
		lblCommand = new JLabel("Command: ");
		lblCommand.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCommand.setBounds(10, 36, 76, 14);
		contentPane.add(lblCommand);
		
		lblGamestate = new JLabel("GameState:");
		lblGamestate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblGamestate.setBounds(10, 61, 76, 14);
		contentPane.add(lblGamestate);
		
		room = new JTextField();
		room.setBounds(64, 9, 106, 20);
		contentPane.add(room);
		room.setColumns(10);
		
		command = new JTextField();
		command.setBounds(84, 34, 106, 20);
		contentPane.add(command);
		command.setColumns(10);
		
		gameState = new JTextField();
		gameState.setBounds(84, 61, 106, 20);
		contentPane.add(gameState);
		gameState.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		btnSubmit.setBounds(10, 92, 89, 23);
		contentPane.add(btnSubmit);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 126, 6, 20);
		contentPane.add(textPane);
		
		textField = new JTextField();
		textField.setBounds(10, 126, 414, 124);
		contentPane.add(textField);
		textField.setColumns(10);

	}


	private interface GameQuery
	{
		@GET("http://localhost:9999/dragon/get") //base url
		Call<Reply> search(@Query("room") String param1,
				@Query("command") String param2,
				@Query("gameState") int param3); //whatever you put in param1 it will put in your url as a query
	}
	
	
	
	public void start(){
		Gson gson = new GsonBuilder().create();
		Retrofit retrofit = new Retrofit.Builder()
//			.client(client)
			.baseUrl("http://localhost:9999/") // a legit base url is needed regardless
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();
	//service class
	
		GameQuery query = retrofit.create(GameQuery.class);

		Call<Reply> call = query.search(room.getText(), command.getText(), Integer.parseInt(gameState.getText()));
		
		/**
		*IDK YET WHERE TO SHOW THE RESULTS OMG
		**/
		Response<Reply> response;
		String reply = "";
		try {
			response = call.execute();
			reply = response.body().getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		textField.setText(reply);
	}
}


