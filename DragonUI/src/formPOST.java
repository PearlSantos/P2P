import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

//@Component
public class formPOST extends JFrame {

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
					formPOST frame = new formPOST();
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
	public formPOST() {
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
		
		room = new JTextField("Room1");
		room.setBounds(64, 9, 106, 20);
		contentPane.add(room);
		room.setColumns(10);
		
		command = new JTextField("checkRoom");
		command.setBounds(84, 34, 106, 20);
		contentPane.add(command);
		command.setColumns(10);
		
		gameState = new JTextField("0");
		gameState.setBounds(84, 61, 106, 20);
		contentPane.add(gameState);
		gameState.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(10, 126, 414, 124);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
				interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
				OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();		
				
				
				Gson gson = new GsonBuilder().create();
				Retrofit retrofit = new Retrofit.Builder()
					.client(client)
					.baseUrl("http://localhost:9999/") // a legit base url is needed regardless
					.addConverterFactory(GsonConverterFactory.create(gson))
					.build();
				
					GameQuery query = retrofit.create(GameQuery.class);
					Answer a = new Answer();
					a.setRoom(room.getText());
					a.setCommand(command.getText());
					a.setState(Integer.parseInt(gameState.getText()));

					Call<Reply> call = query.sendJson(a);
					
					/**
					*IDK YET WHERE TO SHOW THE RESULTS OMG
					**/
					String reply="";
					try {
						Response<Reply> response = call.execute();
						reply = response.body().getMessage();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					textField.setText(reply);
			}
		});
		btnSubmit.setBounds(10, 92, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnLog = new JButton("Log");
		btnLog.setBounds(109, 92, 89, 23);
		contentPane.add(btnLog);

	}


	private interface GameQuery
	{
		
		@POST("dragon/postpojo")
		Call<Reply> sendJson(@Body Answer param);
		
		@POST("dragon/listAll")
		Call<Reply> returnAll();
	

	}
	static class Answer{
		String room;
		String command;
		int gameState;
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		public String getCommand() {
			return command;
		}
		public void setCommand(String command) {
			this.command = command;
		}
//		public String getState() {
//			return state;
//		}
//		public void setState(String state) {
//			this.state = state;
//		}
		
		public int getState() {
			return gameState;
		}
		public void setState(int state) {
			this.gameState = state;
		}
//		}
		
//		String state;
	}
}
	

