package app.components;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import RetrofitJsonTester.JsonService;
//import RetrofitPostTester.JDICTService;
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
import retrofit2.http.POST;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.UUID;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class ChikkaUI extends JFrame {
	private JPanel contentPane;
	private JTextField mobile_number;
	private JTextField movieTitle;

	/**
	 * Create the panel.
	 */
	public ChikkaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number:");
		lblMobileNumber.setBounds(27, 11, 78, 33);
		contentPane.add(lblMobileNumber);
		
		mobile_number = new JTextField();
		mobile_number.setColumns(10);
		mobile_number.setBounds(115, 17, 325, 20);
		contentPane.add(mobile_number);
		
		JLabel label = new JLabel("");
		label.setBounds(227, 154, 46, 14);
		contentPane.add(label);
		
		JLabel lblMovieTitle = new JLabel("Movie Title:");
		lblMovieTitle.setBounds(27, 55, 64, 14);
		contentPane.add(lblMovieTitle);
		
		movieTitle = new JTextField();
		movieTitle.setBounds(115, 48, 325, 20);
		contentPane.add(movieTitle);
		movieTitle.setColumns(10);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
				interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
				OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();		
				
				Gson gson = new GsonBuilder().create();

				
				
				Retrofit retrofit = new Retrofit.Builder()
						.client(client)
						.baseUrl("http://localhost:9999/") 
						.addConverterFactory(GsonConverterFactory.create(gson))
						.build();
				
				ArrayList<String> arr = new ArrayList<>();
				arr.add(mobile_number.getText());
				arr.add(movieTitle.getText());
				
				JDICTService service = retrofit.create(JDICTService.class);
				Call<ResponseBody> call = service.getMovie(arr);
				
				Response<ResponseBody> response;
				try {
					response = call.execute();
					String reply = response.body().string();
					System.out.println(reply);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSend.setBounds(198, 102, 89, 23);
		contentPane.add(btnSend);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChikkaUI frame = new ChikkaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private interface JDICTService
	{	
		@POST("chikka/getMovie")
		Call<ResponseBody> getMovie(@Body ArrayList<String> param);
		
	}
}
